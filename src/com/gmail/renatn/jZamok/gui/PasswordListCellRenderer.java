package com.gmail.renatn.jZamok.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import com.gmail.renatn.jZamok.model.PasswordEntry;

/**
 *
 * @author renat
 */

public class PasswordListCellRenderer implements ListCellRenderer {

    private final RendererPanel panel;

    public PasswordListCellRenderer() {
        panel = new RendererPanel();        
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        Color bg;
        Color fg = list.getForeground();
        Color sg = list.getSelectionBackground();
        
        int listSize = list.getModel().getSize();
        if (listSize > 1) {
            if (index == 0) {
                panel.setIndex(0);
            } else if (index == (listSize - 1)) {
                panel.setIndex(1);
            } else {
                panel.setIndex(3);
            }
        } else {
            panel.setIndex(2);
        }

        if ((index % 2) == 0) {
            bg = list.getBackground().darker();
        } else {
            bg = darker2(list.getBackground());                               
        }
        
        if (value instanceof String) {
            panel.setPasswordEntry(null);
        } else {
            panel.setPasswordEntry((PasswordEntry)value);
        }                

        if (isSelected) {

            bg = list.getSelectionBackground();
            fg = list.getSelectionForeground();
            sg = list.getBackground();
                
        }

        panel.setSelected(fg, sg);
        panel.setBackground(bg);

        return panel;

    }  
    
    private Color darker2(Color c) {
        return new Color(Math.max((int)(c.getRed()*0.9), 0), 
                     Math.max((int)(c.getGreen()*0.9), 0),
                     Math.max((int)(c.getBlue() *0.9), 0));        
    }


    private static class RendererPanel extends GridBagPanel {

        private final JLabel titleLabel, dateLabel, userLabel;
        private final JLabel labelURL, labelEmail;        
        public int index = 0;

        public RendererPanel() {
                        
            setBorder(new EmptyBorder(8, 8, 8, 8));
            setOpaque(true);    

            titleLabel = new JLabel("First");
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
            dateLabel = new JLabel("User");
            dateLabel.setFont(dateLabel.getFont().deriveFont(Font.ITALIC));
            userLabel = new JLabel("Label");
            labelURL = new JLabel("http://jzamok");
            labelEmail = new JLabel("jzamok@mail.com");

            Color c = getForeground();
            dateLabel.setForeground(c.darker());            
            userLabel.setForeground(Color.YELLOW);
            
            c = SystemColor.infoText;
            labelURL.setForeground(c);
            labelEmail.setForeground(new Color(0, 129, 222));
                         
            setInsets(2, 2, 2, 2);
            setFill(GridBagConstraints.HORIZONTAL);            
            setAnchor(GridBagConstraints.WEST);
            place(titleLabel, 1, 1, 2, 1);
            place(labelURL, 2, 2, 2, 1);

            setFill(GridBagConstraints.NONE);            
            place(userLabel, 1, 2, 1, 1);
                        
            setFill(GridBagConstraints.NONE);            
            setAnchor(GridBagConstraints.EAST);
            place(dateLabel, 3, 1, 2, 1);
            place(labelEmail, 3, 2, 1, 1);

        }   
                
        public void setSelected(Color fg, Color sg) {
            titleLabel.setForeground(fg);            
            labelURL.setForeground(sg);            
            labelEmail.setForeground(sg);                        
        }        
                
        public void setPasswordEntry(PasswordEntry entry) {
            if (entry != null) {
                titleLabel.setText(entry.getTitle());
                dateLabel.setText(UIHelper.encodeDate(entry.getLastUpdated()));
                userLabel.setText(entry.getLogin());
                labelURL.setText(entry.getURL());
                labelEmail.setText(entry.getEmail());
            }
        }              
        
        public void setIndex(int index) {
            this.index = index;
        }
        
        private void drawUpRoundRect(Graphics2D g, int x, int y, int w, int h) {
            g.fillRoundRect(x, y-2, w, h, 16, 16);
            g.fillRect(x, y+8, w, h);                          
        }

        private void drawDownRoundRect(Graphics2D g, int x, int y, int w, int h) {
            g.fillRoundRect(x, y, w, h, 16, 16);
            g.fillRect(x, y, w, h-8);                          
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            int x = 2;
            int y = 1;
            int w = getWidth() - 4;
            int h = getHeight() - 1;

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            Color bg = getBackground();
            g2.setColor(bg);
            
            switch (index) {
                case 0:
                    y = 4;
                    drawUpRoundRect(g2, x, y, w, h);
                    break;
                case 1:
                    drawDownRoundRect(g2, x, y, w, h);
                    break;
                case 2:
                    g2.fillRoundRect(x, y, w, h, 16, 16);
                    break;
                default:
                    g2.fillRect(x, y, w, h);
                    break;
            }
            
            g2.dispose();
        }
        

    }

}
