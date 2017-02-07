package goldteam.domain;

public interface Movable {

    public DoubleVector getVelocityVector();

    public void setVelocityScalarDelta(Delta delta);

    public void setVelocityVectorDelta(Delta xDelta, Delta yDelta);

    public Integer getVelocity();

}
