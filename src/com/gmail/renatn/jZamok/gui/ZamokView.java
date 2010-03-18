package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.AppProperties;
import com.gmail.renatn.jZamok.model.PasswordEntry;
import com.gmail.renatn.jZamok.model.PasswordGroup;
import com.gmail.renatn.jZamok.model.ZamokDataModel;
import com.gmail.renatn.jZamok.model.ZamokListener;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class ZamokView extends JPanel implements ZamokListener {

    private JTree tree;
    private JSplitPane mainSplit;

    private GroupView groupView;
       
    private ZamokDataModel dataModel;  
    private ZamokSelection selection; 
    
    private Action editAction;

    public ZamokView(ZamokDataModel model) {        

        if (model == null) {
            throw new IllegalArgumentException("dataModel must be non null");
        }        
        
        this.dataModel = model;
        this.selection = new ZamokSelection(model.getRoot());

        initComponents();              
        
        dataModel.addZamokListener(this);
        
    }
    
    private void initComponents() {
        
        AppProperties conf = AppProperties.getInstance();

        setLayout(new BorderLayout());
        setOpaque(false);

        tree = createTree();
        JScrollPane pane = new JScrollPane(tree);
        pane.setBorder(null);

        PasswordGroup group = dataModel.getRoot();
        groupView = createGroupView(group, conf.getLastView());

        mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, groupView);
        mainSplit.setDividerSize(3);

        // Restore spliters location from conf
        int loc = conf.getSplitLoc(AppProperties.CONF_MAIN_DIV_ID);
        mainSplit.setDividerLocation(loc);

        PropertyChangeListener l = UIHelper.getDividerLocationListener(AppProperties.CONF_MAIN_DIV_ID);
        mainSplit.addPropertyChangeListener(l);

        add(mainSplit, BorderLayout.CENTER);        
                
    }

    public void groupChanged(PasswordGroup group) {
        tree.updateUI();
        groupView.updateUI();
    }    

    public ZamokDataModel getModel() {
        return dataModel;
    }
    
    public ZamokSelection getSelectionModel() {
        return selection;
    }

    public PasswordGroup getSelectedGroup() {
        return selection.getGroup();
    }
    
    public PasswordEntry getSelectedEntry() {
        return selection.getEntry();
    }
    
    //FIXME: Ugly
    public void addEditEntryListener(Action a) {
        this.editAction = a;
    }
    
    public void setPopupEntry(JPopupMenu popup) {
        groupView.addMouseListener(new PopupHandler(popup));        
    }    
    
    public void setPopupTree(JPopupMenu popup) {
        tree.addMouseListener(new PopupHandler(popup));
    }

    public void setFilter(String filterText) {
        groupView.setFilter(filterText);
    }
    
    public void setGroup(PasswordGroup group) {
        selection.setGroup(group);
        groupView.setGroup(group);        
    }
              
    private JTree createTree() {

        ZamokTreeModel treeModel = new ZamokTreeModel(dataModel);
        tree = new JTree(treeModel);        
        tree.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {                
            public void valueChanged(TreeSelectionEvent e) {
                    
                TreePath p = e.getNewLeadSelectionPath();
                if (p != null) {
                    PasswordGroup group = (PasswordGroup) p.getLastPathComponent();
                    setGroup(group);
                }
                
            }
        });

        return tree;
    }
         
    private GroupView createGroupView(PasswordGroup group, int viewAs) {
        GroupView view = GroupViewFactory.createView(group, viewAs);
        view.addSelectionListener(new MySelectionListener());
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {                
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    editAction.actionPerformed(null);
                }                
            }            
        });
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount()>1) {
                    editAction.actionPerformed(null);
                }
            }           
        });
                
        return view;        
    }
    
    public void showAs(int viewAs) {
        int loc = mainSplit.getDividerLocation();
        PasswordGroup group = groupView.getGroup(); 
        mainSplit.remove(groupView);        
        
        groupView = createGroupView(group, viewAs);
        mainSplit.setRightComponent(groupView);
        mainSplit.setDividerLocation(loc);
        mainSplit.revalidate();
        mainSplit.repaint();
    }
    
    /*
     * Entry selection handler
     */
    private class MySelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            PasswordEntry entry = groupView.getSelectedEntry();
            selection.setEntry(entry);           
        }
        
    }

            
}
