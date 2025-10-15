/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Image;

/**
 *
 */
public class ProprietesTouche {

    public int largeurTouche;
    public int hauteurTouche;
    public int rayonTouche;
    public int bordureCercle;
    public int bordureHaut;
    public int bordureBas;
    public int bordureDroite;
    public int bordureGauche;
    public int bordureCentre;
    public String formeTouche;
    public Color couleurBordureCercle;
    public Color couleurFondTouche;
    public Color couleurBordureHaut;
    public Color couleurBordureBas;
    public Color couleurBordureGauche;
    public Color couleurBordureDroite;
    public Color couleurBordureCentre;
    public Image imageFondTouche;

    public ProprietesTouche(InterfacePrincipale interfacePrincipale) {
        this.largeurTouche = interfacePrincipale.getLargeurRect();
        this.hauteurTouche = interfacePrincipale.getHauteurRect();
        this.rayonTouche = interfacePrincipale.getRayonCercle();
        this.bordureCercle = interfacePrincipale.getBordureCercle();
        this.bordureHaut = interfacePrincipale.getBordureHaut();
        this.bordureGauche = interfacePrincipale.getBordureGauche();
        this.bordureBas = interfacePrincipale.getBordureBas();
        this.bordureDroite = interfacePrincipale.getBordureDroite();
        this.couleurBordureCercle = interfacePrincipale.getCouleurBordureCercle();
        this.couleurFondTouche = interfacePrincipale.getCouleurFondTouche();
        this.formeTouche = interfacePrincipale.getFormeSelectionne();
        this.imageFondTouche = interfacePrincipale.getImageFondTouche();
        this.bordureCentre = interfacePrincipale.getBordureCentre();
        this.couleurBordureCentre = interfacePrincipale.getCouleurBordureCentre();
        this.couleurBordureBas = interfacePrincipale.getCouleurBordureBas();
        this.couleurBordureDroite = interfacePrincipale.getCouleurBordureDroite();
        this.couleurBordureGauche = interfacePrincipale.getCouleurBordureGauche();
        this.couleurBordureHaut = interfacePrincipale.getCouleurBordureHaut();
    }

}
