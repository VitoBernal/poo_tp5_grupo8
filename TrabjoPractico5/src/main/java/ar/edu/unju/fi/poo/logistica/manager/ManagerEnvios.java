package ar.edu.unju.fi.poo.logistica.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ar.edu.unju.fi.poo.logistica.model.Envio;
import ar.edu.unju.fi.poo.logistica.model.Paquete;
import ar.edu.unju.fi.poo.logistica.model.RutaDiaria;
import ar.edu.unju.fi.poo.logistica.model.Vehiculo;

public class ManagerEnvios {
    private final List<Envio> envios = new ArrayList<>();
    private final List<Vehiculo> vehiculos = new ArrayList<>();
    private final List<RutaDiaria> rutas = new ArrayList<>();

    // ----- Envíos -----

    public Envio registrarEnvio(int id, String remitente, String destinatario, String direccion) {
        if (buscarEnvio(id).isPresent()) {
            throw new IllegalArgumentException("Ya existe un envío con id " + id);
        }
        Envio e = new Envio(id, remitente, destinatario, direccion);
        envios.add(e);
        return e;
    }

    public void agregarPaquete(int idEnvio, Paquete paquete) {
        Envio e = buscarEnvio(idEnvio)
                .orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio));
        e.agregarPaquete(paquete);
    }

    public void asignarRuta(int idEnvio, LocalDate fecha, String patente) {
        Envio e = buscarEnvio(idEnvio)
                .orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio));
        RutaDiaria r = buscarRuta(fecha, patente).orElseGet(() -> crearRuta(fecha, patente));
        e.asignarRuta(r);
    }

    public void despacharEnvio(int idEnvio) {
        buscarEnvio(idEnvio).orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio)).despachar();
    }

    public void entregarEnvio(int idEnvio) {
        buscarEnvio(idEnvio).orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio)).entregar();
    }

    public void devolverEnvio(int idEnvio) {
        buscarEnvio(idEnvio).orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio)).devolver();
    }

    public void cancelarEnvio(int idEnvio) {
        buscarEnvio(idEnvio).orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio)).cancelar();
    }

    public String mostrarInfoEnvio(int idEnvio) {
        return buscarEnvio(idEnvio)
                .orElseThrow(() -> new NoSuchElementException("Envío no encontrado: " + idEnvio))
                .mostrarInfo();
    }

    // ----- Vehículos -----

    public Vehiculo registrarVehiculo(String patente, double capPeso, double capVolumen) {
        if (buscarVehiculo(patente).isPresent()) {
            throw new IllegalArgumentException("Ya existe un vehículo con patente " + patente);
        }
        Vehiculo v = new Vehiculo(patente, capPeso, capVolumen);
        vehiculos.add(v);
        return v;
    }

    // ----- Rutas -----

    public RutaDiaria crearRuta(LocalDate fecha, String patente) {
        Vehiculo v = buscarVehiculo(patente)
                .orElseThrow(() -> new NoSuchElementException("Vehículo no encontrado: " + patente));
        RutaDiaria r = new RutaDiaria(fecha, v);
        rutas.add(r);
        return r;
    }

    // ----- Búsquedas -----

    public Optional<Envio> buscarEnvio(int id) {
        return envios.stream().filter(e -> e.getId() == id).findFirst();
    }

    public Optional<Vehiculo> buscarVehiculo(String patente) {
        return vehiculos.stream().filter(v -> v.getPatente().equalsIgnoreCase(patente)).findFirst();
    }

    public Optional<RutaDiaria> buscarRuta(LocalDate fecha, String patente) {
        return rutas.stream()
                .filter(r -> r.getFecha().equals(fecha)
                        && r.getVehiculo().getPatente().equalsIgnoreCase(patente))
                .findFirst();
    }

    // ----- Listados -----

    public List<Envio> getEnvios() { return Collections.unmodifiableList(envios); }
    public List<Vehiculo> getVehiculos() { return Collections.unmodifiableList(vehiculos); }
    public List<RutaDiaria> getRutas() { return Collections.unmodifiableList(rutas); }
}