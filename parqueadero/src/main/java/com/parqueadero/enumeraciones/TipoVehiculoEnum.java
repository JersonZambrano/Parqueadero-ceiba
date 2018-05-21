/**
 * 
 */
package com.parqueadero.enumeraciones;

/**
 * @author jerson.zambrano
 *
 */
public enum TipoVehiculoEnum {
	
	MOTO(500,4000),
	CARRO(1000,8000);
	
	private double valorHora;
	private double valorDia;
	
	private TipoVehiculoEnum(double valorHora, double valorDia) {
		this.valorHora = valorHora;
		this.valorDia = valorDia;
	}

	/**
	 * @return the valorHora
	 */
	public double getValorHora() {
		return valorHora;
	}

	/**
	 * @return the valorDia
	 */
	public double getValorDia() {
		return valorDia;
	}	
}
