/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.parqueaderoPostgres.Enumeraciones.ConstantesParametro;
import com.parqueadero.parqueaderoPostgres.Enumeraciones.TipoVehiculoEnum;
import com.parqueadero.parqueaderoPostgres.dao.ParametroRepository;
import com.parqueadero.parqueaderoPostgres.dao.RegistroParqueaderoRepository;
import com.parqueadero.parqueaderoPostgres.modelo.RegistroParqueadero;
import com.parqueadero.parqueaderoPostgres.modelo.Vehiculo;

/**
 * @author jerson.zambrano
 *
 */
@Service
public class ParqueaderoBusniess {

	public static final double HORAS_A_DIAS = 9;
	public static final double HORAS_DEL_DIA = 24;

	@Autowired
	ParametroRepository daoParametro;

	@Autowired
	RegistroParqueaderoRepository daoRegistro;

	public Vehiculo actualizarVehiculo(Vehiculo vehiculo) {
		// return daoRegistro.saveAndFlush(vehiculo);
		return null;
	}

	public Vehiculo saveVehiculo(Vehiculo vehiculo) {
		// return daoRegistro.saveAndFlush(vehiculo);
		daoRegistro.saveAndFlush(new RegistroParqueadero());
		return null;
	}

	public Vehiculo buscarVehiculoActivo(String placa) {
		// return daoRegistro.buscarVehiculoPorPlaca(placa);
		return null;
	}

	public boolean hayCuposParqueadero(Vehiculo v) {

		Long capacidad = TipoVehiculoEnum.CARRO.equals(v.getTipoVehiculo()) ? ConstantesParametro.CAPACIDAD_CARROS
				: ConstantesParametro.CAPACIDAD_MOTOS;

		return daoRegistro.consultarOcupados(v.getTipoVehiculo()) < capacidad;
	}

	public Map<String, Boolean> validarEntrada(Vehiculo v) {

		Map<String, Boolean> validaciones = new HashMap<String, Boolean>();

		validaciones.put(ConstantesParametro.VALIDACION_DOMINGO_LUNES, diaPermitidoIngresar(v.getPlaca()));
		validaciones.put(ConstantesParametro.VALIDACION_DISPONIBILIDAD, hayCuposParqueadero(v));

		return validaciones;
	}

	public boolean diaPermitidoIngresar(String placa) {
		if (placa.toUpperCase().charAt(0) == 'A') {
			Calendar now = Calendar.getInstance();
			return (now.get(Calendar.DAY_OF_WEEK) == 1 || now.get(Calendar.DAY_OF_WEEK) == 2);
		}
		return true;
	}

	public void registraringreso(Vehiculo vehiculo) {
		RegistroParqueadero registro = vehiculo.converToEntity();
		registro.setFechaIngreso(new Date());
		daoRegistro.saveAndFlush(registro);

	}

	public Double registrarSalida(String placa) {
		
		int diasLiquidar = 0;
		int horasLiquidar = 0;
		double valorAdicional = 0;		
		RegistroParqueadero reg = daoRegistro.consultarRegistroSalida(placa);
		if(reg == null) {
			throw new NoResultException("public Double registrarSalida(String placa), no se ha encontrado algun registro");
		}
		reg.setFechaSalida(new Date());
		double horasTotal = Math.ceil((new Double(new Date().getTime() - reg.getFechaIngreso().getTime())) / 3600000);
		diasLiquidar = (int) Math.floor(horasTotal/HORAS_DEL_DIA);
		horasLiquidar = (int) (((horasTotal/HORAS_DEL_DIA)-diasLiquidar)*HORAS_DEL_DIA);
		if (TipoVehiculoEnum.CARRO.equals(reg.getTipoVehiculo()) && horasLiquidar>HORAS_A_DIAS) {
			horasLiquidar=0;
			diasLiquidar++;
		}
		if(TipoVehiculoEnum.MOTO.equals(reg.getTipoVehiculo())&& reg.getCilindraje()>ConstantesParametro.CILINDRAJE_MAYOR) {
			valorAdicional= ConstantesParametro.VALOR_ADICIONAL_MAYOR_CILINDRAJE;
		}
		double precioFacturado=horasLiquidar*(500)+diasLiquidar*(4000)+valorAdicional;
		reg.setValorTotal(precioFacturado);
		daoRegistro.saveAndFlush(reg);
		return precioFacturado;
	}
}
