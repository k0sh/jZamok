package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * User: renat
 * Date: 26.02.2006
 * Time: 13:35:02
 */
public class ZmkCellRender extends DefaultTableCellRenderer {

    private boolean isOdd = false;
    private static final Color ROW_COLOR = new Color(111, 135, 168, 64);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
	isOdd = (row % 2 != 0);
        return super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
    
    }

    @Override
    public void paintComponent(Graphics g) {

        if (isOdd) {

            g.setColor(ROW_COLOR);
            g.fillRect(0,0,getWidth(),getHeight());

	}

        super.paintComponent(g);

    }

}
