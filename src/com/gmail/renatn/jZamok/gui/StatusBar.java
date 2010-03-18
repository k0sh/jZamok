package com.gmail.renatn.jZamok.gui;

/*
 * AppStatuBar.java
 *
 * Created on 2 Ноябрь 2006 г., 15:26
 *
 */

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;

public class StatusBar extends GridBagPanel {

    private final static String STR_TOTAL = UIHelper.getString("Label.Total")+": ";    
    private final static String STR_GROUP = UIHelper.getString("Label.Group")+": ";    

    private JLabel labelTotal, lbCurrent, lbMessage;

    public StatusBar() {

        initComponents();

        place(labelTotal,  1, 1, 1, 1);
        place(lbCurrent,   2, 1, 1, 1);

        setFill(GridBagConstraints.BOTH);
        place(lbMessage,  3, 1, 2, 1);

    }
    
    private JLabel createLabel(String text) {
        final Dimension defsize = new Dimension(96, 24);
        JLabel l = new JLabel(text);
        l.setMinimumSize(defsize);
        l.setPreferredSize(defsize);
        return l;        
    }
    
    private void initComponents() {


        labelTotal = createLabel(STR_TOTAL);
        lbCurrent  = createLabel(STR_GROUP);
        lbMessage = new JLabel();

        Border inside = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        Border outside = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border border = BorderFactory.createCompoundBorder(outside, inside);

        labelTotal.setBorder(border);
        lbMessage.setBorder(border);
        lbCurrent.setBorder(border);

    }

    public void setTotal(int total) {
        labelTotal.setText(STR_TOTAL + Integer.toString(total));
    }
    
    public void setCurrent(int current) {
        lbCurrent.setText(STR_GROUP + Integer.toString(current));       
    }

    public void setMessage(String s) {
            lbMessage.setText(s);		
    }

}




