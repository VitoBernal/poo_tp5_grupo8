package ar.edu.unju.fi.poo;

import ar.edu.unju.fi.poo.ecommerce.manager.ManagerProducto;
import ar.edu.unju.fi.poo.ecommerce.model.Producto;
import ar.edu.unju.fi.poo.ecommerce.model.Usuario;

public class MainEcommerce {
	public static void main(String[] args) {
		ManagerProducto manager = new ManagerProducto();
		System.out.println("Productos disponibles:");
		manager.listar().forEach(System.out::println);

		Usuario usuario = new Usuario("U001", "Ana Pérez", "ana@mail.com");

		Producto laptop = manager.buscarPorCodigo("P001").orElseThrow();
		Producto mouse = manager.buscarPorCodigo("P002").orElseThrow();
		Producto teclado = manager.buscarPorCodigo("P003").orElseThrow();

		usuario.agregarAlCarrito(laptop, 1);
		usuario.agregarAlCarrito(mouse, 2);
		usuario.agregarAlCarrito(teclado, 1);

		System.out.println("\nTotal actual: " + usuario.verTotalCarrito());
		usuario.mostrarCarrito();

		System.out.println("\nRemoviendo mouse...");
		usuario.removerDelCarrito(mouse);
		System.out.println("Total actual: " + usuario.verTotalCarrito());
		usuario.mostrarCarrito();

		System.out.println("\nVaciando carrito...");
		usuario.getCarrito().vaciar();
		System.out.println("Total actual: " + usuario.verTotalCarrito());
		usuario.mostrarCarrito();
	}
}