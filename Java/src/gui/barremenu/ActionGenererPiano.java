/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.barremenu;

import gui.InterfacePrincipale;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.swing.AbstractAction;

/**
 *
 * 
 */
public class ActionGenererPiano extends AbstractAction {
     private final InterfacePrincipale interfacePrincipale;

    public ActionGenererPiano(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         try {
             interfacePrincipale.controleur.genererPiano();
         } catch (InvalidMidiDataException ex) {
             Logger.getLogger(ActionGenererPiano.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
