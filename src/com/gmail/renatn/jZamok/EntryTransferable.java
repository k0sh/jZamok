package com.gmail.renatn.jZamok;

import com.gmail.renatn.jZamok.model.PasswordEntry;

import com.gmail.renatn.jZamok.model.PasswordGroup;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * User: renat
 * Date: 03.03.2006
 * Time: 10:39:39
 */
public class EntryTransferable implements Transferable  {

    public final static String mimeType = DataFlavor.javaJVMLocalObjectMimeType + "; class=com.gmail.renatn.jZamok.model.PasswordEntry";
    private boolean isCut = false;
    private PasswordEntry entry;
    private PasswordGroup group;

    public EntryTransferable(PasswordGroup group, PasswordEntry entry) {

        this.group = group;
        this.entry = entry;

    }

    public DataFlavor[] getTransferDataFlavors() {

        DataFlavor[] flavors = new DataFlavor[1];
        try {
            flavors[0] = new DataFlavor(mimeType);
            return flavors;
        } catch (ClassNotFoundException e) {
            return new DataFlavor[0];
        }

    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.getMimeType().equals(mimeType);
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        if (!isDataFlavorSupported(flavor))
            throw new UnsupportedFlavorException(flavor);

        return entry;
    }
    
    public boolean isCut() {
        return isCut;
    }
    
    public void setCut(boolean cut) {
        this.isCut = cut;
    }
    
    public PasswordGroup getGroup() {
        return group;
    }
    
    public void setGroup(PasswordGroup group) {
        this.group = group;
    }

}
