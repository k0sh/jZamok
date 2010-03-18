/*
 * DelEntryAction.java
 *
 * Created on 29 Август 2007 г., 0:08
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
 *TreePopupMenu
 * @author renat
 */
public class DelEntryAction extends AbstractAction {

    private MainFrame app;   
    
    public DelEntryAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Del"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("key_del16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("key_del.png"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int result = JOptionPane.showConfirmDialog(app, 
            "A you sure you want delete this entry?", MainFrame.APP_NAME, JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.YES_OPTION) {
            ZamokView view = app.getTab();
            ZamokDataModel model = view.getModel();
            PasswordGroup group = view.getSelectedGroup();
            PasswordEntry entry = view.getSelectedEntry();
            model.delEntry(entry, group);
        }
        
    }
    
    
}
