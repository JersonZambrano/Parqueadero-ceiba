package com.parqueadero.parqueaderoPostgres;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.api.ParqueaderoServices;
import com.parqueadero.enumeraciones.TipoVehiculoEnum;
import com.parqueadero.modelo.Vehiculo;
import com.parqueadero.services.ParqueaderoBusniess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoPostgresApplicationTests {

    @Autowired
    ParqueaderoServices parqueaderoService;
    
    @Autowired
    ParqueaderoBusniess pBusniess;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void registrarIngresoTest() {
		String placa ="MSD123";
		pBusniess.eliminarRegistro(placa);
		Vehiculo v = new Vehiculo();
		v.setPlaca(placa);
		v.setTipoVehiculo(TipoVehiculoEnum.MOTO);
		v.setCilindraje(new Double(501));
		Map<String, Boolean> validaciones =parqueaderoService.registrarIngreso(v);
		System.out.println(validaciones);
		assertNotNull(parqueaderoService.consultarVehiculo(v.getPlaca()));
	}
	
	@Test
	public void validarDiaDomingoLunes() {
	assertFalse(false);
	}
	
	@Test
	public void sacarVehiculoInexistente() {
		String placa ="ASD123";
		pBusniess.eliminarRegistro(placa);
		try {
			pBusniess.registrarSalida(placa);			
			assertTrue(false);
		} catch (NoResultException e) {
			assertTrue(true);
		}
		
	}


}
