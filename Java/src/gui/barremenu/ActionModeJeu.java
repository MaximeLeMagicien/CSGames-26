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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;

/**
 *
 */
public class ActionModeJeu implements ActionListener {

    private final InterfacePrincipale interfacePrincipale;

    public ActionModeJeu(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (interfacePrincipale.controleur.getMode() == Mode.SELECTION) {
            interfacePrincipale.controleur.switchEstSelectionneeTouches();
        }
        interfacePrincipale.controleur.setMode(Mode.JEU);
        try {
            interfacePrincipale.controleur.resetSequencePiece();
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(ActionModeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
