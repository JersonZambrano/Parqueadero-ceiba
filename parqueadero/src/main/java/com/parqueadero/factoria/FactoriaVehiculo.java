/**
 * 
 */
package com.parqueadero.factoria;

import com.parqueadero.modelo.Vehiculo;
import com.parqueadero.modelo.VehiculoCarro;
import com.parqueadero.modelo.VehiculoMoto;

/**
 * @author jerson.zambrano
 *
 */
public abstract class FactoriaVehiculo {

	public static Vehiculo getFactura(Vehiculo v) {

		switch (v.getTipoVehiculo()) {
		case CARRO:
			return (VehiculoCarro) v;
		case MOTO:
			return  (VehiculoMoto) v;
		default:
			return null;
		}
	}
}
