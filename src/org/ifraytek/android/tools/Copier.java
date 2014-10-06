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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.ifraytek.android.tools.events.CopierEventObject;
import org.ifraytek.android.tools.events.CopierListener;

/**
 * <p>
 * An abstract copier. It implements most of logic to coordinate events and map source to destination.
 * </p>
 * <p>
 * The implementation is based on the structure of android assets (drawables in this case although the design is structured to handle any other android folder-set).
 * It leaves the job of reading asset data and discovering asset names  to sub-classes
 * </p>
 * 
 * @author IFELERE
 * @version 0.1.0
 */
public abstract class Copier implements Runnable, java.io.Closeable {
    private final File source, destination;
    private boolean overwrite;
    private String[] folders;
    protected final EventListenerList listenerList = new EventListenerList();
    
    /**
     * Construct a copier based on source and destination files
     * @param source The source file (typically a zipped file, a folder can also be used) 
     * @param destination The destination folder (root folder to pre-defined assets on an android project file-tree)
     * @param overwrite The if <tt>true</tt> then the file is overwritten if it exists
     * @param folders An array of folders assets are expected to be copied to (and expected on source path)
     */
    protected Copier(File source, File destination, boolean overwrite, String[] folders) {
        this.source = source;
        this.destination = destination;
        this.overwrite = overwrite;
        this.folders = folders;
    }

    public void addCopierListener(CopierListener l) {
        this.listenerList.add(CopierListener.class, l);
    }
    
    public void removeCopierListener(CopierListener l) {
        this.listenerList.remove(CopierListener.class, l);
    }
    
    public File getSource() {
        return source;
    }

    public File getDestination() {
        return destination;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public String[] getFolders() {
        return folders;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public void setFolders(String[] folders) {
        this.folders = folders;
    }

    /**
     * Called to process a copy
     */
    @Override
    public final void run() {
        if (this.folders != null && this.folders.length > 0) {
            for (String folder : this.folders) {
                treat(folder);
            }
        }
        try {
            this.close();
        } catch (IOException ex) {
            Logger.getLogger(Copier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract String[] getResourceNames(String folder);
    
    protected static String joinPaths(String... paths) {
        String sep = File.separator;
        StringBuilder buff = new StringBuilder();
        for (int i = 0, len = paths.length; i < len; i++) {
            if (i > 0) {
                buff.append(sep);
            }
            buff.append(paths[i]);
        }
        return buff.toString();
    }
    
    private String[] skipResources;

    public String[] getSkipResources() {
        return skipResources;
    }

    public void setSkipResources(String[] skipResources) {
        this.skipResources = skipResources;
        if (this.skipResources != null) {
            Arrays.sort(this.skipResources);
        }
    }
    
    protected void fireResourceSkipped(String source, String destinaton) {
        Object[] listeners = this.listenerList.getListeners(CopierListener.class);
        if (listeners.length > 0) {
            for (int k = listeners.length - 1; k >= 0; k--) {
                ((CopierListener)listeners[k]).onResourceSkipped(new CopierEventObject(source, destinaton, this));
            }
        }
    }
    
    protected void fireResourceCopied(String source, String destinaton) {
        Object[] listeners = this.listenerList.getListeners(CopierListener.class);
        if (listeners.length > 0) {
            for (int k = listeners.length - 1; k >= 0; k--) {
                ((CopierListener)listeners[k]).onResourceCopied(new CopierEventObject(source, destinaton, this));
            }
        }
    }
    
    protected void fireErrorOccured(Exception error) {
        Object[] listeners = this.listenerList.getListeners(CopierListener.class);
        if (listeners.length > 0) {
            for (int k = listeners.length - 1; k >= 0; k--) {
                ((CopierListener)listeners[k]).onErrorOccured(new CopierEventObject(error, this));
            }
        }
    }
    
    protected void treat(String folder) {
        // Discover existing asset names for a folder
        String[] names = this.getResourceNames(folder);
        if (names != null && names.length > 0) {
            
            for (String name : names) {
                //skip if asset of this name should be skipped
                if (this.skipResources != null && Arrays.binarySearch(this.skipResources, name) != -1) {
                    continue;
                }
                //handle the file if it does not exists or it should be overwritten
                if (!isExists(folder, name) || isOverwrite()) {
                    java.io.InputStream in = null;
                    try {
                        in = read(folder, name);
                        write(in, folder, name);
                        fireResourceCopied(joinPaths(getSource().getAbsolutePath(), folder, name),
                                joinPaths(getDestination().getAbsolutePath(), folder, name));
                    } catch (IOException ex) {
                        Logger.getLogger(Copier.class.getName()).log(Level.SEVERE, null, ex);
                        fireErrorOccured(ex);
                    }finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Copier.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }else {
                    fireResourceSkipped(joinPaths(getSource().getAbsolutePath(), folder, name),
                                joinPaths(getDestination().getAbsolutePath(), folder, name));
                }
            }
        }
    }

    /**
     * Get whether an asset in a particular folder exists
     * @param folder Name of the folder
     * @param name Name of asset 
     * @return <tt>true</tt> if it exists
     */
    protected boolean isExists(String folder, String name) {
        File f = new File(this.getDestination(), folder);
        if (f.exists()) {
            f = new File(f, name);
            return f.exists();
        }
        return false;
    }

    /**
     * Abstract method that should be implemented to read asset data given folder and name
     * @param folder name of folder
     * @param name name of asset
     * @return {@link InputStream} input stream of asset data
     * @throws IOException 
     */
    protected abstract InputStream read(String folder, String name) throws IOException;

    /**
     * Writes asset data to a destination file constructed from a folder and name
     * @param in asset data input stream
     * @param folder folder name 
     * @param name name of asset
     * @throws FileNotFoundException
     * @throws IOException 
     */
    protected void write(InputStream in, String folder, String name) throws FileNotFoundException, IOException {
        File f = new File(this.getDestination(), folder);
        if (!f.exists()) {
            f.mkdir();
        }
        File file = new File(f, name);
        //Not sure if this is necessary but of 'overwrite' flag is true then existing asset content must change complitely
        if (file.exists()) {
            file.delete();
        }
        try (java.io.FileOutputStream fout = new FileOutputStream(file)) {
            byte[] buff = new byte[256];
            int count;
            while ((count = in.read(buff)) != -1) {
                fout.write(buff, 0, count);
            }
            fout.flush();
        }
    }
}
