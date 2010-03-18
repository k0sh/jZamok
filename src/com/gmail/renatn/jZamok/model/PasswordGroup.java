package com.gmail.renatn.jZamok.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User: renat
 * Date: 02.02.2006
 * Time: 18:03:49
 */
public class PasswordGroup {

    private String name;
    private List<PasswordGroup> groupList;
    private List<PasswordEntry> entryList;
    private PasswordGroup parent;

    public PasswordGroup(String name, PasswordGroup parent) {

        groupList = new ArrayList<PasswordGroup>();
        entryList = new ArrayList<PasswordEntry>();
        this.parent = parent;
        this.name = name;

    }

    public PasswordGroup(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEntry(PasswordEntry entry) {
        entryList.add(entry);
    }

    public void removeEntry(PasswordEntry entry) {
        entryList.remove(entry);
    }

    public void addGroup(PasswordGroup group) {
        groupList.add(group);
    }

    public void delGroup(PasswordGroup group) {
        groupList.remove(group);
    }

    public PasswordEntry getEntryAt(int index) {
        return entryList.get(index);
    }
    
    public int getEntryCount() {
        return entryList.size();
    }
 
    public List<PasswordGroup> getListGroup() {
        return groupList;
    }
    
    public List<PasswordEntry> getListEntry() {
        return entryList;
    }    

    public PasswordGroup getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return name;
    }

}
