/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

/**
 *
 * @author silvia
 */
public class MasterMind {

    private int longitud;
    private String numeroAleatorio;
    // private String cadenaIntroducida;
    //  private int numeroAciertosMismoLugar;
    //  private int numeroAciertosDiferenteLugar;

    public MasterMind(int longitud) {

        this.longitud = longitud;
        this.numeroAleatorio = numeroAleatorio(longitud);

        //this.numeroAciertosMismoLugar = aciertosMismoLugar(cadenaIntroducida, this.numeroAleatorio);
        //this.numeroAciertosDiferenteLugar = aciertosCualquierLugar(cadenaIntroducida, this.numeroAleatorio);
    }

    public String getNumeroAleatorio() {
        return numeroAleatorio;
    }

    static public String numeroAleatorio(int longitud) {
        StringBuilder cadena = new StringBuilder();
        StringBuilder numerosCadena = new StringBuilder("123456789");
        int numero;
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            numero = random.nextInt(0, numerosCadena.length());
            cadena.append(numerosCadena.toString().charAt(numero));
            numerosCadena.deleteCharAt(numero);
        }
        return cadena.toString();
    }
    

    public int aciertosMismoLugar(String cadenaIntroducida) {
        int contador = 0;
        for (int i = 0; i < cadenaIntroducida.length(); i++) {
            if (this.numeroAleatorio.charAt(i) == cadenaIntroducida.charAt(i)) {
                contador++;
            }
        }
        return contador;
    }

    public int aciertosCualquierLugar(String cadenaIntroducida) {
        int contador = 0;
        for (int i = 0; i < cadenaIntroducida.length(); i++) {

            for (int j = 0; j < this.numeroAleatorio.length(); j++) {
                if (cadenaIntroducida.charAt(i) == this.numeroAleatorio.charAt(j)) {
                    contador++;
                    break;
                }
            }

        }
        return contador;
    }

    public int getLongitud() {
        return longitud;
    }
    
    

}

class TamañoIncorrectoException extends Exception {

    @Override
    public String getMessage() {
        return "Tamaño incorrecto";
    }

}

class ValoresInvalidosException extends Exception {

    @Override
    public String getMessage() {
        return "Valores invalidos";
    }

}

class ValoresRepetidosException extends Exception {

    @Override
    public String getMessage() {
        return "Valores repetidos";
    }

}
