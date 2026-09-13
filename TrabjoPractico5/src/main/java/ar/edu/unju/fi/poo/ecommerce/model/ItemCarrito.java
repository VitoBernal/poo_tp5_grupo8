package ar.edu.unju.fi.poo.ecommerce.model;

public class ItemCarrito {
	private int id;
	private Producto producto;
	private int cantidad;

	public ItemCarrito(int id, Producto producto, int cantidad) {
		if (producto == null)
			throw new IllegalArgumentException("producto requerido");
		if (cantidad <= 0)
			throw new IllegalArgumentException("cantidad debe ser > 0");
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public double subTotal() {
		return producto.getPrecio() * cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ItemCarrito{id=%d, producto=%s, cantidad=%d, subtotal=%.2f}".formatted(id, producto.getNombre(),
				cantidad, subTotal());
	}
}