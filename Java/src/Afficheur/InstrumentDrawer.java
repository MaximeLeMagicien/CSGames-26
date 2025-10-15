package Afficheur;

import gui.InterfacePrincipale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;


public class InstrumentDrawer{
    private Image image;

	public InstrumentDrawer(InterfacePrincipale outer) {
            this.image = outer.controleur.getInstrument().getImageFond();
	}
	public void drawInstrument(Graphics g, Dimension dimension) {
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();
                if (image != null)
                    g.drawImage(image, 0, 0, width, height, null);
	}
}