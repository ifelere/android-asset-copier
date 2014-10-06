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

import org.ifraytek.android.tools.app.ConsoleApp;
import org.ifraytek.android.tools.app.UiFrame;

/**
 * <p>
 * Main entry point. The main window itself can be a main entry point.
 * The intension is to be able to use this application exclusively on the command-line hence it be used in a batch script.
 * Imagine a script one writes to call this program automatically when an asset pack is copied to a particular folder.
 * To turn on console mode pass '-console' command-line argument.
 * </p>
 * <p>
 * To run the as a console application type java -jar &ltjarfile&gt; &lt;source file&gt; &lt;destination directory&gt; -console [other flags].
 * </p>
 * @author IFELERE
 */
public class AssetCopier {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean console = org.apache.commons.lang3.ArrayUtils.contains(args, "-console");
        if (console) {
            ConsoleApp.main(args);
        }else {
            UiFrame.main(args);
        }
        
    }
    
}
