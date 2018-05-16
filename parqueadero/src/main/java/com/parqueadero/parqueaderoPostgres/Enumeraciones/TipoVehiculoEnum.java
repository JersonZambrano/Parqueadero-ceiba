/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.Enumeraciones;

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
}
