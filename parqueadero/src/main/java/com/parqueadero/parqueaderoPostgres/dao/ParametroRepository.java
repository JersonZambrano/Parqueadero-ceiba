/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parqueadero.parqueaderoPostgres.modelo.Parametro;

/**
 * @author jerson.zambrano
 *
 */
public interface ParametroRepository  extends JpaRepository<Parametro, Long> {

	 @Query("SELECT p.valor FROM Parametro p  WHERE p.descripcion=(:parametro)")
	 String consultarParametro(@Param("parametro") String parametro);
}