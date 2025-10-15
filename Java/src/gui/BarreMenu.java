/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.barremenu.ActionAffichageNoms;
import gui.barremenu.ActionAffichagePersistance;
import gui.barremenu.ActionChargerInstrument;
import gui.barremenu.ActionChoisirImageFond;
import gui.barremenu.ActionGenererGuitare;
import gui.barremenu.ActionGenererPiano;
import gui.barremenu.ActionImporterPiece;
import gui.barremenu.ActionModeAjout;
import gui.barremenu.ActionModeJeu;
import gui.barremenu.ActionModeJeuPratique;
import gui.barremenu.ActionModeSelection;
import gui.barremenu.ActionQuitter;
import gui.barremenu.ActionReinitialiserInstrument;
import gui.barremenu.ActionSauvegarderInstrument;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 */
public class BarreMenu extends JMenuBar {

    private JMenu fichierMenu;
    private JMenu optionsMenu;
    private JMenu affichageMenu;
    private JMenu aideMenu;
    private JMenu gabaritsMenu;
    private final InterfacePrincipale interfacePrincipale;

    private JRadioButtonMenuItem modeAjoutTouches;
    private JRadioButtonMenuItem modeJeu;
    private JRadioButtonMenuItem modeSelection;
    private JRadioButtonMenuItem modePratique;
    private JCheckBoxMenuItem affichageNoms;
    private JCheckBoxMenuItem affichagePersistances;
    private JCheckBoxMenuItem affichageTimbre;
    private JMenuItem chargerInstrument;
    private JMenuItem choisirImageInstrument;
    private JMenuItem genenerGuitare;
    private JMenuItem genererPiano;
    private JMenuItem sauvegarderInstrument;
    private JMenuItem reinitialiserInstrument;
    private JMenuItem quitterApplication;
    private JMenuItem importerPiece;
    private ButtonGroup groupeMenuMode;

    public BarreMenu(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
        groupeMenuMode = new ButtonGroup();
        quitterApplication = new JMenuItem("Quitter");
        chargerInstrument = new JMenuItem("Charger un instrument");
        sauvegarderInstrument = new JMenuItem("Sauvegarder un instrument");
        importerPiece = new JMenuItem("Importer une pièce");
        reinitialiserInstrument = new JMenuItem("Reinitialiser le jeu");
        fichierMenu = new JMenu("Fichier");
        optionsMenu = new JMenu("Options de jeu");
        affichageMenu = new JMenu("Options d'affichage");
        gabaritsMenu = new JMenu("Gabarits préprogrammés");
        modeAjoutTouches = new JRadioButtonMenuItem("Mode ajout de touches");
        modeJeu = new JRadioButtonMenuItem("Mode Jeu");
        modeSelection = new JRadioButtonMenuItem("Mode sélection", true);
        modePratique = new JRadioButtonMenuItem("Mode Pratique");
        affichageNoms = new JCheckBoxMenuItem("Afficher les noms des touches");
        affichagePersistances = new JCheckBoxMenuItem("Afficher la persistance");
        choisirImageInstrument = new JMenuItem("Choisir une image de fond");
        genenerGuitare = new JMenuItem("Générer une guitare");
        genererPiano = new JMenuItem("Générer un piano");

        groupeMenuMode.add(modeJeu);
        groupeMenuMode.add(modeAjoutTouches);
        groupeMenuMode.add(modePratique);
        groupeMenuMode.add(modeSelection);

        initialiser();
    }

    private void initialiser() {
        quitterApplication.addActionListener(new ActionQuitter());
        modeSelection.addActionListener(new ActionModeSelection(interfacePrincipale));
        modeAjoutTouches.addActionListener(new ActionModeAjout(interfacePrincipale));
        modeJeu.addActionListener(new ActionModeJeu(interfacePrincipale));
        modePratique.addActionListener(new ActionModeJeuPratique(interfacePrincipale));

        fichierMenu.add(choisirImageInstrument);
        fichierMenu.add(chargerInstrument);
        fichierMenu.add(sauvegarderInstrument);
        fichierMenu.add(importerPiece);
        fichierMenu.add(reinitialiserInstrument);
        fichierMenu.add(quitterApplication);
        add(fichierMenu);

        optionsMenu.add(modeAjoutTouches);
        optionsMenu.add(modeJeu);
        optionsMenu.add(modePratique);
        optionsMenu.add(modeSelection);
        add(optionsMenu);

        affichageNoms.addActionListener(new ActionAffichageNoms(interfacePrincipale));
        affichagePersistances.addActionListener(new ActionAffichagePersistance(interfacePrincipale));
        chargerInstrument.addActionListener(new ActionChargerInstrument(interfacePrincipale));
        sauvegarderInstrument.addActionListener(new ActionSauvegarderInstrument(interfacePrincipale));
        choisirImageInstrument.addActionListener(new ActionChoisirImageFond(interfacePrincipale));
        importerPiece.addActionListener(new ActionImporterPiece(interfacePrincipale));
        reinitialiserInstrument.addActionListener(new ActionReinitialiserInstrument(interfacePrincipale));

        affichageMenu.add(affichageNoms);
        affichageMenu.add(affichagePersistances);
        add(affichageMenu);
        
        genenerGuitare.addActionListener(new ActionGenererGuitare(interfacePrincipale));
        genererPiano.addActionListener(new ActionGenererPiano(interfacePrincipale));
        gabaritsMenu.add(genenerGuitare);
        gabaritsMenu.add(genererPiano);
        add(gabaritsMenu);
        //add(aideMenu);
    }
}
