/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;
import view.View;

/**
 *
 * @author silvia
 */
public class Controller implements ActionListener{
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        
        this.view.setController(this);
        
        updateView();
    }

    
     private void updateView() {

     //todo
     //- view.triesLeftNumbersText -1 
     //- previousTriesText añadir el intento del textArea
     //userTriesText resetearlo
     }
    
    //TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        //todo
        //sacar el texto y usarlo para comprobar como numero del juego

    }

   
    
}
