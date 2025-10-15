/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.awt.Dimension;



/**
 *
 * 
 */
public class Coordonnee implements java.io.Serializable {
    
    private double relativeX ;
    private double relativeY ;
    

    public Coordonnee( double relativeX , double relativeY){
        this.relativeX = relativeX;
        this.relativeY = relativeY;
       
    }
    
   public Coordonnee(int pixelsX, int pixelsY, Dimension dimensionsZoneJeu){
       //double dimensionMin = (double)Integer.min(dimensionsZoneJeu.width, dimensionsZoneJeu.height);
       this.relativeX = pixelsX /  (double)dimensionsZoneJeu.width;
       this.relativeY = pixelsY / (double)dimensionsZoneJeu.height;
   } 
   
    public double getRelativeX() {
        return relativeX ;
    }    
    
    public double getRelativeY() {
        return relativeY;
    }    
    
    public void setRelativeX(double relativeX) {
        this.relativeX = relativeX;
    }
    
    public void setRelativeY(double relativeY) {
        this.relativeY = relativeY;
    }
    
}
