package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.*;

import com.gmail.renatn.jZamok.model.*;
import com.gmail.renatn.jZamok.AppProperties;

/**
 *
 * @author renat
 */
public class GroupTableView extends GroupView {

    private JTable table;    
    private JSplitPane splitPane;
    private PasswordTextView textInfo;    

    public GroupTableView(PasswordGroup group) {

        super(group);
                       
        textInfo = new PasswordTextView();
        ZamokTableModel tableModel = new ZamokTableModel(group);
        
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        table.setAutoCreateRowSorter(true);

      //  table.setDragEnabled(true);
      //  table.setTransferHandler(new TableTransferHandler());
        table.setDefaultRenderer(Object.class, new ZmkCellRender());         
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                PasswordEntry entry = getSelectedEntry();
                if (entry != null) {
                    textInfo.setPasswordEntry(entry);
                }
            }
        });                        
                
        AppProperties conf = AppProperties.getInstance();        
        
        JScrollPane scrollPane = new JScrollPane(table);            
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, textInfo.getPanel());        
        splitPane.setDividerSize(3);
        splitPane.setDividerLocation(conf.getSplitLoc(AppProperties.CONF_CENTR_DIV_ID));         
        
        PropertyChangeListener l = UIHelper.getDividerLocationListener(AppProperties.CONF_CENTR_DIV_ID);        
        splitPane.addPropertyChangeListener(l);           

        setLayout(new BorderLayout());                   
        add(splitPane, BorderLayout.CENTER);                
        
    }        
        
    private int getSelectedRow(JTable table) {
        int row = table.getSelectedRow();
        if (row < 0) {
            return -1;
        }          
        
        return table.convertRowIndexToModel(row);        
    }   
    
    @Override
    public void addKeyListener(KeyListener l) {
        table.addKeyListener(l);                
    }

    @Override
    public void addMouseListener(MouseListener l) {
        table.addMouseListener(l);                
    }
    
    @Override
    public void setGroup(PasswordGroup g) {
        super.setGroup(g);
        
        ZamokTableModel tableModel = new ZamokTableModel(g);
        table.setModel(tableModel);
    }

    @Override
    public PasswordEntry getSelectedEntry() {
        int row = getSelectedRow(table);                
        if (row < 0)
            return null;

        return getGroup().getEntryAt(row); 
    }

    @Override
    public void addSelectionListener(ListSelectionListener l) {
	table.getSelectionModel().addListSelectionListener(l);        
    }

    public void setFilter(String filterText) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
