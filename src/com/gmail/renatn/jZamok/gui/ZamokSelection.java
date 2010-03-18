package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.model.*;
import java.util.ArrayList;

/**
 *
 * @author renat
 */
public class ZamokSelection {
    
    private PasswordGroup group;
    private PasswordEntry entry;
    
    private ArrayList<ZamokSelectionListener> listeners = new ArrayList<ZamokSelectionListener>();

    public ZamokSelection(PasswordGroup group) {
        if (group == null) {
            throw new IllegalArgumentException("group must be non null");
        }        

        this.group = group;
    }
    
    public void addSelectionListener(ZamokSelectionListener l) {
        listeners.add(l);
    }

    public PasswordGroup getGroup() {
        return group;
    }
    
    public void setGroup(PasswordGroup group) {
        this.group = group;
        setEntry(null);
    }
    
    public PasswordEntry getEntry() {
        return entry;
    }
    
    public void setEntry(PasswordEntry entry) {
        this.entry = entry;
        fireValueChanged();
    }    
       
    private void fireValueChanged() {
        for (ZamokSelectionListener listener : listeners) {
            listener.valueChanged(entry);    
        }
        
    }
    
}
