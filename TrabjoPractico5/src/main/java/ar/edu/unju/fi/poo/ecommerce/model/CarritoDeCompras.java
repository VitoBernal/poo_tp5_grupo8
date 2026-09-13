package ar.edu.unju.fi.poo.ecommerce.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CarritoDeCompras {
	private int id;
	private final List<ItemCarrito> items;

	public CarritoDeCompras(int id) {
		this.id = id;
		this.items = new ArrayList<>();
	}

	public void agregar(Producto p, int cantidad) {
		if (p == null)
			throw new IllegalArgumentException("producto requerido");
		if (!p.isActivo())
			throw new IllegalArgumentException("producto inactivo: " + p.getCodigo());
		if (cantidad <= 0)
			throw new IllegalArgumentException("cantidad debe ser > 0");

		Optional<ItemCarrito> existente = items.stream().filter(i -> i.getProducto().getCodigo().equals(p.getCodigo()))
				.findFirst();

		if (existente.isPresent()) {
			ItemCarrito item = existente.get();
			item.setCantidad(item.getCantidad() + cantidad);
		} else {
			items.add(new ItemCarrito(items.size() + 1, p, cantidad));
		}
	}

	public void remover(Producto p) {
		if (p == null)
			return;
		items.removeIf(i -> i.getProducto().getCodigo().equals(p.getCodigo()));
	}

	public double total() {
		return items.stream().mapToDouble(ItemCarrito::subTotal).sum();
	}

	public void vaciar() {
		items.clear();
	}

	public List<ItemCarrito> getItems() {
		return Collections.unmodifiableList(items);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void mostrarCarrito() {
		System.out.println("=== Carrito " + id + " ===");
		if (items.isEmpty()) {
			System.out.println("Carrito vacío");
		} else {
			items.forEach(System.out::println);
			System.out.printf("TOTAL: %.2f%n", total());
		}
	}
}