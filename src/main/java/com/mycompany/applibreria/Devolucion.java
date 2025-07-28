/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

import java.util.GregorianCalendar;

/**
 *
 * @author Tom
 */
public class Devolucion {
    private GregorianCalendar fechaDevolucion;
    private int diasRetraso;
    private int multa;


    public int getDiasRetraso() {
        return diasRetraso;
    }

    public int getMulta() {
        return multa;
    }

    public void setFechaDevolucion(GregorianCalendar fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setDiasRetraso(int diasRetraso) {
        this.diasRetraso = diasRetraso;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }
}


