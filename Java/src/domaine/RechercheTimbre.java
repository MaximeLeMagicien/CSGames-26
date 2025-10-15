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
public class RechercheTimbre implements StrategieRecherche{

    @Override
    public ArrayList<Touche> rechercherTouches(String mot, ArrayList<Touche> touches) {
        ArrayList<Touche> touchesTrouvees = new ArrayList<Touche>();
        for(Touche touche : touches){
            if(touche.getSon() instanceof Note ){
                Note note = (Note) touche.getSon();
                if (note.getTimbre().toUpperCase().equals(mot.toUpperCase())){
                    touchesTrouvees.add(touche);
                }             
            }
        }
        return touchesTrouvees;
    }
    
}
