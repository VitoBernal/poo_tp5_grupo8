package ar.edu.unju.fi.poo;

import java.time.LocalDate;

import ar.edu.unju.fi.poo.logistica.manager.ManagerEnvios;
import ar.edu.unju.fi.poo.logistica.model.Paquete;

public class MainEnvios {
	public static void main(String[] args) {
		ManagerEnvios manager = new ManagerEnvios();

		// 1) Vehículos
		manager.registrarVehiculo("AB123CD", 40, 5000); // capacidad peso 40 kg (para probar exceso)
		manager.registrarVehiculo("EF456GH", 500, 2500);

		// 2) Envíos
		manager.registrarEnvio(1, "Juan", "María", "Av. Siempre Viva 123");
		manager.registrarEnvio(2, "Pedro", "Lucía", "Calle Falsa 456");
		manager.registrarEnvio(3, "Ana", "Carlos", "Boulevard 789");

		// 3) Paquetes
		manager.agregarPaquete(1, new Paquete("PK1", "Caja mediana", 10.5, 30));
		manager.agregarPaquete(1, new Paquete("PK2", "Sobre", 1.2, 5));
		manager.agregarPaquete(2, new Paquete("PK3", "Caja grande", 25.0, 80));
		manager.agregarPaquete(3, new Paquete("PK4", "Caja pequeña", 5.0, 15));

		// 4) Asignar rutas
		manager.asignarRuta(1, LocalDate.now(), "AB123CD");
		manager.asignarRuta(2, LocalDate.now(), "AB123CD");

		// 5) Despachar
		manager.despacharEnvio(1);
		manager.despacharEnvio(2);

		// 6) Mostrar info
		System.out.println(manager.mostrarInfoEnvio(1));
		System.out.println(manager.mostrarInfoEnvio(2));

		// 7) Entregar y devolver
		manager.entregarEnvio(1);
		System.out.println(manager.mostrarInfoEnvio(1));

		manager.devolverEnvio(2);
		System.out.println(manager.mostrarInfoEnvio(2));

		// 8) Probar envío sin paquetes
		manager.registrarEnvio(4, "Luis", "Sofía", "Ruta 8 km 20");
		try {
			manager.asignarRuta(4, LocalDate.now(), "AB123CD");
		} catch (Exception ex) {
			System.out.println("Error esperado (sin paquetes): " + ex.getMessage());
		}

		// 9) Probar exceso de capacidad
		try {
			manager.asignarRuta(3, LocalDate.now(), "AB123CD");
		} catch (Exception ex) {
			System.out.println("Error esperado (capacidad): " + ex.getMessage());
		}
	}
}
