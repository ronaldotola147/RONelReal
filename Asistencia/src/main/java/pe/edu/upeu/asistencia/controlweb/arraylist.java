
package com.mycompany.registrodeestudiantes;

import java.util.Scanner;
import java.util.ArrayList;

public class Registrodeestudiantes {

    public static void main(String[] args) {
        Scanner oscar=new Scanner(System.in);
        ArrayList<String> estudiantes=new ArrayList<>();
        System.out.println("BIENVENIDOS AL SISTEMA ACADEMICO DE LA UPEU");
        System.out.println("MENU DE OPCIONES");
        System.out.println("1.Crear estudiantes");
        System.out.println("2.Listar estudiantes");
        System.out.println("3.Actualizar estudiantes");
        System.out.println("4.Eliminar estudiantes");
        System.out.println("5.salir");
        System.out.println("Elije una opcion");
        int opcion=oscar.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ingresa el nombre del estudiante");
                String nombre=oscar.next();
                estudiantes.add(nombre);
                System.out.println("Estudiante registrado con exito!!!");
                break;
            default:
                throw new AssertionError();
        }

    }
}