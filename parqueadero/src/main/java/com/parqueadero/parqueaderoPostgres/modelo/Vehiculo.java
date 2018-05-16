/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.modelo;

import com.parqueadero.parqueaderoPostgres.Enumeraciones.TipoVehiculoEnum;

/**
 * @author jerson.zambrano
 *
 */
public abstract class Vehiculo {

	
	private String placa;
	
	//private Double Cilindraje;
	
	private TipoVehiculoEnum tipoVehiculo;

	
	public Vehiculo() {
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

//	/**
//	 * @return the cilindraje
//	 */
//	public Double getCilindraje() {
//		return Cilindraje;
//	}
//
//	/**
//	 * @param cilindraje the cilindraje to set
//	 */
//	public void setCilindraje(Double cilindraje) {
//		Cilindraje = cilindraje;
//	}

	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	
	
}
