/*
 * AboutAction.java
 *
 * Created on 28 Август 2007 г., 17:48
 *
 */

package com.gmail.renatn.jZamok.actions;

import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import com.gmail.renatn.jZamok.gui.UIHelper;
import com.gmail.renatn.jZamok.gui.MainFrame;

/**
 *
 * @author renat
 */
public class AboutAction extends AbstractAction {
            
    private JFrame frame;   
    
    public AboutAction(JFrame frame) {
        
        this.frame = frame;       
        putValue(Action.NAME, UIHelper.getString("Menu.About"));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        
        URL img = UIHelper.getResource("jzamok.png");
        String imgsrc = String.format("<img src=\"%s\">", img.toString());
        JOptionPane.showMessageDialog(frame,
            "<html><center><b><font color=red>j</font><font color=navy>Zamok</font></b>"
            +" "+MainFrame.appVersion+"<br>"
            +imgsrc
            +"<br>(c) Nasyrov Renat, 2006 - 2008<br>e-Mail: renatn@gmail.com </center></html>",
            "About", 
            JOptionPane.INFORMATION_MESSAGE
        );
        
    }
    
}
