/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.util.Scanner;
import model.MasterMind;

/**
 *
 * @author silvia
 */
public class AntiguoMainMastermind {

  public static MasterMind mastermind;

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //int lengthString;
        String introducedString = "0000";
       // System.out.println("Indica la longitud de la cadena: ");
       // lengthString = Integer.parseInt(scanner.nextLine());
     //  lengthString=4;

        mastermind = new MasterMind(4);

        for (int i = 1; i <= 10; i++) {
            boolean esCorrecto = false;
            do {
                try {
                    introducedString = validarCadena();
                    esCorrecto = true;

                } catch (IncorrectLengthException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (InvalidValuesException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (RepeatedValuesException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            } while (!esCorrecto);

        }
    }

    static public String validarCadena() throws IncorrectLengthException, InvalidValuesException, RepeatedValuesException {
        String introducedString;

        System.out.println("Escribe un numero de " + mastermind.getLength()+ " de longitud");
        introducedString = scanner.nextLine();
        if (introducedString.length() != mastermind.getLength()) {
            throw new IncorrectLengthException();
        }
        if (!areDigits(introducedString)) {
            throw new InvalidValuesException();
        }
        if (!ArentRepeat(introducedString)) {
            throw new RepeatedValuesException();
        }
        return introducedString;
        
        //TODO cada vez que pregunte por el numnero llamar a la funcion para saber cuantos numeros hay correctos
        
        
    }

    static public boolean areDigits(String introducedString) {
        for (int i = 0; i < introducedString.length(); i++) {
            if (!Character.isDigit(introducedString.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static public boolean ArentRepeat(String introducedString) {
        for (int i = 0; i < introducedString.length() - 1; i++) {
            for (int j = i + 1; j < introducedString.length(); j++) {
                if (introducedString.charAt(i) == introducedString.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}

class IncorrectLengthException extends Exception {

    @Override
    public String getMessage() {
        return "Tamaño incorrecto";
    }

}

class InvalidValuesException extends Exception {

    @Override
    public String getMessage() {
        return "Valores invalidos";
    }

}

class RepeatedValuesException extends Exception {

    @Override
    public String getMessage() {
        return "Valores repetidos";
    }

}