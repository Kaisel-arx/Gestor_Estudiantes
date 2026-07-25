package gestor_estudiantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Estudiante> listaEstudiantes = new ArrayList<>();
            int opcion;
            
            do {
                System.out.println("\n---------------------------------------------------");
                System.out.println("                  EDUNOVA                          ");
                System.out.println("---------------------------------------------------");
                System.out.println("==== SISTEMA DE GESTION DE ESTUDIANTES ====");
                System.out.println("1. Registrar nuevo estudiante");
                System.out.println("2. Ingresar notas del estudiante");
                System.out.println("3. Buscar y mostrar reporte de un estudiante");
                System.out.println("4. Salir");
                System.out.println("---------------------------------------------------");
                System.out.print("Seleccione una opcion: ");
                
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, ingrese un numero valido.");
                    scanner.next();
                    System.out.print("Seleccione una opcion: ");
                }
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1 -> {
                        System.out.print("\nIngrese el nombre del estudiante: ");
                        String nombre = scanner.nextLine();
                        
                        System.out.print("Ingrese la edad: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Ingrese una edad valida.");
                            scanner.next();
                            System.out.print("Ingrese la edad: ");
                        }
                        int edad = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
                        
                        System.out.print("Ingrese la matricula (ej. 2290-35-2026): ");
                        String matricula = scanner.nextLine();
                        
                        Estudiante nuevoEstudiante = new Estudiante(nombre, edad, matricula);
                        listaEstudiantes.add(nuevoEstudiante);
                        System.out.println("Estudiante registrado con exito!");
                    }
                        
                    case 2 -> {
                        if (listaEstudiantes.isEmpty()) {
                            System.out.println("\nNo hay estudiantes registrados todavia.");
                            break;
                        }
                        
                        System.out.print("\nIngrese la matricula del estudiante para buscarlo: ");
                        String matBuscarNota = scanner.nextLine();
                        
                        Estudiante estParaNotas = null;
                        for (Estudiante e : listaEstudiantes) {
                            if (e.getMatricula().equalsIgnoreCase(matBuscarNota)) {
                                estParaNotas = e;
                                break;
                            }
                        }
                        
                        if (estParaNotas != null) {
                            if (estParaNotas.tieneNotasCompletas()) {
                                System.out.println("Este estudiante ya tiene las notas de los 5 cursos completas.");
                                break;
                            }
                            
                            System.out.println("\n--- INGRESO DE LAS 5 NOTAS ---");
                            for (int i = 1; i <= 5; i++) {
                                boolean notaValida = false;
                                while (!notaValida) {
                                    System.out.print("Ingrese la nota del Curso " + i + " (0-100): ");
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Ingrese un valor numerico valido.");
                                        scanner.next();
                                        System.out.print("Ingrese la nota del Curso " + i + " (0-100): ");
                                    }
                                    double nota = scanner.nextDouble();
                                    scanner.nextLine(); // Limpiar buffer
                                    
                                    notaValida = estParaNotas.agregarNota(nota);
                                }
                            }
                            System.out.println("Las 5 notas han sido registradas exitosamente!");
                        } else {
                            System.out.println("Estudiante con matricula '" + matBuscarNota + "' no encontrado.");
                        }
                    }
                        
                    case 3 -> {
                        if (listaEstudiantes.isEmpty()) {
                            System.out.println("\nNo hay estudiantes registrados para buscar.");
                            break;
                        }
                        
                        System.out.print("\nIngrese la matricula del estudiante que desea consultar: ");
                        String matConsultar = scanner.nextLine();
                        
                        Estudiante estEncontrado = null;
                        for (Estudiante e : listaEstudiantes) {
                            if (e.getMatricula().equalsIgnoreCase(matConsultar)) {
                                estEncontrado = e;
                                break;
                            }
                        }
                        
                        if (estEncontrado != null) {
                            estEncontrado.mostrarDetalles();
                        } else {
                            System.out.println("No se encontro ningun estudiante con la matricula '" + matConsultar + "'.");
                        }
                    }
                        
                    case 4 -> System.out.println("\nSaliendo del sistema. Hasta luego!");
                        
                    default -> System.out.println("\nOpcion invalida. Intente de nuevo.");
                }
            } while (opcion != 4);
        }
    }
}