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
public class PieceMusicale {
    private ArrayList<Note> listeNotesFichier = new ArrayList<Note>();
    private String nomFichier;
    private int bpm ;
    

    public PieceMusicale(ArrayList<Note> listeNotesFichier, String nomFichier, int bpm){
        this.listeNotesFichier = listeNotesFichier;
        this.nomFichier = nomFichier;
        this.bpm = bpm;
        }
    public ArrayList<Note> getListeNotesFichier() {
        return listeNotesFichier;
    }
    
    public String getNomFichier() {
        return nomFichier;
    }    
    
    public int getBpm() {
        return bpm;
    }    
    
     public void setListeNotesFichier(ArrayList<Note> listeNotesFichier) {
        this.listeNotesFichier = listeNotesFichier;
    }
     public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
    
   
    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
        
}
