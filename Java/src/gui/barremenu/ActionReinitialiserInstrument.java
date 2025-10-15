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
public class ActionReinitialiserInstrument extends AbstractAction {

    private InterfacePrincipale interfacePrincipale;

    public ActionReinitialiserInstrument(InterfacePrincipale interfacePrinc) {
        this.interfacePrincipale = interfacePrinc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interfacePrincipale.controleur.reinitialiserInstrument();
    }

}
