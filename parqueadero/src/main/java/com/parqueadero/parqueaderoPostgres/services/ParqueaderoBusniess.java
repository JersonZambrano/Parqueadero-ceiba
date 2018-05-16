/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public boolean hayCuposParqueadero (Vehiculo vehiculo) {
		
		return true;
	}
}
