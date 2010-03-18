package com.gmail.renatn.jZamok.gui;

import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * User: renat
 * Date: 21.02.2006
 * Time: 12:46:49
 */

public class AutoPasswordDialog extends TitleDialog {

    private JTextField pswResult, pswLength;
    private JRadioButton btnMixed, btnUpper, btnLower;
    private JCheckBox btnSpec, btnNumber;

    public AutoPasswordDialog(JFrame f) {
        super(f);

        setTitle("Generate Random Password");

        pswLength = new JTextField("8", 5);
        
        JButton btnOk = getOkButton();
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modalResult = !pswResult.getText().equals("");
                AutoPasswordDialog.this.setVisible(false);
            }
        });
        
        JPanel casePanel = createRadioGroup(UIHelper.getString("Label.Case"));
        JPanel usePanel = createCheckBoxGroup(UIHelper.getString("Label.Use"));
        JPanel resultPanel = createResultBox(UIHelper.getString("Label.Result"));      

        GridBagPanel panel = getPanel();
        panel.setInsets(8, 4, 4, 0);
        panel.setAnchor(GridBagConstraints.EAST);
        panel.place(new JLabel(UIHelper.getString("Label.Length")), 2, 1, 1, 1);

        panel.setAnchor(GridBagConstraints.CENTER);
        panel.place(pswLength, 3, 1, 1, 1);

        panel.setFill(GridBagConstraints.HORIZONTAL);
        panel.place(resultPanel, 0, 2, 4, 1);

        panel.setInsets(8, 4, 4, 0);
        panel.setFill(GridBagConstraints.BOTH);
        panel.setAnchor(GridBagConstraints.CENTER);
        panel.place(casePanel, 0, 0, 2, 2);
        panel.place(usePanel, 2, 0, 2, 1);

        ImageIcon icon = UIHelper.getImageIcon("accueil.png");       
        HeaderPanel header = getHeader();
        header.setTitres("Generate Random Password", "Generate random password");
        header.setIcon(icon);
              
        pack();    
        setLocationRelativeTo(f);

    }

    private JPanel createRadioGroup(String title) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title)); //"Case Type"

        btnMixed = (JRadioButton) panel.add(new JRadioButton(UIHelper.getString("Label.Mixed")));
        btnMixed.setSelected(true);
        btnUpper = (JRadioButton) panel.add(new JRadioButton(UIHelper.getString("Label.Upper")));
        btnLower = (JRadioButton) panel.add(new JRadioButton(UIHelper.getString("Label.Lower")));
        ButtonGroup casegroup = new ButtonGroup();
        casegroup.add(btnMixed);
        casegroup.add(btnUpper);
        casegroup.add(btnLower);

        return panel;

    }

    private JPanel createCheckBoxGroup(String title) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        btnSpec = (JCheckBox) panel.add(new JCheckBox(UIHelper.getString("Label.Symbols")));
        btnNumber = (JCheckBox) panel.add(new JCheckBox(UIHelper.getString("Label.Numbers")));
        btnNumber.setSelected(true);

        return panel;

    }

    private JPanel createResultBox(String title) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JButton autoButton = new JButton("...");
        Dimension btnsize = new Dimension(24,21);

        autoButton.setPreferredSize(btnsize);
        autoButton.setMinimumSize(btnsize);
        autoButton.setToolTipText("Generate");
        autoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String psw = generatePassword();
                pswResult.setText(psw);
            }
        });

        pswResult = new JTextField(20);
        String psw = generatePassword();
        pswResult.setText(psw);

        panel.add(pswResult);
        panel.add(autoButton);

        return panel;

    }

    private String generatePassword() {

        int n, v;
        Random generator = new Random();
        StringBuffer password = new StringBuffer();
        int max = Integer.parseInt(pswLength.getText());

        for (int i=0; i<max; i++) {

            n = generator.nextInt(3); // uppercase
            v = generator.nextInt(2); // char, symb, number

            if (btnMixed.isSelected()) {
                if (n == 0)
                    n = generator.nextInt(26) + 65;
                else
                    n = generator.nextInt(26) + 97;
            } else if (btnUpper.isSelected()) {
                n = generator.nextInt(26) + 65;
            } else if (btnLower.isSelected()) {
                n = generator.nextInt(26) + 97;
            }

            if (v == 0) {
                if (btnSpec.isSelected())
                    n = generator.nextInt(15) + 33;
            } else if (v == 1) {
                if (btnNumber.isSelected()) {
                    n = generator.nextInt(10) + 48;
                }
            }

            password.append((char)n);
        }

        return password.toString();
    }

    public String getPassword() {
        return pswResult.getText();
    }

}
