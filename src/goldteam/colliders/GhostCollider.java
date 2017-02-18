/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.GameSounds;

/**
 *
 * @author Caleb Dunham
 */
public class GhostCollider implements CollisionListener{

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        GameSounds.sounds[2].play();
    }
    
}
