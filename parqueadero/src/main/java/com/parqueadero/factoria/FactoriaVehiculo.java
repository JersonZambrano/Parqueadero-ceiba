/**
 * 
 */
package com.parqueadero.factoria;

import com.parqueadero.enumeraciones.TipoVehiculoEnum;
import com.parqueadero.modelo.Vehiculo;
import com.parqueadero.modelo.VehiculoCarro;
import com.parqueadero.modelo.VehiculoMoto;

/**
 * @author jerson.zambrano
 *
 */
public class FactoriaVehiculo {

	public static Vehiculo getFactura(TipoVehiculoEnum tipo) {

		switch (tipo) {
		case CARRO:
			return new VehiculoCarro();
		// break;
		case MOTO:
			return new VehiculoMoto();
		// break;
		default:
			return null;
		// break;
		}
	}
}
