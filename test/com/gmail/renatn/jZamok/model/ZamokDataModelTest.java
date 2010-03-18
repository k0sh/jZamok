/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.renatn.jZamok.model;

import java.io.File;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author renat
 */
public class ZamokDataModelTest {
    
    ZamokDataModel model;

    public ZamokDataModelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        PasswordGroup root = new PasswordGroup("Root");
        model = new ZamokDataModel();
        File file = new File("test.zmk");
        model.setFile(file);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addZamokListener method, of class ZamokDataModel.
     */
    @Test
    public void addZamokListener() {
        System.out.println("addZamokListener");
        ZamokListener listener = null;
        ZamokDataModel instance = new ZamokDataModel();
        instance.addZamokListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEntry method, of class ZamokDataModel.
     */
    @Test
    public void addEntry() {
        System.out.println("addEntry");
        PasswordEntry entry = new PasswordEntry();
        PasswordGroup group = model.getRoot();
        model.addEntry(entry, group);

        PasswordEntry check = model.getRoot().getEntryAt(0);
        assertSame(entry, check);
    }

    /**
     * Test of delEntry method, of class ZamokDataModel.
     */
    @Test
    public void delEntry() {
        System.out.println("delEntry");
        PasswordEntry entry = new PasswordEntry();
        PasswordGroup group = model.getRoot();
        model.addEntry(entry, group);
        model.delEntry(entry, group);
        
        assertEquals(0, group.getEntryCount());
    }

    /**
     * Test of addGroup method, of class ZamokDataModel.
     */
    @Test
    public void addGroup() {
        System.out.println("addGroup");
        model.addGroup("doom", model.getRoot());
        PasswordGroup result = model.getRoot().getListGroup().get(0);
        assertEquals("doom", result.getName());        
    }

    /**
     * Test of renameGroup method, of class ZamokDataModel.
     */
    @Test
    public void renameGroup() {
        System.out.println("renameGroup");
        PasswordGroup group = model.getRoot();
        model.renameGroup("Not Root", group);
        assertEquals("Not Root", model.getRoot().getName());
    }

    /**
     * Test of delGroup method, of class ZamokDataModel.
     */
    @Test
    public void delGroup() {
        System.out.println("delGroup");
        PasswordGroup group = null;
        ZamokDataModel instance = new ZamokDataModel();
        instance.delGroup(group);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class ZamokDataModel.
     */
    @Test
    public void getCount() {
        System.out.println("getCount");
        assertEquals(0, model.getCount());
        model.addEntry(new PasswordEntry(), model.getRoot());
        assertEquals(1, model.getCount());        
    }

    /**
     * Test of setChanged method, of class ZamokDataModel.
     */
    @Test
    public void setChanged() {
        System.out.println("setChanged");
        assertEquals(false, model.isChanged());        
        model.setChanged(true);
        assertEquals(true, model.isChanged());        
    }

    /**
     * Test of isChanged method, of class ZamokDataModel.
     */
    @Test
    public void isChanged() {
        System.out.println("isChanged");
        assertEquals(false, model.isChanged());
        model.addGroup("child", model.getRoot());
        assertEquals(true, model.isChanged());
    }

    /**
     * Test of getRoot method, of class ZamokDataModel.
     */
    @Test
    public void getRoot() {
        System.out.println("getRoot");
        PasswordGroup result = model.getRoot();
        assertEquals("passwords", result.getName());
    }

    /**
     * Test of getFile method, of class ZamokDataModel.
     */
    @Test
    public void getFile() {
        System.out.println("getFile");
        File result = model.getFile();
        assertEquals("test.zmk", result.getName());
    }
   
    /**
     * Test of setFile method, of class ZamokDataModel.
     */
    @Test
    public void setFile() {
        System.out.println("setFile");
        File file = new File("test2.zmk");
        model.setFile(file);
        assertEquals(file, model.getFile());
    }
    
    
    /**
     * Test of isRoot method, of class ZamokDataModel.
     */
    @Test
    public void isRoot() {
        System.out.println("isRoot");
        PasswordGroup group = model.getRoot();
        assertEquals(true, model.isRoot(group));
    }

    /**
     * Test of getPhrase method, of class ZamokDataModel.
     */
    @Test
    public void getPhrase() {
        System.out.println("getPhrase");
        char[] origin = {'s', 'i', 'm', 's', 'i', 'm'};       
        char[] phrase = model.getPhrase();
        boolean result = Arrays.equals(origin, phrase);
        assertEquals(true, result);
    }

    /**
     * Test of setPhrase method, of class ZamokDataModel.
     */
    @Test
    public void setPhrase() {
        System.out.println("setPhrase");
        char[] phrase = {'i', 'd', 'd', 'q', 'd'};       
        model.setPhrase(phrase);
        boolean result = Arrays.equals(phrase, model.getPhrase());
        assertEquals(true, result);        
    }

}