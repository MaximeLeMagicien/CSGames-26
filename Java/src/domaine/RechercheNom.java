/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.ArrayList;

/**
 *
 * 
 */
public class RechercheNom implements StrategieRecherche{

    @Override
    public ArrayList<Touche> rechercherTouches(String mot, ArrayList<Touche> touches) {
        ArrayList<Touche> touchesTrouvees = new ArrayList<Touche>();
        for(Touche touche : touches){
            if(touche.getSon() instanceof Note ){
                Note note = (Note) touche.getSon();

                if (mot.length() == 2){
                    String nom = mot.toUpperCase().substring(0, 1);
                    String octave = mot.substring(1, 2);
                    if (touche.getSon().getNom().equals(nom) && Integer.toString(note.getOctave()).equals(octave)){
                        touchesTrouvees.add(touche);
                    }
                }
                else if (mot.length() == 3){
                    String nom = mot.toUpperCase().substring(0, 2);
                    String octave = mot.substring(2, 3);
                    if (touche.getSon().getNom().equals(nom) && Integer.toString(note.getOctave()).equals(octave)){
                        touchesTrouvees.add(touche);
                    }
                }
            }
        }
        return touchesTrouvees;
    }
    
}
