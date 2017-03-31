package goldteam.domain;

import java.awt.event.ActionListener;

public interface Weapon {

    public void addWeaponListener(ActionListener listener);
    
    public void notifyWeaponListener();
    
    public double getChargeValue();
    
    public void setChargeValue(double chargeValue);
    
    public void setChargeDelta(Delta chargeDelta);

}
