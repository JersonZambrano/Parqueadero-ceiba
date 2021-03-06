/**
 * 
 */
package com.parqueadero.api;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.parqueadero.enumeraciones.ConstantesParametro;
import com.parqueadero.modelo.Vehiculo;
import com.parqueadero.services.ParqueaderoBusniess;

/**
 * @author jerson.zambrano
 *
 */
@CrossOrigin
@RestController
public class ParqueaderoServices {

	Logger logger = Logger.getLogger(ParqueaderoServices.class.getName());

	@Autowired
	private ParqueaderoBusniess parqueaderoService;

	@Valid
	@RequestMapping(value = "/registrarIngreso", method = RequestMethod.POST)
	public Map<String, Boolean> registrarIngreso(@Valid @RequestBody Vehiculo vehiculo) {

		Map<String, Boolean> validaciones = parqueaderoService.validarEntrada(vehiculo);
		if (!validaciones.get(ConstantesParametro.VALIDACION_DISPONIBILIDAD)
				|| !validaciones.get(ConstantesParametro.VALIDACION_DOMINGO_LUNES)
				|| !validaciones.get(ConstantesParametro.VALIDACION_YA_REGISTRADO)) {
			return validaciones;
			
		}
		parqueaderoService.registraringreso(vehiculo);
		return validaciones;
	}

	@RequestMapping(value = "/registrarSalida", method = RequestMethod.POST)
	public Vehiculo registrarSalida(@RequestBody Vehiculo vehiculo) {
		try {
			return parqueaderoService.registrarSalida(vehiculo.getPlaca());
		} catch (NoResultException e) {
			return null;
		}
	}

	@RequestMapping(value = "/consultarVehiculos/{placa}", method = RequestMethod.GET)
	public Vehiculo consultarVehiculo(@PathVariable("placa") String placa) {
		try {
			return parqueaderoService.buscarVehiculo(placa);
		} catch (NonUniqueResultException e) {
			throw new NonUniqueResultException("Se encuentra repetido el vehiculo");
		}
	}
	
	@RequestMapping(value = "/consultarRegistros", method = RequestMethod.GET)
	public List<Vehiculo > consultarTotalRegistros() {
		return parqueaderoService.consultarTotalRegistros(); 
	}
}
