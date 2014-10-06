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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Handles assets bundled in a folder
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
        //nothing to close
    }

    
}
