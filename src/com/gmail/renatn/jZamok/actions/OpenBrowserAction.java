/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.renatn.jZamok.actions;

import com.gmail.renatn.jZamok.gui.UIHelper;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * http://java.sun.com/docs/books/tutorial/uiswing/misc/desktop.html
 *
 * @author renat
 */
public class OpenBrowserAction extends AbstractAction {
    
    private Desktop desktop;
    private JTextField tfURL;
    
    public OpenBrowserAction(JTextField tf) {
        
        this.tfURL = tf;
        
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("url.png"));
        putValue(Action.SHORT_DESCRIPTION, "Open URL in browser");           
        setEnabled(false);
     
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                setEnabled(true);
            }
        }
        
    }

    public void actionPerformed(ActionEvent e) {
        try {
            URI uri = new URI(tfURL.getText());
            desktop.browse(uri);
        } catch (IOException ex) {
            Logger.getLogger(OpenBrowserAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(OpenBrowserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
