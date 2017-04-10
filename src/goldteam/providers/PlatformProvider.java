package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.PlatformBuilderBase;
import java.awt.Point;

public class PlatformProvider {

    public GameObject build(PlatformBuilderBase builder, Point point, int width, int height) {
        builder.createGameObject(point, width, height);
        return builder.getGameObject();
    }
}
