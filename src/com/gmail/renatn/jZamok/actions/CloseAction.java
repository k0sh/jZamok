package com.gmail.renatn.jZamok.actions;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import com.gmail.renatn.jZamok.*;
import com.gmail.renatn.jZamok.data.*;
import com.gmail.renatn.jZamok.gui.ZamokView;
import com.gmail.renatn.jZamok.model.ZamokDataModel;

/**
 *
 * @author renat
 */
public class CloseAction extends AbstractAction {

    private MainFrame app;       
    
    public CloseAction(MainFrame app) {
       
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Close"));
        putValue(Action.SMALL_ICON, UIHelper.getImageIcon("new16.png"));
        putValue(Action.LARGE_ICON_KEY, UIHelper.getImageIcon("new24.png"));      
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK));

        setEnabled(false);        
                
    }
   
    public void actionPerformed(ActionEvent e) {

        ZamokView view = app.getTab();
        if (view == null) {
            throw new IllegalStateException("No opened docs");
        }
        
        ZamokDataModel model = view.getModel();
        if (model.isChanged()) {

            int result = JOptionPane.showConfirmDialog(app,
                    UIHelper.getString("Label.Save")+"\n"
                    + model.getFile().getName(),
                    MainFrame.APP_NAME, 
                    JOptionPane.YES_NO_OPTION);

            if(result == JOptionPane.YES_OPTION) {

                File file = model.getFile();

                // if file is New try dialog
                if (!file.exists()) {
                    file = app.getSaveAsAction().getFile();
                    if (file == null)
                        return;
                }

                save(model, file);

            }
            
        } 
        
        JTabbedPane pageControl = app.getTabbedPane();
        pageControl.remove(view);
        
        if (pageControl.getTabCount() == 0) {
            app.setDocActionsEnabled(false);
        }
        
    }
    
    private void save(ZamokDataModel model, File file) {

        FileStorage storage;

        if (UIHelper.isExtXML(file)) {
            storage = new FileStorage();                
        } else {
            storage = new EncFileStorage(model.getPhrase());
        }

        try {      
            storage.saveToFile(model, file);                            
        } catch (IOException ex) {
            app.showError("Can't save file: "+ex.getMessage());
        }                            
        
    }    
    
}

