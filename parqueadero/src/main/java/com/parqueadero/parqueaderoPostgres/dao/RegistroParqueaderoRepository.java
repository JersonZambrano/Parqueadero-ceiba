package com.parqueadero.parqueaderoPostgres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.parqueadero.parqueaderoPostgres.modelo.RegistroParqueadero;


public interface RegistroParqueaderoRepository extends JpaRepository<RegistroParqueadero, Long>{
	
	 @Query("SELECT v FROM RegistroParqueadero v  WHERE v.placa=(:placa)")
	 RegistroParqueadero buscarVehiculoPorPlaca(@Param("placa") String placa);

}
