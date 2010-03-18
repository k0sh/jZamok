package com.gmail.renatn.jZamok;

import javax.swing.*;
import java.awt.datatransfer.Transferable;

/**
 * User: renat
 * Date: 02.03.2006
 * Time: 12:03:01
 */
public class TreeTransferHandler extends TransferHandler {

    @Override
    public boolean importData(JComponent c, Transferable t) {
        JTree tree = (JTree) c;
        return false;
    }

}
