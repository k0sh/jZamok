/*
 * EntryCopyAction.java
 *
 * Created on 30 Август 2007 г., 12:46
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;

import com.gmail.renatn.jZamok.EntryTransferable;
import com.gmail.renatn.jZamok.model.PasswordEntry;
import com.gmail.renatn.jZamok.model.PasswordGroup;

/**
 *
 * @author renat
 */
public class EntryCopyAction extends AbstractAction {

    private MainFrame app;       
    
    public EntryCopyAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Copy"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("copy16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("copy24.png"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        
        setEnabled(false);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
           
        PasswordGroup group = app.getTab().getSelectedGroup();
        PasswordEntry entry = app.getTab().getSelectedEntry();
        if (entry != null) {
            EntryTransferable selection = new EntryTransferable(group, entry);
            app.getClipboard().setContents(selection, null);
        }

    }
    
}
