package ar.edu.unju.fi.poo.logistica.model;

import java.util.Objects;

public class Paquete {
	private String codigo;
	private String descripcion;
	private double peso;
	private double volumen;

	public Paquete(String codigo, String descripcion, double peso, double volumen) {
		this.codigo = Objects.requireNonNull(codigo, "codigo requerido");
		this.descripcion = Objects.requireNonNull(descripcion, "descripcion requerida");
		if (peso <= 0)
			throw new IllegalArgumentException("peso debe ser > 0");
		if (volumen <= 0)
			throw new IllegalArgumentException("volumen debe ser > 0");
		this.peso = peso;
		this.volumen = volumen;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Paquete paquete))
			return false;
		return Objects.equals(codigo, paquete.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public String toString() {
		return "Paquete{codigo='%s', descripcion='%s', peso=%.2f, volumen=%.2f}".formatted(codigo, descripcion, peso,
				volumen);
	}
}