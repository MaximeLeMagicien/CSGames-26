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
public class Cercle extends Forme {

    private double rayon;
    private double bordureCercle;
    private Color couleurBordureCercle;

    public double getBordureCercle() {
        return bordureCercle;
    }

    public void setBordureCercle(double bordureCercle) {
        this.bordureCercle = bordureCercle;
    }

    public Color getCouleurBordureCercle() {
        return couleurBordureCercle;
    }

    public void setCouleurBordureCercle(Color couleurBordureCercle) {
        this.couleurBordureCercle = couleurBordureCercle;
    }

    public Cercle(Color couleurFond, Color couleurBordureCercle, double rayon, double bordure, double bordureCentre, Color couleurBordureCentre) {
        super(couleurFond.darker(), couleurBordureCentre.darker(), bordureCentre);
        this.rayon = rayon;
        this.bordureCercle = bordure;
        this.couleurBordureCercle = couleurBordureCercle.darker();
    }

    public Cercle(Image imageFond, Color couleurBordureCercle, double rayon, double bordure, double bordureCentre, Color couleurBordureCentre) {
        super(imageFond, couleurBordureCentre.darker(), bordureCentre);
        this.rayon = rayon;
        this.couleurBordureCercle = couleurBordureCercle.darker();
        this.bordureCercle = bordure;
    }

    public double getRayon() {
        return rayon;
    }

    public double getBordure() {
        return bordureCercle;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public void setBordure(double bordure) {
        this.bordureCercle = bordure;
    }


}
