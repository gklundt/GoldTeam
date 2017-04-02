/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.event.ActionListener;

/**
 *
 * @author faaez
 */
public interface Boostable {
    
    public void setBoostableWeapon(boolean boostable);
    
    public boolean isBoostableWeapon();
    
    public void setBoostableHealth(boolean boostable);
    
    public boolean isBoostableHealth();
    
    public void setPermanentBoostableWeapon(boolean boostable);
    
    public boolean isPermanentBoostableWeapon();
    
        /**
     * Anything that needs to be notified of health boost or shield boost
     * @param listener
     */
    public void addBoostableListener(ActionListener listener);

    /**
     * Notifies listeners of changes to health boost and/or shield boost events
     */
    public void notifyBoostableListeners();
}
