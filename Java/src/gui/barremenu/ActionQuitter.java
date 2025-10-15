/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.barremenu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 */
public class ActionQuitter extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    
}
