/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;

/**
 *
 */
public class ProprietesSon {
     public String nomNote;
     public int persistance;
     public int octave;
     public File fichierAudio;
     public String nomTimbre;
     
     public ProprietesSon(InterfacePrincipale interfacePrincipale){
         this.nomTimbre = interfacePrincipale.getNomTimbre();
         nomNote = interfacePrincipale.getNomNote();
         this.persistance = interfacePrincipale.getPersistance();
         this.octave = interfacePrincipale.getOctave();
         fichierAudio = interfacePrincipale.getFichierAudio();
     }
    
}
