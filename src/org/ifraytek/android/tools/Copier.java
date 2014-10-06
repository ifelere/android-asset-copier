/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author IFELERE
 */
public abstract class Copier implements Runnable, java.io.Closeable {
    private final File source, destination;
    private boolean overwrite;
    private String[] folders;
    protected final EventListenerList listenerList = new EventListenerList();
    
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
        String[] names = this.getResourceNames(folder);
        if (names != null && names.length > 0) {
            for (String name : names) {
                if (this.skipResources != null && Arrays.binarySearch(this.skipResources, name) != -1) {
                    continue;
                }
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

    protected boolean isExists(String folder, String name) {
        File f = new File(this.getDestination(), folder);
        if (f.exists()) {
            f = new File(f, name);
            return f.exists();
        }
        return false;
    }

    protected abstract InputStream read(String folder, String name) throws IOException;

    protected void write(InputStream in, String folder, String name) throws FileNotFoundException, IOException {
        File f = new File(this.getDestination(), folder);
        if (!f.exists()) {
            f.mkdir();
        }
        File file = new File(f, name);
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
