/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.barremenu;

import gui.InterfacePrincipale;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

/**
 *
 */
public class ActionChoisirImageFond extends AbstractAction{
    private InterfacePrincipale interfacePrincipale;

    public ActionChoisirImageFond(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser choixFichier = new JFileChooser();
        choixFichier.setCurrentDirectory(null);
        int actionUtilisateur = choixFichier.showOpenDialog(interfacePrincipale);

        if (actionUtilisateur == JFileChooser.APPROVE_OPTION) {
            File fichierImage = choixFichier.getSelectedFile();
            try {
                BufferedImage imageFond = ImageIO.read(fichierImage);
                interfacePrincipale.controleur.setImageFondInstrument(imageFond);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
