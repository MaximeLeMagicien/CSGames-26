/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.zonejeu;

import gui.InterfacePrincipale;
import gui.OptionsGUI;
import gui.ProprietesSon;
import gui.ProprietesTouche;
import jdk.jshell.spi.ExecutionControl;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.SwingUtilities;

/**
 *
 */
public class ZoneJeuListener extends MouseAdapter {

    final InterfacePrincipale interfacePrincipale;

    public ZoneJeuListener(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point pointSouris = e.getPoint();
        interfacePrincipale.actualMousePoint = e.getPoint();
        Dimension dimensionZoneJeu = interfacePrincipale.zoneJeu.getSize();

        switch (interfacePrincipale.controleur.getMode()) {
            case AJOUTTOUCHE:
                OptionsGUI options = new OptionsGUI(interfacePrincipale);
                ProprietesSon propsSon = new ProprietesSon(interfacePrincipale);

                ProprietesTouche propsTouche = new ProprietesTouche(interfacePrincipale);

                interfacePrincipale.controleur.creerTouche(pointSouris, options, propsSon, propsTouche, dimensionZoneJeu); // remplacer interface par les caracteristiques necessaires?
                break;
                case SELECTION:
                if(SwingUtilities.isLeftMouseButton(e)){
                    interfacePrincipale.controleur.switchEstSelectionneeTouches();
                    interfacePrincipale.controleur.toucheSelectionnee(pointSouris, dimensionZoneJeu);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point pointSouris = e.getPoint();
        Dimension dimensionZoneJeu = interfacePrincipale.zoneJeu.getSize();

        switch (interfacePrincipale.controleur.getMode()) {
            case JEU:
                interfacePrincipale.controleur.jouerTouche(pointSouris, dimensionZoneJeu);
                break;
            case JEUPRATIQUE:
                interfacePrincipale.controleur.jouerTouche(pointSouris, dimensionZoneJeu);
                break;
            default:
                throw new RuntimeException("should never happen");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Dimension dimensionZoneJeu = interfacePrincipale.zoneJeu.getSize();

        switch (interfacePrincipale.controleur.getMode()) {
            case JEU:
                interfacePrincipale.controleur.arretTouche();
                break;
            case JEUPRATIQUE:
                interfacePrincipale.controleur.arretTouche();
                break;
            default:
                break;

        }
    }
}
