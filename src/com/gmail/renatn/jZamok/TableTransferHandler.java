package com.gmail.renatn.jZamok;

import com.gmail.renatn.jZamok.model.PasswordEntry;
import com.gmail.renatn.jZamok.gui.ZamokTableModel;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * User: renat
 * Date: 02.03.2006
 * Time: 10:50:48
 */
public class TableTransferHandler extends TransferHandler {

    private String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=com.gmail.renatn.jZamok.model.PasswordEntry";
    private DataFlavor entryFlavor;
    private PasswordEntry entry;
    private boolean shouldRemove;

    public TableTransferHandler() {
        try {
            entryFlavor = new DataFlavor(mimeType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    protected Transferable createTransferable(JComponent c) {

        JTable table = (JTable)c;
        ZamokTableModel tableModel = (ZamokTableModel) table.getModel();
        int selRow = table.getSelectedRow();
        entry = new PasswordEntry(); //tableModel.getEntryAt(selRow);
        shouldRemove = true;
        return new PasswordEntryTransferable(entry);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean importData(JComponent c, Transferable t) {
        return false;
    }

    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable t, int action) {

    }

    class PasswordEntryTransferable implements Transferable {

        private PasswordEntry entry;

        public PasswordEntryTransferable(PasswordEntry entry) {

            this.entry = entry;

        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] {entryFlavor};  //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

    }
}
