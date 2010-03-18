package com.gmail.renatn.jZamok.gui;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/**
 * User: renat
 * Date: 27.02.2006
 * Time: 11:12:35
 */
public class HeaderPanel extends JPanel {

    private JLabel iconLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;

    public HeaderPanel(String title, String description, ImageIcon icon) {

        super(new BorderLayout());

        JPanel titres = new JPanel(new GridLayout(3, 1));
        titres.setBorder(new EmptyBorder(8, 0, 8, 0));
        titres.setOpaque(false);

        titleLabel = new JLabel(title);
        titres.add(titleLabel);

        descriptionLabel = new JLabel(description);
        Font police = descriptionLabel.getFont().deriveFont(Font.PLAIN);
        descriptionLabel.setFont(police);
        titres.add(descriptionLabel);

        iconLabel = new JLabel(icon);
        iconLabel.setBorder(new EmptyBorder(0, 12, 0, 12));

        add(iconLabel, BorderLayout.WEST);
        add(titres, BorderLayout.CENTER);
        add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, icon.getIconHeight()+16));

    }

    public void setTitres(String title, String description) {

        titleLabel.setText(title);
        descriptionLabel.setText(description);
        
    }
    
    public void setIcon(ImageIcon icon) {
        iconLabel.setIcon(icon);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isOpaque()) {
            return;
        }

        Color control = UIManager.getColor("control");
        int width = getWidth();
        int height = getHeight();

        Icon icon = iconLabel.getIcon();
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(new GradientPaint(icon.getIconWidth(), 0, 
				new Color(111, 135, 168, 64), width, 0, control));
        g2.fillRect(0,0,width,height);

    }
}
