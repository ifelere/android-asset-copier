/*
 * Copyright (C) 2014 IFELERE
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.ifraytek.android.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Handles assets bundled in zipped packages
 * @author IFELERE
 */
public class ZippedCopier extends Copier {
    private ZipFile zipFile;
    public ZippedCopier(File source, File destination, boolean overwrite, String[] folders) {
        super(source, destination, overwrite, folders);
    }
    
    private String rootEntry = "res";

    public String getRootEntry() {
        if (rootEntry == null) {
            rootEntry = "res";
        }
        return rootEntry;
    }

    public void setRootEntry(String rootEntry) {
        this.rootEntry = rootEntry;
    }
    
    
    private ZipFile getZipFile() throws IOException {
        if (zipFile == null) {
            zipFile = new ZipFile(getSource());
        }
        return zipFile;
    }
    
   
    @Override
    protected String[] getResourceNames(String folder) {
        try {
            ZipFile z = this.getZipFile();
            if (z != null) {
                Enumeration<? extends ZipEntry> entries = z.entries();
                ArrayList<String> list = new ArrayList<>();
                while (entries.hasMoreElements()) {
                    ZipEntry e = entries.nextElement();
                    if (!e.isDirectory()) {
                        int idx = e.getName().indexOf(folder + "/");
                        if (idx > 0) {
                            list.add(e.getName().substring(idx + (folder + "/").length()));
                        }
                    }
                }
                return list.toArray(new String[list.size()]);
            }
        } catch (IOException ex) {
            Logger.getLogger(ZippedCopier.class.getName()).log(Level.SEVERE, null, ex);
            fireErrorOccured(ex);
        }
        return new String[0];
    }

    @Override
    protected InputStream read(String folder, String name) throws IOException {
        ZipFile z = this.getZipFile();
        if (z != null) {
            Enumeration<? extends ZipEntry> entries = z.entries();
            while (entries.hasMoreElements()) {
                ZipEntry e = entries.nextElement();
                if (e.getName().contains(folder) && e.getName().endsWith(name)) {
                    return z.getInputStream(e);
                }
            }
        }
        throw new IOException(String.format("'%s/%s' not found in zipped file", folder, name));
    }

    @Override
    public void close() throws IOException {
        if (this.zipFile != null) {
            this.zipFile.close();
            this.zipFile = null;
        }
    }
    
}
