/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ifraytek.android.tools;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author IFELERE
 */
public class Copiers {
    public static boolean isZippedFile(File file) {
        return java.util.regex.Pattern.compile("\\.zip$",
                java.util.regex.Pattern.CASE_INSENSITIVE)
                .matcher(file.getName()).find();
    }
    public static Copier createDrawable(File path, File destination) throws IOException {
//        Properties p = new Properties();
//        p.load(Copier.class.getResourceAsStream("resources/" + Copier.class.getName() + ".properties"));
        String[] folders = "drawable-hdpi;drawable-ldpi;drawable-mdpi;drawable-xhdpi;drawable-xxhdpi".split(";");
        
        if (isZippedFile(path)) {
            return new ZippedCopier(path, destination, false, folders);
        }
        return new DefaultCopier(path, destination, false, folders);
    }
}
