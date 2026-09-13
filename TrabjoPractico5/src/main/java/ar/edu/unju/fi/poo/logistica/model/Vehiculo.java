package ar.edu.unju.fi.poo.logistica.model;

import java.util.Objects;

public class Vehiculo {
	private String patente;
	private double capPeso;
	private double capVolumen;

	public Vehiculo(String patente, double capPeso, double capVolumen) {
		this.patente = Objects.requireNonNull(patente, "patente requerida");
		if (capPeso <= 0)
			throw new IllegalArgumentException("capPeso debe ser > 0");
		if (capVolumen <= 0)
			throw new IllegalArgumentException("capVolumen debe ser > 0");
		this.capPeso = capPeso;
		this.capVolumen = capVolumen;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public double getCapPeso() {
		return capPeso;
	}

	public void setCapPeso(double capPeso) {
		this.capPeso = capPeso;
	}

	public double getCapVolumen() {
		return capVolumen;
	}

	public void setCapVolumen(double capVolumen) {
		this.capVolumen = capVolumen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Vehiculo vehiculo))
			return false;
		return Objects.equals(patente, vehiculo.patente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(patente);
	}

	@Override
	public String toString() {
		return "Vehiculo{patente='%s', capPeso=%.2f, capVolumen=%.2f}".formatted(patente, capPeso, capVolumen);
	}
}