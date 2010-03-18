package com.gmail.renatn.jZamok.gui;


import com.gmail.renatn.jZamok.model.PasswordEntry;
import java.util.EventListener;

/**
 *
 * @author renat
 */
public interface ZamokSelectionListener extends EventListener {
    
    /** 
      * Called whenever the value of the selection changes.
      * @param e the event that characterizes the change.
      */
    void valueChanged(PasswordEntry entry);    

}
