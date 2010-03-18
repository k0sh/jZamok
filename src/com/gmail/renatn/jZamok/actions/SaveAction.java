/*
 * SaveAction.java
 *
 * Created on 28 Август 2007 г., 17:02
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import javax.swing.*;
import java.io.File;

import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.ZamokDataModel;
import com.gmail.renatn.jZamok.workers.SaveWorker;

/**
 *
 * @author renat
 */
public class SaveAction extends AbstractAction {
    
    private MainFrame app;   
    
    public SaveAction(MainFrame app) {
        
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Save"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("save16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("save24.png"));  
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Save (Ctrl-S)");
        
        setEnabled(false);
        
    }
          
    public void actionPerformed(ActionEvent e) {
        
        ZamokView view = app.getTab();
        if (view == null) {
            throw new IllegalStateException();
        }
        
        ZamokDataModel model = view.getModel();
        File file = model.getFile();
        
        if (!file.exists()) {
            app.getSaveAsAction().actionPerformed(null);
            return;
        }        
        
        SaveWorker worker = new SaveWorker(app, file);
        worker.execute();
        
    }    
    
}
