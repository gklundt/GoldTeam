package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.HudBuilderBase;
import java.awt.Point;

public class HudProvider {

    public GameObject build(HudBuilderBase builder, Point point, Object watchable) {
        builder.createGameObject(point, watchable);
        return builder.getGameObject();
    }
}
