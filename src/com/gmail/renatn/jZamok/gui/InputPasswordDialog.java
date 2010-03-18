package com.gmail.renatn.jZamok.gui;

import com.gmail.renatn.jZamok.AppProperties;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * User: renat
 * Date: 10.02.2007
 * Time: 23:25:29
 */
public class InputPasswordDialog extends JDialog {

    private boolean modalResult = false;

    private JTextField tfPassword;
    private JPasswordField pfPassword;
    private JCheckBox chkShow;

    public InputPasswordDialog(JFrame f, String filename) {

        super(f, true);
        setTitle("Open file - "+filename);

        GridBagPanel contentPane = new GridBagPanel();
        setContentPane(contentPane);

        EnterListener enterListener = new EnterListener();
        tfPassword = new JTextField(20);
        tfPassword.addKeyListener(enterListener);
        pfPassword = new JPasswordField(20);
        pfPassword.addKeyListener(enterListener);

        final CardLayout carder = new CardLayout();
        final JPanel carderPane = new JPanel(carder);

        carderPane.add("hide", pfPassword);
        carderPane.add("visible", tfPassword);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okAction();
            }
        });
        btnOK.setMinimumSize(AppProperties.DEF_BTN_SIZE);
        btnOK.setPreferredSize(AppProperties.DEF_BTN_SIZE);
        
        chkShow = new JCheckBox(UIHelper.getString("Label.Visible"));
        chkShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carder.next(carderPane);
                if (chkShow.isSelected()) {
                    String s = new String(pfPassword.getPassword());
                    tfPassword.setText(s);
                    tfPassword.requestFocusInWindow();
                } else {
                    pfPassword.setText(tfPassword.getText());                                        
                    pfPassword.requestFocusInWindow();
                }
            }
        });

        contentPane.setAnchor(GridBagConstraints.WEST);
        contentPane.setInsets(4, 4, 8, 0);

        contentPane.place(new JLabel(UIHelper.getImageIcon("jzamok.png")), 1, 1, 1, 3);
        contentPane.place(new JLabel(UIHelper.getString("Label.Enter")), 2, 1, 1, 1);
        contentPane.place(carderPane, 2, 2, 1, 1);
        contentPane.place(chkShow, 2, 3, 1, 1);

        contentPane.setInsets(4, 2, 8, 8);
        contentPane.setAnchor(GridBagConstraints.EAST);
        contentPane.place(btnOK, 1, 4, 2, 1);
        
        contentPane.registerKeyboardAction(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeDialog();
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
     

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeDialog();
            }
        });

        pack();
        setLocationRelativeTo(f);

    }

    private void okAction() {
        modalResult = true;
        closeDialog();
    }

    private void closeDialog() {
        setVisible(false);
        dispose();
    }

    public boolean showDialog() {
        modalResult = false;
        setVisible(true);
        return modalResult;
    }

    public char[] getPassword() {
        if (chkShow.isSelected()) {
            return tfPassword.getText().toCharArray();
        } else {
            return pfPassword.getPassword();
        }
    }

    private class EnterListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                okAction();
            } 
        }                
    }
}
