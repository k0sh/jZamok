package com.gmail.renatn.jZamok.data;

import java.io.*;

import javax.swing.filechooser.FileFilter;

/**
 * User: renat
 * Date: 27.01.2006
 * Time: 12:10:07
 */

public class ZMKFileFilter extends FileFilter {

    private static final String extension = "zmk";
    private static final String description = "jZamok storage file (*."+extension+")";

    public boolean accept(File f) {

        if(f.isDirectory())
                return true;
        String fileName = f.getName().toLowerCase();
        return fileName.endsWith(extension);
    }


    public String getDescription() {
	    return description;
    }

}
