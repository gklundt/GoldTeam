package goldteam.characters;

import goldteam.builders.LauncherEnemyBuilder;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObjectBuilderBase;
import goldteam.domain.GamePanelBase;
import goldteam.gamedata.GameData;
import goldteam.providers.GameObjectProvider;
import java.awt.Point;

public class Flyer extends BaseEnemy
{
    private GamePanelBase panel;
    private int timeSinceAttacked;
    private final int maxSpeed;

    public Flyer(GameEngine gamedata, Point point, GamePanelBase panel)
    {
        super(gamedata, point);
        this.panel = panel;
        maxSpeed = 6;
    }

    @Override
    protected void Update() {
        timeSinceAttacked++;
        int xdif = gamedata.getMovableCharacter().PositionVector().x - positionVector.x;
        if (xdif > 0) {
            moveRight(xdif);
        } else {
            moveLeft(xdif);
        }

        if (timeSinceAttacked > 20) {
            attack();
        }
    }

    private void moveLeft(int xdif) {
        xdif /= 10;
        if (xdif < -maxSpeed) {
            xdif = -maxSpeed;
        }
        positionVector.x += xdif;

    }

    private void moveRight(int xdif) {
        xdif /= 10;
        if (xdif > maxSpeed) {
            xdif = maxSpeed;
        }
        positionVector.x += xdif;

    }

    private void attack()
    {
        this.panel.createLauncher(positionVector);
        timeSinceAttacked = 0;
    }

}
