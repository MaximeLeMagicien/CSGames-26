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
public class RechercheForme implements StrategieRecherche{

    @Override
    public ArrayList<Touche> rechercherTouches(String mot, ArrayList<Touche> touches) {
        ArrayList<Touche> touchesTrouvees = new ArrayList<Touche>();
        for(Touche touche : touches){
            if (mot.toUpperCase().equals("RECTANGLE")){
                if (touche.getForme() instanceof Rectangle){
                    touchesTrouvees.add(touche);
                }
            }
            else if (mot.toUpperCase().equals("CERCLE")){
                if (touche.getForme() instanceof Cercle){
                    touchesTrouvees.add(touche);
                }
            }
        }
        return touchesTrouvees;
        }
        
    }
