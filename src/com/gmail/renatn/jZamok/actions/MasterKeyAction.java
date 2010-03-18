/*
 * MasterKeyAction.java
 *
 * Created on 28 Август 2007 г., 19:18
 *
 */

package com.gmail.renatn.jZamok.actions;

/**
 *
 * @author renat
 */
import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.ZamokDataModel;
import com.gmail.renatn.jZamok.model.ZamokDataModel;

public class MasterKeyAction extends AbstractAction {

    private MainFrame app;   
    
    public MasterKeyAction(MainFrame app) {
        
        this.app = app;
        
        putValue(Action.NAME, "Master key");
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("jzamok24.png"));
        putValue(Action.SHORT_DESCRIPTION, "Set master key");

        setEnabled(false);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String result = JOptionPane.showInputDialog(app, "Enter secret phrase", "");
        if (result != null && !"".equals(result)) {
            ZamokView view = app.getTab();
            if (view == null) {
                throw new IllegalStateException("Document is empty");
            }
            ZamokDataModel model = view.getModel();
            model.setPhrase(result.toCharArray());
            model.setChanged(true);
        }
        
    }
    
}
