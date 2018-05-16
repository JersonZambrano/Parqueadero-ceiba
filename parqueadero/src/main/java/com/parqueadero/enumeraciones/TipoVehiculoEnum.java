/**
 * 
 */
package com.parqueadero.enumeraciones;

/**
 * @author jerson.zambrano
 *
 */
public enum TipoVehiculoEnum {
	
	MOTO("Moto",500,4000),
	CARRO("Carro",1000,8000);
	
	private String descripcion;
	private double valorHora;
	private double valorDia;
	
	private TipoVehiculoEnum(String descripcion, double valorHora, double valorDia) {
		this.descripcion = descripcion;
		this.valorHora = valorHora;
		this.valorDia = valorDia;
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
	 * @return the valorHora
	 */
	public double getValorHora() {
		return valorHora;
	}

	/**
	 * @param valorHora the valorHora to set
	 */
	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	/**
	 * @return the valorDia
	 */
	public double getValorDia() {
		return valorDia;
	}

	/**
	 * @param valorDia the valorDia to set
	 */
	public void setValorDia(double valorDia) {
		this.valorDia = valorDia;
	}
	
}
