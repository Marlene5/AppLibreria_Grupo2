package com.mycompany.applibreria;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorLibros {

    public static boolean eliminarLibro(int isbn, ArrayList<Libro> libros) {
        Iterator<Libro> iterator = libros.iterator();

        while (iterator.hasNext()) {
            Libro libro = iterator.next();
            if (libro.getISBN() == isbn) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean existeISBN(int isbn, ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getISBN() == isbn) {
                return true;
            }
        }
        return false;
    }
    // Aquí también podrías agregar métodos como:
    // - agregarLibro
    // - editarLibro
    // - buscarLibro
}
