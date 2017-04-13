package goldteam.domain;

public interface Platform {

    public Double getDurabilityPercentage();

    public void setDurabilityPercentage(Double durabilityPercentage);

    public Double getDecayRate();

    public void setDecayRate(Double decayRate);

    public boolean isSolidTop();

    public boolean isSolidBottom();

    public void setSolidTop(Boolean isSolidTop);

    public void setSolidBottom(Boolean isSolidBottom);
    
    public int getWidth();
    
    public int getHeight();

}