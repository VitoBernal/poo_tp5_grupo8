package ar.edu.unju.fi.poo.ecommerce.model;

public class Usuario {
	private String id;
	private String nombre;
	private String email;
	private CarritoDeCompras carrito;

	public Usuario(String id, String nombre, String email) {
		this(id, nombre, email, new CarritoDeCompras(1));
	}

	public Usuario(String id, String nombre, String email, CarritoDeCompras carrito) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.carrito = carrito;
	}

	public void agregarAlCarrito(Producto p, int cantidad) {
		carrito.agregar(p, cantidad);
	}

	public void removerDelCarrito(Producto p) {
		carrito.remover(p);
	}

	public double verTotalCarrito() {
		return carrito.total();
	}

	public void mostrarCarrito() {
		carrito.mostrarCarrito();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CarritoDeCompras getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoDeCompras carrito) {
		this.carrito = carrito;
	}
}