package ar.edu.unju.fi.poo.ecommerce.model;

import java.util.Objects;

public class Producto {
	private String codigo;
	private String nombre;
	private double precio;
	private boolean activo;

	public Producto(String codigo, String nombre, double precio, boolean activo) {
		this.codigo = Objects.requireNonNull(codigo, "codigo requerido");
		this.nombre = Objects.requireNonNull(nombre, "nombre requerido");
		if (precio < 0)
			throw new IllegalArgumentException("precio no puede ser negativo");
		this.precio = precio;
		this.activo = activo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Producto producto))
			return false;
		return Objects.equals(codigo, producto.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public String toString() {
		return "Producto{codigo='%s', nombre='%s', precio=%.2f, activo=%s}".formatted(codigo, nombre, precio, activo);
	}
}