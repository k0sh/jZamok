package com.gmail.renatn.jZamok.data;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * User: renat
 * Date: 15.02.2006
 * Time: 11:52:46
 */

public class XMLFileFilter extends FileFilter {

    private static final String extension = ".xml";
    private static final String description = "XML files (*"+extension+")";

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(extension);
    }

    @Override
    public String getDescription() {
        return description;
    }

}
