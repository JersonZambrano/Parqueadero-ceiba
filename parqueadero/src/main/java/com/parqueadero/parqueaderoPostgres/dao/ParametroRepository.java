/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parqueaderoPostgres.modelo.Parametro;

/**
 * @author jerson.zambrano
 *
 */
public interface ParametroRepository  extends JpaRepository<Parametro, Long> {

}
