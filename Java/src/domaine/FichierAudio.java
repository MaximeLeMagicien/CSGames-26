/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.io.File;
import javax.sound.sampled.*;

/**
 *
 * 
 */
public class FichierAudio implements Son, java.io.Serializable {

    private File fichier;
    transient private AudioInputStream ais;
    transient private Clip clip;
    private boolean estEnCours;
    private String nomFichier;
    transient private Controleur controleur;

    public FichierAudio(File fichier, Controleur controleur) {
        this.controleur = controleur;
        this.fichier = fichier;
        this.nomFichier = fichier.getName();

        try {
            ais = AudioSystem.getAudioInputStream(fichier);
            this.clip = AudioSystem.getClip();

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent e) {
                    if (e.getType() == LineEvent.Type.STOP) {
                        estEnCours = false;
                        controleur.informerObservateurChangementJeu();
//                        clip.close();

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getFichier() {
        return fichier;
    }

    public void setFichier(File fichier) {
        this.fichier = fichier;
    }


    public void jouer() {
        try {
            estEnCours = true;
            controleur.informerObservateurChangementJeu();
            if (!clip.isOpen()) {
                clip.open(ais);
            }
            clip.setMicrosecondPosition(0);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void stop() {
        clip.stop();
        estEnCours = false;
        controleur.informerObservateurChangementJeu();

    }

    @Override
    public boolean getEnCours() {
        return this.estEnCours;
    }

    @Override
    public String getNom() {
        return nomFichier;
    }

    public void fichierCharge(Controleur controleur) {
        try {
            ais = AudioSystem.getAudioInputStream(fichier);
            this.clip = AudioSystem.getClip();
            this.controleur = controleur;

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent e) {
                    if (e.getType() == LineEvent.Type.STOP) {
                        estEnCours = false;
                        controleur.informerObservateurChangementJeu();
//                        clip.close();

                    }

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
