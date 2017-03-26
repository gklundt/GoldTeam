package goldteam.providers;

import goldteam.domain.DoubleVector;
import goldteam.domain.GameObject;
import goldteam.domain.ProjectileBuilderBase;
import java.awt.Point;

public class ProjectileProvider {

    public GameObject build(ProjectileBuilderBase builder, Point point, DoubleVector speed) {
        builder.createGameObject(point, speed);
        return builder.getGameObject();
    }
}
