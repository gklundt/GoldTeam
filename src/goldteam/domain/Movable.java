package goldteam.domain;

public interface Movable {

    public DoubleVector getVelocityVector();

    public void setVelocityScalarDelta(Delta delta);

    public Integer getVelocity();

}