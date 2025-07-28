/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public abstract class Usuario {
    private String RUN;
    private String nombreCompleto;
    private String genero;
    private int isbnPrestado = 0;



    public Usuario(String RUN, String nombreCompleto, String genero, int isbnPrestado) {
        this.RUN = RUN;
        this.nombreCompleto = nombreCompleto;
        this.genero = genero;
        this.isbnPrestado = isbnPrestado;
    }





    public String getGenero() {
        return genero;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getisbnPrestado() {
        return isbnPrestado;
    }


    /**
     * @return the RUN
     */


    public String getRUN() {
        return RUN;
    }

    /**
     * @param RUN the RUN to set
     */
    public void setRUN(String RUN) {
        this.RUN = RUN;
    }

    public void setnombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setgenero(String genero) {
        genero = genero;
    }
    public void setisbnPrestado(int isbnPrestado) {
        isbnPrestado = isbnPrestado;
    }

}
