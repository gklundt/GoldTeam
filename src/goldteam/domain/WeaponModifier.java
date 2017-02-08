package goldteam.domain;

public interface WeaponModifier extends Modifier {

    public Delta getForceDelta();

    public void setForceDelta( Delta delta);

    public Delta getStrikeScalarDelta();

    public void setStrikeScalarDelta( Delta delta);

}