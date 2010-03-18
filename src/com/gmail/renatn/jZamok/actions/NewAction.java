/*
 * NewAction.java
 *
 * Created on 30 Август 2007 г., 15:55
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.model.ZamokDataModel;

/**
 *
 * @author renat
 */
public class NewAction extends AbstractAction {

    private MainFrame app;       
    
    public NewAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.New"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("new16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("new24.png"));             
                
    }
   
    public void actionPerformed(ActionEvent e) {
        app.openDocument(new ZamokDataModel());
    }              
    
}
