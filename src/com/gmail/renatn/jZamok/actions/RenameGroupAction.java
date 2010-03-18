/*
 * RenameGroupAction.java
 *
 * Created on 31 Август 2007 г., 10:44
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.*;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.*;

/**
 *
 * @author renat
 */
public class RenameGroupAction extends AbstractAction {

    private MainFrame app;     
    
    public RenameGroupAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("PMenu.Ren"));
    
    }

    public void actionPerformed(ActionEvent e) {

        String result = JOptionPane.showInputDialog(app, "Enter the new name folder", 
                app.getTab().getSelectedGroup().getName());

        if ( (result != null) && (!"".equals(result)) ) {

            ZamokView view = app.getTab();
            ZamokDataModel model = view.getModel();
            PasswordGroup group = view.getSelectedGroup();            
            model.renameGroup(result, group);

        }
        
    }

}
