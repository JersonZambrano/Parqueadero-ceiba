/**
 * 
 */
package com.parqueadero.modelo;

import com.parqueadero.enumeraciones.TipoVehiculoEnum;

/**
 * @author jerson.zambrano
 *
 */
public  class Vehiculo {

	
	private String placa;
	
	private Double Cilindraje;
	
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

	/**
	 * @return the cilindraje
	 */
	public Double getCilindraje() {
		return Cilindraje;
	}

	/**
	 * @param cilindraje the cilindraje to set
	 */
	public void setCilindraje(Double cilindraje) {
		Cilindraje = cilindraje;
	}

	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public RegistroParqueadero converToEntity() {
		RegistroParqueadero reg = new RegistroParqueadero();
		reg.setCilindraje(this.Cilindraje);
		reg.setPlaca(this.placa);
		reg.setTipoVehiculo(this.tipoVehiculo);
		return reg;
	}
}
