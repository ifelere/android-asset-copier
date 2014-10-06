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
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import org.ifraytek.android.tools.Copiers;

/**
 * A Dialog to show inputs to accept source file/folder and a destination folder (the destination on the IDE file tree)
 * @author IFELERE
 * @version 0.1.0
 */
public class CopyJobDialog extends javax.swing.JDialog {

    /**
     * Creates new form CopyJobDialog
     * @param parent
     * @param modal
     */
    public CopyJobDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSource = new javax.swing.JTextField();
        txtDestination = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnBrowseSource = new javax.swing.JButton();
        btnBrowseDestination = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Source");

        jLabel2.setText("Destination");

        btnCancel.setText("Cancel");
        btnCancel.setDefaultCapable(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnBrowseSource.setText("...");
        btnBrowseSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseSourceActionPerformed(evt);
            }
        });

        btnBrowseDestination.setText("...");
        btnBrowseDestination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseDestinationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSource, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .addComponent(txtDestination)))
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBrowseSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBrowseDestination, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseSource))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseDestination))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void setCurrentSourceDir(File currentSourceDir) {
        CopyJobDialog.currentSourceDir = currentSourceDir;
    }

    public static void setCurrentDestinationDir(File currentDestinationDir) {
        CopyJobDialog.currentDestinationDir = currentDestinationDir;
    }

    public static File getCurrentSourceDir() {
        return currentSourceDir;
    }

    public static File getCurrentDestinationDir() {
        return currentDestinationDir;
    }
    
    
    
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        doClose(true);
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        doClose(false);
    }//GEN-LAST:event_btnCancelActionPerformed
    private static File currentSourceDir;
    private void btnBrowseSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseSourceActionPerformed
       File f = selectFile(txtSource, folderSourceOnly ? "Select Folder Having Packages" : "Select Resource Package",
               folderSourceOnly, currentSourceDir);
       if (f != null) {
           currentSourceDir = folderSourceOnly ? f : f.getParentFile();
           source = f;
       }
    }//GEN-LAST:event_btnBrowseSourceActionPerformed
    private static File currentDestinationDir;
    private void btnBrowseDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseDestinationActionPerformed
       File f = selectFile(txtDestination, "Select Destination Folder", true, currentDestinationDir);
       if (f != null) {
           currentDestinationDir = f;
           destination = f;
       }
    }//GEN-LAST:event_btnBrowseDestinationActionPerformed

    private File source, destination;
   
    private File selectFile(JTextField field, String title, boolean folderOnly, File currentFolder) {
        JFileChooser fc = new JFileChooser();
        if (currentFolder != null) {
            fc.setCurrentDirectory(currentFolder);
        }
        if (folderOnly) {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        } else {
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fc.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    return Copiers.isZippedFile(f);
                }

                @Override
                public String getDescription() {
                    return "Android Resource Package (Folder/Zipped File)";
                }
            });
        }

        fc.setDialogTitle(title);
        fc.setMultiSelectionEnabled(false);
        if (fc.showOpenDialog(getRootPane()) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            field.setText(f.getAbsolutePath());
            return f;
        }
        return null;
    }

    private boolean folderSourceOnly;

    public boolean isFolderSourceOnly() {
        return folderSourceOnly;
    }

    public void setFolderSourceOnly(boolean folderSourceOnly) {
        this.folderSourceOnly = folderSourceOnly;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseDestination;
    private javax.swing.JButton btnBrowseSource;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtDestination;
    private javax.swing.JTextField txtSource;
    // End of variables declaration//GEN-END:variables

    private void doClose(boolean b) {
        if (b) {
            if (this.source != null && this.destination != null) {
                dialogResult = DIALOG_OK;
                setVisible(false);
                dispose();
            }
        }else {
            dialogResult = DIALOG_CANCELLED;
            setVisible(false);
            dispose();
        }
    }
    
    public static final int DIALOG_OK = 10, DIALOG_CANCELLED = -10;
    private int dialogResult = DIALOG_CANCELLED;

    public File getSource() {
        return source;
    }

    public File getDestination() {
        return destination;
    }

    public int getDialogResult() {
        return dialogResult;
    }
    
}
