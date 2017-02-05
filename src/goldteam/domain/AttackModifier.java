package goldteam.domain;

public interface AttackModifier extends Modifier {

    public Delta getShieldDelta();

    public Delta getHealthDelta();

    public void setShieldDelta(Delta delta);

    public void setHealthDelta(Delta delta);

}