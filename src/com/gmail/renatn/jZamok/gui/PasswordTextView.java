package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import javax.swing.*;

import com.gmail.renatn.jZamok.model.PasswordEntry;

/**
 * User: renat
 * Date: 09.02.2006
 * Time: 22:23:28
 */
public class PasswordTextView {

    private JEditorPane text;
    private JPanel panel;
    private PasswordEntry entry;

    public PasswordTextView() {

        text = new JEditorPane();
        text.setContentType("text/html");
        text.setEditable(false);

        panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(text),BorderLayout.CENTER);

    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPasswordEntry(PasswordEntry entry) {
        
        this.entry = entry;

        String html = "<html><b>Title</b>: " + entry.getTitle()
            + "; <b>Login:</b> " + entry.getLogin()
            + "; <b>Password:</b> " + entry.getPassword()
            + "; <b>URL:</b> <a href=/>" + entry.getURL()
            + "</a>; <b>Email:</b> " + entry.getEmail() 
            + "; <b>Notes:</b> " + entry.getNotes()
            + "; <b>Last Modification:</b> " + UIHelper.encodeDate(entry.getLastUpdated())
            + "</html>";
        text.setText(html);
        
    }

    public void clear() {
        if (entry != null) {
            entry = null;
            text.setText("");
        }
    }
}
