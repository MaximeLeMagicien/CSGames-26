package Afficheur;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import domaine.*;

public class MainDrawer {

	private final Controleur controler;
	private ToucheDrawerFactory toucheDrawerFactory;
	private InstrumentDrawer instrumentDrawer;
	private Dimension initialDimension;

	public MainDrawer(Controleur controler,
			ToucheDrawerFactory toucheDrawerFactory, InstrumentDrawer instrumentDrawer,
			Dimension initialDimension) {
		this.controler = controler;
		this.toucheDrawerFactory = toucheDrawerFactory;
		this.instrumentDrawer = instrumentDrawer;
		this.initialDimension = initialDimension;
	}

	public void draw(Graphics g) {
		instrumentDrawer.drawInstrument(g, initialDimension);
		List<Touche> touches = controler.getInstrument().getListeTouches();
		for (Touche touche : touches) {
                    ToucheDrawer toucheDrawer = toucheDrawerFactory.getItemDrawer(touche);
                    OptionAffichageDrawer optionAffichageDrawer = toucheDrawerFactory.getItemOptionAffichageDrawer(touche);
                    if(controler.getAffichageNom()){
                        optionAffichageDrawer.drawOptionNom(g, touche);
                    }
                    if(controler.getAffichagePersistance()){
                        optionAffichageDrawer.drawOptionPersistance(g, touche);
                    }
                    if(controler.getAffichageTimbre()){
                        optionAffichageDrawer.drawOptionTimbre(g, touche);
                    }
                    toucheDrawer.drawTouche(g, touche);
		}
	}
        
}
