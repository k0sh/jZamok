/*
 * LoadWorker.java
 *
 * Created on 31 Август 2007 г., 11:30
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
import com.gmail.renatn.jZamok.gui.InputPasswordDialog;
import com.gmail.renatn.jZamok.model.PasswordGroup;

/**
 *
 * @author renat
 */
public class LoadWorker extends SwingWorker {
    
    private MainFrame app;
    private File file;
    private boolean isCrypted = false;
    private FileStorage store;
    
    public LoadWorker(MainFrame app, File file) {
        this.app = app;
        this.file = file;
    }

    @Override
    protected Object doInBackground() throws Exception {
        
        app.setStatus("Loading...");

        if (!UIHelper.isExtXML(file)) {

            InputPasswordDialog dlg = new InputPasswordDialog(app, file.getName());
            if (dlg.showDialog()) {
                isCrypted = true;
                store = new EncFileStorage(dlg.getPassword());               
            } else {
                return null;
            }

        } else {
            store = new FileStorage();
        }

        store.loadFromFile(file);

        return store.getRoot();
        
    }
    
    @Override
    protected void done() {
        try {
           
            app.setStatus("");
            
            PasswordGroup root = (PasswordGroup) get();
            if (root == null)
                return;
    
            ZamokDataModel model = new ZamokDataModel(root);
            model.setFile(file);
            if (isCrypted) {
                char[] phrase = ((EncFileStorage) store).getPhrase();
                model.setPhrase(phrase);
            }
            
            app.openDocument(model);
            
        } catch (Exception e) {
            //e.printStackTrace();
            app.showError("Hash test failed: The key is wrong or the file is damged.");            
        }
    }
    
}
