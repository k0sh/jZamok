/*
 * FindAction.java
 *
 * Created on 28 Август 2007 г., 16:37
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.FindDialog;

/**
 *
 * @author renat
 */
public class FindAction extends AbstractAction {
    
    private MainFrame app;   
    
    public FindAction(MainFrame app) {
        
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Find")+"...");
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("find16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("find24.png"));
        putValue(Action.ACCELERATOR_KEY, 
                KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Find entry");
        
    }
    
    @Override
   public void actionPerformed(ActionEvent e) {

        FindDialog dlg = new FindDialog(app);
        dlg.setVisible(true);
        
    }    
    
}
