/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.Ghost;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.GameSounds;
import goldteam.domain.ModType;

/**
 *
 * @author Caleb Dunham
 */
public class GhostCollider implements CollisionListener{

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        Ghost g1 = (Ghost) a;
        Ghost g2 = (Ghost) b;
        
        if(g1.getShieldValue() > 0) {
            GameSounds.sounds[3].play();
            g1.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(g1.getHealthValue() > 1) {
            GameSounds.sounds[2].play();
            g1.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(g1.getHealthValue() == 1) {
            GameSounds.sounds[11].play();
            g1.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        }
        
        if(g2.getShieldValue() > 0) {
            GameSounds.sounds[3].play();
            g2.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(g2.getHealthValue() > 1) {
            GameSounds.sounds[2].play();
            g2.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(g2.getHealthValue() == 1) {
            GameSounds.sounds[11].play();
            g2.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        }
    }
    
}
