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
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

/**
 *
 */
public class ActionChargerInstrument extends AbstractAction {

    private Controleur controleur;
    private InterfacePrincipale interfacePrincipale;

    public ActionChargerInstrument(InterfacePrincipale interfacePrinc) {
        this.controleur = interfacePrinc.controleur;
        this.interfacePrincipale = interfacePrinc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser choixFichier = new JFileChooser();
        choixFichier.setCurrentDirectory(null);
        int actionUtilisateur = choixFichier.showOpenDialog(interfacePrincipale);

        if (actionUtilisateur == JFileChooser.APPROVE_OPTION) {
            File fichier = choixFichier.getSelectedFile();
            this.controleur.chargerInstrument(fichier);
            interfacePrincipale.setNomInstrument(this.controleur.getNomInstrument());
        }
    }

}
