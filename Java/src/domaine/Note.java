/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.HashMap;
import javax.swing.Timer;

/**
 *
 * 
 */
public class Note implements Son, java.io.Serializable {

    private String timbre;
    private int frequence;
    private String nom;
    private int octave;
    private int persistance;
    private final HashMap<String, Integer> noteMidiMap = new HashMap<String, Integer>();
    private final HashMap<String, Integer> timbreInstruMap = new HashMap<String, Integer>();
    private int numInstru;
    private int channelEnCours;

    public int getChannelEnCours() {
        return channelEnCours;
    }

    public void setChannelEnCours(int channelEnCours) {
        this.channelEnCours = channelEnCours;
    }

    public int getNumInstru() {
        return numInstru;
    }

    public void setNumInstru(int numInstru) {
        this.numInstru = numInstru;
    }

    private int midiNote;

    public int getMidiNote() {
        return midiNote;
    }

    public void setMidiNote(int midiNote) {
        this.midiNote = midiNote;
    }
    private long tempsDebutNote;

    public long getTempsDebutNote() {
        return tempsDebutNote;
    }

    public void setTempsDebutNote(long tempsDebutNote) {
        this.tempsDebutNote = tempsDebutNote;
    }
    private boolean estEnCours;

    public boolean isEstEnCours() {
        return estEnCours;
    }

    public void setEstEnCours(boolean estEnCours) {
        this.estEnCours = estEnCours;
    }
    private Timer timer;

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Note(String nom, int octave, int persistance, String timbre) {
        this.timbre = timbre;
        this.nom = nom;
        this.octave = octave;
        this.persistance = persistance;
        noteMidiMap.put("C", 0);
        noteMidiMap.put("C#", 1);
        noteMidiMap.put("D", 2);
        noteMidiMap.put("D#", 3);
        noteMidiMap.put("E", 4);
        noteMidiMap.put("F", 5);
        noteMidiMap.put("F#", 6);
        noteMidiMap.put("G", 7);
        noteMidiMap.put("G#", 8);
        noteMidiMap.put("A", 9);
        noteMidiMap.put("A#", 10);
        noteMidiMap.put("B", 11);

        timbreInstruMap.put("Piano", 0);
        timbreInstruMap.put("Xylophone", 14);
        timbreInstruMap.put("Guitare", 26);
        timbreInstruMap.put("Violon", 40);
        timbreInstruMap.put("Trompette", 57);
        timbreInstruMap.put("Flute", 75);

        numInstru = timbreInstruMap.get(timbre);
        midiNote = noteToMidi();
    }

    public int getFrequence() {
        return frequence;
    }

    private int noteToMidi() {
        int midinote = noteMidiMap.get(nom);
        midinote += (octave) * 12;
        return midinote;
    }

    public String getNom() {
        return nom;
    }

    public int getOctave() {
        return octave;
    }

    public String getNomOctave() {
        return nom + String.valueOf(octave);
    }

    public String getTimbre() {
        return timbre;
    }

    public int getPersistance() {
        return persistance;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setPersistance(int persistance) {
        this.persistance = persistance;
    }

    public boolean getEnCours() {
        return this.estEnCours;
    }

    private int frequencyToMidiNote(int frequence) {
        double[] midiArray = new double[127];
        int a = 440; // a is 440 hz...
        for (int x = 0; x < 127; ++x) {
            midiArray[x] = (a / 32) * (2 ^ ((x - 9) / 12));
        }
        return 1;
    }

}
