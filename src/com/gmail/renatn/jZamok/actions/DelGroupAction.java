/*
 * DelGroupAction.java
 *
 * Created on 29 Август 2007 г., 17:38
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.PasswordGroup;
import com.gmail.renatn.jZamok.model.ZamokDataModel;

/**
 *
 * @author renat
 */
public class DelGroupAction extends AbstractAction {

    private MainFrame app;     
    
    public DelGroupAction(MainFrame app) {
       
        this.app = app;
        putValue(Action.NAME, UIHelper.getString("PMenu.Del"));
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        ZamokView view = app.getTab();
        ZamokDataModel model = view.getModel();
        PasswordGroup group = view.getSelectedGroup();

        if (model.isRoot(group)) {
            JOptionPane.showMessageDialog(app, "Cannot delete root!", "jZamok", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(app, 
                "Are you sure you want delete the selection folder?", 
                MainFrame.APP_NAME,
                JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.YES_OPTION) {

            model.delGroup(group);

        }
    
    }
    
}
