/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */

public class GabaritGuitare implements StrategieGabarit{
    
    private int octave;
    private int PERSISTANCE = 0;
    private String TIMBRE = "Guitare";
    private List<String> listeNote = new ArrayList<String>();
    private Controleur controleur;
    
    private Color couleurCorde = Color.WHITE;
    private Color couleurTouche = Color.ORANGE;
    private Color couleurFret = Color.DARK_GRAY;
    private Color couleurContour = Color.BLACK;
    
    @Override
    public Instrument genererInstrument(Controleur controleur) {
        this.controleur = controleur;
        Instrument instrument = new Instrument(controleur);
        //public Note(String nom, int octave, int persistance, String timbre, Controleur controleur)
        listeNote.add("C");
        listeNote.add("C#");
        listeNote.add("D");
        listeNote.add("D#");
        listeNote.add("E");
        listeNote.add("F");
        listeNote.add("F#");
        listeNote.add("G");
        listeNote.add("G#");
        listeNote.add("A");
        listeNote.add("A#");
        listeNote.add("B");
        int nbTouche = 0;
        for(int j = 0; j < 6; j++){
            for(int i = 0; i <= 12; i++){
            nbTouche += 1;
            if(nbTouche > 8 && nbTouche < 14){
                octave = 6;
            }else if(nbTouche > 0 && nbTouche < 9 || nbTouche > 14 && nbTouche < 27 || nbTouche > 31 && nbTouche < 40 || nbTouche > 49 && nbTouche < 53){
                octave = 5;
            }else if(nbTouche > 52 && nbTouche < 56 || nbTouche > 65 && nbTouche < 74){
                octave = 3;
            }else{
                octave = 4;
            }
            Coordonnee coord = new Coordonnee(0.04+0.05*(i+1),0.12*(j+1));
            Note note= new Note(listeNote.get((4+i+(j*7))%12), octave, PERSISTANCE, TIMBRE);
            if(j == 2){
                note= new Note(listeNote.get((7+i)%12), octave, PERSISTANCE, TIMBRE);
            }else if(j >= 2){
                note= new Note(listeNote.get((5+i+(j*7))%12), octave, PERSISTANCE, TIMBRE);
            }
            
            Rectangle rectangle = new Rectangle(couleurTouche, 0.05, 0.05, 0.01, 0.01, 0.01, 0.01, 0.002*(j+1), couleurContour, couleurContour, couleurFret, couleurFret, couleurCorde);
            
            Touche touche = new Touche(note, rectangle, coord);
            instrument.ajouterTouche(touche);
            }
        }
        instrument.setTimbrePiece("Guitare");
        return instrument;
    }
}
