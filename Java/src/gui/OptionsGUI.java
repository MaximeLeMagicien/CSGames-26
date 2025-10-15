/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 */
public class OptionsGUI {
    public boolean optionNote;
    public boolean optionFichierAudio;
    public boolean optionImageTouche;
    public boolean optionCouleurTouche;
    
    public OptionsGUI(InterfacePrincipale interfacePrincipale){
        
        optionNote = interfacePrincipale.getOptionNote();
        optionFichierAudio = interfacePrincipale.getOptionFichier();
        optionImageTouche = interfacePrincipale.getOptionImageFond();
        optionCouleurTouche = interfacePrincipale.getOptionCouleurFond();
    }
    
}
