package ar.edu.unju.fi.poo.logistica.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Envio {
	private int id;
	private String remitente;
	private String destinatario;
	private String direccionEntrega;
	private EstadoEnvio estado;
	private final List<Paquete> paquetes;
	private RutaDiaria ruta;

	public Envio(int id, String remitente, String destinatario, String direccionEntrega) {
		this.id = id;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.direccionEntrega = direccionEntrega;
		this.estado = EstadoEnvio.GENERADO; // estado por defecto
		this.paquetes = new ArrayList<>();
	}

	// ----- Operaciones de negocio -----

	public void agregarPaquete(Paquete p) {
		if (p == null)
			throw new IllegalArgumentException("paquete requerido");
		if (estado == EstadoEnvio.ENTREGADO || estado == EstadoEnvio.DEVUELTO || estado == EstadoEnvio.CANCELADO) {
			throw new IllegalStateException("No se pueden agregar paquetes a un envío " + estado);
		}
		paquetes.add(p);
	}

	public void asignarRuta(RutaDiaria ruta) {
		if (ruta == null)
			throw new IllegalArgumentException("ruta requerida");
		if (paquetes.isEmpty()) {
			throw new IllegalStateException("Un envío sin paquetes no se puede asignar a una ruta");
		}
		ruta.agregarEnvio(this); // la ruta valida capacidad y agrega
		this.ruta = ruta;
		if (this.estado == EstadoEnvio.GENERADO) {
			this.estado = EstadoEnvio.EN_ALMACEN;
		}
	}

	public void despachar() {
		if (paquetes.isEmpty())
			throw new IllegalStateException("No se puede despachar un envío sin paquetes");
		if (ruta == null)
			throw new IllegalStateException("No se puede despachar un envío sin ruta asignada");
		if (estado != EstadoEnvio.EN_ALMACEN) {
			throw new IllegalStateException("Solo se puede despachar un envío EN_ALMACEN. Estado actual: " + estado);
		}
		this.estado = EstadoEnvio.EN_RUTA;
	}

	public void entregar() {
		if (estado != EstadoEnvio.EN_RUTA) {
			throw new IllegalStateException("Solo se puede entregar un envío EN_RUTA. Estado actual: " + estado);
		}
		this.estado = EstadoEnvio.ENTREGADO;
	}

	public void devolver() {
		if (estado != EstadoEnvio.EN_RUTA && estado != EstadoEnvio.ENTREGADO) {
			throw new IllegalStateException(
					"Solo se puede devolver un envío EN_RUTA o ENTREGADO. Estado actual: " + estado);
		}
		this.estado = EstadoEnvio.DEVUELTO;
	}

	public void cancelar() {
		if (estado == EstadoEnvio.ENTREGADO || estado == EstadoEnvio.DEVUELTO) {
			throw new IllegalStateException("No se puede cancelar un envío " + estado);
		}
		this.estado = EstadoEnvio.CANCELADO;
	}

	public String mostrarInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== ENVÍO ").append(id).append(" ===\n");
		sb.append("Remitente: ").append(remitente).append("\n");
		sb.append("Destinatario: ").append(destinatario).append("\n");
		sb.append("Dirección: ").append(direccionEntrega).append("\n");
		sb.append("Estado: ").append(estado).append("\n");
		sb.append("Ruta: ")
				.append(ruta != null ? ruta.getFecha() + " / " + ruta.getVehiculo().getPatente() : "Sin asignar")
				.append("\n");
		sb.append("Paquetes:\n");
		if (paquetes.isEmpty()) {
			sb.append("  (sin paquetes)\n");
		} else {
			paquetes.forEach(p -> sb.append("  - ").append(p).append("\n"));
		}
		sb.append("Peso total: %.2f kg\n".formatted(pesoTotal()));
		sb.append("Volumen total: %.2f dm3\n".formatted(volumenTotal()));
		return sb.toString();
	}

	// ----- Auxiliares -----

	public double pesoTotal() {
		return paquetes.stream().mapToDouble(Paquete::getPeso).sum();
	}

	public double volumenTotal() {
		return paquetes.stream().mapToDouble(Paquete::getVolumen).sum();
	}

	// ----- Getters / Setters -----

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public EstadoEnvio getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnvio estado) {
		this.estado = estado;
	}

	public List<Paquete> getPaquetes() {
		return Collections.unmodifiableList(paquetes);
	}

	public RutaDiaria getRuta() {
		return ruta;
	}

	public void setRuta(RutaDiaria ruta) {
		this.ruta = ruta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Envio envio))
			return false;
		return id == envio.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}

	@Override
	public String toString() {
		return "Envio{id=%d, destinatario='%s', estado=%s}".formatted(id, destinatario, estado);
	}
}