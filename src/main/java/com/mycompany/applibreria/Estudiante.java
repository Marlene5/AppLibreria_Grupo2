/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.applibreria;

/**
 *
 * @author Tom
 */
public class Estudiante extends Usuario {
    private String carrera;

        public Estudiante(String Run, String nombreCompleto, String genero, String carrera){
            super(Run, nombreCompleto, genero,0);
            this.carrera = carrera;
    }



}

