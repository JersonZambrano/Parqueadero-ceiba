/**
 * 
 */
package com.parqueadero.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parqueadero.dao.ParametroRepository;
import com.parqueadero.dao.RegistroParqueaderoRepository;
import com.parqueadero.enumeraciones.ConstantesParametro;
import com.parqueadero.enumeraciones.TipoVehiculoEnum;
import com.parqueadero.modelo.RegistroParqueadero;
import com.parqueadero.modelo.Vehiculo;

/**
 * @author jerson.zambrano
 *
 */
@Service
public class ParqueaderoBusniess {

	public static final double HORAS_A_DIAS = 9;
	public static final double HORAS_DEL_DIA = 24;
	public static final Long HORAS_EPOCH = 3600000L;
	public static final String LLAVE_DIA = "DIAS";
	public static final String LLAVE_HORA = "HORAS";
	public static final String LLAVE_VALOR_ADICIONAL = "VALOR_ADICIONAL";

	@Autowired
	private ParametroRepository daoParametro;

	@Autowired
	private RegistroParqueaderoRepository daoRegistro;

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public Vehiculo buscarVehiculo(String placa) {
		try {

			RegistroParqueadero reg = daoRegistro.buscarVehiculoPorPlaca(placa);
			if (reg != null) {
				Vehiculo v = new Vehiculo();
				v.entityToVehiculo(reg);
				return v;
			}
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public boolean hayCuposParqueadero(Vehiculo v) {

		Long capacidad = TipoVehiculoEnum.CARRO.equals(v.getTipoVehiculo()) ? ConstantesParametro.CAPACIDAD_CARROS
				: ConstantesParametro.CAPACIDAD_MOTOS;

		return daoRegistro.consultarOcupados(v.getTipoVehiculo()) < capacidad;
	}

	/**
	 * 
	 * @param v
	 * @return
	 */
	public Map<String, Boolean> validarEntrada(Vehiculo v) {

		Map<String, Boolean> validaciones = new HashMap<>();
		validaciones.put(ConstantesParametro.VALIDACION_DOMINGO_LUNES, diaPermitidoIngresar(v.getPlaca()));
		validaciones.put(ConstantesParametro.VALIDACION_DISPONIBILIDAD, hayCuposParqueadero(v));
		validaciones.put(ConstantesParametro.VALIDACION_YA_REGISTRADO, vehiculoNoRegistrado(v));
		return validaciones;
	}

	private Boolean vehiculoNoRegistrado(Vehiculo v) {
		return buscarVehiculo(v.getPlaca()) == null ? true : false;
	}

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public boolean diaPermitidoIngresar(String placa) {
		if (placa.toUpperCase().charAt(0) == 'A') {
			Calendar now = Calendar.getInstance();
			return (now.get(Calendar.DAY_OF_WEEK) == 1 || now.get(Calendar.DAY_OF_WEEK) == 2);
		}
		return true;
	}

	/**
	 * 
	 * @param vehiculo
	 */
	public void registraringreso(Vehiculo vehiculo) {
		RegistroParqueadero registro = vehiculo.converToEntity();
		registro.setFechaIngreso(new Date());
		daoRegistro.saveAndFlush(registro);
	}

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public Double registrarSalida(String placa) {
		double valorAdicional = 0;
		RegistroParqueadero reg = daoRegistro.consultarRegistroSalida(placa);
		if (reg == null) {
			throw new NoResultException(
					"public Double registrarSalida(String placa), no se ha encontrado algun registro");
		}
		reg.setFechaSalida(new Date());
		if (TipoVehiculoEnum.MOTO.equals(reg.getTipoVehiculo())
				&& reg.getCilindraje() > ConstantesParametro.CILINDRAJE_MAYOR) {
			valorAdicional = ConstantesParametro.VALOR_ADICIONAL_MAYOR_CILINDRAJE;
		}
		Map<String, Integer> calculoDiaHora = calcularDiasYHoras(reg.getFechaIngreso(), reg.getTipoVehiculo());
		double precioFacturado = calculoDiaHora.get(LLAVE_HORA) * (reg.getTipoVehiculo().getValorHora())
				+ calculoDiaHora.get(LLAVE_DIA) * (reg.getTipoVehiculo().getValorDia()) + valorAdicional;
		reg.setValorTotal(precioFacturado);
		daoRegistro.saveAndFlush(reg);
		return precioFacturado;
	}

	/**
	 * 
	 * @param fechaIngreso
	 * @param tipoVehiculo
	 * @return
	 */
	public Map<String, Integer> calcularDiasYHoras(Date fechaIngreso, TipoVehiculoEnum tipoVehiculo) {
		Map<String, Integer> calculoDiaHora = new HashMap<>();
		int diasLiquidar = 0;
		int horasLiquidar = 0;
		double horasTotal = Math.ceil((new Double(new Date().getTime() - fechaIngreso.getTime())) / HORAS_EPOCH);
		diasLiquidar = (int) Math.floor(horasTotal / HORAS_DEL_DIA);
		horasLiquidar = (int) (((horasTotal / HORAS_DEL_DIA) - diasLiquidar) * HORAS_DEL_DIA);
		if (horasLiquidar > HORAS_A_DIAS) {
			horasLiquidar = 0;
			diasLiquidar++;
		}
		calculoDiaHora.put(LLAVE_DIA, diasLiquidar);
		calculoDiaHora.put(LLAVE_HORA, horasLiquidar);
		return calculoDiaHora;
	}

	public Boolean eliminarRegistro(String placa) {
		try {
			RegistroParqueadero reg = daoRegistro.buscarVehiculoPorPlaca(placa);
			if (reg == null) {
				return false;
			}
			daoRegistro.delete(reg);
			return true;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Vehiculo> consultarTotalRegistros() {
		List<Vehiculo> listVehiculos = null;
		List<RegistroParqueadero> reg = daoRegistro.buscarTotalRegistros();
		if (!reg.isEmpty() && reg != null) {
			listVehiculos = new ArrayList<>();
			for (RegistroParqueadero registroParqueadero : reg) {
				Vehiculo v = new Vehiculo();
				v.entityToVehiculo(registroParqueadero);
				listVehiculos.add(v);
			}
		}
		return listVehiculos;
	}
}
