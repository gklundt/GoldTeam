package goldteam.domain;

public interface Weapon {

    public Double getForce();

    public void setForceDelta(Delta delta);

    public DoubleVector getStrikeVector();

    public void setStrikeVector(DoubleVector strikeVector);

    public void setStrikeScalarDelta(Delta delta);

}
