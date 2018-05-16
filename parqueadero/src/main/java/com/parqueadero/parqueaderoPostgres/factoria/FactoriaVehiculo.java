/**
 * 
 */
package com.parqueadero.parqueaderoPostgres.factoria;

import com.parqueadero.parqueaderoPostgres.Enumeraciones.TipoVehiculoEnum;
import com.parqueadero.parqueaderoPostgres.modelo.Vehiculo;
import com.parqueadero.parqueaderoPostgres.modelo.VehiculoCarro;
import com.parqueadero.parqueaderoPostgres.modelo.VehiculoMoto;

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
