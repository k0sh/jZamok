package com.gmail.renatn.jZamok;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * User: renat
 * Date: 10.02.2007
 * Time: 22:20:28
 */
public final class AppProperties {

    private final static int DEFWIDTH = 600;
    private final static int DEFHEIGHT = 400;
    
    /*public final static int UI_MAIN_DIV_ID = 1;
    public final static int UI_CENTR_DIV_ID = 2;   */
    
    public final static String CONF_WIDTH_ID = "Application.Width";
    public final static String CONF_HEIGHT_ID = "Application.Height";
    public final static String CONF_MAIN_DIV_ID = "Application.MainDivider";
    public final static String CONF_CENTR_DIV_ID = "Application.CenterDivider";    

    public final static String XML_GROUP_KEY = "group";    
    public final static String XML_ENTRY_KEY = "entry";    
    
    public final static Dimension DEF_BTN_SIZE = new Dimension(96, 24);    

    public final static String profilePath = System.getProperty("user.home")
            + File.separator + ".config" + File.separator + "jZamok";
    private final static String fileProperties = profilePath + File.separator + "rc.ini";

    private static AppProperties instance = null;
    private Properties appProperties;

    private AppProperties() {
        
        Properties defProperties = new Properties();
        defProperties.put(CONF_WIDTH_ID, Integer.toString(DEFWIDTH));
        defProperties.put(CONF_HEIGHT_ID, Integer.toString(DEFHEIGHT));
        defProperties.put(CONF_MAIN_DIV_ID, "150");
        defProperties.put(CONF_CENTR_DIV_ID, "200");
        defProperties.put("Files.Last", "");
        appProperties = new Properties(defProperties);

    }

    public void load() throws IOException {
        FileInputStream fin = new FileInputStream(fileProperties);
        appProperties.load(fin);
        fin.close();
    }

    public void save() throws IOException {
        FileOutputStream fout = new FileOutputStream(fileProperties);
        appProperties.store(fout, "jZamok");
        fout.close();
    }

    public static synchronized AppProperties getInstance() {
        if (instance == null) {
            instance = new AppProperties();
        }
        return instance;
    }

    public Dimension getSize() {
        int wt = Integer.parseInt(appProperties.getProperty(CONF_WIDTH_ID));
        int ht = Integer.parseInt(appProperties.getProperty(CONF_HEIGHT_ID));
        return new Dimension(wt, ht);
    }

    public void setSize(Dimension size) {
        appProperties.put(CONF_WIDTH_ID, Integer.toString(size.width));
        appProperties.put(CONF_HEIGHT_ID, Integer.toString(size.height));
    }

    public File getLastFile() {
        
        String fileName = appProperties.getProperty("Files.Last");
        
        if (fileName != null && !"".equals(fileName)) {
            File f = new File(fileName);
            if (f.exists()) 
                return f;
        }        
      
        return null;                    
    
    }

    public void setLastFile(String fileName) {
        appProperties.put("Files.Last", fileName);       
    }
    
    public int getLastView() {
        return Integer.parseInt(appProperties.getProperty("Application.ViewAs", "0"));        
    }
          
    public void setLastView(int type) {
        appProperties.put("Application.ViewAs", Integer.toString(type));       
    }
    
    public void setSplitLoc(String id, int loc) {
        appProperties.put(id, Integer.toString(loc));
    }       
    
    public int getSplitLoc(String id) {
        return Integer.parseInt(appProperties.getProperty(id, "200"));
    }
        
    public static boolean checkProfile() {
        File profile = new File(profilePath);
        return profile.exists() || profile.mkdirs();
    }

}
