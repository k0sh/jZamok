package com.gmail.renatn.jZamok.actions;

import com.gmail.renatn.jZamok.gui.MainFrame;
import javax.swing.Action;


/**
 *
 * @author renat
 */
public class EntryActionFactory {

    private Action addAction;
    private Action editAction;
    private Action delAction;
    
    private static EntryActionFactory instance;
    
    private EntryActionFactory(MainFrame app) {
        addAction = new AddEntryAction(app);
        editAction = new EditEntryAction(app);
        delAction = new DelEntryAction(app);        
    }
    
    public static EntryActionFactory getInstance(MainFrame app) {
        if (instance == null) {
            instance = new EntryActionFactory(app);
        }
        return instance;
    }
    
    public Action getAction(String action) {
        if (action.equals("add")) {
            return addAction;
        } else if (action.equals("edit")) {
            return editAction;            
        } else if (action.equals("delete")) {
            return delAction;
        } else {
            throw new IllegalArgumentException("Unsupported action type");
        }
            
    }
    
    public Action[] getActions() {
        Action[] a = new Action[10];
        a[0] = addAction;
        a[1] = editAction;
        a[2] = delAction;
        return a;
    }
    
}