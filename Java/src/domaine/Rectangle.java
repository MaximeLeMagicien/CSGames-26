/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.awt.Color;
import java.awt.Image;


/**
 *
 * 
 */
public class Rectangle extends Forme {

    private double hauteur;
    private double largeur;
    private double bordureDroite;
    private double bordureHaut;
    private double bordureBas;
    private double bordureGauche;
    private Color couleurBordureBas;
    private Color couleurBordureGauche;
    private Color couleurBordureDroite;
    private Color couleurBordureHaut;
    private Color couleurBordureCentre;

    public Rectangle(Color couleurFond, double hauteur, double largeur, double bordureDroite,
            double bordureHaut, double bordureBas, double bordureGauche, double bordureCentre, Color couleurHaut,
            Color couleurBas, Color couleurDroite, Color couleurGauche, Color couleurCentre) {
        super(couleurFond.darker(), couleurCentre.darker(), bordureCentre);
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.bordureDroite = bordureDroite;
        this.bordureHaut = bordureHaut;
        this.bordureBas = bordureBas;
        this.bordureGauche = bordureGauche;
        this.couleurBordureBas = couleurBas.darker();
        this.couleurBordureHaut = couleurHaut.darker();
        this.couleurBordureDroite = couleurDroite.darker();
        this.couleurBordureGauche = couleurGauche.darker();

    }

    public Rectangle(Image imageFond, double hauteur, double largeur, double bordureDroite,
            double bordureHaut, double bordureBas, double bordureGauche, double bordureCentre,
            Color couleurHaut, Color couleurBas, Color couleurDroite, Color couleurGauche, Color couleurCentre) {
        super(imageFond, couleurCentre.darker(), bordureCentre);
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.bordureDroite = bordureDroite;
        this.bordureHaut = bordureHaut;
        this.bordureBas = bordureBas;
        this.bordureGauche = bordureGauche;
        this.couleurBordureBas = couleurBas.darker();
        this.couleurBordureHaut = couleurHaut.darker();
        this.couleurBordureDroite = couleurDroite.darker();
        this.couleurBordureGauche = couleurGauche.darker();

    }

    public double getHauteur() {
        return hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getBordureDroite() {
        return bordureDroite;
    }

    public double getBordureHaut() {
        return bordureHaut;
    }

    public double getBordureBas() {
        return bordureBas;
    }

    public double getBordureGauche() {
        return bordureGauche;
    }

    public void setBordureDroite(double bordureDroite) {
        this.bordureDroite = bordureDroite;
    }

    public void setBordureHaut(double bordureHaut) {
        this.bordureHaut = bordureHaut;
    }

    public void setBordureBas(double bordureBas) {
        this.bordureBas = bordureBas;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public void setBordureGauche(double bordureGauche) {
        this.bordureGauche = bordureGauche;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }
    
    public Color getCouleurBordureBas() {
        return couleurBordureBas;
    }

    public void setCouleurBordureBas(Color couleurBordureBas) {
        this.couleurBordureBas = couleurBordureBas;
    }

  
    public Color getCouleurBordureGauche() {
        return couleurBordureGauche;
    }

    public void setCouleurBordureGauche(Color couleurBordureGauche) {
        this.couleurBordureGauche = couleurBordureGauche;
    }

    public Color getCouleurBordureDroite() {
        return couleurBordureDroite;
    }

    public void setCouleurBordureDroite(Color couleurBordureDroite) {
        this.couleurBordureDroite = couleurBordureDroite;
    }

    public Color getCouleurBordureHaut() {
        return couleurBordureHaut;
    }

    public void setCouleurBordureHaut(Color couleurBordureHaut) {
        this.couleurBordureHaut = couleurBordureHaut;
    }


}
