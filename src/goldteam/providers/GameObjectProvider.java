package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.GameObjectBuilderBase;
import java.awt.Point;

public class GameObjectProvider {

    public GameObject build(GameObjectBuilderBase builder, Point point) {
        builder.createGameObject(point);
        return builder.getGameObject();
    }
}
