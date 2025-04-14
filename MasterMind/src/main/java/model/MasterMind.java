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

    private int length;
    private String randomNumber;
    

    public MasterMind(int longitud) {

        this.length = longitud;
        this.randomNumber = createRandomNumber(longitud);
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    static public String createRandomNumber(int longitud) {
        StringBuilder chain = new StringBuilder();
        StringBuilder numbersOfChain = new StringBuilder("123456789");
        int numero;
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            numero = random.nextInt(0, numbersOfChain.length());
            chain.append(numbersOfChain.toString().charAt(numero));
            numbersOfChain.deleteCharAt(numero);
        }
        return chain.toString();
    }
    

    public int hitsSamePlace(String introducedString) {
        int contador = 0;
        for (int i = 0; i < introducedString.length(); i++) {
            if (this.randomNumber.charAt(i) == introducedString.charAt(i)) {
                contador++;
            }
        }
        return contador;
    }

    public int hitsAnyWhere(String introducedString) {
        int contador = 0;
        for (int i = 0; i < introducedString.length(); i++) {

            for (int j = 0; j < this.randomNumber.length(); j++) {
                if (introducedString.charAt(i) == this.randomNumber.charAt(j)) {
                    contador++;
                    break;
                }
            }

        }
        return contador;
    }

    public int getLength() {
        return length;
    } 

}


