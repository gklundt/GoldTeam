package goldteam.domain;

import java.awt.event.ActionListener;

public interface Weapon {

    public Double getForce();

    public void setForceDelta(Delta delta);

    public DoubleVector getStrikeVector();

    public void setStrikeVector(DoubleVector strikeVector);

    public void setStrikeScalarDelta(Delta delta);
    
    public void addWeaponListener(ActionListener listener);

}
