package com.mycompany.applibreria;

public class ValidadorRUN {

    public static boolean validarRUN(String run) {

        run = run.replace(".", "").replace("-", "").toUpperCase();

        if (!run.matches("\\d{7,8}[0-9K]")) {
            return false;
        }

        String cuerpo = run.substring(0, run.length() -1);
        char dvIngresado = run.charAt(run.length()-1);

        int suma = 0;
        int multiplicador = 2;

        for (int i = cuerpo.length() - 1; i>=0;i--){
            suma += Character.getNumericValue(cuerpo.charAt(i))*multiplicador;
            multiplicador = (multiplicador ==7) ? 2 : multiplicador + 1;

        }

        int resto = 11- (suma%11);
        char dvCalculado;

        if (resto ==11){
            dvCalculado= '0';
        } else if (resto==10){
            dvCalculado = 'K';
        } else{
            dvCalculado = (char) ('0'+ resto);
        }
        return dvCalculado == dvIngresado;
    }
}
