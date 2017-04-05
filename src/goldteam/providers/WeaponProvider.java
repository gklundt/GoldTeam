package goldteam.providers;

import goldteam.domain.GameObject;
import goldteam.domain.Weapon;
import goldteam.domain.WeaponBuilderBase;
import java.awt.Point;

public class WeaponProvider {

    public GameObject build(WeaponBuilderBase builder, Point point, Weapon weapon) {
        builder.createGameObject(point, weapon);
        return builder.getGameObject();
    }
}
