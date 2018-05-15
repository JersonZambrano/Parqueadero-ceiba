/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author jerson.zambrano
 *
 */
@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParametro;
	
	private String descripcion;
	
	private String valor;



	/**
	 * @return the idParametro
	 */
	public Long getIdParametro() {
		return idParametro;
	}

	/**
	 * @param idParametro the idParametro to set
	 */
	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	
}
