package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.model.*;
import com.gmail.renatn.jZamok.AppProperties;

/**
 * User: renat
 * Date: 19.02.2007
 * Time: 23:40:12
 */
public class FindDialog extends JDialog {
    
    private FindAction findAction = new FindAction();
    
    private JTextField tfPhrase;
    private JCheckBox chkTitle, chkURL, chkNote;

    private MainFrame app;   

    public FindDialog(MainFrame app) {

        super(app, UIHelper.getString("Menu.Find"), true);
        this.app = app;
     
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);
        
        GridBagPanel mainPanel = new GridBagPanel();
        panel.add(mainPanel, BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        tfPhrase = new JTextField(20);
        tfPhrase.addKeyListener(new EnterListener());

        chkTitle = new JCheckBox("Into Title", true);
        chkURL = new JCheckBox("Into URL", true);
        chkNote = new JCheckBox("Into Note", true);
        
        JButton btnFind = new JButton(findAction);
        btnFind.setMinimumSize(AppProperties.DEF_BTN_SIZE);
        btnFind.setPreferredSize(AppProperties.DEF_BTN_SIZE);
       
        mainPanel.setAnchor(GridBagConstraints.WEST);
        mainPanel.setInsets(8, 4, 8, 4);

        mainPanel.place(new JLabel(UIHelper.getString("Menu.Find")+":"), 1, 1, 1, 1);
        mainPanel.place(tfPhrase, 2, 1, 1, 1);

        mainPanel.place(chkTitle, 1, 2, 2, 1);
        mainPanel.place(chkURL, 1, 3, 2, 1);
        mainPanel.place(chkNote, 1, 4, 2, 1);
       
        buttonsPanel.add(btnFind);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeDialog();
            }
        });

        pack();
        setLocationRelativeTo(app);

    }

    private void closeDialog() {
        setVisible(false);
        dispose();
    }

    private class FindAction extends AbstractAction {

        private PasswordGroup searchResult;
        private boolean searchAll = true;
        private Pattern pattern = null;

        public FindAction() {
            putValue(Action.NAME, UIHelper.getString("Menu.Find"));
        }
        
        public void actionPerformed(ActionEvent e) {
            if (tfPhrase.getText().length() == 0) {
                return;
            }       
            
            searchAll = (chkNote.isSelected() && chkURL.isSelected() 
                            && chkTitle.isSelected());

            //TODO: Progress Dialog
            pattern = Pattern.compile(".*" + tfPhrase.getText().toLowerCase() + ".*");

            PasswordGroup root = app.getModel().getRoot();
            searchResult = new PasswordGroup("Search", null);
            ZamokView view = app.getTab();
            view.setGroup(searchResult);
            
            search(root);
            
            app.updateTab();

        }
        
        private boolean compare(String text) {
            Matcher matcher = pattern.matcher(text.toLowerCase());
            return matcher.matches();
        }
        
        private void search(PasswordGroup group) {

            for (PasswordEntry entry : group.getListEntry()) {
                
                if (searchAll) {
                                        
                    if (compare(entry.getURL())
                        || compare(entry.getTitle())
                        || compare(entry.getNotes())) {
                        
                        searchResult.addEntry(entry);
                    
                    } 
                
                } else {
                    if ((chkURL.isSelected() && compare(entry.getURL())) ||
                        (chkTitle.isSelected() && compare(entry.getTitle())) ||
                        (chkNote.isSelected() && compare(entry.getNotes()))) {

                        searchResult.addEntry(entry);
                        
                    }
                }
                
            }     

            for (PasswordGroup g : group.getListGroup()) {
                search(g);
            }

        }
    
    }
    
    private class EnterListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                findAction.actionPerformed(null);
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                closeDialog();
            }
        }
    }
    

}
