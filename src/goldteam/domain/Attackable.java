package goldteam.domain;

import java.awt.event.ActionListener;

/**
 * This is the interface for any game object that can be attacked
 * @author gordon
 */
public interface Attackable {

    /**
     * Current Shield Value
     * Decreases damage taken
     * @return  int the current shield value
     */
    public int getShieldValue();

    /**
     * Current Health Value
     * 0 health = dead
     * @return  int the current health value
     */
    public int getHealthValue();

    /**
     * Modifies the Shield value by this amount
     * @param delta 
     */
    public void setShieldDelta(Delta delta);

    /**
     * Modifies the Health value by this amount
     * @param delta
     */
    public void setHealthDelta(Delta delta);
    
    /**
     * Anything that needs to be notified of health or shield deltas
     * @param listener
     */
    public void addAttackableListener(ActionListener listener);

<<<<<<< HEAD
    public int getArrowCount();
    
    public void setArrowDelta(Delta delta);

    public int getLifeValue();
    
    public void setLifeValue(Delta delta);

    public void notifyAttackableListeners();
}
=======
    /**
     * Notifies listeners of changes to health and/or shield events
     */
    public void notifyAttackableListeners();

    public double getChargeValue();
    
    public void setChargeDelta(Delta delta);
}
>>>>>>> dev
