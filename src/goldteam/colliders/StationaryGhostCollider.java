/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;


import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;

/**
 *
 * @author faaez
 */
public class StationaryGhostCollider implements CollisionListener{

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        //System.out.println("Handle the collision if you can!");
    }

}
