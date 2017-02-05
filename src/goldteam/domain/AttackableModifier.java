package goldteam.domain;

public interface AttackableModifier extends Modifier {

    public Delta getShieldDelta();

    public Delta getHealthDelta();

    public void setShieldDelta(Delta delta);

    public void setHealthDelta(Delta delta);

}