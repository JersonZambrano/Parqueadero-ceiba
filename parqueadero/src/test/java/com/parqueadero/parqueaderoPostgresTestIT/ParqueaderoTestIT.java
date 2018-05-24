package com.parqueadero.parqueaderoPostgresTestIT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParqueaderoTestIT {

	public ParqueaderoTestIT() {
		// TODO Auto-generated constructor stub
	}

	private static WebDriver driver = null;

	@BeforeClass
	public static void inicializarDriver() {
		//		System.setProperty("webdriver.chrome.driver", "C:/webdrivers/chromedriver.exe");
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

		assertEquals(mensaje, "El vehiculo fue registrado con exito");
	}

	@Test
	public void registrarVehiculoYaRegsitradoTest() {
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

		WebDriverWait wait2 = new WebDriverWait(driver, 2);
		WebElement alertaModal2 = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal"));
		wait2.until(ExpectedConditions.visibilityOf(alertaModal2));

		WebElement alertaModalMensaje = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-parqueadero/div/app-alert-modal/div/h5"));
		String mensaje = alertaModalMensaje.getText();

		assertEquals(mensaje, "El vehiculo ya se encuentra en el parqueadero");
	}
	
	@Test
	public void registrarSalidaTest() {
		
		//Se registra el Vehiculo
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
		
		//Se saca el vehiculo del parqueadero
		WebElement botonSalirNav = driver.findElement(By.xpath("/html/body/app-root/div[1]/span[2]"));
		botonSalirNav.click();
	
		WebElement placaSalirElement = driver.findElement(
				By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div/div[1]/div/input"));
		placaSalirElement.sendKeys(placa);


		WebElement botonSalir = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div/div[2]/button"));
		botonSalir.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 2);
		WebElement panelResultado = driver
				.findElement(By.xpath("/html/body/app-root/app-registrar-salida/div/div[3]/form/div[2]"));
		wait2.until(ExpectedConditions.visibilityOf(panelResultado));

		WebElement valorTotal = driver
				.findElement(By.className("total-pago"));
		String mensaje = valorTotal.getText();
		assertTrue(mensaje!= null);
	}
	
	
	

	@AfterClass
	public static void cerrarDriver() {
		driver.quit();
	}

	private String generarStringRando() {
		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		return "C"+new String(array, Charset.forName("UTF-8"));

	}

}
