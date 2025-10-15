package Afficheur;

import domaine.Cercle;
import domaine.Coordonnee;
import domaine.Forme;
import domaine.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import domaine.Touche;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;

public class ToucheDrawer {

    private int dimensionMin;
    private Dimension dimensionZoneJeu;
    private Color colorCentre;
    private Color colorHaut;
    private Color colorBas;
    private Color colorDroite;
    private Color colorGauche;
    private Color colorFond;
    private Color colorCercleBordure;
    private Color colorCercle;
    private BufferedImage imageFond;

    ToucheDrawer(Touche touche, Dimension dimensionZoneJeu) {
        dimensionMin = Integer.min(dimensionZoneJeu.height, dimensionZoneJeu.width);
        this.dimensionZoneJeu = dimensionZoneJeu;
    }

    public void drawTouche(Graphics g, Touche touche) {
        Forme formeTouche = touche.getForme();
        if (formeTouche instanceof Cercle) {
            drawToucheCercle(g, touche);
        } else if (formeTouche instanceof Rectangle) {
            drawToucheRectangle(g, touche);
        }
    }

    public void drawToucheCercle(Graphics g, Touche touche) {
        Cercle cercleTouche = (Cercle) touche.getForme();
        int rayon = (int) (cercleTouche.getRayon() * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        int rayonBordure = ((int) (cercleTouche.getBordure() * dimensionMin)) + rayon;
        colorCercleBordure = cercleTouche.getCouleurBordureCercle();
        colorCercle = cercleTouche.getCouleurFond();
        if (touche.getSon().getEnCours() || touche.getEstRecherchee()) {
            surbrillance(colorCercleBordure, colorCercle);
        }
        //BORDURE DU CERCLE
        g.setColor(colorCercleBordure);
        g.fillOval(centreTouche.x - rayonBordure, centreTouche.y - rayonBordure, rayonBordure * 2, rayonBordure * 2);
        //Bordure de sélection
        if (touche.getEstSélectionnee()) {
            bordureSelection(g, centreTouche.x - rayonBordure, centreTouche.y - rayonBordure, (rayonBordure * 2), (rayonBordure * 2));
        }
        //Touche
        //Image de fond                
        if (cercleTouche.getImageFond() != null) {
            imageFond = (BufferedImage) cercleTouche.getImageFond();
            if (touche.getSon().getEnCours() || touche.getEstRecherchee()) {
                imageFond = copierImage(imageFond);
                RescaleOp surbrillance = new RescaleOp(1.3f, 0, null);
                surbrillance.filter(imageFond, imageFond);
            }

            Shape formeImage = new Ellipse2D.Double(centreTouche.x - rayon, centreTouche.y - rayon, rayon * 2, rayon * 2);
            g.setClip(formeImage);
            g.drawImage(imageFond, centreTouche.x - rayon, centreTouche.y - rayon, (rayon * 2), (rayon * 2), null);
            g.setClip(null);
        } else {
            g.setColor(colorCercle);
            g.fillOval(centreTouche.x - rayon, centreTouche.y - rayon, rayon * 2, rayon * 2);
        }
    }

    public void drawToucheRectangle(Graphics g, Touche touche) {
        Rectangle rectangleTouche = (Rectangle) touche.getForme();
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        int hauteur = (int) (rectangleTouche.getHauteur() * dimensionMin);
        int largeur = (int) (rectangleTouche.getLargeur() * dimensionMin);
        int pointYBordure = (int) ((rectangleTouche.getBordureBas() - rectangleTouche.getBordureHaut()) * dimensionMin);
        int pointXBordure = (int) ((rectangleTouche.getBordureDroite() - rectangleTouche.getBordureGauche()) * dimensionMin);
        int hauteurBordure = (int) ((rectangleTouche.getBordureBas() + rectangleTouche.getBordureHaut()) * dimensionMin) + hauteur;
        int largeurBordure = (int) ((rectangleTouche.getBordureDroite() + rectangleTouche.getBordureGauche()) * dimensionMin) + largeur;
        int pointXDroite = (int) ((rectangleTouche.getBordureDroite()) * dimensionMin);
        int largeurDroite = (int) ((rectangleTouche.getBordureDroite()) * dimensionMin);
        int pointXGauche = (int) ((rectangleTouche.getBordureGauche()) * dimensionMin);
        int largeurGauche = (int) ((rectangleTouche.getBordureGauche()) * dimensionMin);
        int pointYHaut = (int) ((rectangleTouche.getBordureHaut()) * dimensionMin);
        int hauteurHaut = (int) ((rectangleTouche.getBordureHaut()) * dimensionMin);
        int pointYBas = (int) ((rectangleTouche.getBordureBas()) * dimensionMin);
        int hauteurBas = (int) ((rectangleTouche.getBordureBas()) * dimensionMin);
        colorCentre = rectangleTouche.getCouleurBordureCentre();
        colorHaut = rectangleTouche.getCouleurBordureHaut();
        colorBas = rectangleTouche.getCouleurBordureBas();
        colorDroite = rectangleTouche.getCouleurBordureDroite();
        colorGauche = rectangleTouche.getCouleurBordureGauche();
        colorFond = rectangleTouche.getCouleurFond();

        if (touche.getSon().getEnCours() || touche.getEstRecherchee()) {
            surbrillance(colorCentre, colorHaut, colorBas, colorDroite, colorGauche, colorFond);
        }
        //BORDURE HAUT
        g.setColor(colorHaut);
        g.fillRect((int) centreTouche.x - largeurBordure / 2 + pointXBordure / 2, (int) centreTouche.y - (hauteurHaut + hauteur) / 2 - pointYHaut / 2, largeurBordure, hauteurHaut + hauteur);
        //BORDURE BAS
        g.setColor(colorBas);
        g.fillRect((int) centreTouche.x - largeurBordure / 2 + pointXBordure / 2, (int) centreTouche.y - (hauteurBas + hauteur) / 2 + pointYBas / 2, largeurBordure, hauteurBas + hauteur);
        //BORDURE DROITE
        g.setColor(colorDroite);
        g.fillRect((int) centreTouche.x - (largeurDroite + largeur) / 2 + pointXDroite / 2, (int) centreTouche.y - hauteur / 2, largeurDroite + largeur, hauteur);
        //BORDURE GAUCHE
        g.setColor(colorGauche);
        g.fillRect((int) centreTouche.x - (largeurGauche + largeur) / 2 - pointXGauche / 2, (int) centreTouche.y - hauteur / 2, largeurGauche + largeur, hauteur);
        //Image de fond
        if (rectangleTouche.getImageFond() != null) {
            imageFond = (BufferedImage) rectangleTouche.getImageFond();
            if (touche.getSon().getEnCours()  || touche.getEstRecherchee()) {
                imageFond = copierImage(imageFond);
                RescaleOp surbrillance = new RescaleOp(1.3f, 0, null);
                surbrillance.filter(imageFond, imageFond);
            }

            Shape formeImage = new java.awt.Rectangle(centreTouche.x - largeur / 2, (int) centreTouche.y - hauteur / 2, largeur, hauteur);
            g.setClip(formeImage);
            g.drawImage(imageFond,(int) centreTouche.x - largeur / 2, (int) centreTouche.y - hauteur / 2, largeur, hauteur, null);
            g.setClip(null);
        } else {//TOUCHE
            g.setColor(colorFond);
            g.fillRect((int) centreTouche.x - largeur / 2, (int) centreTouche.y - hauteur / 2, largeur, hauteur);
        }
        //BORDURE CENTRE
        int hauteurCentre = (int) ((rectangleTouche.getBordureCentre()) * dimensionMin);
        g.setColor(colorCentre);
        g.fillRect((int) centreTouche.x - largeur / 2, (int) centreTouche.y - hauteurCentre / 2, largeur, hauteurCentre);
        //Bordure de selection
        if (touche.getEstSélectionnee()) {
            bordureSelection(g, centreTouche.x - (largeurGauche + largeur + largeurDroite) / 2 + pointXBordure / 2, centreTouche.y - (hauteurBas + hauteur + hauteurHaut) / 2 + pointYBordure / 2, largeurBordure, hauteurBordure);
        }
    }

    private void bordureSelection(Graphics g, int x, int y, int largeur, int hauteur) {
        g.setColor(new Color(255, 0, 0));
        g.drawRect((int) x, y, largeur, hauteur);
        g.drawRect((int) x - 1, y - 1, largeur + 2, hauteur + 2);
    }

    private void surbrillance(Color colorCentre, Color colorHaut, Color colorBas, Color colorDroite, Color colorGauche, Color colorFond) {
        this.colorCentre = colorCentre.brighter();
        this.colorHaut = colorHaut.brighter();
        this.colorBas = colorBas.brighter();
        this.colorDroite = colorDroite.brighter();
        this.colorGauche = colorGauche.brighter();
        if (colorFond != null) {
            this.colorFond = colorFond.brighter();
        }
    }

    private void surbrillance(Color colorCercleBordure, Color colorCercle) {

        this.colorCercleBordure = colorCercleBordure.brighter();
        if (colorCercle != null) {
            this.colorCercle = colorCercle.brighter();
        }

    }

    static BufferedImage copierImage(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }

}
