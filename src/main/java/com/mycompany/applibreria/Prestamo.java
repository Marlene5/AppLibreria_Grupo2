/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.time.LocalDate;

/**
 *
 * @author Tom
 */
public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private GregorianCalendar fecha;
    private Devolucion devolucion;
    private GregorianCalendar fechaDevolucionEsperada;
    private int diasPrestamo;


    // DEBE COMPLETAR ESTE CONSTRUCTOR
    public Prestamo(Usuario usuario, Libro libro, int diasPrestamo) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha = new GregorianCalendar();
        this.devolucion = null;
        this.diasPrestamo = diasPrestamo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario diasPrestamo() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @param libro the libro to set
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * @return the fecha
     */
    public GregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the devolucion
     */
    public Devolucion getDevolucion() {
        return devolucion;
    }

    /**
     * @param devolucion the devolucion to set
     */
    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }
    public GregorianCalendar getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public void setFechaDevolucionEsperada(GregorianCalendar fechaDevolucionEsperada) {
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
    }
    public String obtenerTipoDeUsuario() {
        if (getUsuario() instanceof Docente) {
            return "Docente";
        }

        return "Estudiante";
    }

    // SOLICITO LOS PARÁMETROS DE ENTRADA DE LA DEVOLUCIÓN
    public void asignarDevolucion() {

        Devolucion devolucion = new Devolucion();

        // ASINGO LA DEVOLUCIÓN RESPETANDO LA RELACIÓN DE COMPOSICIÓN
        // DEBIDO A QUE DEVOLUCIÓN SE INSTANCIÓ DENTRO DEL OBJETO Y NO POR FUERA
        setDevolucion(devolucion);

        // TENGO QUE HABILITAR AL USUARIO
        usuario.setisbnPrestado(0);

        // TENGO QUE AUMENTAR EL STOCK DISPONIBLE Y DISMINUIR EL STOCK ASIGNADO
        libro.setCantidad_disponible(libro.getCantidad_disponible() + 1);

        // TENGO QUE COBRAR MULTA SI ES QUE CORRESPONDE (PENDIENTE DE IMPLEMENTAR LÓGICA)
        GregorianCalendar hoy = new GregorianCalendar();
        devolucion.setFechaDevolucion(hoy);

// calcular días de retraso
        long tiempoPrestamoMs = this.fecha.getTimeInMillis();
        long tiempoDevolucionMs = hoy.getTimeInMillis();
        int diasTranscurridos = (int) ((tiempoDevolucionMs - tiempoPrestamoMs) / (1000 * 60 * 60 * 24));

// este valor debería guardarse como atributo `diasPrestamo` o `fechaLimite`
        int diasPrestamo = this.diasPrestamo; // debe ser el valor que se ingresó al crear el préstamo
        int diasRetraso = diasTranscurridos - diasPrestamo;

        if (diasRetraso > 0) {
            devolucion.setDiasRetraso(diasRetraso);
            devolucion.setMulta(diasRetraso * 1000);
        } else {
            devolucion.setDiasRetraso(0);
            devolucion.setMulta(0);
        }

    }

    public static Prestamo ingresarPrestamo(int ISBN, String RUN, ArrayList<Libro> libros, ArrayList<Usuario> usuarios) {
        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MeTODO BUSCARLIBRO
        Libro libro = buscarLibro(ISBN, libros);

        // SI EL LIBRO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        if (libro == null) {
            throw new IllegalArgumentException("El libro a buscar no existe.");
        }

        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MeTODO BUSCARUSUARIO
        Usuario usuario = buscarUsuario(RUN, usuarios);

        // SI EL USUARIO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario a buscar no existe.");
        }

        // EN ESTE PUNTO, YA SABEMOS QUE EL USUARIO Y EL LIBRO YA EXISTEN
        // AHORA DEBEMOS REALIZAR LAS VALIDACIONES

        // AQUÍ VALIDAMOS QUE EL LIBRO TENGA COMO MÍNIMO UN EJEMPLAR //
        if (libro.getCantidad_disponible() <= 0) {
            throw new IllegalArgumentException("El libro no tiene ejemplares disponibles.");
        }

        // AQUÍ VALIDAMOS QUE EL USUARIO DEBA ESTAR HABILITADO PARA EL PRÉSTAMO //
        if (usuario.getisbnPrestado() != 0) {
            throw new IllegalArgumentException("El usuario ya tiene un libro prestado.");
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese cantidad de días de préstamo: ");
        int dias = Integer.parseInt(sc.nextLine());

        int limite = (usuario instanceof Docente) ? 20 : 10;
        if (dias > limite) {
            throw new IllegalArgumentException("El usuario excede el límite de días de préstamo permitido.");
        }



        // UNAS VEZ GENERADA TODAS LAS VALIDACIONES

        // REGISTRAMOS EL ISBN COMO PRÉSTAMO EN EL USUARIO
        usuario.setisbnPrestado(libro.getISBN());

        // REDUCIMOS LA CANTIDAD DISPONIBLE DEL LIBRO
        libro.setCantidad_disponible(libro.getCantidad_disponible() - 1);

        // GENERAMOS UNA INSTANCIA DE PRÉSTAMO
        Prestamo prestamo = new Prestamo(usuario, libro, dias);
        GregorianCalendar fechaDevolucion = new GregorianCalendar();
        fechaDevolucion.add(GregorianCalendar.DAY_OF_MONTH, dias);
        prestamo.setFechaDevolucionEsperada(fechaDevolucion);


        // RETORNAMOS EL PRÉSTAMO VALIDADO
        return prestamo;
    }

    public static void ingresarDevolucion(int ISBN, String RUN, ArrayList<Prestamo> prestamos) {
        // EN BASE A LA GUÍA, DEBEMOS VALIDAR QUE EXISTA EL LIBRO Y EL USUARIO

        // LUEGO DEBEMOS VALIDAR QUE EL USUARIO A BUSCAR Y EL ISBN EXISTAN
        // ASIGNO UNA VARIABLE CON VALOR A LO QUE RETORNE EL MeTODO BUSCAR PRESTAMO
        Prestamo prestamo = buscarPrestamo(ISBN, RUN, prestamos);

        // SI EL PRÉSTAMO ES NULO, ES PORQUE NO LO HE ENCONTRADO
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo a buscar no existe.");
        }

        // UNA VEZ GENERADAS TODAS LAS VALIDACIONES, EJECUTAMOS EL MeTODO ASIGNAR DEVOLUCIÓN
        prestamo.asignarDevolucion();
    }

    public static Libro buscarLibro(int ISBN, ArrayList<Libro> libros) {
        // BUSCO EL LIBRO EN EL ARREGLO DE LIBROS
        for (int i = 0; i < libros.size(); i++) {
            // VOY OBTENIENDO CADA LIBRO EN EL ARREGLO DE LIBROS
            Libro libro = libros.get(i);

            // PREGUNTO SI EL ISBN DEL LIBRO ES IGUAL AL LIBRO QUE BUSCO
            if (libro.getISBN() == ISBN) {
                // SI LO ENCUENTRO, LO RETORNO
                return libro;
            }
        }

        // SI NO LO ENCUENTRO, RETORNO UN NULL
        return null;
    }

    public static Usuario buscarUsuario(String RUN, ArrayList<Usuario> usuarios) {
        // BUSCO EL LIBRO EN EL ARREGLO DE USUARIOS
        for (int i = 0; i < usuarios.size(); i++) {
            // VOY OBTENIENDO CADA USUARIO EN EL ARREGLO DE USUARIOS
            Usuario usuario = usuarios.get(i);

            // PREGUNTO SI EL RUT DEL USUARIO ES IGUAL AL RUN QUE BUSCO
            if (usuario.getRUN().equals(RUN)) {
                // SI LO ENCUENTRO, LO RETORNO
                return usuario;
            }
        }

        // SI NO LO ENCUENTRO, RETORNO UN NULL
        return null;
    }

    public static Prestamo buscarPrestamo(int ISBN, String RUN, ArrayList<Prestamo> prestamos) {
        for (Prestamo prestamo : prestamos) {
            if (
                    prestamo.getUsuario().getRUN().equals(RUN) &&
                            prestamo.getLibro().getISBN() == ISBN &&
                            prestamo.getDevolucion() == null //solo prestamos activos
            ) {
                return prestamo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        // GENERAMOS UN ESTADO BASE
        String estadoBase = "Prestamo: \n" +
                "ISBN: " + getLibro().getISBN() + "\n" +
                "RUN: " + getUsuario().getRUN() + "\n" +
                "Arrendado por: " + obtenerTipoDeUsuario() + "\n" +
                "Estado: ";

        // LO MODIFICAMOS EN BASE A LA DEVOLUCIÓN
        if (getDevolucion() == null) {
            estadoBase += "En préstamo.";
        } else {
            estadoBase += "Devuelto.";
        }

        return estadoBase;
    }
    public String generarTarjeta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Convertimos fecha de préstamo (GregorianCalendar → LocalDate)
        LocalDate fechaInicioDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String fechaInicio = formatter.format(fechaInicioDate);

        // Convertimos fecha de devolución esperada (ya guardada) a LocalDate
        LocalDate fechaDevolucionDate = fechaDevolucionEsperada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String fechaDevolucion = formatter.format(fechaDevolucionDate);

        String tarjeta = "\n======= TARJETA DE PRÉSTAMO =======\n" +
                "Título del libro : " + libro.getTitulo() + "\n" +
                "ISBN             : " + libro.getISBN() + "\n" +
                "RUN Usuario      : " + usuario.getRUN() + "\n" +
                "Nombre           : " + usuario.getNombreCompleto() + "\n" +
                "Tipo Usuario     : " + (usuario instanceof Docente ? "Docente" : "Estudiante") + "\n" +
                "Fecha Préstamo   : " + fechaInicio + "\n" +
                "Fecha Devolución : " + fechaDevolucion + "\n" +
                "===================================\n";
        return tarjeta;
    }


    public void mostrarMulta() {
        if (devolucion != null && devolucion.getDiasRetraso() > 0) {
            System.out.println("El libro fue devuelto con " + devolucion.getDiasRetraso() + " día(s) de retraso.");
            System.out.println("Multa aplicada: $" + devolucion.getMulta());
        }
    }



}

