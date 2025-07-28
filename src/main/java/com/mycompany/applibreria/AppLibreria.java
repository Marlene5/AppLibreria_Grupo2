/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.applibreria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.mycompany.applibreria.GestorLibros;


/**
 *
 *
 */
public class AppLibreria {

    public static void main(String[] args) {

        /*
        Tipo de dato DateTime
         */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date and Time: " + dtf.format(now));


       // GENERAMOS DATOS DE USUARIOS BASE
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>(
                Arrays.asList(
                        new Docente("1-1","andres", "H","veterinario", false, true),
                        new Docente("1-2","manuel", "M","dentista", false, false),
                        new Docente("1-3","rodrigo", "H","comercial",  false, true),
                        new Estudiante("2-1", "Camilo","H", "informatica"),
                        new Estudiante("2-2", "Andrea","M","veterinaria"),
                        new Estudiante("2-3","Camila","M","comercial")
               )
        );
        Scanner input = new Scanner(System.in);
       // ArrayList<Usuario> usuarios = new ArrayList<>();


        // GENERAMOS DATOS DE LIBROS BASE
        ArrayList<Libro> libros = new ArrayList<Libro>(
                Arrays.asList(
                        new Libro(1,"titulo1","autor1",1,1,"https:image1"),
                        new Libro(2,"titulo2","autor2",3,3,"https:image2"),
                        new Libro(3,"titulo3","autor3",11,10,"https:image3"),
                        new Libro(4,"titulo4","autor4",18,9,"https:image4"),
                        new Libro(5,"titulo5","autor5",12,2,"https:image5")
                )
        );


        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        // UN LISTA DE DEVOLUCIONES
/*
        Prestamo prestamoCaducado = new Prestamo(usuarios.get(4),libros.get(4),2);
        GregorianCalendar fechaDevolucion = new GregorianCalendar();
        fechaDevolucion.add(GregorianCalendar.DAY_OF_MONTH, -5);
        System.out.println(fechaDevolucion);
        System.out.println(prestamoCaducado);
        prestamoCaducado.setFechaDevolucionEsperada(fechaDevolucion);

        prestamos.add(prestamoCaducado);
*/
        // Simulamos que el préstamo fue hace 5 días y debía devolverlo hace 3 días
        Prestamo prestamoCaducado = new Prestamo(usuarios.get(4), libros.get(4), 2);

// Ajustamos la fecha del préstamo a hace 5 días
        GregorianCalendar fechaPrestamo = new GregorianCalendar();
        fechaPrestamo.add(GregorianCalendar.DAY_OF_MONTH, -5);
        prestamoCaducado.setFecha(fechaPrestamo);

// Establecemos que debía devolverlo hace 3 días
        GregorianCalendar fechaDevolucionEsperada = new GregorianCalendar();
        fechaDevolucionEsperada.add(GregorianCalendar.DAY_OF_MONTH, -3);
        prestamoCaducado.setFechaDevolucionEsperada(fechaDevolucionEsperada);

        prestamos.add(prestamoCaducado);

// Simulamos la devolución actual (hoy)
        Prestamo.ingresarDevolucion(libros.get(4).getISBN(), usuarios.get(4).getRUN(), prestamos);

// Verificamos multa
        prestamoCaducado.mostrarMulta();

        // GENERAMOS UN PRÉSTAMO
       // Prestamo prestamo = Prestamo.ingresarPrestamo(1, "1-2", libros, usuarios);
        // AGREGAMOS EL PRÉTAMO AL ARREGLO DE PRÉSTAMOS
        //prestamos.add(prestamo);
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
       // System.out.println(prestamo.toString());
        // GENERAMOS UNA DEVOLUCION
        //Prestamo.ingresarDevolucion(1, "1-2", prestamos);
       // System.out.println("-----------------------------------------------------------");
        // IMPRIMIMOS EL ESTADO ACTUAL DEL PRÉSTAMO
        //System.out.println(prestamo.toString());
/*
        System.out.println("RUN en préstamo: " + prestamoCaducado.getUsuario().getRUN());
        System.out.println("ISBN en préstamo: " + prestamoCaducado.getLibro().getISBN());
        System.out.println("Tiene devolución?: " + (prestamoCaducado.getDevolucion() != null));

        System.out.println("RUN buscado: " + usuarios.get(4).getRUN());
        System.out.println("ISBN buscado: " + libros.get(4).getISBN());
*/
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n------ MENÚ ------");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Ver todos los préstamos");
            System.out.println("4. Eliminar usuario por rut");
            System.out.println("5. Eliminar libro por ISBN");
            System.out.println("6. Agregar usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese ISBN del libro: ");
                        int isbnPrestamo = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese RUN del usuario: ");
                        String runPrestamo = scanner.nextLine();
                        try {
                            Prestamo nuevoPrestamo = Prestamo.ingresarPrestamo(isbnPrestamo, runPrestamo, libros, usuarios);
                            prestamos.add(nuevoPrestamo);
                            System.out.println("Préstamo registrado con éxito.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Ingrese ISBN del libro para devolución: ");
                        int isbnDevolucion = Integer.parseInt(scanner.nextLine());
                        System.out.print("Ingrese RUN del usuario: ");
                        String runDevolucion = scanner.nextLine();
                        try {
                            Prestamo.ingresarDevolucion(isbnDevolucion, runDevolucion, prestamos);
                            System.out.println("Devolución registrada con éxito.");

                            // Mostrar detalles de multa si corresponde
                            Prestamo prestamoDevuelto = Prestamo.buscarPrestamo(isbnDevolucion, runDevolucion, prestamos);
                            if (prestamoDevuelto != null) {
                                prestamoDevuelto.mostrarMulta();
                            }

                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("\nLista de préstamos:");
                        for (Prestamo p : prestamos) {
                            System.out.println(p.toString());
                            System.out.println("--------------------------");
                        }
                        break;
                    case 4:
                        System.out.print("Ingrese RUN del usuario a eliminar: ");
                        String runEliminar = scanner.nextLine();
                        boolean eliminado = GestorUsuarios.eliminarUsuario(runEliminar, usuarios);

                        if (eliminado) {
                            System.out.println("Usuario eliminado exitosamente.");
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                        break;

                    case 5:
                        System.out.print("Ingrese ISBN del libro a eliminar: ");
                        int isbnEliminar;
                        try {
                            isbnEliminar = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("ISBN inválido.");
                            break;
                        }

                        boolean libroEliminado = GestorLibros.eliminarLibro(isbnEliminar, libros);
                        if (libroEliminado) {
                            System.out.println("Libro eliminado exitosamente.");
                        } else {
                            System.out.println("No se encontró un libro con ese ISBN.");
                        }
                        break;

                    case 6:
                        GestorUsuarios.agregarUsuario(usuarios, scanner);
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }

        } while (opcion != 0);
    }

}

