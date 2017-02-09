/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.panels;

import javax.swing.JPanel;

/**
 *
 * @author gordon
 */
public abstract class ManagedPanel extends JPanel {
    
    protected final PanelManager panelManager;
    
    public ManagedPanel(PanelManager panelManager){
        this.panelManager= panelManager;
    }
    
}
