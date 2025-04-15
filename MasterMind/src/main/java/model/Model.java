package model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author silvia
 */
public class Model {
     private String randomNumber;
    private static final int columns = 4;
    private static final int filas = 10;
    //guardar el historial de intentos:
    private ArrayList<String> attemptsHistory;

    public Model() {
        this.randomNumber = createRandomNumber(columns);
        System.out.println(randomNumber);
        this.attemptsHistory = new ArrayList<>();
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

//    public int hitsAnyWhere(String introducedString) {
//        int contador = 0;
//        for (int i = 0; i < introducedString.length(); i++) {
//
//            for (int j = 0; j < this.randomNumber.length(); j++) {
//                if (introducedString.charAt(i) == this.randomNumber.charAt(j)) {
//                    contador++;
//                    break;
//                }
//            }
//
//        }
//        return contador;
//    }

    public int hitsAnyWhere(String introducedString) {
    int cont = 0;
    String secret = this.randomNumber;
    boolean[] secretUse = new boolean[secret.length()]; // dig igual o no

    for (int i = 0; i < introducedString.length(); i++) {
        char digitIntroduced = introducedString.charAt(i);

        // si hay un acierto nomisma posición 
        if (secret.charAt(i) == digitIntroduced) {
            continue; // Si es acierto namisma posición, lo ignoramos 
        }

        // Buscamos en una posición diferente
        for (int j = 0; j < secret.length(); j++) {
            if (!secretUse[j] && secret.charAt(j) == digitIntroduced && i != j) {
                cont++;
                secretUse[j] = true; 
                break; // Pasamos al siguiente dgito 
            }
        }
    }
    return cont;
}
     
     
     
     
    public int getLength() {
        return columns;
    } 

     //  reiniciar el juego (generarnd nuevo num secreto)
    public void resetGame() {
        this.randomNumber = createRandomNumber(columns);
        // this.attemptsHistory.clear(); // Si implementamos el historial
    }
    
    
    
     //guardar el historial de intentos
     public void addAttempt(String attempt) {
         this.attemptsHistory.add(attempt);
     }
          //obter el historial de intentos

    
     public ArrayList<String> getAttemptsHistory() {
         return attemptsHistory;
     }
    //todo q termine al acertar
     //todo q los intentos terminen en 0
}
