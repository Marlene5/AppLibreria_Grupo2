/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

public class Libro {
    private int ISBN;
    private String titulo;
    private String Autor;
    private int Cantidad_biblio;
    private int Cantidad_disponible;
    private String imagen;

    public Libro(int ISBN, String titulo, String autor, int cantidad_biblio, int cantidad_disponible, String imagen) {
        if (cantidad_biblio <= 0) {
            throw new IllegalArgumentException("La cantidad total de libros debe ser mayor a cero.");
        }

        if (cantidad_disponible <= 0 || cantidad_disponible > cantidad_biblio) {
            throw new IllegalArgumentException("La cantidad disponible debe ser mayor que cero y menor o igual a la cantidad total.");
        }

        this.ISBN = ISBN;
        this.titulo = titulo;
        this.Autor = autor;
        this.Cantidad_biblio = cantidad_biblio;
        this.Cantidad_disponible = cantidad_disponible;
        this.imagen = imagen;
    }


    public int getISBN() {
        return ISBN;
    }


    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return Autor;
    }

    public int getCantidad_biblio() {
        return Cantidad_biblio;
    }

    public int getCantidad_disponible() {
        return Cantidad_disponible;
    }

    public String getImagen() {
        return imagen;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public void setCantidad_biblio(int cantidad_biblio) {
        Cantidad_biblio = cantidad_biblio;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        Cantidad_disponible = cantidad_disponible;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
     public void setTitulo(String titulo) {
         this.titulo = titulo;
     }
   // public void setISBN(int ISBN) {
  //      this.ISBN = ISBN;
  //  }

   // public String getTitulo() {
     //   return titulo;
   // }


  //  }
}
