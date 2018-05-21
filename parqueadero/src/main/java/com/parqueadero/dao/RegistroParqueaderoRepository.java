package com.parqueadero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parqueadero.enumeraciones.TipoVehiculoEnum;
import com.parqueadero.modelo.RegistroParqueadero;

public interface RegistroParqueaderoRepository extends JpaRepository<RegistroParqueadero, Long> {

	@Query("SELECT v FROM RegistroParqueadero v  WHERE v.placa=(:placa) AND fechaSalida IS NULL")
	RegistroParqueadero buscarVehiculoPorPlaca(@Param("placa") String placa);

	@Query("SELECT COUNT(r) FROM RegistroParqueadero r  WHERE r.tipoVehiculo=(:tipoVehiculo) AND r.fechaSalida IS NULL")
	Integer consultarOcupados(@Param("tipoVehiculo") TipoVehiculoEnum tipoVehiculo);
	
	@Query("SELECT v FROM RegistroParqueadero v  WHERE v.placa=(:placa) AND fechaSalida IS NULL")
	RegistroParqueadero consultarRegistroSalida(@Param("placa") String placa);
	
	@Query("SELECT v FROM RegistroParqueadero v  WHERE fechaSalida IS NULL")
	List<RegistroParqueadero> buscarTotalRegistros();

}
