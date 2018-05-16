/**
 * 
 */
package com.parqueadero.api;

import java.util.Map;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.enumeraciones.ConstantesParametro;
import com.parqueadero.factoria.FactoriaVehiculo;
import com.parqueadero.modelo.Vehiculo;
import com.parqueadero.services.ParqueaderoBusniess;


/**
 * @author jerson.zambrano
 *
 */
@RestController
public class ParqueaderoServices {

	@Autowired
	ParqueaderoBusniess parqueaderoService;
	
	@Valid
    @RequestMapping(value="/registrarIngreso", method=RequestMethod.POST)
    public Map<String, Boolean> registrarIngreso(@Valid @RequestBody Vehiculo vehiculo) {
    	
    	//FactoriaVehiculo.getFactura(vehiculo);
		Map<String, Boolean> validaciones=parqueaderoService.validarEntrada(vehiculo);
		if(!validaciones.get(ConstantesParametro.VALIDACION_DISPONIBILIDAD) || !validaciones.get(ConstantesParametro.VALIDACION_DOMINGO_LUNES)){
			return validaciones;
		}
		parqueaderoService.registraringreso(vehiculo);
    	return validaciones;
    }
    
    @RequestMapping(value="/registrarSalida", method=RequestMethod.POST)
    public Double registrarSalida(@RequestBody Vehiculo vehiculo) {
    	try {
    		return parqueaderoService.registrarSalida(vehiculo.getPlaca());
		} catch (NoResultException e) {
			return null;
		}
    }
    
    @RequestMapping(value="/consultarVehiculos", method=RequestMethod.GET)
    public String consultarVehiculo() {
    	return "por fin";
    }
}
