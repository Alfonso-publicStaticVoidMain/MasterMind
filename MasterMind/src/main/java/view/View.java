package view;

import controller.Controller;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {
    
     //private JScrollPane scrollPane;
    private JTextArea userTriesText;
    private JButton submitButton;
    private JTextField previousTriesText;
    private JTextField triesLeftNumbersText;
    //TODO texto final de Acertaste! para colocar nmeros y que se vea en verde por ejemplo
    
    private Controller controller;
    
    
    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        
        userTriesText= new JTextArea( );
        submitButton= new JButton("Submit");
        submitButton.setActionCommand("submit");
        previousTriesText= new JTextField(" ");//todo meter dentro de un scroll
        previousTriesText.setEditable(false);//intentos previos
        triesLeftNumbersText= new JTextField("intentos restantes: 10");
        triesLeftNumbersText.setEditable(false);
        
        add(userTriesText);
        add(submitButton);
        add(previousTriesText);
        add(triesLeftNumbersText);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
   
    }
    
    public void setController(Controller controller){
        this.controller=controller;
        submitButton.addActionListener((ActionListener) controller);
    }

    public String getUserTry(){
        return userTriesText.getText();
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
/*
todo:    para que vaya pasando de un textfield a otro 
   private JTextField[] textFields;
for (int i = 0; i < numberOfFields; i++) {
            textFields[i] = new JTextField(3); // Ancho para un dígito
            textFields[i].setHorizontalAlignment(JTextField.CENTER);
            add(textFields[i]);

            final int currentIndex = i;
            textFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c) || textFields[currentIndex].getText().length() >= 1) {
                        e.consume(); // Ignora caracteres no numéricos o si ya hay un dígito
                    }
                }


// Establecer el foco en el primer campo al iniciar
        if (numberOfFields > 0) {
            textFields[0].requestFocusInWindow();
        }
    }
//para ir cambiando de textfield al siguiente
    @Override
                public void keyReleased(KeyEvent e) {
                    if (textFields[currentIndex].getText().length() == 1) {
                        if (currentIndex < numberOfFields - 1) {
                            textFields[currentIndex + 1].requestFocusInWindow();
                        } else {
                            // Opcional: realizar alguna acción cuando se llena el último campo
                            System.out.println("Todos los campos llenos.");
                        }
                    }
                }
            });
        }

*/