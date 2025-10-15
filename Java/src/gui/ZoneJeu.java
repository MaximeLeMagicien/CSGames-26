/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Afficheur.InstrumentDrawer;
import Afficheur.MainDrawer;
import Afficheur.ToucheDrawer;
import Afficheur.ToucheDrawerFactory;
import gui.zonejeu.MotionListener;
import gui.zonejeu.ZoneJeuListener;
import java.awt.Graphics;
import javax.swing.JPanel;
import domaine.Observateur;

/**
 *
 */
public class ZoneJeu extends JPanel implements Observateur {
    private final InterfacePrincipale interfacePrincipale;
    private ToucheDrawerFactory toucheDrawerFactory;
    private InstrumentDrawer instrumentDrawer;
    
    public ZoneJeu(InterfacePrincipale interfacePrincipale){
        this.interfacePrincipale = interfacePrincipale;
        initialiser();
    }
    
    public void initialiser(){
        addMouseListener(new ZoneJeuListener(interfacePrincipale));
        addMouseMotionListener(new MotionListener(interfacePrincipale));
        interfacePrincipale.controleur.ajouterObservateur(this);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
	MainDrawer mainDrawer = new MainDrawer(interfacePrincipale.controleur,
				new ToucheDrawerFactory(interfacePrincipale),new InstrumentDrawer(interfacePrincipale), this.getSize());
        mainDrawer.draw(g);
        
    }
    
    @Override
    public void changementJeu(){
        repaint();
    }
    
}
