package goldteam.domain;

public interface Attackable {

    public Double getShieldValue();

    public Double getHealthValue();

    public void setShieldDelta(Delta delta);

    public void setHealthDelta(Delta delta);

}