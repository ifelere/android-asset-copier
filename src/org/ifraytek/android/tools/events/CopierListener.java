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

import java.util.EventListener;

/**
 * A CopierListener interface to dispatch notification of copy events
 * @author IFELERE
 * @version 0.1.0
 */
public interface CopierListener extends EventListener
{
    /**
     * Fired when a asset has been successfully copied to destination
     * @param e 
     */
    void onResourceCopied(CopierEventObject e);
    
    /**
     * Fire when an asset is skipped (i.e exists on the destination)
     * @param e 
     */
    void onResourceSkipped(CopierEventObject e);
    
    /**
     * Fired when an error has occurred
     * @param e 
     */
    void onErrorOccured(CopierEventObject e);
}
