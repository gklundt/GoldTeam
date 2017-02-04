package goldteam.domain;

public interface MovementModifier extends Modifier {

    public void setVelocityDelta(Delta delta);

    public Delta getVelocityDelta();

}