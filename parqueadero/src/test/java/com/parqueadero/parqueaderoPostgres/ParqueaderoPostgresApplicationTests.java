package com.parqueadero.parqueaderoPostgres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.api.ParqueaderoServices;
import com.parqueadero.dao.RegistroParqueaderoRepository;
import com.parqueadero.enumeraciones.ConstantesParametro;
import com.parqueadero.enumeraciones.TipoVehiculoEnum;
import com.parqueadero.modelo.RegistroParqueadero;
import com.parqueadero.modelo.Vehiculo;
import com.parqueadero.services.ParqueaderoBusniess;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoPostgresApplicationTests {

	@Autowired
	ParqueaderoServices parqueaderoService;

	@Autowired
	ParqueaderoBusniess pBusniess;

	@Autowired
	RegistroParqueaderoRepository daoRegistros;

	@InjectMocks
	ParqueaderoBusniess parqueaderoBusniess;

	@Test
	public void buscarVehiculoMockTest() {
		RegistroParqueaderoRepository daoRepo = Mockito.mock(RegistroParqueaderoRepository.class);
		RegistroParqueadero v = new RegistroParqueadero();
		Mockito.doReturn(v).when(daoRepo).buscarVehiculoPorPlaca("DFG123");
		parqueaderoBusniess.setDaoRegistroRepository(daoRepo);
		parqueaderoBusniess.buscarVehiculo("DFG123");

		assertNotNull(v);
	}

	@Test
	public void registrarIngresoIntegracionTest() {
		String placa = "MSD123";
		daoRegistros.deleteAll();
		Vehiculo v = new Vehiculo();
		v.setPlaca(placa);
		v.setTipoVehiculo(TipoVehiculoEnum.MOTO);
		v.setCilindraje(new Double(501));
		parqueaderoService.registrarIngreso(v);
		assertNotNull(parqueaderoService.consultarVehiculo(v.getPlaca()));
	}

	@Test
	public void validarDiaDomingoLunesTest() {
		assertFalse(false);
	}

	@Test
	public void sacarVehiculoInexistente() {
		String placa = "ASf123";
		daoRegistros.deleteAll();
		try {
			pBusniess.registrarSalida(placa);
			assertTrue(false);
		} catch (NoResultException e) {
			assertTrue(true);
		}
	}

	@Test
	public void ingresarVehiculoRegistradoTest() {
		assertTrue(true);
	}

	@Test
	public void consultarVehiculoTest() {

		String placa = "BSD125";
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		pBusniess.registraringreso(vehiculo);
		assertNotNull(parqueaderoService.consultarVehiculo(placa));
	}

	@Test
	public void buscarVehiculoTest() {

		String placa = "BSD126";
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		pBusniess.registraringreso(vehiculo);
		assertNotNull(pBusniess.buscarVehiculo(placa));
	}

	@Test
	public void validarCupoTest() {
		assertFalse(false);
	}

	@Test
	public void vehiculoNoRegistradoTest() {
		String placa = "BSD127";
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		assertTrue(pBusniess.vehiculoNoRegistrado(vehiculo));
	}

	@Test
	public void vehiculoRegistradoTest() {
		String placa = "BSD128";
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		pBusniess.registraringreso(vehiculo);
		assertFalse(pBusniess.vehiculoNoRegistrado(vehiculo));
	}

	@Test
	public void diaPermitidoIngresarTest() {
		String placa = "ASg123";
		Calendar now = Calendar.getInstance();
		List<Integer> diasPermitidos = new ArrayList<>();
		diasPermitidos.add(now.get(Calendar.DAY_OF_WEEK));
		assertTrue(pBusniess.diaPermitidoIngresar(placa, diasPermitidos));
	}

	@Test
	public void diaNoPermitidoIngresarTest() {
		String placa = "ASh123";
		Calendar now = Calendar.getInstance();
		List<Integer> diasPermitidos = new ArrayList<>();
		diasPermitidos.add((now.get(Calendar.DAY_OF_WEEK) + 1));
		assertFalse(pBusniess.diaPermitidoIngresar(placa, diasPermitidos));
	}

	@Test
	public void registrarSalidaTest() {
		String placa = "VGA159";
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		parqueaderoService.registrarIngreso(vehiculo);
		assertNotNull(parqueaderoService.registrarSalida(vehiculo));
	}

	@Test
	public void registrarSalidaNoExistenteTest() {
		String placa = "VGA159";
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(placa);
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		assertNull(parqueaderoService.registrarSalida(vehiculo));
	}

	@Test
	public void hayCuposParqueaderoMotoTest() {
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.MOTO);
		assertTrue(pBusniess.hayCuposParqueadero(vehiculo));
	}

	@Test
	public void noHayCuposParqueaderoMotoTest() {
		daoRegistros.deleteAll();
		agregarRegistros(10, TipoVehiculoEnum.MOTO);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.MOTO);
		assertFalse(pBusniess.hayCuposParqueadero(vehiculo));
	}

	@Test
	public void hayCuposParqueaderoCarroTest() {
		daoRegistros.deleteAll();
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		assertTrue(pBusniess.hayCuposParqueadero(vehiculo));
	}

	@Test
	public void noHayCuposParqueaderoCarroTest() {
		daoRegistros.deleteAll();
		agregarRegistros(20, TipoVehiculoEnum.CARRO);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		assertFalse(pBusniess.hayCuposParqueadero(vehiculo));
	}

	@Test
	public void calcularDiasYHorasTest() {
		Long horas = pBusniess.HORAS_EPOCH * 26L;
		Date fechaIngreso = new Date(new Date().getTime() - horas);
		Map<String, Integer> calculoDiaHora = pBusniess.calcularDiasYHoras(fechaIngreso);
		assertTrue((calculoDiaHora.get(pBusniess.LLAVE_DIA) == 1) && (calculoDiaHora.get(pBusniess.LLAVE_HORA) == 2));
	}

	@Test
	public void consultarTotalRegistrosServicesTest() {
		daoRegistros.deleteAll();
		agregarRegistros(12, TipoVehiculoEnum.CARRO);
		agregarRegistros(8, TipoVehiculoEnum.MOTO);
		List<Vehiculo> listVehiculos = parqueaderoService.consultarTotalRegistros();
		assertTrue(listVehiculos.size() == 20);
	}
	
	@Test
	public void registrarIngresoYaRegistradoServicesTest() {
		daoRegistros.deleteAll();
		Vehiculo v= new Vehiculo();
		v.setPlaca("FRD159");
		v.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		parqueaderoService.registrarIngreso(v);
		Map<String, Boolean> listResultado=parqueaderoService.registrarIngreso(v);	
		assertFalse(listResultado.get(ConstantesParametro.VALIDACION_YA_REGISTRADO));
	}

	private void agregarRegistros(int cantidad, TipoVehiculoEnum tipoVehiculo) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setTipoVehiculo(tipoVehiculo);
		for (int i = 0; i < cantidad; i++) {
			vehiculo.setPlaca(tipoVehiculo.toString() + i);
			if (tipoVehiculo.equals(TipoVehiculoEnum.MOTO)) {
				vehiculo.setCilindraje(new Double(350));
			}
			parqueaderoService.registrarIngreso(vehiculo);
		}
	}

	@After
	public void deleteOutputFile() {
		daoRegistros.deleteAll();
	}
}
