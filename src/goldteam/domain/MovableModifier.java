package goldteam.domain;

public interface MovableModifier extends Modifier {

    public void setVelocityDelta(Delta delta);

    public Delta getVelocityDelta();

}