/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Date;


/**
 *
 * 
 */
public class Touche implements java.io.Serializable { 
    private Son son ;
    private Boolean estSelectionnee ;
    private Forme forme ;
    private Coordonnee coordonnee ;
    private Boolean estRecherchee;

    public Boolean getEstRecherchee() {
        return estRecherchee;
    }

    public void setEstRecherchee(Boolean estRecherchee) {
        this.estRecherchee = estRecherchee;
    }
  

    public Touche(Son son, Forme forme,Coordonnee coordonnee){
        this.son = son;
        this.estSelectionnee = false;
        this.estRecherchee = false;
        this.forme = forme;
        this.coordonnee = coordonnee;
       
    }

    //Touche(Son sonTouche, Forme formeTouche, Coordonnee coordonneeCentreTouche) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    public Son getSon() {
        return son;
    }    
    
    public Boolean getEstSélectionnee() {
        return estSelectionnee;
    }
    
    public Forme getForme() {
        return forme;
    }    
    
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }    
    
    public void setSon(Son son) {
        this.son = son;
    }     
   
    public void setEstSélectionnee(Boolean estSelectionnee) {
        this.estSelectionnee = estSelectionnee;
    }
  
    
    public void setForme(Forme forme) {
        this.forme = forme;
    }
        
    public void setListeCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }
    
    public void switchEstSelection() {
		this.estSelectionnee = !this.estSelectionnee;
    }
    
    public void nouveauPoint(Point pointSouris, Dimension dimensionZoneJeu) {
        this.coordonnee = new Coordonnee(pointSouris.x, pointSouris.y, dimensionZoneJeu);
	}
}

