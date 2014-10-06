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
package org.ifraytek.android.tools.events;

import java.util.EventObject;

/**
 * An EventObject super-class that encapsulates basic information like
 * the source file being treated, the resolved destination file and {@link Exception} object if it occurs
 * @author IFELERE
 */
public class CopierEventObject extends EventObject {

    public CopierEventObject(Object source) {
        super(source);
    }

    /**
     * Construct new instance given the source file, destination and event source object
     * @param sourceFile The source file name of the asset (the path of the asset in the zipped package)
     * @param destination The resolved destination file
     * @param source The event source
     * 
     * This could result from a copy or skip event
     */
    public CopierEventObject(String sourceFile, String destination, Object source) {
        super(source);
        this.sourceFile = sourceFile;
        this.destination = destination;
    }

    /**
     * Construct a new instance to report an error
     * @param error The error object
     * @param source The event source
     */
    public CopierEventObject(Exception error, Object source) {
        super(source);
        this.error = error;
    }
    
    
    private String tag;
    private String sourceFile, destination;
    private Exception error;

    /**
     * Gets the error object if it exists
     * @return 
     */
    public Exception getError() {
        return error;
    }

    /**
     * Sets the error object when thrown
     * @param error 
     */
    public void setError(Exception error) {
        this.error = error;
    }
    

    /**
     * Get a special tag for the event (not used at the moment)
     * @return 
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set a special tag for the event (not used at the moment)
     * @param tag 
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets the source path of the asset
     * @return 
     */
    public String getSourceResource() {
        return sourceFile;
    }

    /**
     * Sets the source path of the asset
     * @param source the path (typically location of asset in zipped package)
     */
    public void setSourceResource(String source) {
        this.sourceFile = source;
    }

    /**
     * gets the destination path
     * @return 
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination path
     * @param destination 
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

   
    
}
