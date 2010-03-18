/*
 * EntryCutAction.java
 *
 * Created on 30 Август 2007 г., 15:47
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
public class EntryCutAction extends AbstractAction {

    private MainFrame app;       
    
    /** Creates a new instance of EntryCutAction */
    public EntryCutAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Cut"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("cut16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("cut24.png"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
        
        setEnabled(false);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        PasswordGroup group = app.getTab().getSelectedGroup();
        PasswordEntry entry = app.getTab().getSelectedEntry();
        if (entry != null) {
            EntryTransferable selection = new EntryTransferable(group, entry);
            selection.setCut(true);
            app.getClipboard().setContents(selection, null);
        }

    }
}
