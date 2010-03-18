package com.gmail.renatn.jZamok.gui;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import com.gmail.renatn.jZamok.model.*;

/**
 *
 * @author renat
 */
public abstract class GroupView extends JPanel {

    public final static int LIST_VIEW = 0;
    public final static int TABLE_VIEW = 1;

    private PasswordGroup group;
    
    public GroupView(PasswordGroup g) {                
        this.group = g;
    }
    
    public abstract PasswordEntry getSelectedEntry();
    public abstract void addSelectionListener(ListSelectionListener l);
    public abstract void setFilter(String filterText);
        
    public void setGroup(PasswordGroup group) {
        this.group = group;
    }

    public PasswordGroup getGroup() {
        return group;
    }


       
}
