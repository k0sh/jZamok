package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import javax.swing.*;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Date;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * User: renat
 * Date: 26.02.2006
 * Time: 16:31:47
 */

public final class UIHelper {

    private static ResourceBundle resources;
    //private static SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    static {
        try {
            resources = ResourceBundle.getBundle("resources.jZamok", Locale.getDefault());
        } catch (MissingResourceException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public static String getString(String key) {
        String value;
        value = resources.getString(key);
        if (value == null)
            value = "Could not find resource: " + key + " ";
        return value;
    }

    public static String encodeDate(Date date) {
        return (new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")).format(date);
    }

    public static ImageIcon getImageIcon(String filename) {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                getResource(filename))
                );
    }
    
    public static URL getResource(String filename) {
        return Main.class.getResource("images/"+filename);
    }

    public static boolean isExtXML(File file) {
        return file.getName().toLowerCase().endsWith(".xml");
    }
    
    public static PropertyChangeListener getDividerLocationListener(String id) {
        return new DividerLocationListener(id);
    }
    
    private static class DividerLocationListener implements PropertyChangeListener {
        
        private String id;
        
        public DividerLocationListener(String id) {
            this.id = id;
        }
           
        public void propertyChange(PropertyChangeEvent evt) {                
            
            if (evt.getPropertyName().equals(JSplitPane.DIVIDER_LOCATION_PROPERTY)) {
                
                int i = ((Integer)evt.getNewValue()).intValue();                
                AppProperties conf = AppProperties.getInstance();                    
                conf.setSplitLoc(id, i);                                        
                
            } 

        }
    }
         
}