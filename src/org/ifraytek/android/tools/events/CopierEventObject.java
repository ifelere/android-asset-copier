/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ifraytek.android.tools.events;

import java.util.EventObject;

/**
 *
 * @author IFELERE
 */
public class CopierEventObject extends EventObject {

    public CopierEventObject(Object source) {
        super(source);
    }

    public CopierEventObject(String sourceFile, String destination, Object source) {
        super(source);
        this.sourceFile = sourceFile;
        this.destination = destination;
    }

    public CopierEventObject(Exception error, Object source) {
        super(source);
        this.error = error;
    }
    
    
    private String tag;
    private String sourceFile, destination;
    private Exception error;

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }
    

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSourceResource() {
        return sourceFile;
    }

    public void setSourceResource(String source) {
        this.sourceFile = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

   
    
}
