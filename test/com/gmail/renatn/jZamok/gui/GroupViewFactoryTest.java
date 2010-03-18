/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.renatn.jZamok.gui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.gmail.renatn.jZamok.model.PasswordGroup;

/**
 *
 * @author renat
 */
public class GroupViewFactoryTest {
    
    private PasswordGroup group;

    public GroupViewFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        group = new PasswordGroup("Test");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createView method, of class GroupViewFactory.
     */
    @Test
    public void createView() {
        System.out.println("createView");
        GroupView view = GroupViewFactory.createView(group, 0);
        assertEquals(true, view instanceof GroupListView);
        view = GroupViewFactory.createView(group, 1);
        assertEquals(true, view instanceof GroupTableView);
    }

}