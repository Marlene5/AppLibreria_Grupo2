package com.mycompany.applibreria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GestorUsuarios {

    public static boolean eliminarUsuario(String run, ArrayList<Usuario> usuarios) {
        Iterator<Usuario> iterator = usuarios.iterator();

        while (iterator.hasNext()) {
            Usuario u = iterator.next();
            if (u.getRUN().equals(run)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static void agregarUsuario(ArrayList<Usuario> usuarios, Scanner scanner) {
        System.out.println("\nIngrese tipo de usuario (docente o estudiante): ");
        String tipo = scanner.nextLine().trim().toLowerCase();

        System.out.print("Ingrese RUN: ");
        String run = scanner.nextLine();

        if (!ValidadorRUN.validarRUN(run)) {
            System.out.println("RUN inválido. Verifique el formato y dígito verificador.");
            return;
        }

        for (Usuario u : usuarios) {
            if (u.getRUN().equals(run)) {
                System.out.println("Ya existe un usuario con ese RUN.");
                return;
            }
        }

        System.out.print("Ingrese nombre completo: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese género (M/F): ");
        String genero = scanner.nextLine().toUpperCase();

        while (!ValidadorUsuario.esGeneroValido(genero)) {
            System.out.println("Género inválido. Intente nuevamente (M o F): ");
            genero = scanner.nextLine().toUpperCase();
        }

        if (tipo.equals("docente")) {
            System.out.print("Ingrese profesión: ");
            String profesion = scanner.nextLine();

            System.out.print("¿Tiene posgrado? (true/false): ");
            boolean tienePosgrado = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("¿Está activo? (true/false): ");
            boolean activo = Boolean.parseBoolean(scanner.nextLine());

            usuarios.add(new Docente(run, nombre, genero, profesion, tienePosgrado, activo));
        } else if (tipo.equals("estudiante")) {
            System.out.print("Ingrese carrera: ");
            String carrera = scanner.nextLine();

            usuarios.add(new Estudiante(run, nombre, genero, carrera));
        } else {
            System.out.println("Tipo de usuario no válido. Se omitirá esta entrada.");
        }

        System.out.println("Usuario agregado exitosamente.");
    }
}


    // FALTA Agregar métodos
    // - buscarUsuario

    // - editarUsuario

