/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	public boolean hayCuposParqueadero(Vehiculo vehiculo) {

		return true;
	}

	public Map<String, Boolean> validarEntrada(Vehiculo v) {

		Map<String, Boolean> validaciones = new HashMap<String, Boolean>();

		// SE VALIDA DIA PERMITIVO PARA LAS PLACAS QUE COMIENCEN CON A
		validaciones.put(ConstantesParametro.VALIDACION_DOMINGO_LUNES, diaPermitidoIngresar(v.getPlaca()));

		Long capacidad = TipoVehiculoEnum.CARRO.equals(v.getTipoVehiculo()) ? ConstantesParametro.CAPACIDAD_CARROS
				: ConstantesParametro.CAPACIDAD_MOTOS;

		//Se valida la capacidad
		validaciones.put(ConstantesParametro.VALIDACION_DISPONIBILIDAD,
				daoRegistro.consultarOcupados(v.getTipoVehiculo()) < capacidad);

		return validaciones;
	}
	
	public boolean diaPermitidoIngresar(String placa) {
		System.out.println(placa+"---------------placa");
		if(placa.toUpperCase().charAt(0) == 'A') {
			Calendar now = Calendar.getInstance();
			System.out.println(now.get(Calendar.DAY_OF_WEEK)+ "--------------------------------");
			return (now.get(Calendar.DAY_OF_WEEK) == 1 || now.get(Calendar.DAY_OF_WEEK) == 2);
		}
		return true;
	}

	public void registraringreso(Vehiculo vehiculo) {
		RegistroParqueadero registro = vehiculo.converToEntity();
		registro.setFechaIngreso(new Date());
		daoRegistro.saveAndFlush(registro);
		
	}
}
