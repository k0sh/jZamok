/*
 * SaveAsAction.java
 *
 * Created on 28 Август 2007 г., 17:22
 *
 */

package com.gmail.renatn.jZamok.actions;


import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import com.gmail.renatn.jZamok.data.*;
import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.workers.SaveWorker;

/**
 *
 * @author renat
 */
public class SaveAsAction extends AbstractAction {
    
    private MainFrame app;   
    
    public SaveAsAction(MainFrame app) {
        
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.SaveAs"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_MASK));
        setEnabled(false);
        
    }

    public void actionPerformed(ActionEvent e) {
               
        File file = getFile();        
        if (file == null)
            return;
        
        SaveWorker worker = new SaveWorker(app, file);
        worker.execute();
            
    }

    public File getFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new XMLFileFilter());
        chooser.addChoosableFileFilter(new ZMKFileFilter());
        chooser.setSelectedFile(app.getModel().getFile());

        if (chooser.showSaveDialog(app) != JFileChooser.APPROVE_OPTION) 
            return null;

        File file = chooser.getSelectedFile();            
        
        return checkFileExt(chooser.getFileFilter(), file);
        
    }
    
    /**
     *  
     * @param filter selected FileFilter
     * @param f the selected file
     * @return the File with right extension
     */    
    private File checkFileExt(FileFilter filter, File f) {
        
        String path;
        String ext;
        if (filter instanceof XMLFileFilter) {
            ext = ".xml";
        } else {
            ext = ".zmk";            
        }        

        String fname = f.getName();

        if (fname.endsWith(ext)) {
        
            // It's correct
             path = f.getAbsolutePath();
            
        } else if (fname.endsWith(".xml") || fname.endsWith(".zmk")) {

            // Replace extension 
            int i = f.getAbsolutePath().lastIndexOf(".");
            String withoutExt = f.getAbsolutePath().substring(0, i);
            path = withoutExt + ext;
            
        } else {    

            // No valid extension
            path = f.getAbsolutePath() + ext;            

        }   
    
        return new File(path);        
        
    }             
        
}
