/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.providers;

import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameObject;
import java.awt.Point;

/**
 *
 * @author fchishti-sw
 */
public class CollectableProvider {
    public GameObject build(CollectableBuilderBase builder, Point point, Object watchable) {
        builder.createGameObject(point);
        return builder.getGameObject();
    }
}
