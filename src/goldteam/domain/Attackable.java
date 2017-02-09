package goldteam.domain;

import java.awt.event.ActionListener;

public interface Attackable {

    public Double getShieldValue();

    public Double getHealthValue();

    public void setShieldDelta(Delta delta);

    public void setHealthDelta(Delta delta);
    
    public void addAttackableListener(ActionListener listener);

}
