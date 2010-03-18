// TODO : Drag And Drop or Copy,Cut,Paste
// TODO : New file - set master password and name
// TODO : Translate
// TODO : Search
// TODO : Lock file
// TODO : Autocomplete email
// TODO : Synchronize docs
// TODO : To ligth URL Label in the List of Entries

package com.gmail.renatn.jZamok.gui;

/**
 * User: renat
 * Date: 17.01.2006
 * Time: 21:40:34
 */

import com.gmail.renatn.jZamok.*;
import com.gmail.renatn.jZamok.actions.*;
import com.gmail.renatn.jZamok.model.PasswordEntry;
import com.gmail.renatn.jZamok.model.PasswordGroup;
import com.gmail.renatn.jZamok.model.ZamokDataModel;
import com.gmail.renatn.jZamok.model.ZamokListener;
import com.gmail.renatn.jZamok.workers.LoadWorker;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

public final class MainFrame extends JFrame {

    public final static String APP_NAME = "jZamok";
    public final static String appVersion = "0.8.0"; 

    private Action openAction = new OpenAction(this);
    private Action saveAction = new SaveAction(this);
    private SaveAsAction saveAsAction = new SaveAsAction(this);
    private Action closeAction = new CloseAction(this);
    private Action findAction = new FindAction(this);
    private Action pasteAction = new EntryPasteAction(this);
    private Action keyAction = new MasterKeyAction(this);
    private Action addAction = new AddEntryAction(this);
    private Action editAction = new EditEntryAction(this);
    private Action delAction = new DelEntryAction(this);
    private Action copyAction = new EntryCopyAction(this);
    private Action cutAction = new EntryCutAction(this);
    private Action newAction = new NewAction(this);
    private Action exitAction = new ExitAction(this);

    private JRadioButtonMenuItem miAsTable, miAsList;
    private JTabbedPane pageControl;
    private StatusBar statusBar;
    private JPopupMenu popupEntry, popupTree;

    private AppProperties conf = AppProperties.getInstance();
    private Clipboard localClipboard = new Clipboard("local");
    
    private List<Action> entryActions = new ArrayList<Action>();
    private List<Action> docActions = new ArrayList<Action>();
    
