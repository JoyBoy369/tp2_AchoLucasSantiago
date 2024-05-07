package ar.edu.unju.fi.ejercicio5.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

public class PagoTarjeta implements Pago {

	private long numeroTarjeta;
	private LocalDate fechaPago;
	private double montoPagado;

	public PagoTarjeta() {
		super();
	}

	public PagoTarjeta(long numeroTarjeta, LocalDate fechaPago, double montoPagado) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.fechaPago = fechaPago;
		this.montoPagado = montoPagado;
	}

	public long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	@Override
	public double realizarPago(double monto) {
		return montoPagado += (montoPagado * 15) / 100;
	}

	@Override
	public void imprimirRecibo() {

		montoPagado = realizarPago(montoPagado);

		double montoRedondeado = Math.round(montoPagado * 100.0) / 100.0;
		String montoFormateado = String.format("%.2f", montoRedondeado);

		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		String fechaFormateada = formato.format(fecha);

		System.out.println("Número de tarjeta: " + numeroTarjeta);
		System.out.println("Fecha de pago: " + fechaFormateada);
		System.out.println("Monto pagado: " + montoFormateado);
	}

}