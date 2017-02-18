package goldteam.domain;

public class Delta {

    public static Delta create(Double delta, ModType modType) {
        return new Delta(delta, modType);
    }

    private Delta(Double delta, ModType modType) {
        this.delta = delta;
        this.modType = modType;
    }

    public Double delta;

    public ModType modType;

}
