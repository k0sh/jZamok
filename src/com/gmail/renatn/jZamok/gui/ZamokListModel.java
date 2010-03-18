package com.gmail.renatn.jZamok.gui;

import javax.swing.AbstractListModel;
import com.gmail.renatn.jZamok.model.PasswordGroup;
import com.gmail.renatn.jZamok.model.PasswordEntry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author renat
 */
public class ZamokListModel extends AbstractListModel {

    private PasswordGroup group;
    private List<PasswordEntry> filteredList = new ArrayList<PasswordEntry>();
    private Pattern pattern;

    public ZamokListModel(PasswordGroup group) {
        this.group = group;
    }

    private boolean compare(String text) {
        Matcher matcher = pattern.matcher(text.toLowerCase());
        return matcher.matches();
    }

    private void refilter() {
        filteredList.clear();

        for (PasswordEntry entry : group.getListEntry()) {
            if (compare(entry.getURL())
                || compare(entry.getTitle())
                || compare(entry.getNotes())) {

                   filteredList.add(entry);

            }
        }
    }

    private boolean isFiltered() {
        return (filteredList.size() != 0);
    }

    public int getSize() {
        return (isFiltered()) ? filteredList.size() : group.getEntryCount();
    }

    public void setFilter(String filterText) {
        if ("".equals(filterText)) {
            filteredList.clear();            
        } else {
            pattern = Pattern.compile(".*" +filterText.toLowerCase() + ".*");
            refilter();
        }
        fireContentsChanged(this, 0, getSize());
    }

    public Object getElementAt(int index) {
        return isFiltered() ? filteredList.get(index) : group.getEntryAt(index);
    }

}        

