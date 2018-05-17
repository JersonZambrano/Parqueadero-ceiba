package com.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
//@ComponentScan(basePackageClasses = ParqueaderoServices.class)
public class ParqueaderoPostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoPostgresApplication.class, args);
	}
}
