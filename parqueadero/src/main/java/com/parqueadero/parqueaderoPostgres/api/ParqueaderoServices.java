/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Vehiculo registrarIngreso(@RequestBody Vehiculo vehiculo) {
    	Vehiculo v=null;
    	if(vehiculo.getPlaca()!= null) {
    		v = parqueaderoService.buscarVehiculoActivo(vehiculo.getPlaca());
    	}
    	System.out.println(v);
    	return parqueaderoService.actualizarVehiculo(vehiculo);
    	

    };
    
    @RequestMapping(value="/registrarSalida", method=RequestMethod.POST)
    public String registrarSalida() {
    	return "por fin";
    };
    
    @RequestMapping(value="/consultarVehiculos", method=RequestMethod.GET)
    public String consultarVehiculo() {
    	return "por fin";
    };
}
