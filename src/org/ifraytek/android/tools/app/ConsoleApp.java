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

package org.ifraytek.android.tools.app;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ifraytek.android.tools.Copier;
import org.ifraytek.android.tools.Copiers;
import org.ifraytek.android.tools.events.CopierEventObject;
import org.ifraytek.android.tools.events.CopierListener;

/**
 *
 * @author IFELERE
 */
public class ConsoleApp implements CopierListener, Runnable {
    private File source, destination;
    
    public static void main(String[] args) {
        boolean silent = false;
        final String sample = "Must be <app> <source> <destination> -console [other flags]";
        if (args.length < 3) {
            Logger.getLogger(ConsoleApp.class.getName())
                    .log(Level.SEVERE, "Invalid arguments. " + sample);
            return;
        }
        if (args[0].startsWith("-") || args[1].startsWith("-")) {
             Logger.getLogger(ConsoleApp.class.getName())
                    .log(Level.SEVERE, "Invalid arguments. " + sample);
             return;
        }
        ConsoleApp c = new ConsoleApp();
        
        c.source = new File(args[0]);
        c.destination = new File(args[1]);
        if (org.apache.commons.lang3.ArrayUtils.contains(args, "-silent")
                || org.apache.commons.lang3.ArrayUtils.contains(args, "-q")
                || org.apache.commons.lang3.ArrayUtils.contains(args, "-Q")) {
            silent = true;
             Logger.getLogger(ConsoleApp.class.getName()).setLevel(Level.OFF);
        }
        c.run();
        if (!silent) {
            System.out.println("Type enter key to exit");
            Scanner s = new Scanner(System.in);
            s.nextLine();
        }
        
    }

    @Override
    public void onResourceCopied(CopierEventObject e) {
        Logger.getLogger(ConsoleApp.class.getName())
                .log(Level.FINE, String.format("'%s' copied to '%s'", e.getSourceResource(), e.getDestination()));
    }

    @Override
    public void onResourceSkipped(CopierEventObject e) {
        Logger.getLogger(ConsoleApp.class.getName())
                .log(Level.FINE, String.format("'%s' skipped", e.getSourceResource()));
    }

    @Override
    public void onErrorOccured(CopierEventObject e) {
//        Logger.getLogger(ConsoleApp.class.getName())
//                .log(Level.WARNING, e.getError().getMessage());
    }

    @Override
    public void run() {
        if (!this.source.exists()) {
            Logger.getLogger(ConsoleApp.class.getName()).log(Level.SEVERE, String.format("source '%s' not found%n", this.source.getAbsolutePath()));
            return;
        }
        
        if (!this.destination.exists()) {
            Logger.getLogger(ConsoleApp.class.getName()).log(Level.SEVERE, String.format("destination '%s' not found%n", this.destination.getAbsolutePath()));
            return;
        }
        
        try {
            Copier copier = Copiers.createDrawable(source, destination);
            copier.addCopierListener(this);
            copier.run();
        } catch (IOException ex) {
            Logger.getLogger(ConsoleApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
