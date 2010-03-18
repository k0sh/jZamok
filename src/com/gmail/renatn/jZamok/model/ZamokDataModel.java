/*
 * ZamokDataModel.java
 *
 * Created on 27 Август 2007 г., 16:11
 *
 */

package com.gmail.renatn.jZamok.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renat
 */
public class ZamokDataModel {

    private final static char[] DEFPASSWORD = {'s', 'i', 'm', 's', 'i', 'm'};
    
    private PasswordGroup root;
    private boolean changed = false;
    
    private File file;
    private char[] phrase;
    
    private static int objects = 1;    

    private List<ZamokListener> listeners = new ArrayList<ZamokListener>();
        
    public ZamokDataModel(PasswordGroup root) {       
        this.root = root;
                
        phrase = DEFPASSWORD;        
        String fname = "noname"+(objects++)+".zmk";
        file = new File(fname);

    }

    public ZamokDataModel() {               
        this(new PasswordGroup("passwords"));
    }
           
    public void addZamokListener(ZamokListener listener) {
        if (listener != null)
            listeners.add(listener);        
    }            
    
    public void addEntry(PasswordEntry entry, PasswordGroup group) {
        group.addEntry(entry);
        fireGroupChanged(group);          
    }
    
    public void delEntry(PasswordEntry entry, PasswordGroup group) {
        group.removeEntry(entry);
        fireGroupChanged(group);          
    }   
    
    public void addGroup(String groupName, PasswordGroup parentGroup) {       
        PasswordGroup g = new PasswordGroup(groupName, parentGroup); 
        parentGroup.addGroup(g);    
        fireGroupChanged(parentGroup);       
    }

    public void renameGroup(String name, PasswordGroup group) {
        group.setName(name);
        fireGroupChanged(group);
    }
       
    // FIXME: 
    public void delGroup(PasswordGroup group) {        
        PasswordGroup parentGroup = group.getParent();
        parentGroup.delGroup(group);
        fireGroupChanged(parentGroup);                                
    }
    
   
    private void fireGroupChanged(PasswordGroup group) {
        
        setChanged(true);
        
        for (ZamokListener listener : listeners) 
            listener.groupChanged(group);
        
    }
    
    private int getEntryCount(PasswordGroup group) {

        int count = 0;

        count += group.getEntryCount();

        List<PasswordGroup> groups = group.getListGroup();
        for (PasswordGroup pswgroup : groups) {
            count += getEntryCount(pswgroup);
        }

        return count;
    }    
       
    public int getCount() {
        return getEntryCount(root);
    }

    public void setChanged(boolean changed) {
        this.changed = changed;           
    }

    public boolean isChanged() {
        return changed;
    }  
    
    public PasswordGroup getRoot() {
        return root;
    }
       
    public File getFile() {
        return file;
    }
       
    public boolean isRoot(PasswordGroup group) {
        return getRoot().equals(group);
    }    
        
    /**
     * @param file associated with this model
    **/
    public void setFile(File file) {
        this.file = file;        
    }
    
    public char[] getPhrase() {
        return phrase;
    }
    
    /**
     * @param phrase char array content secret phrase
     *               with encrypted file
     */   
    public void setPhrase(char[] phrase) {
        this.phrase = phrase;
    }
       
}
