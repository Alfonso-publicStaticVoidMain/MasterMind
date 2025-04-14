package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author silvia
 */
public class View extends javax.swing.JFrame {
    //array de 4 textfield para que el usuario escriba los 4 digitos
    private JTextField[] textFields; 
    // Array para que se muestreslos JTextArea de los intentos del usuario
    private JTextArea[][] userTriesTexts; 
    //Resto de elementos
    private JButton submitButton;
    private JTextField previousTriesText;
    private JTextField triesLeftNumbersText;
    //TODO texto final de Acertaste! para colocar nmeros y que se vea en verde por ejemplo

    private Controller controller;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Panel para el usuario
        textFields= new JTextField[4];
        JPanel fieldsPanel= new JPanel(new FlowLayout());
        for(int i=0;i<textFields.length;i++){
            textFields[i]= new JTextField(3);//ancho para un digito
            textFields[i].setHorizontalAlignment(JTextField.CENTER);//horiz
            fieldsPanel.add(textFields[i]);//añades al panel
        }
        
        
        //Panel para intentos de usuario:
        userTriesTexts = new JTextArea[10][4];
        JPanel triesPanel = new JPanel(new GridLayout(10, 4));
        
      for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                userTriesTexts[i][j] = new JTextArea(10, 4); // 1 fila, 3 columnas de ancho
                userTriesTexts[i][j].setEditable(false); // No permitir edición directa
                //userTriesTexts[i][j].setHorizontalAlignment(SwingConstants.CENTER); // Centrar texto
                triesPanel.add(userTriesTexts[i][j]);
            }
        }
        add(triesPanel);
   
        
        //panel para el resto de elementos
        submitButton = new JButton("Submit");
        submitButton.setActionCommand("submit");
        previousTriesText = new JTextField(" ");//todo meter dentro de un scroll
        previousTriesText.setEditable(false);//intentos previos
        triesLeftNumbersText = new JTextField("intentos restantes: 10");
        triesLeftNumbersText.setEditable(false);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submitButton);
        buttonPanel.add(previousTriesText);
        buttonPanel.add(triesLeftNumbersText); 
     
        //se añaden los paneles al contenedor       
        add(fieldsPanel);
        add(triesPanel);
        add(buttonPanel);
        
        //tamaño adaptado, contenido centrado, visible
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setController(Controller controller) {
        this.controller = controller;
        submitButton.addActionListener((ActionListener) controller);
    }

    
    //todo obtener cada uno de los textos
  //  public String getUserTry() {
       // return userTriesText1.getText();
   // }


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
