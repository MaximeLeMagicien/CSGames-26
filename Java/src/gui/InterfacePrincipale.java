/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domaine.Controleur;
import domaine.Mode;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 */
public class InterfacePrincipale extends JFrame {

    public ZoneJeu zoneJeu;
    public Controleur controleur;
    private final JPanel panneauPrincipal;
    private final BarreMenu barreMenuHaut;
    private final PanneauGauche panneauGauche;
    public Point actualMousePoint;

    public InterfacePrincipale() throws IOException {
        controleur = new Controleur();
        panneauPrincipal = new JPanel();
        barreMenuHaut = new BarreMenu(this);
        panneauGauche = new PanneauGauche(this);
        zoneJeu = new ZoneJeu(this);
        actualMousePoint = new Point();
        initialiser();
    }

    private void initialiser() throws IOException {
        this.setMinimumSize(new Dimension(800, 720));
        setJMenuBar(barreMenuHaut);
        setTitle("Le Bellophone");
        initialiserPanneauPrincipal();
        setContentPane(panneauPrincipal);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initialiserPanneauPrincipal() throws IOException {
        BorderLayout layout = new BorderLayout();
        
        panneauPrincipal.setLayout(layout);
        panneauPrincipal.add(panneauGauche, BorderLayout.WEST);
        panneauPrincipal.add(zoneJeu, BorderLayout.CENTER);
    }

    public boolean getOptionFichier() {
        return panneauGauche.getOptionFichier();
    }

    public boolean getOptionNote() {
        return panneauGauche.getOptionNote();
    }

    public String getNomTimbre() {
        return panneauGauche.getTimbre();
    }

    public String getNomNote() {
        return panneauGauche.getNomNote();
    }

    public int getPersistance() {
        return panneauGauche.getPersistance();
    }

    public int getOctave() {
        return panneauGauche.getOctave();
    }

    public String getFormeSelectionne() {
        return panneauGauche.getFormeSelectionnee();
    }

    public int getLargeurRect() {
        return panneauGauche.getLargeurRect();
    }

    public int getHauteurRect() {
        return panneauGauche.getHauteurRect();
    }

    public int getRayonCercle() {
        return panneauGauche.getRayonCercle();
    }

    public Color getCouleurBordureCercle() {
        return panneauGauche.getCouleurBordureCercle();
    }

    public Color getCouleurFondTouche() {
        return panneauGauche.getCouleurFondTouche();
    }

    public boolean getOptionCouleurFond() {
        return panneauGauche.getOptionCouleurFond();
    }

    public boolean getOptionImageFond() {
        return panneauGauche.getOptionImageFond();
    }

    public Image getImageFondTouche() {
        return panneauGauche.getImageFondTouche();
    }

    public int getBordureCercle() {
        return panneauGauche.getBordureCercle();
    }

    public int getBordureHaut() {
        return panneauGauche.getBordureHaut();
    }

    public int getBordureBas() {
        return panneauGauche.getBordureBas();
    }

    public int getBordureGauche() {
        return panneauGauche.getBordureGauche();
    }

    public int getBordureDroite() {
        return panneauGauche.getBordureDroite();
    }

    public File getFichierAudio() {
        return panneauGauche.getFichierAudio();
    }

    public Color getCouleurBordureHaut() {
        return panneauGauche.getCouleurBordureHaut();
    }

    public Color getCouleurBordureDroite() {
        return panneauGauche.getCouleurBordureDroite();
    }

    public Color getCouleurBordureBas() {
        return panneauGauche.getCouleurBordureBas();
    }

    public Color getCouleurBordureGauche() {
        return panneauGauche.getCouleurBordureGauche();
    }

    public Color getCouleurBordureCentre() {
        return panneauGauche.getCouleurBordureCentre();
    }
    public int getBordureCentre(){
        return panneauGauche.getBordureCentre();
    }
    public void setNomInstrument(String nouveauNom){
        panneauGauche.setNomInstrument(nouveauNom);
    }
    public void setTextePiece(String nouveauTexte){
        panneauGauche.setTextePiece(nouveauTexte);
    }
    
}
