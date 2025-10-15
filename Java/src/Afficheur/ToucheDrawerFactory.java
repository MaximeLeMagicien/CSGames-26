package Afficheur;

import gui.InterfacePrincipale;
import domaine.Touche;
import Afficheur.ToucheDrawer;
import java.awt.Dimension;


public class ToucheDrawerFactory{
    private Dimension dimensionZoneJeu;

	public ToucheDrawerFactory(InterfacePrincipale outer) {
            dimensionZoneJeu = outer.zoneJeu.getSize();
	}

	public ToucheDrawer getItemDrawer(Touche touche) {
		return new ToucheDrawer(touche,dimensionZoneJeu);
	}
        
        public OptionAffichageDrawer getItemOptionAffichageDrawer(Touche touche) {
		return new OptionAffichageDrawer(touche,dimensionZoneJeu);
	}
                    
}
