/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.api;

import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parqueaderoPostgres.Enumeraciones.ConstantesParametro;
import com.parqueadero.parqueaderoPostgres.factoria.FactoriaVehiculo;
import com.parqueadero.parqueaderoPostgres.modelo.Vehiculo;
import com.parqueadero.parqueaderoPostgres.services.ParqueaderoBusniess;


/**
 * @author jerson.zambrano
 *
 */
@RestController
//@RequestMapping(value = "/error")
public class ParqueaderoServices {

	@Autowired
	ParqueaderoBusniess parqueaderoService;
    @RequestMapping(value="/registrarIngreso", method=RequestMethod.POST)
    public Map<String, Boolean> registrarIngreso(@RequestBody Vehiculo vehiculo) {
    	
    	Vehiculo v= FactoriaVehiculo.getFactura(vehiculo.getTipoVehiculo());
		Map<String, Boolean> validaciones=parqueaderoService.validarEntrada(vehiculo);
		if(!validaciones.get(ConstantesParametro.VALIDACION_DISPONIBILIDAD) || !validaciones.get(ConstantesParametro.VALIDACION_DOMINGO_LUNES)){
			return validaciones;
		}
		parqueaderoService.registraringreso(vehiculo);
    	return validaciones;
    };
    
    @RequestMapping(value="/registrarSalida", method=RequestMethod.POST)
    public Double registrarSalida(@RequestBody Vehiculo vehiculo) {
    	try {
    		Double valorFacturado=parqueaderoService.registrarSalida(vehiculo.getPlaca());
    		return valorFacturado;
		} catch (NoResultException e) {
			return null;
		}
    };
    
    @RequestMapping(value="/consultarVehiculos", method=RequestMethod.GET)
    public String consultarVehiculo() {
    	return "por fin";
    };
}
