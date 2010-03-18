/*
 * AddEntyAction.java
 *
 * Created on 28 Август 2007 г., 19:24
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.PasswordEntryDialog;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.PasswordEntry;
import com.gmail.renatn.jZamok.model.PasswordGroup;
import com.gmail.renatn.jZamok.model.ZamokDataModel;

/**
 *
 * @author renat
 */
public class AddEntryAction extends AbstractAction {

    private MainFrame app;   
    
    public AddEntryAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Add"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("key_add16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("key_add.png"));        
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
        
        setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        PasswordEntry entry = new PasswordEntry();

        PasswordEntryDialog dlg = new PasswordEntryDialog(app);
        dlg.setEntry(entry);
        
        boolean res = dlg.showDialog();
        if (res) {
            ZamokView view = app.getTab();
            ZamokDataModel model = view.getModel();
            PasswordGroup group = view.getSelectedGroup();
            model.addEntry(entry, group);
        }
        
    }
    
}
