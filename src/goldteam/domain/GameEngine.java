package goldteam.domain;

public interface GameEngine {

    public void processInput();

    public void update();

    public void render();

    public boolean isActive();

}