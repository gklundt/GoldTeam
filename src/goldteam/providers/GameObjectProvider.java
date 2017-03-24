package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.GameObjectBuilderBase;

public class GameObjectProvider {

    public GameObject build(GameObjectBuilderBase builder) {
        builder.createGameObject();
        return builder.getGameObject();
    }
}
