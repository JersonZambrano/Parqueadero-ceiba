/**
 * 
 */
package com.parqueadero.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.NoResultException;
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
	ParametroRepository daoParametro;

	@Autowired
	RegistroParqueaderoRepository daoRegistro;

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public Vehiculo buscarVehiculoActivo(String placa) {
		// return daoRegistro.buscarVehiculoPorPlaca(placa);
		return null;
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

		Map<String, Boolean> validaciones = new HashMap<String, Boolean>();
		validaciones.put(ConstantesParametro.VALIDACION_DOMINGO_LUNES, diaPermitidoIngresar(v.getPlaca()));
		validaciones.put(ConstantesParametro.VALIDACION_DISPONIBILIDAD, hayCuposParqueadero(v));

		return validaciones;
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
		Map<String, Integer> calculoDiaHora = new HashMap<String, Integer>();
		int diasLiquidar = 0;
		int horasLiquidar = 0;
		double horasTotal = Math.ceil((new Double(new Date().getTime() - fechaIngreso.getTime())) / HORAS_EPOCH);
		diasLiquidar = (int) Math.floor(horasTotal / HORAS_DEL_DIA);
		horasLiquidar = (int) (((horasTotal / HORAS_DEL_DIA) - diasLiquidar) * HORAS_DEL_DIA);
		if (TipoVehiculoEnum.CARRO.equals(tipoVehiculo) && horasLiquidar > HORAS_A_DIAS) {
			horasLiquidar = 0;
			diasLiquidar++;
		}
		calculoDiaHora.put(LLAVE_DIA, diasLiquidar);
		calculoDiaHora.put(LLAVE_HORA, horasLiquidar);
		return calculoDiaHora;
	}
}
