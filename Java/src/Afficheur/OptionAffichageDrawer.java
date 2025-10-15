package Afficheur;

import domaine.Cercle;
import domaine.Coordonnee;
import domaine.Forme;
import domaine.Note;
import domaine.Rectangle;
import java.awt.Graphics;
import java.awt.Point;

import domaine.Touche;
import java.awt.Color;
import java.awt.Dimension;

public class OptionAffichageDrawer {

    private int dimensionMin;
    private Dimension dimensionZoneJeu;

    OptionAffichageDrawer(Touche touche, Dimension dimensionZoneJeu) {
        dimensionMin = Integer.min(dimensionZoneJeu.height, dimensionZoneJeu.width);
        this.dimensionZoneJeu = dimensionZoneJeu;
    }


    //NOM TOUCHE//////////////////////////////////////////////////////////////
    public void drawOptionNom(Graphics g, Touche touche) {
        Color color = Color.black;
        g.setColor(color);
        Forme formeTouche = touche.getForme();
        if (formeTouche instanceof Cercle) {
            drawOptionNomCercle(g, touche);
        } else if (formeTouche instanceof Rectangle) {
            drawOptionNomRectangle(g, touche);
        }
    }
    
    private void drawOptionNomCercle(Graphics g, Touche touche) {
        String nom = touche.getSon().getNom();
        if(touche.getSon() instanceof Note){
            nom = ((Note)touche.getSon()).getNomOctave();
        }
        Cercle cercleTouche = (Cercle) touche.getForme();
        int rayon = (int) ((cercleTouche.getRayon()+cercleTouche.getBordure()) * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        g.drawString(nom, centreTouche.x - rayon, centreTouche.y - (rayon + rayon/4));
    }
    
    private void drawOptionNomRectangle(Graphics g, Touche touche) {
        String nom = touche.getSon().getNom();
        if(touche.getSon() instanceof Note){
            nom = ((Note)touche.getSon()).getNomOctave();
        }
        Rectangle rectangleTouche = (Rectangle) touche.getForme();
        int largeur = (int) ((rectangleTouche.getLargeur()) * dimensionMin);
        int hauteur = (int) ((rectangleTouche.getHauteur()+ rectangleTouche.getBordureHaut()) * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        g.drawString(nom, centreTouche.x - largeur/2, centreTouche.y - (hauteur + hauteur/2)/2);
    }
    
    //PERSISTANCE//////////////////////////////////////////////////////////////
    public void drawOptionPersistance(Graphics g, Touche touche) {
        if(touche.getSon() instanceof Note){
            Forme formeTouche = touche.getForme();
            if (formeTouche instanceof Cercle) {
                drawOptionPersistanceCercle(g, touche);
            }else if (formeTouche instanceof Rectangle) {
                drawOptionPersistanceRectangle(g, touche);
            }
        }
    }
    
    private void drawOptionPersistanceCercle(Graphics g, Touche touche) {
        Note note = (Note)touche.getSon();
        String persistance = String.valueOf(note.getPersistance());
        Cercle cercleTouche = (Cercle) touche.getForme();
        int rayon = (int) ((cercleTouche.getRayon()+cercleTouche.getBordure()) * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        g.drawString(persistance, centreTouche.x + (rayon + rayon/4), centreTouche.y + (rayon + rayon/4));
    }
    
    private void drawOptionPersistanceRectangle(Graphics g, Touche touche) {
        Note note = (Note)touche.getSon();
        String persistance = String.valueOf(note.getPersistance());
        Rectangle rectangleTouche = (Rectangle) touche.getForme();
        int hauteur = (int) ((rectangleTouche.getHauteur() + rectangleTouche.getBordureHaut()) * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        g.drawString(persistance, centreTouche.x, centreTouche.y + (hauteur + hauteur/2)/2 + 2);
    }
    
    //TIMBRE//////////////////////////////////////////////////////////////
    public void drawOptionTimbre(Graphics g, Touche touche) {
        if(touche.getSon() instanceof Note){
            Forme formeTouche = touche.getForme();
            if (formeTouche instanceof Cercle) {
                drawOptionTimbreCercle(g, touche);
            }else if (formeTouche instanceof Rectangle) {
                drawOptionTimbreRectangle(g, touche);
            }
        }
    }
    
    private void drawOptionTimbreCercle(Graphics g, Touche touche) {
        Note note = (Note)touche.getSon();
        String timbre = String.valueOf(note.getTimbre());
        Cercle cercleTouche = (Cercle) touche.getForme();
        int rayon = (int) ((cercleTouche.getRayon()+cercleTouche.getBordure()) * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        g.drawString(timbre, centreTouche.x, centreTouche.y + (rayon + rayon/4));
    }
    
    private void drawOptionTimbreRectangle(Graphics g, Touche touche) {
        Note note = (Note)touche.getSon();
        String timbre = String.valueOf(note.getTimbre());
        Rectangle rectangleTouche = (Rectangle) touche.getForme();
        int hauteur = (int) ((rectangleTouche.getHauteur() + rectangleTouche.getBordureHaut()) * dimensionMin);
        int largeur = (int) ((rectangleTouche.getLargeur()) * dimensionMin);
        Coordonnee coordonneeTouche = touche.getCoordonnee();
        Point centreTouche = new Point((int) (coordonneeTouche.getRelativeX() * dimensionZoneJeu.width), (int) (coordonneeTouche.getRelativeY() * dimensionZoneJeu.height));
        g.drawString(timbre, centreTouche.x - largeur, centreTouche.y + (hauteur + hauteur/2)/2 + 15);
    }

}
