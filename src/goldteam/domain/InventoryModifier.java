package goldteam.domain;

public interface InventoryModifier extends Modifier {

    public Delta getCountDelta();

    public void setCountDelta(Delta delta);

}
