/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import javax.swing.JPanel;

/**
 *
 * @author gordon
 */
public abstract class ManagedPanelBase extends JPanel {
    
    protected final PanelManager panelManager;
    
    public ManagedPanelBase(PanelManager panelManager){
        this.panelManager= panelManager;
    }
}
