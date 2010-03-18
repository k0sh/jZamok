/*
 * OpenAction.java
 *
 * Created on 30 Август 2007 г., 16:26

 */

package com.gmail.renatn.jZamok.actions;


import java.awt.event.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.data.*;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.ZamokDataModel;
import com.gmail.renatn.jZamok.workers.LoadWorker;

/**
 *
 * @author renat
 */
public class OpenAction extends AbstractAction {
        
    private MainFrame app;

    public OpenAction(MainFrame app) {

        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Open"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("open16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("open24.png"));

        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        putValue(Action.SHORT_DESCRIPTION, "Open file");

    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser;
        
        ZamokView view = app.getTab();
        if (view != null) {
            ZamokDataModel model = view.getModel();
            String path = model.getFile().getAbsolutePath();
            chooser = new JFileChooser(path); 
        } else {
            chooser = new JFileChooser();             
        }
        chooser.addChoosableFileFilter(new XMLFileFilter());
        chooser.addChoosableFileFilter(new ZMKFileFilter());

        int result = chooser.showOpenDialog(app);
        if (result != JFileChooser.APPROVE_OPTION)
            return;
            
        LoadWorker worker = new LoadWorker(app, chooser.getSelectedFile());
        worker.execute();
        
    }

 

}

