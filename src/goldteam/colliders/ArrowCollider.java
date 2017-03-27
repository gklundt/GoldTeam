/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.Arrow;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;

/**
 *
 * @author faaez
 */
public class ArrowCollider implements CollisionListener{

    private Collidable arrow;
    private Collidable enemy;
            
    private void DoCollision(){
        Arrow a = (Arrow) arrow;
        Collidable g = (Collidable) enemy;
        
        a.setCollided(true);
        g.setCollided(true);
    }
    
    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if(a instanceof Arrow){
            arrow = a;
            enemy = b;
            DoCollision();
        }
        
        if(b instanceof Arrow){
            arrow = b;
            enemy = a;
            DoCollision();
        }
    }
    
}