    public MainFrame () {

        setTitle(APP_NAME);
        setSize(conf.getSize());
        setIconImage(UIHelper.getImageIcon("jzamok.png").getImage());

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        JToolBar toolBar = createToolBar();
        c.add(toolBar, BorderLayout.NORTH);

        pageControl = new JTabbedPane();
        pageControl.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                updateTab();

                if (pageControl.getTabCount() != 0) {
                    File file = getModel().getFile();
                    if (file.exists()) {
                        conf.setLastFile(file.getPath());
                    }
                }

            }

        });
        c.add(pageControl, BorderLayout.CENTER);

        statusBar = new StatusBar();
        c.add(statusBar, BorderLayout.SOUTH);   

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitAction.actionPerformed(null);
            }
        });                       
        
        entryActions.add(editAction);
        entryActions.add(delAction);
        entryActions.add(copyAction);
        entryActions.add(cutAction);       
        
        docActions.add(saveAction);
        docActions.add(saveAsAction);
        docActions.add(closeAction);
        docActions.add(findAction);
        docActions.add(keyAction);
        docActions.add(addAction);
        
        File file = conf.getLastFile();
        if (file != null)
            loadAction(file);
        
    }    

    private int getViewType() {
        if (miAsTable.isSelected()) {
            return GroupView.TABLE_VIEW;
        } else if (miAsList.isSelected()) {
            return GroupView.LIST_VIEW;
        } else {
            return GroupView.LIST_VIEW;
        }
    }    
    
    private JMenuBar createMenuBar() {

        JMenuItem menuItem;
        JMenuBar mb = new JMenuBar();

        JMenu file = mb.add(new JMenu(UIHelper.getString("Menu.File")));
        file.setMnemonic(KeyEvent.VK_F);
        file.add(newAction);
        file.add(openAction);
        file.addSeparator();

        file.add(saveAction);   
        file.add(saveAsAction);        
        file.add(closeAction);        
        
        file.addSeparator();

        file.add(new JMenuItem(UIHelper.getString("Menu.Option")));
        file.addSeparator();

        final File f = conf.getLastFile();
        if (f != null) {
                      
            menuItem = file.add(new JMenuItem(f.getName()));
            menuItem.setToolTipText(f.getAbsolutePath());

            menuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    loadAction(f);
                }
            });
            
            file.addSeparator();
            
        }
        
        file.add(exitAction);
        
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int viewAs = getViewType();
                
                for (int i=0; i<pageControl.getTabCount(); i++) {
                    ZamokView view = (ZamokView) pageControl.getComponentAt(i);                               
                    view.showAs(viewAs);
                    view.setPopupEntry(popupEntry);
                }
                conf.setLastView(viewAs);
            }
        };
        
        JMenu view = mb.add(new JMenu(UIHelper.getString("Menu.View")));
        view.setMnemonic(KeyEvent.VK_V);
        miAsTable = (JRadioButtonMenuItem) view.add(new JRadioButtonMenuItem("As Table"));
        miAsList = (JRadioButtonMenuItem) view.add(new JRadioButtonMenuItem("As List"));
        miAsTable.addActionListener(al);
        miAsList.addActionListener(al);
        
        ButtonGroup viewGroup = new ButtonGroup();
        viewGroup.add(miAsTable);
        viewGroup.add(miAsList);
        
        int viewAs = conf.getLastView();        
        switch (viewAs) {
            case GroupView.TABLE_VIEW:
                miAsTable.setSelected(true);
                break;
            default:
                miAsList.setSelected(true);                               
        }
        
        
        JMenu edit = mb.add(new JMenu(UIHelper.getString("Menu.Edit")));
        edit.setMnemonic(KeyEvent.VK_E);
        edit.add(cutAction);
        edit.add(copyAction);
        edit.add(pasteAction);
        edit.addSeparator();
        
        edit.add(addAction);
        edit.add(editAction);
        edit.add(delAction);
        edit.addSeparator();
        
        edit.add(findAction);

        JMenu help = mb.add(new JMenu("?"));
        help.setMnemonic(KeyEvent.VK_H);
        menuItem = help.add(new JMenuItem(UIHelper.getString("Menu.Help")));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.this, 
                        UIHelper.getString("Label.Help"),
                        APP_NAME, 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
                
        help.add(new AboutAction(MainFrame.this));
  
        return mb;
        
    }

    private JToolBar createToolBar() {
                
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);
        tb.setRollover(true);

        tb.add(newAction);
        tb.add(openAction);
        tb.add(saveAction);
        tb.addSeparator();

        tb.add(cutAction);
        tb.add(copyAction);
        tb.add(pasteAction);
        tb.addSeparator();

        tb.add(addAction);
        tb.add(editAction);
        tb.add(delAction);
        tb.addSeparator();
        
        tb.add(findAction);
        tb.addSeparator();

        tb.add(keyAction);
        tb.add(Box.createHorizontalGlue());

        final JTextField searchField = new JTextField();
        searchField.setMinimumSize(new Dimension(120, 21));
        searchField.setPreferredSize(new Dimension(120, 21));
        searchField.setMaximumSize(new Dimension(120, 21));

        final ActionListener searchListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ZamokView view = getTab();
                if (view != null)
                    view.setFilter(searchField.getText());
            }

        };

        searchField.addActionListener(searchListener);
        searchField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                searchListener.actionPerformed(null);
            }

            public void removeUpdate(DocumentEvent e) {
                searchListener.actionPerformed(null);                
            }

            public void changedUpdate(DocumentEvent e) {
            }
        });


        tb.add(searchField);
        tb.addSeparator();

        return tb;

    }      
    
    private JPopupMenu createPopupEntry() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(addAction);
        popup.add(editAction);
        popup.add(delAction);
        popup.addSeparator();

        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);
        popup.addSeparator();
        
        ActionListener al = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JMenuItem m = (JMenuItem) e.getSource();
                ZamokView view = getTab();
                PasswordEntry entry= view.getSelectedEntry();
                if (entry == null) {
                    return;
                }
                String retval="";
                if (m.getText().equals("Username")) {
                    retval = entry.getLogin();
                } else if (m.getText().equals("Password")) {
                    retval = entry.getPassword(false);                    
                } else if (m.getText().equals("URL")) {
                    retval = entry.getURL();                    
                } else if (m.getText().equals("eMail")) {
                    retval = entry.getEmail();
                }

                if (!"".equals(retval)) {
                    StringSelection selection = new StringSelection(retval);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, null);
                }
                
            }
            
        };
        
        JMenuItem mi;
        JMenu menuCopy = (JMenu) popup.add(new JMenu(UIHelper.getString("Menu.Copy")));
        mi = createPopupCopyMenuItem("username.png", "Username", al);
        menuCopy.add(mi);
        mi = createPopupCopyMenuItem("password.png", "Password", al);
        menuCopy.add(mi);
        mi = createPopupCopyMenuItem("url.png", "URL", al);
        menuCopy.add(mi);
        mi = createPopupCopyMenuItem("mail.png", "eMail", al);
        menuCopy.add(mi);
        
        return popup;        
    }
    
    private JMenuItem createPopupCopyMenuItem(String icon, String title, ActionListener al) {
        JMenuItem mi = new JMenuItem(title);
        mi.addActionListener(al);
        mi.setIcon(UIHelper.getImageIcon(icon));        
        return mi;        
    }
    
    private JPopupMenu getTreePopupMenu() {
        if (popupTree == null ) {
            popupTree = new JPopupMenu();
            popupTree.add(new AddGroupAction(this));
            popupTree.add(new RenameGroupAction(this));
            popupTree.add(new DelGroupAction(this));
            popupTree.addSeparator();
            popupTree.add(addAction);
        }
        
        return popupTree;
    }
    
    private void loadAction(File file) {
        
        LoadWorker worker = new LoadWorker(MainFrame.this, file);
        worker.execute();         
    
    }  
    
    public void openDocument(ZamokDataModel model) {

        model.addZamokListener(new ZamokListener() {
            public void groupChanged(PasswordGroup group) {
                updateTab();
            }
        });
                 
        ZamokView tab = new ZamokView(model);
        if (popupEntry == null) {
            popupEntry = createPopupEntry();
        }

        tab.setPopupEntry(popupEntry);        
        tab.setPopupTree(getTreePopupMenu());
        tab.addEditEntryListener(editAction);
        tab.getSelectionModel().addSelectionListener(new ZamokSelectionListener() {

            public void valueChanged(PasswordEntry entry) {
                boolean enabled = (entry != null);
                setEntryActionsEnabled(enabled);
                PasswordGroup g = getTab().getSelectedGroup();
                statusBar.setCurrent(g.getEntryCount());
            }
            
        });
        
        pageControl.addTab(model.getFile().getName(), tab);        
        pageControl.setSelectedComponent(tab);

        setDocActionsEnabled(true);
        addAction.setEnabled(true);
                
    }  
    
    public void setDocActionsEnabled(boolean b) {
        for (Action action : docActions) {
            action.setEnabled(b);
        }    
        if (!b) {
            setEntryActionsEnabled(false);
        }
    }     
                   
    public void setEntryActionsEnabled(boolean b) {
        for (Action action : entryActions) {
            action.setEnabled(b);
        }       
    }  
    
    public void showError(String msg) {
        JOptionPane.showMessageDialog(MainFrame.this, msg, APP_NAME, JOptionPane.ERROR_MESSAGE);
    } 
    
    public void setStatus(String msg) {
        statusBar.setMessage(msg);
    }
    
    public void updateTab() {

        ZamokView view = getTab();                          
        if (view == null) {
            setTitle(APP_NAME);
            statusBar.setTotal(0);
            statusBar.setCurrent(0);
            return;
        }
        ZamokDataModel model = view.getModel();
        File file = model.getFile();
        
        String title = String.format("%s - %s", APP_NAME, file.getName());
        if (model.isChanged())
            title += " *";
        setTitle(title);
        
        statusBar.setTotal(model.getCount());
        statusBar.setCurrent(getTab().getSelectedGroup().getEntryCount());
                
        pageControl.setTitleAt(pageControl.getSelectedIndex(), file.getName());
        pageControl.setToolTipTextAt(pageControl.getSelectedIndex(), file.getPath());                
        
    }     

    public Action getAddEntryAction() { return addAction; }
    public Action getCloseAction() { return closeAction; }
    public Action getCopyAction() { return copyAction; }    
    public Action getCutAction() { return cutAction; }    
    public Action getPasteAction() { return pasteAction; }    
    public Action getSaveAction() { return saveAction; }
    public SaveAsAction getSaveAsAction() { return saveAsAction; }
    
    public JTabbedPane getTabbedPane() {
        return pageControl;
    }
    
    public Clipboard getClipboard() {
        return localClipboard;
    }
    
    public ZamokView getTab() {
       return (ZamokView) pageControl.getSelectedComponent();        
    }
   
    
    public ZamokDataModel getModel() {
       ZamokView view = getTab();
       if (view == null)
           throw new IllegalArgumentException("Model is null");
       return view.getModel();
    }  
                    
}