package goldteam.domain;

public interface DepletableModifier extends Modifier {

    public Delta getCountDelta();

    public void setCountDelta(Delta delta);

}
