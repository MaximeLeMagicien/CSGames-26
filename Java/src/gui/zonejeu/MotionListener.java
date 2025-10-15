package gui.zonejeu;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import domaine.Mode;
import gui.InterfacePrincipale;

public class MotionListener extends MouseMotionAdapter {

  private InterfacePrincipale interfacePrincipale;
  private Point pointSouris;

  public MotionListener(InterfacePrincipale interfacePrincipale) {
    this.interfacePrincipale = interfacePrincipale;
    this.pointSouris = new Point();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (interfacePrincipale.controleur.getMode() == Mode.SELECTION) {
      Dimension dimensionZoneJeu = interfacePrincipale.zoneJeu.getSize();
      if (SwingUtilities.isRightMouseButton(e)) {
        pointSouris.setLocation((interfacePrincipale.actualMousePoint.x),
                                (interfacePrincipale.actualMousePoint.y));
        interfacePrincipale.controleur.majPositionTouche(pointSouris, dimensionZoneJeu);
        interfacePrincipale.actualMousePoint = e.getPoint();
      }
    }

  }
}
