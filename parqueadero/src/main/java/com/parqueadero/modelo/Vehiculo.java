/**
 * 
 */
package com.parqueadero.modelo;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.lang.NonNull;

import com.parqueadero.enumeraciones.TipoVehiculoEnum;

/**
 * @author jerson.zambrano
 *
 */
@Valid
public  class Vehiculo {

	@NonNull
	private String placa;
	
	private Double cilindraje;
	
	private TipoVehiculoEnum tipoVehiculo;
	
	private Date fechaIngreso;
	
	private Double valorTotal;

	/**
	 * @return the valorTotal
	 */
	public Double getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
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
		return cilindraje;
	}

	/**
	 * @param cilindraje the cilindraje to set
	 */
	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public RegistroParqueadero converToEntity() {
		RegistroParqueadero reg = new RegistroParqueadero();
		reg.setCilindraje(this.cilindraje);
		reg.setPlaca(this.placa);
		reg.setTipoVehiculo(this.tipoVehiculo);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 */
	public void entityToVehiculo(RegistroParqueadero reg) {
		this.placa= reg.getPlaca();
		this.cilindraje = reg.getCilindraje();
		this.fechaIngreso = reg.getFechaIngreso();
		this.tipoVehiculo = reg.getTipoVehiculo();
		this.valorTotal = reg.getValorTotal();
	}
}
