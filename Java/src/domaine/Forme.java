/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;


/**
 *
 * 
 */
public abstract class Forme implements Serializable{
    private Color couleurFond;
    transient private Image imageFond;
    private boolean estFormeImageFond;
    private Color couleurBordureCentre;
    private Color couleurBordureHaut;
    private Color couleurBordureBas;
    private Color couleurBordureGauche;
    private Color couleurBordureDroite;
    private Color couleurBordureCercle;
    
    public Image getImageFond() {
        return imageFond;
    }

    public void setImageFond(Image imageFond) {
        this.imageFond = imageFond;
    }

    public boolean isEstFormeImageFond() {
        return estFormeImageFond;
    }

    public void setEstFormeImageFond(boolean estFormeImageFond) {
        this.estFormeImageFond = estFormeImageFond;
    }

    public Color getCouleurBordureCentre() {
        return couleurBordureCentre;
    }

    public void setCouleurBordureCentre(Color couleurBordureCentre) {
        this.couleurBordureCentre = couleurBordureCentre;
    }
    
    public Color getCouleurBordureHaut() {
        return couleurBordureHaut;
    }

    public void setCouleurBordureHaut(Color couleurBordureHaut) {
        this.couleurBordureHaut = couleurBordureHaut;
    }
    
    public Color getCouleurBordureBas() {
        return couleurBordureBas;
    }

    public void setCouleurBordureBas(Color couleurBordureBas) {
        this.couleurBordureBas = couleurBordureBas;
    }
    
    public Color getCouleurBordureDroite() {
        return couleurBordureDroite;
    }

    public void setCouleurBordureDroite(Color couleurBordureDroite) {
        this.couleurBordureDroite = couleurBordureDroite;
    }
    
    public Color getCouleurBordureGauche() {
        return couleurBordureGauche;
    }

    public void setCouleurBordureGauche(Color couleurBordureGauche) {
        this.couleurBordureGauche = couleurBordureGauche;
    }
    
    public double getBordureCentre() {
        return bordureCentre;
    }

    public void setBordureCentre(double bordureCentre) {
        this.bordureCentre = bordureCentre;
    }
    private double bordureCentre;
    
    public Forme(Color couleurFond, Color couleurBordureCentre, double bordureCentre){
        this.couleurFond = couleurFond;
        this.couleurBordureCentre = couleurBordureCentre;
        this.bordureCentre = bordureCentre;
        estFormeImageFond = false;
    }
    public Forme(Image imageFond, Color couleurBordureCentre, double bordureCentre){
        this.imageFond = imageFond;
        this.couleurBordureCentre = couleurBordureCentre;
        this.bordureCentre = bordureCentre;
        estFormeImageFond = true;
    }
    
    public void setCouleurFond(Color color) {
        this.couleurFond = color;
    }
    
    public Color getCouleurFond() {
        return couleurFond;
    }    
    
    public Image getImagefond(){
        return imageFond;
    }
    
    public Color getCouleurBordureCercle() {
        return couleurBordureCercle;
    }

    public void setCouleurBordureCercle(Color couleurBordureCercle) {
        this.couleurBordureCercle = couleurBordureCercle;
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (imageFond != null)
            ImageIO.write((BufferedImage) imageFond, "png", out);

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        imageFond = (ImageIO.read(in));
    }
   
}
