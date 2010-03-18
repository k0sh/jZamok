/*
 * ZamokHandler.java
 *
 * Created on 14 Август 2007 г., 11:03
 *
 */
package com.gmail.renatn.jZamok.data;

import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.gmail.renatn.jZamok.model.*;
import com.gmail.renatn.jZamok.AppProperties;

/**
 * @author renat
 */
public class ZamokHandler extends DefaultHandler {
    
    StringBuffer accumulator;
    PasswordGroup group, parent, root;
    PasswordEntry entry;  
    
    @Override
    public void startDocument () {
        accumulator = new StringBuffer();
    }

    @Override
    public void characters (char ch[], int start, int length) {
        accumulator.append(ch, start, length);
    }
    
    @Override
    public void startElement (String uri, String localName,
			      String qName, Attributes attributes) {
        accumulator.setLength(0);
        
        if (qName.equals(AppProperties.XML_GROUP_KEY)) {
            
            String groupName = attributes.getValue("name");            
            
            group = new PasswordGroup(groupName, parent);                        
            if (parent != null) {
                parent.addGroup(group);
            } else {
                root = group;
            }
            parent = group;                  
            
        } else if (qName.equals(AppProperties.XML_ENTRY_KEY)) {
            
            entry = new PasswordEntry();
            
        }                    
    }
    
    @Override
    public void endElement (String uri, String localName, String qName) {
        
        if (qName.equals("title")) {
            entry.setTitle(accumulator.toString()); 
        } else if (qName.equals("login")) {
            entry.setLogin(accumulator.toString());
        } else if (qName.equals("password")) {
            entry.setPassword(accumulator.toString());
        } else if (qName.equals("url")) {
            entry.setURL(accumulator.toString());
        } else if (qName.equals("email")) {
            entry.setEmail(accumulator.toString());
        } else if (qName.equals("notes")) {
            entry.setNotes(accumulator.toString());
        } else if (qName.equals("updated")) {
            Date date = new Date(Long.parseLong(accumulator.toString()));
            entry.setLastUpdated(date);
        } else if (qName.equals(AppProperties.XML_ENTRY_KEY)) {
          
            group.addEntry(entry);
        
        } else if (qName.equals(AppProperties.XML_GROUP_KEY)) {

            parent = parent.getParent(); 
            
        }
        
    }             
    
    public PasswordGroup getRoot() {
        return root;
    }
                
}
