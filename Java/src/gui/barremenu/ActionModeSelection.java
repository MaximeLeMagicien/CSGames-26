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
public class ActionModeSelection implements ActionListener{
    
    private final InterfacePrincipale interfacePrincipale;
    
    public ActionModeSelection(InterfacePrincipale interfacePrincipale){
        this.interfacePrincipale= interfacePrincipale;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        interfacePrincipale.controleur.setMode(Mode.SELECTION);
    }
}
