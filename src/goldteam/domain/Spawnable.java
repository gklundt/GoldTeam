package goldteam.domain;

import java.awt.Point;

public interface Spawnable {

    public Point getStartVector();

    public Point setStartVector();

    public Integer getRespawnTime();

    public void setRespawnTime(Integer seconds);

    public void setSafeTime(Integer seconds);

    public Integer getSafeTime();

}