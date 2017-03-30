package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.PlatformBuilderBase;
import java.awt.Point;

public class PlatformProvider {

    public GameObject build(PlatformBuilderBase builder, Point point, int height, int width) {
        builder.createGameObject(point, height, width);
        return builder.getGameObject();
    }
}
