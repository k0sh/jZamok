/*
 * SaveWorker.java
 *
 * Created on 31/09/2007, 11:50
 *
 */

package com.gmail.renatn.jZamok.workers;

import java.io.*;
import javax.swing.SwingWorker;

import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.UIHelper;

import com.gmail.renatn.jZamok.model.ZamokDataModel;
import com.gmail.renatn.jZamok.data.EncFileStorage;
import com.gmail.renatn.jZamok.data.FileStorage;

/**
 *
 * @author renat
 */
public class SaveWorker extends SwingWorker {
    
    private MainFrame app;
    private File file;
        
    public SaveWorker(MainFrame app, File file) {
        this.app = app;
        this.file = file;                
    }

    @Override
    protected Object doInBackground() throws Exception {
        
        FileStorage storage;
        ZamokDataModel model = app.getModel();
                       
        if (UIHelper.isExtXML(file)) {
            storage = new FileStorage();                
        } else {
            storage = new EncFileStorage(model.getPhrase());
        }
      
        storage.saveToFile(model, file);         
         
        return model;            
    }
    
    @Override
    protected void done() {
        
        try {
            
            ZamokDataModel model = (ZamokDataModel) get();                       
            model.setChanged(false);
            model.setFile(file);

            app.updateTab();
            
        } catch (Exception e) {
            app.showError("Can't save file: "+e.getMessage());
        }
        
    }
   
    
}
