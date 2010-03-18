/*
 * ExitAction.java
 *
 * Created on 18 Сентябрь 2007 г., 10:55
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import com.gmail.renatn.jZamok.gui.MainFrame;
import com.gmail.renatn.jZamok.AppProperties;
import com.gmail.renatn.jZamok.gui.UIHelper;

/**
 *
 * @author renat
 */
public class ExitAction extends AbstractAction {
    
    private MainFrame app;   
    
    public ExitAction(MainFrame app) {
        
        this.app = app;

        putValue(Action.NAME, UIHelper.getString("Menu.Exit"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JTabbedPane pageControl = app.getTabbedPane();
        for (ChangeListener listener : pageControl.getChangeListeners()) {
            pageControl.removeChangeListener(listener);
        }
        
        int total = pageControl.getTabCount();
        while (total>0) {
            app.getCloseAction().actionPerformed(null);
            total -= 1;            
        }
                   
        shutdown();    

    }
       
    private void shutdown() {
        
        app.setVisible(false);
        
        AppProperties conf = AppProperties.getInstance();                
        conf.setSize(app.getSize());        // Save window size
        
        try {
            conf.save();
        } catch (IOException e) {
            app.showError("Unable to save the application properties");
        }        
        
        app.dispose();
               
    }
        
}
