package gestor_estudiantes;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private final String nombre;
    private final int edad;
    private final String matricula;
    private final List<Double> notas;
    private final int TOTAL_CURSOS = 5;

    // Constructor
    public Estudiante(String nombre, int edad, String matricula) {
        this.nombre = nombre;
        this.edad = edad;
        this.matricula = matricula;
        this.notas = new ArrayList<>();
    }

    // Método para agregar notas validando el límite
    public boolean agregarNota(double nota) {
        if (notas.size() >= TOTAL_CURSOS) {
            System.out.println("Error: Este estudiante ya tiene registradas las notas de los " + TOTAL_CURSOS + " cursos.");
            return false;
        }
        
        if (nota >= 0.0 && nota <= 100.0) {
            notas.add(nota);
            System.out.println("Nota agregada con exito. (" + notas.size() + "/" + TOTAL_CURSOS + ")");
            return true;
        } else {
            System.out.println("Error: La nota debe estar entre 0 y 100.");
            return false;
        }
    }

    // Método para calcular el promedio de los 5 cursos
    public double calcularPromedio() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    // Método modificado para mostrar solo la informacion basica y el resultado del promedio
    public void mostrarDetalles() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("            BOLETA DE CALIFICACIONES              ");
        System.out.println("--------------------------------------------------");
        System.out.println("Matricula      : " + matricula);
        System.out.println("Nombre         : " + nombre);
        System.out.println("Edad           : " + edad + " anos");
        System.out.printf("Promedio Final : %.2f\n", calcularPromedio());
        System.out.println("--------------------------------------------------");
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean tieneNotasCompletas() {
        return notas.size() == TOTAL_CURSOS;
    }
}
