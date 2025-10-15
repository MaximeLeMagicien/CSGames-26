/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.barremenu;

import domaine.Controleur;
import gui.InterfacePrincipale;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

/**
 *
 *
 */
public class ActionImporterPiece extends AbstractAction {

    private final InterfacePrincipale interfacePrincipale;

    public ActionImporterPiece(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser choixFichier = new JFileChooser();
        choixFichier.setCurrentDirectory(null);
        int actionUtilisateur = choixFichier.showOpenDialog(interfacePrincipale);

        if (actionUtilisateur == JFileChooser.APPROVE_OPTION) {
            File fichier = choixFichier.getSelectedFile();
            try {
                this.interfacePrincipale.controleur.parsePieceMusicale(fichier, interfacePrincipale);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ActionImporterPiece.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidMidiDataException ex) {
                Logger.getLogger(ActionImporterPiece.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
