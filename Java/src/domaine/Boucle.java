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
public class Boucle {
    private ArrayList<Note> notesJouees = new ArrayList<Note>();
    private int numero;
    private Boolean enregristrementEnCours ;
    

    public Boucle(ArrayList<Note> notesJouees, int numero, Boolean enregristrementEnCours){
        this.notesJouees = notesJouees;
        this.numero = numero;
        this.enregristrementEnCours = enregristrementEnCours;
        }
    public ArrayList<Note> getNotesJouees() {
        return notesJouees;
    }
    
    public int getNumero() {
        return numero;
    }    
    
    public Boolean getEnregristrementEnCours() {
        return enregristrementEnCours;
    }    
    
     public void setNotesJouees(ArrayList<Note> notesJouees) {
        this.notesJouees = notesJouees;
    }
     public void setNumero(int numero) {
        this.numero = numero;
    }
    
   
    public void setEnregristrementEnCours(Boolean enregristrementEnCours) {
        this.enregristrementEnCours = enregristrementEnCours;
    }
    
}
