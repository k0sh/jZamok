/*
 * EditEntryAction.java
 *
 * Created on 29 Август 2007 г., 0:01
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.PasswordEntryDialog;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.gui.PasswordEntryDialog;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.PasswordEntry;

/**
 *
 * @author renat
 */
public class EditEntryAction extends AbstractAction {

    private MainFrame app;       
    
    public EditEntryAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Modify"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("key_edt16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("key_edt.png"));
        
        setEnabled(false);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ZamokView view = app.getTab();        
        PasswordEntry entry = view.getSelectedEntry();        
        if (entry == null) {
            return;
        }          
        
        PasswordEntryDialog dlg = new PasswordEntryDialog(null, entry);       
        boolean res = dlg.showDialog();
        if (res)
            view.getModel().setChanged(true);        
        
        dlg.dispose();
        
    }    

}
