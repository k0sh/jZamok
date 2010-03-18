package com.gmail.renatn.jZamok.data;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

import com.gmail.renatn.jZamok.model.*;
import com.gmail.renatn.jZamok.AppProperties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: renat
 * Date: 17.01.2006
 * Time: 22:24:45
 */

public class FileStorage {

    private PasswordGroup root;

    protected void load(InputStream in) throws IOException {

        ZamokHandler handler = new ZamokHandler();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);     // We don't want validation
        factory.setNamespaceAware(false); // No namespaces please
        
        try {
           
            SAXParser parser = factory.newSAXParser();
            parser.parse(in, handler);

            root = handler.getRoot();            

        } catch (SAXException e) {
            Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }          
        
    }
    
    public void loadFromFile(File file) throws IOException {
        load( new FileInputStream(file) );        
    }        
    
    protected void save(ZamokDataModel dataModel, OutputStream out) throws IOException {

        StreamResult rout = new StreamResult(out);
        
        try {

            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = tf.newTransformerHandler();
            Transformer t = handler.getTransformer();
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty(OutputKeys.INDENT, "yes");        

            handler.setResult(rout);

            handler.startDocument();         
            printDataModel(dataModel.getRoot(), handler);      
            handler.endDocument();
        
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        
    }
   
    public void saveToFile(ZamokDataModel model, File file) throws IOException {
        save(model, new FileOutputStream(file));        
    }
    
    public PasswordGroup getRoot() {
        return root;
    }    
    
    private void printElement(TransformerHandler handler, String qName, String value) throws SAXException {
        
        handler.startElement(null, null, qName, null);
        if (value != null)
            handler.characters(value.toCharArray(), 0, value.length());
        handler.endElement(null, null, qName);
        
    }
    
    private void printEntryXML(PasswordEntry psw, TransformerHandler handler) throws SAXException {

        handler.startElement(null, null, AppProperties.XML_ENTRY_KEY, null);
        
        printElement(handler, "title", psw.getTitle());
        printElement(handler, "login", psw.getLogin());
        printElement(handler, "password", psw.getPassword(false));
        printElement(handler, "url", psw.getURL());
        printElement(handler, "email", psw.getEmail());            
        printElement(handler, "notes", psw.getNotes());
        printElement(handler, "updated", Long.toString(psw.getLastUpdated().getTime()));
                    
        handler.endElement(null, null, AppProperties.XML_ENTRY_KEY);
        
    }
    
    public void printDataModel(PasswordGroup root, TransformerHandler handler) throws SAXException {  
        
        AttributesImpl att = new AttributesImpl();
        att.addAttribute(null, null, "name", "CDATA", root.getName());

        handler.startElement(null, null, AppProperties.XML_GROUP_KEY, att);
        
        for (int i=0; i<root.getEntryCount(); i++) {
            PasswordEntry entry = root.getEntryAt(i);
            printEntryXML(entry, handler);    
        }
       
        for (PasswordGroup group : root.getListGroup()) {
            printDataModel(group, handler);    
        }        
       
        handler.endElement(null, null, AppProperties.XML_GROUP_KEY);

    }
    
}
