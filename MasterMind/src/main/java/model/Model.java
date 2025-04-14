package model;

import java.util.Random;

/**
 *
 * @author silvia
 */
public class Model {
     private String randomNumber;
    private static final int columns = 4;
    private static final int filas = 10;

    public Model() {
        this.randomNumber = createRandomNumber(columns);
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
        return columns;
    } 

    
}
