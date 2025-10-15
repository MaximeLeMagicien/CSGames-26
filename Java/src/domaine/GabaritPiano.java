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
public class GabaritPiano implements StrategieGabarit{

    private int octave;
    private final int PERSISTANCE = 0;
    private final String TIMBRE = "Piano";
    private List<String> listeNote = new ArrayList<String>(7);
    private List<String> listeDiese = new ArrayList<String>(5);
    private final Color couleurBlanche = Color.white;
    private final Color couleurNoir = Color.DARK_GRAY;
    private final double largeurBordure = 0.0025;
    private final double hauteurTouchesBlanches = 0.35;
    private final double largeurTouches = 0.035;
    private final double hauteurTouchesNoires = 0.20;
    @Override
    public Instrument genererInstrument(Controleur controleur) {
        Instrument instrument = new Instrument(controleur);
        //public Note(String nom, int octave, int persistance, String timbre, Controleur controleur)
        listeNote.add("C");
        listeNote.add("D");
        listeNote.add("E");
        listeNote.add("F");
        listeNote.add("G");
        listeNote.add("A");
        listeNote.add("B");
        
        listeDiese.add("C#");
        listeDiese.add("D#");
        listeDiese.add("F#");
        listeDiese.add("G#");
        listeDiese.add("A#");
        for (int i = 0; i < 35; ++i){
            
            if (i <= 6){
                this.octave = 3;
            }
            if (i > 6 && i <= 13){
                this.octave = 4;
            }
            if (i > 13 && i <= 20){
                this.octave = 5;
            }
            if ( i > 20 && i <= 28) {
                this.octave = 6;
            }
            
            if (i > 28){
                this.octave = 7;
            }
                
            
            Note note= new Note(listeNote.get(i%7), octave, PERSISTANCE, TIMBRE);

            Coordonnee coord = new Coordonnee(0.04+0.025*(i+1),0.3);
            Rectangle rectangle;
            rectangle = new Rectangle(couleurBlanche, hauteurTouchesBlanches, largeurTouches, largeurBordure, largeurBordure,
                    largeurBordure, largeurBordure, 0, couleurNoir, couleurNoir, couleurNoir, couleurNoir, couleurNoir);
            Touche touche = new Touche(note, rectangle, coord);
            instrument.ajouterTouche(touche);
        }
        int j = -1;
        for (int i = 0; i < 35; ++i){
            
            if (i == 2 || i == 6 || i == 9 || i == 13 || i == 16 || i == 20 || i == 23 || i == 27 || i == 30 || i == 34){
                continue;
            }
            if (i <= 6){
                j = j + 1;
                this.octave = 3;
            }
            if (i > 6 && i <= 13){
                j = j + 1;
                this.octave = 4;
            }
            if (i > 13 && i <= 20){
                j = j + 1;
                this.octave = 5;
            }
            if ( i > 20 && i <= 28) {
                j = j + 1;
                this.octave = 6;
            }
            
            if (i > 28){
                j = j + 1;
                this.octave = 7;
            }
            
            Note note= new Note(listeDiese.get(j%5), octave, PERSISTANCE, TIMBRE); 
            Coordonnee coord = new Coordonnee(0.052+0.025*(i+1),0.235);
            Rectangle rectangle;
            rectangle = new Rectangle(couleurNoir, hauteurTouchesNoires, largeurTouches, largeurBordure, largeurBordure, largeurBordure,
                largeurBordure, 0, couleurBlanche, couleurBlanche, couleurBlanche, couleurBlanche, couleurBlanche);
            Touche touche = new Touche(note, rectangle, coord);
            instrument.ajouterTouche(touche);
        }
        instrument.setTimbrePiece("Piano");
        return instrument;
    }
   
}
