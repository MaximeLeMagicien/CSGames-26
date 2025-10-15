/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * 
 */
public class PanneauGauche extends javax.swing.JPanel {

    private File fichierAudio;
    private Image imageFondTouche;
    private HashMap<String, Color> traductionCouleur;
    private final InterfacePrincipale interfacePrincipale;
    private ArrayList<Integer> listIntBoucle;
    private Runnable thread;

    /**
     * Creates new form PanneauGauche
     */
    public PanneauGauche(InterfacePrincipale interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
        initComponents();
        construireDictCouleurs();

        txtBarreRecherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                interfacePrincipale.controleur.recherche(txtBarreRecherche.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                interfacePrincipale.controleur.recherche(txtBarreRecherche.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                interfacePrincipale.controleur.recherche(txtBarreRecherche.getText());
            }
        });
        listIntBoucle = new ArrayList<Integer>(Collections.nCopies(9, 0));
        setKeyBindings();
        thread = new Runnable() {
            public void run() {
                ActionListener updateBarreProgres = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jProgressBar1.setValue((int) interfacePrincipale.controleur.getInstrument().getSequenceur().getMicrosecondPosition());
//                        int progresSecondes = (jProgressBar1.getValue() / 1000000);
//                        int progresMinutes = progresSecondes / 60;
//                        String progres = String.format("%d:%02d", progresMinutes, progresSecondes % 60);
                        jProgressBar1.setString(interfacePrincipale.controleur.getPositionPieceEnMinutes());
                    }
                };
                Timer timerProgress = new Timer(25, updateBarreProgres);
                timerProgress.start();
            }
        };
        jProgressBar1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int nouvelleValeurSecondes = (int) Math.round(((double) evt.getX() / (double) jProgressBar1.getWidth()) * jProgressBar1.getMaximum());
                interfacePrincipale.controleur.getInstrument().getSequenceur().setMicrosecondPosition(nouvelleValeurSecondes);
                interfacePrincipale.controleur.startPieceMusicale();
            }
        });
        cboxTimbre.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                interfacePrincipale.controleur.getInstrument().setTimbrePiece(interfacePrincipale.getNomTimbre());
            }
        });
    }

    private void construireDictCouleurs() {
        traductionCouleur = new HashMap<String, Color>();
        traductionCouleur.put("Rouge", Color.RED);
        traductionCouleur.put("Jaune", Color.YELLOW);
        traductionCouleur.put("Vert", Color.GREEN);
        traductionCouleur.put("Bleu", Color.BLUE);
        traductionCouleur.put("Orange", Color.ORANGE);
        traductionCouleur.put("Noir", Color.BLACK);
    }

    public boolean getOptionFichier() {
        return this.rbtnFichierAudio.isSelected();
    }

    public boolean getOptionNote() {
        return this.rbtnNote.isSelected();
    }

    public String getTimbre() {
        return (String) this.cboxTimbre.getSelectedItem();
    }

    public String getNomNote() {
        return (String) this.cboxNote.getSelectedItem();
    }

    public int getPersistance() {
        try {
            return Integer.parseInt(this.txtPersistance.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getOctave() {
        return Integer.parseInt(this.cboxOctave.getSelectedItem().toString());
    }

    public String getFormeSelectionnee() { // Code de la méthode inspiré de https://stackoverflow.com/questions/35561244/get-the-name-of-the-selected-radio-button-from-button-group
        for (Enumeration<AbstractButton> boutonsForme = buttonGroup2.getElements(); boutonsForme.hasMoreElements();) {
            AbstractButton bouton = boutonsForme.nextElement();
            if (bouton.isSelected()) {
                return bouton.getText();
            }
        }
        return null;
    }

    public int getLargeurRect() {
        try {
            return Integer.parseInt(this.txtLargeur.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getHauteurRect() {
        try {
            return Integer.parseInt(this.txtHauteur.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getRayonCercle() {
        try {
            return Integer.parseInt((this.txtRayon.getText()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Color getCouleurBordureCercle() {
        return traductionCouleur.get(this.cboxCouleurBordureCercle.getSelectedItem().toString());
    }

    public Color getCouleurFondTouche() {
        return traductionCouleur.get(this.cboxCouleurTouche.getSelectedItem().toString());
    }

    public boolean getOptionCouleurFond() {
        return this.rbtnCouleurFondTouche.isSelected();
    }

    public boolean getOptionImageFond() {
        return this.rbtnImageFondTouche.isSelected();
    }

    public Image getImageFondTouche() {
        return this.imageFondTouche;
    }

    public int getBordureCercle() {
        try {
            return Integer.parseInt(this.txtBordureCercle.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getBordureHaut() {
        try {
            return Integer.parseInt(txtHaut.getText());

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getBordureBas() {
        try {
            return Integer.parseInt(txtBas.getText());

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getBordureGauche() {
        try {
            return Integer.parseInt(txtGauche.getText());

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getBordureDroite() {
        try {
            return Integer.parseInt(txtDroite.getText());

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getBordureCentre() {
        try {
            return Integer.parseInt(txtCentre.getText());

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public File getFichierAudio() {
        return this.fichierAudio;
    }

    public Color getCouleurBordureHaut() {
        return traductionCouleur.get(this.cboxCouleurBordureHaut.getSelectedItem().toString());
    }

    public Color getCouleurBordureDroite() {
        return traductionCouleur.get(this.cboxCouleurBordureDroite.getSelectedItem().toString());
    }

    public Color getCouleurBordureGauche() {
        return traductionCouleur.get(this.cboxCouleurBordureGauche.getSelectedItem().toString());
    }

    public Color getCouleurBordureBas() {
        return traductionCouleur.get(this.cboxCouleurBordureBas.getSelectedItem().toString());
    }

    public Color getCouleurBordureCentre() {
        return traductionCouleur.get(this.cboxCouleurBordureCentre.getSelectedItem().toString());
    }

    public void setNomInstrument(String nouveauNom) {
        txtNomInstrument.setText(nouveauNom);
    }

    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap inputMap = getInputMap(condition);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "1");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "2");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "3");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "4");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "5");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "6");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "7");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "8");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "9");

        actionMap.put("1", new KeyAction("1"));
        actionMap.put("2", new KeyAction("2"));
        actionMap.put("3", new KeyAction("3"));
        actionMap.put("4", new KeyAction("4"));
        actionMap.put("5", new KeyAction("5"));
        actionMap.put("6", new KeyAction("6"));
        actionMap.put("7", new KeyAction("7"));
        actionMap.put("8", new KeyAction("8"));
        actionMap.put("9", new KeyAction("9"));
    }

    private void boucle(int numeroBoucle) {
        int nbClick = listIntBoucle.get(numeroBoucle) + 1;
        listIntBoucle.set(numeroBoucle, nbClick);
        if (nbClick % 3 == 1) {
            interfacePrincipale.controleur.enregistrementBouclage();
        } else if (nbClick % 3 == 2) {
            interfacePrincipale.controleur.startBouclage(numeroBoucle);
        } else {
            interfacePrincipale.controleur.stopBouclage(numeroBoucle);
        }
    }

    void setTextePiece(String nouveauTexte) {
        JTextArea area = (JTextArea) this.jScrollPane1.getViewport().getView();
        area.setText(nouveauTexte);
    }

    private class KeyAction extends AbstractAction {

        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            int numeroBoucle = Integer.parseInt(actionEvt.getActionCommand());
            boucle(numeroBoucle-1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.public String getNomNote(){ return
     * (String) this.cboxNote.getSelectedItem(); }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPannelEdition = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        txtBarreRecherche = new javax.swing.JTextField();
        lblCaracTouche = new javax.swing.JLabel();
        btnModifierTouche = new javax.swing.JButton();
        btnSupprimerTouche = new javax.swing.JButton();
        cboxTimbre = new javax.swing.JComboBox<>();
        lblTimbre = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cboxNote = new javax.swing.JComboBox<>();
        cboxOctave = new javax.swing.JComboBox<>();
        txtPersistance = new javax.swing.JTextField();
        rbtnRectangle = new javax.swing.JRadioButton();
        rbtnCercle = new javax.swing.JRadioButton();
        txtRayon = new javax.swing.JTextField();
        txtLargeur = new javax.swing.JTextField();
        txtHauteur = new javax.swing.JTextField();
        cboxCouleurBordureCercle = new javax.swing.JComboBox<>();
        rbtnNote = new javax.swing.JRadioButton();
        rbtnFichierAudio = new javax.swing.JRadioButton();
        lblSonTouche = new javax.swing.JLabel();
        rbtnImageFondTouche = new javax.swing.JRadioButton();
        rbtnCouleurFondTouche = new javax.swing.JRadioButton();
        cboxCouleurTouche = new javax.swing.JComboBox<>();
        lblNomImagefond = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtHaut = new javax.swing.JTextField();
        txtDroite = new javax.swing.JTextField();
        txtGauche = new javax.swing.JTextField();
        txtBas = new javax.swing.JTextField();
        txtBordureCercle = new javax.swing.JTextField();
        txtCentre = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        cboxCouleurBordureHaut = new javax.swing.JComboBox<>();
        cboxCouleurBordureDroite = new javax.swing.JComboBox<>();
        cboxCouleurBordureGauche = new javax.swing.JComboBox<>();
        cboxCouleurBordureBas = new javax.swing.JComboBox<>();
        cboxCouleurBordureCentre = new javax.swing.JComboBox<>();
        txtNomInstrument = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanelJeu = new javax.swing.JPanel();
        btnMetronomeJeu = new javax.swing.JButton();
        sliderMetronomeJeu = new javax.swing.JSlider();
        jSeparator1 = new javax.swing.JSeparator();
        jProgressBar1 = new javax.swing.JProgressBar();
        btnStop = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnBoucle1 = new javax.swing.JButton();
        btnBoucle2 = new javax.swing.JButton();
        btnBoucle3 = new javax.swing.JButton();
        btnBoucle4 = new javax.swing.JButton();
        btnBoucle5 = new javax.swing.JButton();
        btnBoucle6 = new javax.swing.JButton();
        txtPiece = new javax.swing.JLabel();
        btnBoucle7 = new javax.swing.JButton();
        btnBoucle8 = new javax.swing.JButton();
        btnBoucle9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAffichagePiece = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setName(""); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        jPannelEdition.setName(""); // NOI18N
        jPannelEdition.setLayout(new java.awt.GridBagLayout());
        jPannelEdition.add(jTabbedPane3, new java.awt.GridBagConstraints());

        txtBarreRecherche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtBarreRecherche.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBarreRecherche.setText("Recherche");
        txtBarreRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarreRechercheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(txtBarreRecherche, gridBagConstraints);

        lblCaracTouche.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        lblCaracTouche.setText("Caractéristiques de la touche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(lblCaracTouche, gridBagConstraints);

        btnModifierTouche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnModifierTouche.setText("Modifier touche");
        btnModifierTouche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierToucheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(btnModifierTouche, gridBagConstraints);

        btnSupprimerTouche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnSupprimerTouche.setText("Supprimer touche");
        btnSupprimerTouche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerToucheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(btnSupprimerTouche, gridBagConstraints);

        cboxTimbre.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxTimbre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Piano", "Xylophone", "Guitare","Violon", "Trompette", "Flute"}));
        cboxTimbre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxTimbreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(cboxTimbre, gridBagConstraints);

        lblTimbre.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lblTimbre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimbre.setText("Timbre de l'instrument");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(lblTimbre, gridBagConstraints);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(jSeparator3, gridBagConstraints);

        cboxNote.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxNote.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" }));
        cboxNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxNoteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(cboxNote, gridBagConstraints);

        cboxOctave.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxOctave.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cboxOctave.setSelectedIndex(3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(cboxOctave, gridBagConstraints);

        txtPersistance.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtPersistance.setText("Persistance (ms)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(txtPersistance, gridBagConstraints);

        buttonGroup2.add(rbtnRectangle);
        rbtnRectangle.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        rbtnRectangle.setText("Rectangle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(rbtnRectangle, gridBagConstraints);

        buttonGroup2.add(rbtnCercle);
        rbtnCercle.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        rbtnCercle.setText("Cercle");
        rbtnCercle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCercleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(rbtnCercle, gridBagConstraints);

        txtRayon.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtRayon.setText("Rayon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPannelEdition.add(txtRayon, gridBagConstraints);

        txtLargeur.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtLargeur.setText("Largeur");
        txtLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLargeurActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPannelEdition.add(txtLargeur, gridBagConstraints);

        txtHauteur.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtHauteur.setText("Hauteur");
        txtHauteur.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPannelEdition.add(txtHauteur, gridBagConstraints);

        cboxCouleurBordureCercle.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurBordureCercle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPannelEdition.add(cboxCouleurBordureCercle, gridBagConstraints);

        buttonGroup1.add(rbtnNote);
        rbtnNote.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        rbtnNote.setText("Note");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(rbtnNote, gridBagConstraints);

        buttonGroup1.add(rbtnFichierAudio);
        rbtnFichierAudio.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        rbtnFichierAudio.setText("Fichier Audio");
        rbtnFichierAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFichierAudioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(rbtnFichierAudio, gridBagConstraints);

        lblSonTouche.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        lblSonTouche.setText("Son de la touche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.1;
        jPannelEdition.add(lblSonTouche, gridBagConstraints);

        buttonGroup3.add(rbtnImageFondTouche);
        rbtnImageFondTouche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        rbtnImageFondTouche.setText("Image de fond:");
        rbtnImageFondTouche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnImageFondToucheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(rbtnImageFondTouche, gridBagConstraints);

        buttonGroup3.add(rbtnCouleurFondTouche);
        rbtnCouleurFondTouche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        rbtnCouleurFondTouche.setText("Couleur de fond:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(rbtnCouleurFondTouche, gridBagConstraints);

        cboxCouleurTouche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurTouche.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        cboxCouleurTouche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxCouleurToucheActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPannelEdition.add(cboxCouleurTouche, gridBagConstraints);

        lblNomImagefond.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lblNomImagefond.setText("Nom du fichier chargé");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(lblNomImagefond, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridLayout(2, 0));

        txtHaut.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtHaut.setText("Haut");
        jPanel8.add(txtHaut);

        txtDroite.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtDroite.setText("Droite");
        jPanel8.add(txtDroite);

        txtGauche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtGauche.setText("Gauche");
        jPanel8.add(txtGauche);

        txtBas.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtBas.setText("Bas");
        jPanel8.add(txtBas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(jPanel8, gridBagConstraints);

        txtBordureCercle.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtBordureCercle.setText("Bordure cercle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(txtBordureCercle, gridBagConstraints);

        txtCentre.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        txtCentre.setText("Centre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(txtCentre, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridLayout(2, 0));

        cboxCouleurBordureHaut.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurBordureHaut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        jPanel9.add(cboxCouleurBordureHaut);

        cboxCouleurBordureDroite.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurBordureDroite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        jPanel9.add(cboxCouleurBordureDroite);

        cboxCouleurBordureGauche.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurBordureGauche.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        jPanel9.add(cboxCouleurBordureGauche);

        cboxCouleurBordureBas.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurBordureBas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        jPanel9.add(cboxCouleurBordureBas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(jPanel9, gridBagConstraints);

        cboxCouleurBordureCentre.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cboxCouleurBordureCentre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Jaune", "Vert", "Bleu", "Orange", "Noir" }));
        cboxCouleurBordureCentre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxCouleurBordureCentreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(cboxCouleurBordureCentre, gridBagConstraints);

        txtNomInstrument.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        txtNomInstrument.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNomInstrument.setText("Nom de l'instrument");
        txtNomInstrument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomInstrumentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPannelEdition.add(txtNomInstrument, gridBagConstraints);
        jPannelEdition.add(jPanel10, new java.awt.GridBagConstraints());

        jTabbedPane2.addTab("Édition", jPannelEdition);

        jPanelJeu.setName(""); // NOI18N
        jPanelJeu.setLayout(new java.awt.GridBagLayout());

        btnMetronomeJeu.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnMetronomeJeu.setText("Activer Métronome");
        btnMetronomeJeu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetronomeJeuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnMetronomeJeu, gridBagConstraints);

        sliderMetronomeJeu.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        sliderMetronomeJeu.setMajorTickSpacing(10);
        sliderMetronomeJeu.setMaximum(160);
        sliderMetronomeJeu.setPaintLabels(true);
        sliderMetronomeJeu.setPaintTicks(true);
        sliderMetronomeJeu.setToolTipText("BPM");
        sliderMetronomeJeu.setValue(80);
        sliderMetronomeJeu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderMetronomeJeuStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(sliderMetronomeJeu, gridBagConstraints);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 23;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanelJeu.add(jSeparator1, gridBagConstraints);

        jProgressBar1.setString("0:00");
        jProgressBar1.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanelJeu.add(jProgressBar1, gridBagConstraints);

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelJeu.add(btnStop, gridBagConstraints);

        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelJeu.add(btnPlay, gridBagConstraints);

        jLabel3.setText("Bouclage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(jLabel3, gridBagConstraints);

        btnBoucle1.setMnemonic('B');
        btnBoucle1.setText("Boucle 1");
        btnBoucle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle1, gridBagConstraints);

        btnBoucle2.setMnemonic('2');
        btnBoucle2.setText("Boucle 2");
        btnBoucle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle2, gridBagConstraints);

        btnBoucle3.setText("Boucle 3");
        btnBoucle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle3, gridBagConstraints);

        btnBoucle4.setText("Boucle 4");
        btnBoucle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle4, gridBagConstraints);

        btnBoucle5.setText("Boucle 5");
        btnBoucle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle5, gridBagConstraints);

        btnBoucle6.setText("Boucle 6");
        btnBoucle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle6, gridBagConstraints);

        txtPiece.setText("Piece");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(txtPiece, gridBagConstraints);

        btnBoucle7.setText("Boucle 7");
        btnBoucle7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle7, gridBagConstraints);

        btnBoucle8.setText("Boucle 8");
        btnBoucle8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle8, gridBagConstraints);

        btnBoucle9.setText("Boucle 9");
        btnBoucle9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(btnBoucle9, gridBagConstraints);

        txtAffichagePiece.setEditable(false);
        txtAffichagePiece.setColumns(20);
        txtAffichagePiece.setFont(new java.awt.Font("Courier", 0, 13)); // NOI18N
        txtAffichagePiece.setRows(5);
        jScrollPane1.setViewportView(txtAffichagePiece);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanelJeu.add(jScrollPane1, gridBagConstraints);

        jLabel4.setText("*Cliquez sur les boutons ou touche du clavier (F1-F9)");
        jLabel4.setMaximumSize(new java.awt.Dimension(481, 32));
        jLabel4.setMinimumSize(new java.awt.Dimension(481, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 3;
        jPanelJeu.add(jLabel4, gridBagConstraints);

        jTabbedPane2.addTab("Jeu", jPanelJeu);

        jTabbedPane2.setSelectedIndex(1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        add(jTabbedPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomInstrumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomInstrumentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomInstrumentActionPerformed

    private void cboxCouleurToucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxCouleurToucheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxCouleurToucheActionPerformed

    private void rbtnImageFondToucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnImageFondToucheActionPerformed
        JFileChooser choixFichier = new JFileChooser();
        choixFichier.setCurrentDirectory(null);
        int actionUtilisateur = choixFichier.showOpenDialog(this);
        
        if(actionUtilisateur == JFileChooser.APPROVE_OPTION){
            File fichierImage = choixFichier.getSelectedFile();
            try{
                imageFondTouche = ImageIO.read(fichierImage);
            } catch(IOException ex){
                ex.printStackTrace();
            }
            this.lblNomImagefond.setText(fichierImage.getName());
           
        } else{
            this.buttonGroup3.clearSelection();
        }
    }//GEN-LAST:event_rbtnImageFondToucheActionPerformed

    private void rbtnFichierAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFichierAudioActionPerformed
        JFileChooser choixFichier = new JFileChooser();
        choixFichier.setCurrentDirectory(null);
        int actionUtilisateur = choixFichier.showOpenDialog(this);
        
        if(actionUtilisateur == JFileChooser.APPROVE_OPTION){
            fichierAudio = choixFichier.getSelectedFile();
            this.lblSonTouche.setText(fichierAudio.getName());
        } else{
            this.buttonGroup1.clearSelection();
        }
    }//GEN-LAST:event_rbtnFichierAudioActionPerformed

    private void txtLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLargeurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLargeurActionPerformed

    private void rbtnCercleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCercleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnCercleActionPerformed

    private void cboxNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxNoteActionPerformed

    private void cboxTimbreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxTimbreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxTimbreActionPerformed

    private void btnSupprimerToucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerToucheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprimerToucheActionPerformed

    private void btnModifierToucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierToucheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifierToucheActionPerformed

    private void txtBarreRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarreRechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarreRechercheActionPerformed

    private void btnMetronomeJeuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetronomeJeuActionPerformed
        interfacePrincipale.controleur.toggleMetronome();
        if (btnMetronomeJeu.getText().equals("Désactiver le métronome")) {
            btnMetronomeJeu.setText("Activer le métronome");
        } else {
            btnMetronomeJeu.setText("Désactiver le métronome");
        }
    }//GEN-LAST:event_btnMetronomeJeuActionPerformed

    private void sliderMetronomeJeuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderMetronomeJeuStateChanged
        interfacePrincipale.controleur.setBpm(sliderMetronomeJeu.getValue());
    }//GEN-LAST:event_sliderMetronomeJeuStateChanged

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        interfacePrincipale.controleur.stopPieceMusicale();

    }//GEN-LAST:event_btnStopActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        interfacePrincipale.controleur.startPieceMusicale();
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum((int) interfacePrincipale.controleur.getInstrument().getSequenceur().getMicrosecondLength());
        SwingUtilities.invokeLater(thread);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnBoucle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle5ActionPerformed
        boucle(5);
    }//GEN-LAST:event_btnBoucle5ActionPerformed

    private void cboxCouleurBordureCentreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxCouleurBordureCentreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboxCouleurBordureCentreActionPerformed

    private void btnBoucle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle1ActionPerformed
        boucle(1);
    }//GEN-LAST:event_btnBoucle1ActionPerformed

    private void btnBoucle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle2ActionPerformed
        boucle(2);
    }//GEN-LAST:event_btnBoucle2ActionPerformed

    private void btnBoucle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle3ActionPerformed
        boucle(3);
    }//GEN-LAST:event_btnBoucle3ActionPerformed

    private void btnBoucle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle4ActionPerformed
        boucle(4);
    }//GEN-LAST:event_btnBoucle4ActionPerformed

    private void btnBoucle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle6ActionPerformed
        boucle(6);
    }//GEN-LAST:event_btnBoucle6ActionPerformed

    private void btnBoucle7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle7ActionPerformed
        boucle(7);
    }//GEN-LAST:event_btnBoucle7ActionPerformed

    private void btnBoucle8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle8ActionPerformed
        boucle(8);
    }//GEN-LAST:event_btnBoucle8ActionPerformed

    private void btnBoucle9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle9ActionPerformed
        boucle(9);
    }//GEN-LAST:event_btnBoucle9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoucle1;
    private javax.swing.JButton btnBoucle2;
    private javax.swing.JButton btnBoucle3;
    private javax.swing.JButton btnBoucle4;
    private javax.swing.JButton btnBoucle5;
    private javax.swing.JButton btnBoucle6;
    private javax.swing.JButton btnBoucle7;
    private javax.swing.JButton btnBoucle8;
    private javax.swing.JButton btnBoucle9;
    private javax.swing.JButton btnMetronomeJeu;
    private javax.swing.JButton btnModifierTouche;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton btnSupprimerTouche;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboxCouleurBordureBas;
    private javax.swing.JComboBox<String> cboxCouleurBordureCentre;
    private javax.swing.JComboBox<String> cboxCouleurBordureCercle;
    private javax.swing.JComboBox<String> cboxCouleurBordureDroite;
    private javax.swing.JComboBox<String> cboxCouleurBordureGauche;
    private javax.swing.JComboBox<String> cboxCouleurBordureHaut;
    private javax.swing.JComboBox<String> cboxCouleurTouche;
    private javax.swing.JComboBox<String> cboxNote;
    private javax.swing.JComboBox<String> cboxOctave;
    private javax.swing.JComboBox<String> cboxTimbre;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelJeu;
    private javax.swing.JPanel jPannelEdition;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblCaracTouche;
    private javax.swing.JLabel lblNomImagefond;
    private javax.swing.JLabel lblSonTouche;
    private javax.swing.JLabel lblTimbre;
    private javax.swing.JRadioButton rbtnCercle;
    private javax.swing.JRadioButton rbtnCouleurFondTouche;
    private javax.swing.JRadioButton rbtnFichierAudio;
    private javax.swing.JRadioButton rbtnImageFondTouche;
    private javax.swing.JRadioButton rbtnNote;
    private javax.swing.JRadioButton rbtnRectangle;
    private javax.swing.JSlider sliderMetronomeJeu;
    private javax.swing.JTextArea txtAffichagePiece;
    private javax.swing.JTextField txtBarreRecherche;
    private javax.swing.JTextField txtBas;
    private javax.swing.JTextField txtBordureCercle;
    private javax.swing.JTextField txtCentre;
    private javax.swing.JTextField txtDroite;
    private javax.swing.JTextField txtGauche;
    private javax.swing.JTextField txtHaut;
    private javax.swing.JTextField txtHauteur;
    private javax.swing.JTextField txtLargeur;
    private javax.swing.JTextField txtNomInstrument;
    private javax.swing.JTextField txtPersistance;
    private javax.swing.JLabel txtPiece;
    private javax.swing.JTextField txtRayon;
    // End of variables declaration//GEN-END:variables

}
