/*
 * EntryPasteAction.java
 *
 * Created on 28 Август 2007 г., 18:23
 *
 */

package com.gmail.renatn.jZamok.actions;

import com.gmail.renatn.jZamok.model.PasswordEntry;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import java.awt.datatransfer.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.EntryTransferable;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.PasswordGroup;
import com.gmail.renatn.jZamok.model.ZamokDataModel;

/**
 *
 * @author renat
 */

public class EntryPasteAction extends AbstractAction {

    private MainFrame app;   

    public EntryPasteAction(MainFrame app) {
        
        this.app = app;
        
        putValue(Action.NAME, UIHelper.getString("Menu.Paste"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("paste16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("paste24.png"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Transferable contents = app.getClipboard().getContents(null);
        if (contents == null)
            return;

        try {

            DataFlavor flavor = new DataFlavor(EntryTransferable.mimeType);
            PasswordEntry entry = (PasswordEntry) contents.getTransferData(flavor);
            PasswordEntry copyEntry = entry.clone();
            
            ZamokView view = app.getTab();
            if (view == null) {
                return;
            }
            
            ZamokDataModel model = view.getModel();
            PasswordGroup group = view.getSelectedGroup();
            if (group != null) {                
                model.addEntry(copyEntry, group);
                EntryTransferable t = (EntryTransferable) contents;
                if (t.isCut()) {
                    PasswordGroup g = t.getGroup();
                    g.removeEntry(entry);
                }
            }

        } catch (ClassNotFoundException e1) {
            app.showError(e1.getMessage());
        } catch (UnsupportedFlavorException e1) {
            app.showError(e1.getMessage());
        } catch (IOException e1) {
            app.showError(e1.getMessage());
        }

    }

}


