package com.gmail.renatn.jZamok.model;

import java.util.Date;

/**
 * User: renat
 * Date: 18.01.2006
 * Time: 16:38:35
 */

public class PasswordEntry implements Cloneable {

    private String title;
    private String login;
    private String url;
    private String notes;
    private String email;

    private Date lastUpdate;
    private String password;

    public PasswordEntry(String title, String login, String password, 
            String url, String notes, String email) {
        this.title = title;
        this.login = login;
        this.url = url;
        this.notes = notes;
        this.email = email;
        setPassword(password);
    }

    public PasswordEntry() {
        this("", "", "", "", "", "");
    }

    private String getStars() {
        StringBuffer t = new StringBuffer();
        for (int i=0; i<password.length(); i++)
            t.append('*');
        return t.toString();
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getURL() { return url; }
    public void setURL(String url) { this.url = url; }
       
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Date getLastUpdated() { return lastUpdate; }
    public void setLastUpdated(Date date) { this.lastUpdate = date; }
    
    public String getPassword(boolean hide) {
        if (hide) {
            return getStars();
        }
        return password;
    }

    public String getPassword() {
        return getPassword(true);
    }

    public void setPassword(String password) {
        this.password = password;
        this.lastUpdate = new Date();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.title != null ? this.title.hashCode() : 0);
        hash = 29 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 29 * hash + (this.url != null ? this.url.hashCode() : 0);
        hash = 29 * hash + (this.notes != null ? this.notes.hashCode() : 0);
        hash = 29 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 29 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        PasswordEntry other = (PasswordEntry) obj;
        
        boolean eq = title.equals(other.title) && login.equals(other.login)
                && url.equals(other.url) && notes.equals(other.notes) 
                && email.equals(other.email) && password.equals(other.password);
        return eq;
    }

    
    @Override
    public PasswordEntry clone() {
        
        try {
            PasswordEntry clone = (PasswordEntry) super.clone();
            
            clone.setEmail(email);
            clone.setLogin(login);
            clone.setNotes(notes);
            clone.setPassword(password);
            clone.setTitle(title);
            clone.setURL(url);
            
            return clone;
        } catch (CloneNotSupportedException ex) {
            assert false;
        }
        
        return null;
    }

    @Override
    public String toString() {
        return String.format(
            "Title: %s; Login: %s; Password: %s; URL: %s; Email: %s; Notes: %s",
                title, login, password, url, email, notes);
    }
    
}
