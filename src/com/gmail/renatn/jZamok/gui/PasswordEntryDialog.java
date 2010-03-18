package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.model.PasswordEntry;
import com.gmail.renatn.jZamok.actions.OpenBrowserAction;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * User: renat
 * Date: 18.01.2006
 * Time: 16:45:05
 */

public class PasswordEntryDialog extends TitleDialog {

    private PasswordEntry entry;
    
    private Action generateAction, urlAction;

    private JTextField title, login, password, url, updated, email;
    private JTextArea notes;

    private JButton btnBrowser, btnAuto;

    public PasswordEntryDialog(JFrame f, PasswordEntry psw) {

        super(f);

        title = new JTextField(20);
        login = new JTextField(20);
        password = new JTextField(20);
        url = new JTextField(20);
        email = new JTextField(20);

        notes = new JTextArea(5, 20);
        notes.setLineWrap(true);
        notes.setWrapStyleWord(true);
        JScrollPane textPane = new JScrollPane(notes);

        updated = new JTextField(20);
        updated.setEditable(false);

        generateAction = new GenerateAction(f);
        urlAction = new OpenBrowserAction(url);        
        
        btnAuto = createSmallIconButton(generateAction);
        btnBrowser = createSmallIconButton(urlAction);
        
        getOkButton().addActionListener(new  OkListener());

        JLabel lblTitle = new JLabel("Title:");
        JLabel lblName = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblUrl = new JLabel("URL:");
        JLabel lblMail = new JLabel("eMail:");
        JLabel lblDescr = new JLabel("Notes:");
        JLabel lblDate = new JLabel("Updated:");

        GridBagPanel panel = getPanel();
        panel.setInsets(4, 4, 4, 4);
        panel.setAnchor(GridBagConstraints.EAST);

        panel.place(lblTitle,    0, 1, 1, 1);
        panel.place(lblName,     0, 2, 1, 1);
        panel.place(lblPassword, 0, 3, 1, 1);
        panel.place(lblUrl,      0, 4, 1, 1);
        panel.place(lblMail,     0, 5, 1, 1);
        panel.place(lblDescr,    0, 6, 1, 1);
        panel.place(lblDate,  0, 11, 1, 1);

        panel.place(btnAuto, 2, 3, 1, 1);
        panel.place(btnBrowser, 2, 4, 1, 1);

        panel.setFill(GridBagConstraints.HORIZONTAL);

        panel.place(title,    1, 1, 1, 1);
        panel.place(login,    1, 2, 1, 1);
        panel.place(password, 1, 3, 1, 1);
        panel.place(url,      1, 4, 1, 1);
        panel.place(email,    1, 5, 1, 1);
        panel.place(updated,  1, 11, 1, 1);

        panel.setFill(GridBagConstraints.BOTH);
        panel.place(textPane, 1, 6, 1, 5);

        panel.setFill(GridBagConstraints.HORIZONTAL);
        panel.setAnchor(GridBagConstraints.EAST);
               
        ImageIcon icon = UIHelper.getImageIcon("accueil.png");       
        HeaderPanel header = getHeader();
        header.setIcon(icon);
        
         if (psw == null) {        
            this.setTitle("New");
            header.setTitres("Add Entry", "Add a new password entry");
        } else {
            header.setTitres("Edit Entry", "Edit a password entry");
            setEntry(psw);
        }  
           
        pack();
        setLocationRelativeTo(f);
           
    }
    
    public PasswordEntryDialog(JFrame f) {
        this(f, null);
    }
    
    private JButton createSmallIconButton(Action a) {
        Dimension btnsize = new Dimension(16, 16);
        JButton btn = new JButton(a);
        btn.setPreferredSize(btnsize);
        btn.setMinimumSize(btnsize);
        return btn;
    }
    
    public void setEntry(PasswordEntry psw) {
        this.entry = psw;
        this.setTitle(psw.getTitle());

        title.setText(psw.getTitle());
        login.setText(psw.getLogin());
        url.setText(psw.getURL());
        email.setText(psw.getEmail());
        notes.setText(psw.getNotes());
        password.setText(psw.getPassword(false));        
        updated.setText(UIHelper.encodeDate(psw.getLastUpdated()));       
    }
   
    private class OkListener implements ActionListener {
            
        public void actionPerformed(ActionEvent e) {

            if (title.getText().trim().length() < 1 &&
                login.getText().trim().length() < 1 &&
                url.getText().trim().length() < 1 &&
                notes.getText().trim().length() < 1 &&
                password.getText().trim().length() < 1) {

                JOptionPane.showMessageDialog(PasswordEntryDialog.this,
                        "One or more settings is missing.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            
            // Whether changed entry             
            final PasswordEntry oldEntry = entry.clone();

            entry.setTitle(title.getText());
            entry.setLogin(login.getText());
            entry.setURL(url.getText());
            entry.setNotes(notes.getText());
            entry.setPassword(password.getText());
            entry.setEmail(email.getText());

            if (!oldEntry.equals(entry)) {
                modalResult = true;
            }
            PasswordEntryDialog.this.setVisible(false);
        }
        
    }
    
    private class GenerateAction extends AbstractAction {

        private JFrame f;
        
        public GenerateAction(JFrame f) {
            this.f = f;
            
            putValue(Action.SMALL_ICON, UIHelper.getImageIcon("autogen.png"));
            putValue(Action.SHORT_DESCRIPTION, "Generate random password");           
        }
        
        public void actionPerformed(ActionEvent e) {
         
            AutoPasswordDialog dlg = new AutoPasswordDialog(f);
            
            if (dlg.showDialog()) {
            
                if (password.getText().equals("")) {
                 
                    password.setText(dlg.getPassword());
                
                } else {
              
                    int res = JOptionPane.showConfirmDialog(PasswordEntryDialog.this, 
                            "Want replace exists password?", "Replace", 
                            JOptionPane.YES_NO_OPTION);
                    
                    if (res == JOptionPane.YES_OPTION)
                        password.setText(dlg.getPassword());
                
                }
            
                dlg.dispose();
            }
        
        }
    }
        
}
