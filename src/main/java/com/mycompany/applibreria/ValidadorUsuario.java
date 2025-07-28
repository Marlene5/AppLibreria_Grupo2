package com.mycompany.applibreria;

public class ValidadorUsuario {
    public static boolean esGeneroValido(String genero) {
        if (genero == null) return false;
        genero = genero.trim().toUpperCase();
        return genero.equals("M") || genero.equals(("F"));
    }
}
