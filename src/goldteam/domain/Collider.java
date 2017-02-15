/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author faaez
 */
public interface Collider {
    
    public Rectangle getCollider();
    
    public void setCollider(Point position);
}
