/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 *
 */
public class Composition {

    private int bpm;
    private List<Paragraphe> paragraphes;

    public Composition() {
        bpm = 0;
        paragraphes = new ArrayList<Paragraphe>();
    }

    public Composition(int bpm) {
        this.bpm = bpm;
        paragraphes = new ArrayList<Paragraphe>();
    }

    public Composition(File fichier) throws FileNotFoundException {
        this.bpm = 0;
        paragraphes = new ArrayList<Paragraphe>();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fichier)));
        while (scanner.hasNext() && this.bpm == 0) {
            String ligne = scanner.nextLine();
            if (!(ligne.startsWith("//") || ligne.isEmpty())) {
                try {
                    this.bpm = (Integer.parseInt(ligne));
                } catch (NumberFormatException e) {
                    return; //La premiere ligne non vide et non commentee n'est pas un nombre de BPM        
                }
            }

        }
        boolean paragrapheEnCours = false;
        Paragraphe paragraphe = new Paragraphe();
        while (scanner.hasNext()) {
            String ligne = scanner.nextLine();
            if (!ligne.startsWith("//")) {
                if (!paragrapheEnCours) { // premiere rangee de notes
                    if (!ligne.isEmpty()) {
                        paragraphe = new Paragraphe();
                        paragrapheEnCours = true;

                        ligne = ligne.replace("|", "");
                        LigneNotes notes = new LigneNotes(ligne.trim().split("\\s+"));
                        paragraphe.addLigneNote(notes);
                    }
                } else { //Si c'est des notes on les ajoute, sinon on met fin au paragraphe et on garde les durees
                    if (ligne.matches(".*([a-gA-G]).*")) { // contient des notes
                        ligne = ligne.replace("|", "");
                        LigneNotes notes = new LigneNotes(ligne.trim().split("\\s+"));
                        paragraphe.addLigneNote(notes);
                    } else {
                        if (!ligne.isEmpty()) {
                            ligne = ligne.replace("|", "");
                            paragraphe.setDureees(ligne.trim().split("\\s+"));
                        }
                        paragrapheEnCours = false;
                        this.paragraphes.add(paragraphe);
                    }
                }
            }
        }
        if (paragrapheEnCours) {
            paragrapheEnCours = false;
            this.paragraphes.add(paragraphe);
        }
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public List<Paragraphe> getParagraphes() {
        return paragraphes;
    }

    public void setParagraphes(List<Paragraphe> paragraphes) {
        this.paragraphes = paragraphes;
    }

    public void addParagraphe(Paragraphe paragraphe) {
        this.paragraphes.add(paragraphe);
    }
}
