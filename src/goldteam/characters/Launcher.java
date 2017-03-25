package goldteam.characters;

import goldteam.domain.GameEngine;
import java.awt.Point;

public class Launcher extends BaseEnemy {

    private final boolean goesLeft;
    private final int maxSpeed;

    public Launcher(GameEngine gamedata, Point initialPoint, boolean l) {
        super(gamedata, initialPoint);
        maxSpeed = 6;
        goesLeft = l;
    }

    @Override
    protected void Update() {
        if (goesLeft) {
            positionVector.x -= maxSpeed;
        } else {
            positionVector.x += maxSpeed;
        }
    }
}
