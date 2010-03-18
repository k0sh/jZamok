package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import com.gmail.renatn.jZamok.model.*;

/**
 * A component that displays a list of PasswordEntry and allows the user to select
 * one or more items. 
 * 
 * @author renat
 */
public class GroupListView extends GroupView {
    
    private JList list;
        
    public GroupListView(PasswordGroup group) {

        super(group);

        ZamokListModel listModel = new ZamokListModel(group);
        list = new JList();
        list.setPrototypeCellValue("xxx");
        list.setCellRenderer(new PasswordListCellRenderer());
        list.setModel(listModel);

        JScrollPane pane = new JScrollPane(list);
        pane.setBorder(null);
        pane.setOpaque(false);
        pane.getViewport().setOpaque(false);
        setLayout(new BorderLayout());            
        add(pane, BorderLayout.CENTER);

    }

    @Override
    public void setGroup(PasswordGroup g) {
        super.setGroup(g);
        
        ZamokListModel listModel = new ZamokListModel(g);
        list.setModel(listModel);
    }

    @Override
    public PasswordEntry getSelectedEntry() {
         return (PasswordEntry) list.getSelectedValue();
    }

    @Override
    public void addSelectionListener(ListSelectionListener l) {
        list.getSelectionModel().addListSelectionListener(l);
    }

    @Override
    public void setFilter(String filterText) {
        ZamokListModel model = (ZamokListModel) list.getModel();
        model.setFilter(filterText);
    }

    @Override
    public void addKeyListener(KeyListener l) {
        list.addKeyListener(l);
    }

    @Override
    public void addMouseListener(MouseListener l) {
        list.addMouseListener(l);
    }

            
}
