/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;
import javax.swing.Timer;

/**
 *
 */
public class Instrument implements Receiver, Serializable {

    private ArrayList<Touche> listeTouches = new ArrayList<Touche>();
    private String nomInstrument;
    private int numInstru = 0;
    private int prochainChannel = 0;

    public Sequencer getSequenceur() {
        return sequenceur;
    }

    public void setSequenceur(Sequencer sequenceur) {
        this.sequenceur = sequenceur;
    }
    transient private Image imageFond;
    transient private Synthesizer midiSynth;
    transient private javax.sound.midi.Instrument[] instr;
    transient private MidiChannel[] mChannels;
    transient private Controleur controleur;
    private int channelAFermer;
    transient private Sequencer sequenceur;
    transient private Transmitter transmetteur;
    transient private Map<String, Double> mapDurees;
    transient private final HashMap<String, Integer> noteMidiMap = new HashMap<String, Integer>();
    transient private final HashMap<String, Integer> timbreInstruMap = new HashMap<String, Integer>();

    private static final int VOLUME = 100;
    private static final int PPQ = 24;

    public Instrument(ArrayList<Touche> listeTouches, String nomInstrument, Image imageFond, Controleur controleur) {
        this.listeTouches = listeTouches;
        this.nomInstrument = nomInstrument;
        this.imageFond = imageFond;
        this.controleur = controleur;
        initialiserSynthMidi();
        initialiserSequenceur();
        mapDurees = new HashMap<>();
        mapDurees.put("_", 1.0);
        mapDurees.put(",", 0.5);
        mapDurees.put(".", 0.25);
        mapDurees.put(",", 0.5);

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
    }

    public Instrument() {
        this.listeTouches = new ArrayList<Touche>();
        this.nomInstrument = "Instrument sans nom";
        initialiserSynthMidi();
        initialiserSequenceur();
        mapDurees = new HashMap<>();
        mapDurees.put("_", 1.0);
        mapDurees.put(",", 0.5);
        mapDurees.put(".", 0.25);
        mapDurees.put(",", 0.5);

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

    }

    public Instrument(Controleur controleur) {
        this.listeTouches = new ArrayList<Touche>();
        this.nomInstrument = "Instrument sans nom";
        this.controleur = controleur;
        initialiserSynthMidi();
        initialiserSequenceur();
        mapDurees = new HashMap<>();
        mapDurees.put("_", 1.0);
        mapDurees.put(",", 0.5);
        mapDurees.put(".", 0.25);
        mapDurees.put(",", 0.5);

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

    }

    public MidiChannel[] getChannels(){
        return mChannels;
    }
    
    private void initialiserSynthMidi() {
        try {
            midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            instr = midiSynth.getDefaultSoundbank().getInstruments();
            mChannels = midiSynth.getChannels();
        } catch (MidiUnavailableException e) {
        }
    }

    public void jouerSon(Son son) {
        if (son instanceof Note) {
            jouerNote((Note) son);
        } else if (son instanceof FichierAudio) {
            FichierAudio fichier = (FichierAudio) son;
            fichier.jouer();
        }
    }

    private void jouerNote(Note note) {
        int channelAJouer = prochainChannel % 15;
        mChannels[channelAJouer].programChange(instr[note.getNumInstru()].getPatch().getProgram());
        int midiNote = note.getMidiNote();
        mChannels[channelAJouer].noteOn(midiNote, 100);
        note.setTempsDebutNote(System.currentTimeMillis());
        note.setEstEnCours(true);
        note.setChannelEnCours(channelAJouer);

        controleur.informerObservateurChangementJeu();
    }

