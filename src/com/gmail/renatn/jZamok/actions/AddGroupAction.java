/*
 * AddGroupAction.java
 *
 * Created on 29 Август 2007 г., 17:32
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.*;

/**
 *
 * @author renat
 */
public class AddGroupAction extends AbstractAction {

    private MainFrame app;     
    
    public AddGroupAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("PMenu.Folder"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("folder_new.png"));
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        ZamokView view = app.getTab();
        ZamokDataModel model = view.getModel();
        PasswordGroup group = view.getSelectedGroup();           
        
        String result = JOptionPane.showInputDialog(app, "Enter Folder name", "");
        if ((result != null) && (!"".equals(result))) {
            model.addGroup(result, group);

        }
        
    }
    
}
