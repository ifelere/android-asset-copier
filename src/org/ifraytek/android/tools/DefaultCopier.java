/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ifraytek.android.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author IFELERE
 */
public class DefaultCopier extends Copier {

    public DefaultCopier(File source, File destination, boolean overwrite, String[] folders) {
        super(source, destination, overwrite, folders);
    }
    
    private File getResourceRoot() {
        if ("res".equals(this.getSource().getName())) {
            return this.getSource();
        }
        File f = new File(getSource(), "res");
        if (f.exists()) {
            return f;
        }
        throw new RuntimeException("resource root not found");
    }

    @Override
    protected String[] getResourceNames(String folder) {
        File f = new File(getResourceRoot(), folder);
        if (f.exists()) {
            return f.list();
        }
        return new String[0];
    }

    @Override
    protected InputStream read(String folder, String name) throws FileNotFoundException {
        return new FileInputStream(new File(new File(getResourceRoot(), folder), name));
    }

    @Override
    public void close() throws IOException {
        
    }

    
}
