package org.example.aplication;

import org.example.aplication.service.MedicoService;
import org.example.aplication.service.MedicoServiceImpl;
import org.example.domain.Medico;
import org.example.infraestructure.repository.MedicoRepositoryImpl;
import org.example.interfaces.MedicoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MedicoService medicoService;

    static {
        MedicoRepository medicoRepository = new MedicoRepositoryImpl();
        medicoService = new MedicoServiceImpl(medicoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Listar medicos");
            System.out.println("2. Crear medico");
            System.out.println("3. Actualizar medico");
            System.out.println("4. Eliminar medico");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarMedicos();
                    break;
                case 2:
                    crearProducto();
                    break;
                case 3:
                    actualizarMedico();
                    break;
                case 4:
                    eliminarMedico();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarMedicos() {
        List<Medico> medicos = medicoService.findAll();
        if (medicos.isEmpty()) {
            System.out.println("No hay medicos registrados.");
        } else {
            System.out.println("Listado de medicos:");
            for (Medico medico : medicos) {
                System.out.println(medico);
            }
        }
    }

    private static void crearProducto() {
        System.out.print("Ingrese el id del medico: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del medico: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del medico: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Medico medico = new Medico();
        medico.setId(cod);
        medico.setNombre(nombre);
        medico.setEdad(edad);

        try {
            medicoService.save(medico);
            System.out.println("Medico creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarMedico() {
        System.out.print("Ingrese el ID del medico a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Medico medico = medicoService.findById(id);
        if (medico == null) {
            System.out.println("No se encontró el medico con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del producto (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            medico.setNombre(nombre);
        }

        System.out.print("Ingrese el nuevo precio del producto (0 para no cambiar): ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        if (edad > 0) {
            medico.setEdad(edad);
        }

        try {
            medicoService.update(medico);
            System.out.println("Medico actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarMedico() {
        System.out.print("Ingrese el ID del medico a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Medico medico = medicoService.findById(id);
        if (medico == null) {
            System.out.println("No se encontró el medico con ID " + id);
            return;
        }

        medicoService.delete(id);
        System.out.println("Medico eliminado exitosamente.");
    }
}
