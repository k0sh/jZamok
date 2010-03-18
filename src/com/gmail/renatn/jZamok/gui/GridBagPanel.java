package com.gmail.renatn.jZamok.gui;

import javax.swing.*;
import java.awt.*;

/**
 * User: renat
 * Date: 25.12.2006
 * Time: 16:53:44
 */
public class GridBagPanel extends JPanel {

	private GridBagConstraints constr = new GridBagConstraints();

	public GridBagPanel() {
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
	}

	public void place(Component cmp, int x, int y, int w, int h) {
	    constr.gridx = x;
	    constr.gridy = y;
	    constr.gridwidth = w;
	    constr.gridheight = h;
	    add(cmp, constr);
        }

	public void setAnchor(int i) {
            constr.anchor = i;
	}

	public void setFill(int i) {
            constr.fill = i;
            if (i == GridBagConstraints.HORIZONTAL) {
                setWeight(100, 0);
            } else if (i == GridBagConstraints.VERTICAL) {
                setWeight(0, 100);
            }  else if (i == GridBagConstraints.BOTH) {
                setWeight(100, 100);
            } else {
                setWeight(0, 0);
            }
	}

	public void setWeight(int x, int y) {
            constr.weightx = x;
            constr.weighty = y;
	}

	public void setInsets(int top, int left, int right, int bottom) {
            constr.insets.top = top;
            constr.insets.left = left;
            constr.insets.right = right;
            constr.insets.bottom = bottom;		
	}

	public void setInsets(Insets insets) {
            constr.insets.top = insets.top;
            constr.insets.left = insets.left;
            constr.insets.right = insets.right;
            constr.insets.bottom = insets.bottom;
	}             

}
