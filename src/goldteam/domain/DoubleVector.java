package goldteam.domain;

public class DoubleVector {

    public Double x;
    public Double y;

    public DoubleVector() {
        this(0.0, 0.0);
    }
    
    public DoubleVector(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
