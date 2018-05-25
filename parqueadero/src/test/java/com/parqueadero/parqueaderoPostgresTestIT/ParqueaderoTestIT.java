package com.parqueadero.parqueaderoPostgresTestIT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.dao.RegistroParqueaderoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParqueaderoTestIT {

	@Autowired
	RegistroParqueaderoRepository daoRegistros;

	public static final String MENSAJE_SACAR_VEHICULO_INEXISTENTE = "El vehículo no se encuentra registrado actualmente en el parqueadero";
	public static final String MENSAJE_VEHICULO_YA_REGISTRADO = "El vehículo ya se encuentra en el parqueadero";
	public static final String MENSAJE_VEHICULO_REGISTRADO_OK = "El vehículo fue registrado con éxito";
	private static final String CHAR_LIST = "bcdefghijklmnopqrstuvwxyzBCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;
	private static WebDriver driver = null;

	@BeforeClass
	public static void inicializarDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/");
	}

	@Test
	public void registrarVehiculoTest() {
		WebElement botonRegistrarNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[1]"));
		botonRegistrarNav.click();

		WebElement placaElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[1]/div/input"));
		placaElement.sendKeys(generarStringRando());

		WebElement tipoVehiculoElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[2]/div/select"));
		tipoVehiculoElement.sendKeys("CARRO");

		WebElement botonRegistrar = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[3]/button"));
		botonRegistrar.click();

		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement alertaModal = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
		wait.until(ExpectedConditions.visibilityOf(alertaModal));

		WebElement alertaModalMensaje = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal/div/h5"));
		String mensaje = alertaModalMensaje.getText();

		assertEquals(mensaje, MENSAJE_VEHICULO_REGISTRADO_OK);
	}

	@Test
	public void registrarVehiculoYaRegsitradoTest() {
		daoRegistros.deleteAll();
		WebElement botonRegistrarNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[1]"));
		botonRegistrarNav.click();
		String placa = generarStringRando();
		WebElement placaElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[1]/div/input"));
		placaElement.sendKeys(placa);

		WebElement tipoVehiculoElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[2]/div/select"));
		tipoVehiculoElement.sendKeys("CARRO");

		WebElement botonRegistrar = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[3]/button"));
		botonRegistrar.click();

		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement alertaModal = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
		wait.until(ExpectedConditions.visibilityOf(alertaModal));

		WebElement placa2Element = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[1]/div/input"));
		placa2Element.sendKeys(placa);

		WebElement tipoVehiculo2Element = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[2]/div/select"));
		tipoVehiculo2Element.sendKeys("CARRO");

		WebElement botonRegistrar2 = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[3]/button"));
		botonRegistrar2.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 1);
		WebElement alertaModal2 = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
		wait2.until(ExpectedConditions.visibilityOf(alertaModal2));

		WebElement alertaModalMensaje = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal/div/h5"));
		String mensaje = alertaModalMensaje.getText();

		assertEquals(mensaje, MENSAJE_VEHICULO_YA_REGISTRADO);
	}

	@Test
	public void registrarSalidaTest() {
		daoRegistros.deleteAll();
		// Se registra el Vehiculo
		WebElement botonRegistrarNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[1]"));
		botonRegistrarNav.click();
		String placa = generarStringRando();
		WebElement placaElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[1]/div/input"));
		placaElement.sendKeys(placa);

		WebElement tipoVehiculoElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[2]/div/select"));
		tipoVehiculoElement.sendKeys("CARRO");

		WebElement botonRegistrar = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[3]/button"));
		botonRegistrar.click();

		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement alertaModal = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
		wait.until(ExpectedConditions.visibilityOf(alertaModal));

		// Se saca el vehiculo del parqueadero
		WebElement botonSalirNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[2]"));
		botonSalirNav.click();

		WebElement placaSalirElement = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div/div[1]/div/input"));
		placaSalirElement.sendKeys(placa);

		WebElement botonSalir = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div/div[2]/button"));
		botonSalir.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 2);
		WebElement panelResultado = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div[2]"));
		wait2.until(ExpectedConditions.visibilityOf(panelResultado));

		WebElement valorTotal = driver.findElement(By.className("total-pago"));
		String mensaje = valorTotal.getText();
		assertTrue(mensaje != null);
	}

	/**
	 * prueba que se encarga de verificar el registrar salida para un vehiculo que
	 * no se encuntra registrado
	 */
	@Test
	public void registrarSalidaFallaTest() {
		daoRegistros.deleteAll();
		String placa = generarStringRando();

		// Se saca el vehiculo del parqueadero
		WebElement botonSalirNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[2]"));
		botonSalirNav.click();

		WebElement placaSalirElement = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div/div[1]/div/input"));
		placaSalirElement.sendKeys(placa);

		WebElement botonSalir = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div/div[2]/button"));
		botonSalir.click();

		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement alertMensaje = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/app-alert-modal"));
		wait.until(ExpectedConditions.visibilityOf(alertMensaje));

		WebElement mensajeAlerta = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/app-alert-modal/div/h5"));
		String mensaje = mensajeAlerta.getText();

		assertEquals(mensaje, MENSAJE_SACAR_VEHICULO_INEXISTENTE);
	}

	/**
	 * prueba que se encarga de verificar el registrar salida para un vehiculo que
	 * no se encuntra registrado
	 */
	@Test
	public void registrarYconsultarVehiculoTest() {

		String placa = generarStringRando();

		WebElement botonRegistrarNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[1]"));
		botonRegistrarNav.click();

		WebElement placaElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[1]/div/input"));
		placaElement.sendKeys(placa);

		WebElement tipoVehiculoElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[2]/div/select"));
		tipoVehiculoElement.sendKeys("CARRO");

		WebElement botonRegistrar = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[3]/button"));
		botonRegistrar.click();

		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement alertaModal = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
		wait.until(ExpectedConditions.visibilityOf(alertaModal));

		// busca el veh�culo
		WebElement botonSalirNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[3]"));
		botonSalirNav.click();

		WebElement placaSalirElement = driver
				.findElement(By.xpath("/html/body/app-root/app-consultar-vehiculos/div/div[4]/form/div[1]/div/input"));
		placaSalirElement.sendKeys(placa);

		WebElement botonConsulta = driver.findElement(By.className("btn-primary"));
		botonConsulta.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 2);
		WebElement tablaElement = driver
				.findElement(By.xpath("/html/body/app-root/app-consultar-vehiculos/div/div[4]/form/div[3]"));
		wait2.until(ExpectedConditions.visibilityOf(tablaElement));

		WebElement valorPlaca = driver.findElement(By.xpath("//*[@id=\"tablaRegistros\"]/tbody/tr/td[2]"));
		System.out.println(valorPlaca.getText() + "placa en tabla--------------");
		System.out.println(placa + "placa real--------------------");

		assertEquals(valorPlaca.getText(), placa);
	}

	// /**
	// * prueba que se encarga de verificar el registrar salida para un vehiculo que
	// * no se encuntra registrado
	// */
	// @Test
	// public void registrarConsultarRetirarVehiculo() {
	//
	// String placa = generarStringRando();
	//
	// WebElement botonRegistrarNav =
	// driver.findElement(By.xpath("/html/body/app-root/div[1]/span[1]"));
	// botonRegistrarNav.click();
	//
	// WebElement placaElement = driver.findElement(
	// By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[1]/div/input"));
	// placaElement.sendKeys(placa);
	//
	// WebElement tipoVehiculoElement = driver.findElement(
	// By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[2]/div/select"));
	// tipoVehiculoElement.sendKeys("CARRO");
	//
	// WebElement botonRegistrar = driver
	// .findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/div[3]/form/div[3]/button"));
	// botonRegistrar.click();
	//
	// WebDriverWait wait = new WebDriverWait(driver, 2);
	// WebElement alertaModal = driver
	// .findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
	// wait.until(ExpectedConditions.visibilityOf(alertaModal));
	//
	// // busca el veh�culo
	// WebElement botonSalirNav =
	// driver.findElement(By.xpath("/html/body/app-root/div[1]/span[3]"));
	// botonSalirNav.click();
	//
	// WebElement placaSalirElement = driver
	// .findElement(By.xpath("/html/body/app-root/app-consultar-vehiculos/div/div[4]/form/div[1]/div/input"));
	// placaSalirElement.sendKeys(placa);
	//
	// WebElement botonConsulta = driver.findElement(By.className("btn-primary"));
	// botonConsulta.click();
	//
	// WebDriverWait wait2 = new WebDriverWait(driver, 2);
	// WebElement tablaElement = driver
	// .findElement(By.xpath("/html/body/app-root/app-consultar-vehiculos/div/div[4]/form/div[3]"));
	// wait2.until(ExpectedConditions.visibilityOf(tablaElement));
	//
	//
	//
	//
	// WebElement valorPlaca =
	// driver.findElement(By.xpath("//*[@id=\"tablaRegistros\"]/tbody/tr/td[2]"));
	//
	// assertEquals(valorPlaca.getText(), placa);
	// }

	@AfterClass
	public static void cerrarDriver() {
		driver.quit();
	}

	private String generarStringRando() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();

	}

	/**
	 * This method generates random numbers
	 * 
	 * @return int
	 */
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

}