    public void stopApresPersistance(Note note) {
        long tempsEcoule = System.currentTimeMillis() - note.getTempsDebutNote();
        int persistance = note.getPersistance();
        int midiNote = note.getMidiNote();
        if (tempsEcoule >= persistance) {
            mChannels[note.getChannelEnCours()].noteOff(midiNote);
            note.setEstEnCours(false);
            controleur.informerObservateurChangementJeu();

        } else {
            prochainChannel++;
            note.setTimer(new Timer((persistance - (int) tempsEcoule), (ActionEvent e) -> {
                mChannels[note.getChannelEnCours()].noteOff(midiNote);
                note.setEstEnCours(false);
                controleur.informerObservateurChangementJeu();
                prochainChannel--;
            }));
            Timer timer = note.getTimer();
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void stopSon(Son son) {
        if (son instanceof Note) {
            Note note = (Note) son;
            mChannels[note.getChannelEnCours()].noteOff(note.getMidiNote());
            note.getTimer().stop();
            prochainChannel--;
            note.setEstEnCours(false);
        } else if (son instanceof FichierAudio) {
            FichierAudio fichier = (FichierAudio) son;
            fichier.stop();
        }

    }

    public ArrayList<Touche> getListeTouches() {
        return listeTouches;
    }

    public String getNomInstrument() {
        return nomInstrument;
    }

    public Image getImageFond() {
        return this.imageFond;
    }

    public void setNomInstrument(String nomInstrument) {
        this.nomInstrument = nomInstrument;
    }

    public void setListeTouches(ArrayList<Touche> listeTouches) {
        this.listeTouches = listeTouches;
    }

    public void setImageFond(Image imageFond) {
        this.imageFond = imageFond;
    }

    public void ajouterTouche(Touche touche) {
        this.listeTouches.add(touche);
    }

    public void modifierNom(String nom) {
        this.setNomInstrument(nom);
    }

    public void majToucheSelectionnee(Point pointSouris, Dimension dimensionZoneJeu) {
        for (Touche touche : this.listeTouches) {
            if (touche.getEstSélectionnee()) {
                touche.nouveauPoint(pointSouris, dimensionZoneJeu);
            }
        }
    }

    public void switchEstSelectionneeListTouche() {
        for (Touche touche : this.listeTouches) {
            if (touche.getEstSélectionnee()) {
                touche.switchEstSelection();
                break;
            }
        }
    }

    public void deleteTouche(Touche touche) {
        this.listeTouches.remove(touche);
    }

    /*
    Pour la serialization de l'image de fond
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (imageFond != null) {
            ImageIO.write((BufferedImage) imageFond, "png", out);
        }

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        imageFond = (ImageIO.read(in));
        initialiserSynthMidi();

    }

    public void chargerInstrument(Controleur controleur) {
        this.controleur = controleur;
    }

    private void initialiserSequenceur() {
        try {
            this.sequenceur = MidiSystem.getSequencer();
            sequenceur.open();
            transmetteur = sequenceur.getTransmitter();
            transmetteur.setReceiver(this);
        } catch (MidiUnavailableException e) {

        }

    }

    public void creerSequenceMidi(Composition composition) throws InvalidMidiDataException {
        sequenceur.setTempoInBPM((float) composition.getBpm());
        Sequence sequence = new Sequence(Sequence.PPQ, PPQ); // Un "tick" est egal a 1/PPQ de temps (un temps = une note normale)
        Track piece = sequence.createTrack();
        
        ShortMessage numInstrument = new ShortMessage();
        numInstrument.setMessage(ShortMessage.PROGRAM_CHANGE, 0, numInstru, 0);
        piece.add(new MidiEvent(numInstrument, 0));

        int tickDebutLigne = 0;
        int tickDebutParagraphe = 0;
        for (Paragraphe paragraphe : composition.getParagraphes()) {
            String[] durees = paragraphe.getDureees();
            if (durees == null) {
                for (LigneNotes ligne : paragraphe.getLignes()) {
                    tickDebutLigne = tickDebutParagraphe;
                    for (String note : ligne.notes) {
                        tickDebutLigne += PPQ;
                        if (!note.toUpperCase().equals("X")) {
                            int numNote = noteToMidi(note);
                            ajouterEventsMidiNote(piece, numNote, tickDebutLigne, PPQ);
                        }
                    }
                }
                tickDebutParagraphe = tickDebutLigne;
            } else { // On assume que les durees sont specifiees pour toutes les notes
                for (int i = 0; i < durees.length; i++) {
                    int dureeEnTicks = 0;
                    if (mapDurees.containsKey(durees[i])) {
                        dureeEnTicks = (int) (mapDurees.get(durees[i]) * PPQ);
                    } else {
                        try {
                            dureeEnTicks = PPQ * (Integer.parseInt(durees[i])); // Si c'est 2,3,4 temps par exemple
                        } catch (NumberFormatException e) {
                            dureeEnTicks = PPQ; // Si une duree inconnue, la valeur par defaut (1 temps est utilise)
                        }
                    }
                    tickDebutLigne += dureeEnTicks;//
                    for (LigneNotes ligne : paragraphe.getLignes()) {
                        if (!ligne.notes[i].toUpperCase().equals("X")) {
                            int numNote = noteToMidi(ligne.notes[i]);
                            ajouterEventsMidiNote(piece, numNote, tickDebutLigne, dureeEnTicks);//
                        }
                    }
                }
            }
        }
        sequenceur.setSequence(sequence);
    }

    public void ajouterEventsMidiNote(Track track, int numeroNote, long tickDebut, long dureeEnTicks) {
        ShortMessage message = new ShortMessage();
        try {
            if (controleur.getMode() == Mode.JEUPRATIQUE){
                message.setMessage(ShortMessage.NOTE_ON, 0, numeroNote, 0);
            }
            else {
                message.setMessage(ShortMessage.NOTE_ON, 0, numeroNote, VOLUME);
            }
            
        } catch (InvalidMidiDataException exception) {

        }
        MidiEvent debutNote = new MidiEvent(message, tickDebut);
        track.add(debutNote);

        message = new ShortMessage();
        try {
            message.setMessage(ShortMessage.NOTE_OFF, 0, numeroNote, VOLUME);
        } catch (InvalidMidiDataException exception) {

        }
        MidiEvent finNote = new MidiEvent(message, tickDebut + dureeEnTicks);
        track.add(finNote);

    }

    private int noteToMidi(String nom) {
        try {
            int octave = 4;
            String nomNote = nom;
            if (nom.length() == 3) {
                octave = Integer.parseInt(nom.substring(1, 2));
                nomNote = nom.substring(0, 1) + nom.substring(2, 3);
            } else if (nom.length() == 2) {
                if (!nom.contains("#")) {
                    nomNote = nom.substring(0, 1);
                    octave = Integer.parseInt(nom.substring(1, 2));
                } else {
                    nomNote = nom;
                }
            }
            int midinote = noteMidiMap.get(nomNote.toUpperCase()); // Quoi faire si pas dans la map... 
            midinote += (octave) * 12;
            return midinote;
        } catch (NumberFormatException e) {
            return 0; //!!!!!!! a revoir !!!!!!!!
        }

    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            ShortMessage messageNote = (ShortMessage) message;
            int channel = messageNote.getChannel();
            if (messageNote.getCommand() == NOTE_ON) {
                int noteMidi = messageNote.getData1();
                for (Touche touche : rechercherTouchesMidi(noteMidi)) {
                    touche.setEstRecherchee(true);
                }

            } else if (messageNote.getCommand() == NOTE_OFF) {
                int noteMidi = messageNote.getData1();
                for (Touche touche : rechercherTouchesMidi(noteMidi)) {
                    touche.setEstRecherchee(false);
                }
            }
            controleur.informerObservateurChangementJeu();

        }
    }

    @Override
    public void close() {
    }

    public ArrayList<Touche> rechercherTouchesMidi(int noteMidi) {
        ArrayList<Touche> touchesTrouvees = new ArrayList<Touche>();
        for (Touche touche : this.listeTouches) {
            if (touche.getSon() instanceof Note) {
                Note note = (Note) touche.getSon();
                if (note.getMidiNote() == noteMidi) {
                    touchesTrouvees.add(touche);
                }
            }
        }
        return touchesTrouvees;
    }

    public void setTimbrePiece(String timbre) {
        numInstru = timbreInstruMap.get(timbre);
    }

}
