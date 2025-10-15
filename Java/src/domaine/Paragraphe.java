/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class Paragraphe {
    private String[] dureees;
    private List<LigneNotes> lignes;

    public Paragraphe(){
        lignes = new ArrayList<LigneNotes>();
    }
    
    public String[] getDureees() {
        return dureees;
    }

    public void setDureees(String[] dureees) {
        this.dureees = dureees;
    }

    public List<LigneNotes> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneNotes> lignes) {
        this.lignes = lignes;
    }
    
    public void addLigneNote(LigneNotes ligne){
        this.lignes.add(ligne);
    }
    
}
