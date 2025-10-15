/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.barremenu;

import domaine.Mode;
import gui.InterfacePrincipale;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class ActionModeAjout implements ActionListener{
    
    private final InterfacePrincipale interfacePrincipale;
    
    public ActionModeAjout(InterfacePrincipale interfacePrincipale){
        this.interfacePrincipale= interfacePrincipale;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(interfacePrincipale.controleur.getMode() == Mode.SELECTION){
          interfacePrincipale.controleur.switchEstSelectionneeTouches();
        }
        interfacePrincipale.controleur.setMode(Mode.AJOUTTOUCHE);
    }
    
}
