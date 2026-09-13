package ar.edu.unju.fi.poo.ecommerce.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.poo.ecommerce.model.Producto;

public class ManagerProducto {
	private final List<Producto> productos = new ArrayList<>();

	public ManagerProducto() {
		inicializar();
	}

	private void inicializar() {
		productos.add(new Producto("P001", "Laptop Lenovo", 1500.00, true));
		productos.add(new Producto("P002", "Mouse inalámbrico", 25.50, true));
		productos.add(new Producto("P003", "Teclado mecánico", 80.00, true));
		productos.add(new Producto("P004", "Monitor 24''", 220.00, true));
		productos.add(new Producto("P005", "Audífonos Bluetooth", 45.00, true));
		productos.add(new Producto("P006", "Webcam HD", 60.00, false)); // inactivo para probar
	}

	public List<Producto> listar() {
		return Collections.unmodifiableList(productos);
	}

	public Optional<Producto> buscarPorCodigo(String codigo) {
		return productos.stream().filter(p -> p.getCodigo().equalsIgnoreCase(codigo)).findFirst();
	}

	public Optional<Producto> buscarPorNombre(String nombre) {
		return productos.stream().filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase())).findFirst();
	}

	public void agregar(Producto p) {
		if (p == null)
			throw new IllegalArgumentException("producto requerido");
		if (buscarPorCodigo(p.getCodigo()).isPresent()) {
			throw new IllegalArgumentException("Ya existe producto con código " + p.getCodigo());
		}
		productos.add(p);
	}
}