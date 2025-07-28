/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */

public class Docente extends Usuario {
    private String profesion;
    private boolean esMagister;
    private boolean esDoctor;

    public Docente(String Run, String nombreCompleto, String genero, String profesion, boolean esMagister, boolean esDoctor){
        super(Run, nombreCompleto, genero, 0);
        this.profesion = profesion;
        this.esMagister = esMagister;
        this.esDoctor = esDoctor;
    }

    public String getProfesion() {
        return profesion;
    }

    public boolean isEsMagister() {
        return esMagister;
    }

    public boolean isEsDoctor() {
        return esDoctor;
    }

    public void setProfesion(String Profesion) {
        this.profesion = Profesion;
    }

    public void setEsMagister(boolean esMagister) {
        this.esMagister = esMagister;
    }

    public void setEsDoctor(boolean esDoctor) {
        this.esDoctor = esDoctor;
    }
}

