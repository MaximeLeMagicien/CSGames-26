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
public interface StrategieRecherche {
    
    ArrayList<Touche> rechercherTouches(String mot, ArrayList<Touche> touches);
    
}
