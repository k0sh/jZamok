package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.model.*;

import javax.swing.tree.*;
import javax.swing.event.TreeModelListener;

/**
 * User: renat
 * Date: 17.01.2006
 * Time: 22:30:03
 */
public class ZamokTreeModel implements TreeModel {

    private ZamokDataModel dataModel;

    public ZamokTreeModel(ZamokDataModel model) {
        this.dataModel = model;               
    }

    public Object getRoot() {
        return dataModel.getRoot();
    }

    public int getChildCount(Object parent) {

        PasswordGroup group = (PasswordGroup) parent;
        return group.getListGroup().size();

    }

    public int getIndexOfChild(Object parent, Object child) {

        PasswordGroup group = (PasswordGroup) parent;
        PasswordGroup chld =  (PasswordGroup) child;
        return group.getListGroup().indexOf(chld);

    }

    public Object getChild(Object parent, int index) {

        PasswordGroup group = (PasswordGroup) parent;
        return group.getListGroup().get(index);

    }

    public boolean isLeaf(Object Node) {

        PasswordGroup group = (PasswordGroup) Node;
        return (group.getListGroup().size() == 0);

    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }


}
