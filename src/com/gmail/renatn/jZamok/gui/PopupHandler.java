/*
 * PopupHandler.java
 *
 * Created on 20 Ноябрь 2007 г., 0:36
 *
 */

package com.gmail.renatn.jZamok.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

/**
 *
 * @author renat
 */
public class PopupHandler extends MouseAdapter {
    
    private JPopupMenu popup;
    
    public PopupHandler(JPopupMenu popup) {
	this.popup = popup;
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
	if (event.isPopupTrigger()) {
	    popup.show(event.getComponent(), event.getX(), event.getY());
	}
    }

    @Override
    public void mouseReleased(MouseEvent event) {
	if (event.isPopupTrigger()) {
	    popup.show(event.getComponent(), event.getX(), event.getY());	    
	}
    }
    
}
