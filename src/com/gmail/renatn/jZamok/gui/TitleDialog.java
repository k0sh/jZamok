package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author renat
 */
public class TitleDialog extends JDialog {
    
    private GridBagPanel mainPanel;
    private HeaderPanel headerPanel;
    private JButton btnOk;

    protected boolean modalResult = false;
    
    public TitleDialog(JFrame f) {
        super(f, true);
        
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        
        ImageIcon icon = UIHelper.getImageIcon("accueil.png");
        
        headerPanel = new HeaderPanel("Add Entry", "Add a new password entry", icon);           
        mainPanel = new GridBagPanel();
        mainPanel.registerKeyboardAction(new EscListener(),
             KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
             JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnOk = new JButton("OK");
        btnOk.setMinimumSize(new Dimension(96, 24));
        btnOk.setPreferredSize(new Dimension(96, 24));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnOk);
        
        c.add(headerPanel, BorderLayout.NORTH);
        c.add(mainPanel, BorderLayout.CENTER);
        c.add(buttonPanel, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnOk);

    }
    
    public GridBagPanel getPanel() {
        return mainPanel;
    }
    
    public HeaderPanel getHeader() {
        return headerPanel;
    }
    
    public JButton getOkButton() {
        return btnOk;
    }
    
    private class EscListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
        
    }

    public boolean showDialog() {
        modalResult = false;
        setVisible(true);
        return modalResult;
    }
    
    
}
