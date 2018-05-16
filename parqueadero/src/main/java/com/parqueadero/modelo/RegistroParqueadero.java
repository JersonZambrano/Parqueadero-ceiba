/**
 * 
 */
package com.parqueadero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.parqueadero.enumeraciones.TipoVehiculoEnum;

/**
 * @author jerson.zambrano
 *
 */
@Entity
@Table(name = "registro")
public class RegistroParqueadero  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4323746425375359993L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRegistro;
	
	private String placa;
	
	private Date fechaIngreso;
	
	private Date fechaSalida;
	
	private Double valorTotal;
	
	private TipoVehiculoEnum tipoVehiculo;
	
	private Double cilindraje;
	

	
	public RegistroParqueadero() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idRegistro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}

	/**
	 * @param idRegistro the idRegistro to set
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
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
	 * @return the fechaSalida
	 */
	public Date getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * @param fechaSalida the fechaSalida to set
	 */
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

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

	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}	

}
