package com.gmail.renatn.jZamok.gui;

import javax.swing.table.*;

import com.gmail.renatn.jZamok.model.*;

/**
 * User: renat
 * Date: 17.01.2006
 * Time: 21:58:50
 */

public class ZamokTableModel extends AbstractTableModel {

    private final String[] colNames = {"Title", UIHelper.getString("Label.Login"), "Password", "Url", "Email"};
  
    private PasswordGroup group;

    public ZamokTableModel(PasswordGroup group) {        
        this.group = group;                             
    }
        
    @Override
    public int getRowCount() {
        return group.getEntryCount();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {

        PasswordEntry entry =  group.getEntryAt(row);
        
        switch (col) {
            case 0 : return entry.getTitle();
            case 1 : return entry.getLogin();
            case 2 : return entry.getPassword();
            case 3 : return entry.getURL();
            case 4 : return entry.getEmail();
        }
        
        return null;
    
    }
    
    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
