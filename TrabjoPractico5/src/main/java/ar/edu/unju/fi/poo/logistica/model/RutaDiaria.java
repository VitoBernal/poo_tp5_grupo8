package ar.edu.unju.fi.poo.logistica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RutaDiaria {
	private LocalDate fecha;
	private Vehiculo vehiculo;
	private final List<Envio> envios;

	public RutaDiaria(LocalDate fecha, Vehiculo vehiculo) {
		if (fecha == null)
			throw new IllegalArgumentException("fecha requerida");
		if (vehiculo == null)
			throw new IllegalArgumentException("vehiculo requerido");
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.envios = new ArrayList<>();
	}

	public void agregarEnvio(Envio envio) {
		if (envio == null)
			throw new IllegalArgumentException("envio requerido");
		if (envio.getPaquetes().isEmpty()) {
			throw new IllegalStateException("Un envío sin paquetes no se puede asignar a una ruta");
		}
		if (envios.contains(envio))
			return; // ya está en la ruta

		double pesoActual = getPesoTotalAsignado();
		double pesoNuevo = envio.pesoTotal();
		if (pesoActual + pesoNuevo > vehiculo.getCapPeso()) {
			throw new IllegalArgumentException("Capacidad de peso excedida. Actual=%.2f, nuevo=%.2f, máximo=%.2f"
					.formatted(pesoActual, pesoNuevo, vehiculo.getCapPeso()));
		}

		double volumenActual = getVolumenTotalAsignado();
		double volumenNuevo = envio.volumenTotal();
		if (volumenActual + volumenNuevo > vehiculo.getCapVolumen()) {
			throw new IllegalArgumentException("Capacidad de volumen excedida. Actual=%.2f, nuevo=%.2f, máximo=%.2f"
					.formatted(volumenActual, volumenNuevo, vehiculo.getCapVolumen()));
		}

		envios.add(envio);
		envio.setRuta(this);
	}

	public double getPesoTotalAsignado() {
		return envios.stream().mapToDouble(Envio::pesoTotal).sum();
	}

	public double getVolumenTotalAsignado() {
		return envios.stream().mapToDouble(Envio::volumenTotal).sum();
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Envio> getEnvios() {
		return Collections.unmodifiableList(envios);
	}

	@Override
	public String toString() {
		return "RutaDiaria{fecha=%s, vehiculo=%s, envios=%d, pesoTotal=%.2f}".formatted(fecha, vehiculo.getPatente(),
				envios.size(), getPesoTotalAsignado());
	}
}