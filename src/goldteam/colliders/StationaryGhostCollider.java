/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.domain.Collider;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author faaez
 */
public class StationaryGhostCollider implements Collider{

    private Rectangle collider;
    
    public StationaryGhostCollider(Point base, Dimension dimensions){
        this.collider = new Rectangle(base, dimensions);
    }

    @Override
    public Rectangle getCollider() {
        return this.collider;
    }

    @Override
    public void setCollider(Point base) {
        this.collider.x = base.x;
        this.collider.y = base.y;
    }
}
