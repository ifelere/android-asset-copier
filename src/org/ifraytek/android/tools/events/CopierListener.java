/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ifraytek.android.tools.events;

import java.util.EventListener;

/**
 *
 * @author IFELERE
 */
public interface CopierListener extends EventListener
{
    void onResourceCopied(CopierEventObject e);
    void onResourceSkipped(CopierEventObject e);
    void onErrorOccured(CopierEventObject e);
}
