package com.parqueadero.parqueaderoPostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.parqueadero.parqueaderoPostgres.api.ParqueaderoServices;

@SpringBootApplication
//@ComponentScan(basePackageClasses = ParqueaderoServices.class)
public class ParqueaderoPostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoPostgresApplication.class, args);
	}
}
