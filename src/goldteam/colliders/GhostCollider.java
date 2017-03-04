/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.animators.BigGhostAnimation;
import goldteam.characters.Arrow;
import goldteam.characters.Ghost;
import goldteam.domain.AnimationState;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.GameSounds;
import goldteam.domain.ModType;
import goldteam.domain.Movable;
import goldteam.domain.PanelManager;
import goldteam.domain.Platform;

/**
 *
 * @author Caleb Dunham
 */
public class GhostCollider implements CollisionListener{
    
    private Ghost ghost;
    private Ghost ghost1;
    private Arrow arrow;
    private Arrow arrow1;
    private boolean boolswitch = true;
    public GhostCollider() {} 
    
    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        
            if ((a instanceof Ghost) && (b instanceof Arrow)) {
                this.ghost = (Ghost) a;
                this.arrow = (Arrow) b;
                DoCollisionAonG();
            } else if ((b instanceof Ghost) && (a instanceof Arrow)) {
                this.ghost = (Ghost) b;
                this.arrow = (Arrow) a;
                DoCollisionAonG();
            }
            
            if ((a instanceof Ghost) && (b instanceof Ghost)) {
                this.ghost = (Ghost) a;
                this.ghost1 = (Ghost) b;
                DoCollisionGonG();
            }
            
            if ((a instanceof Arrow) && (b instanceof Arrow)) {
                this.arrow = (Arrow) a;
                this.arrow1 = (Arrow) b;
                DoCollisionAonA();
            }
    }
    
    private void DoCollisionAonG() {
        
        if(boolswitch) {
            ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            ghost.setRemoveAnimator(ghost.getAnimator());
            ghost.setNewAnimator(AnimationState.HURT);
            ghost.notifyAnimationChangeListeners();
            boolswitch = false;
        } else {
            ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            ghost.setRemoveAnimator(ghost.getAnimator());
            ghost.setNewAnimator(AnimationState.DEFAULT);
            ghost.notifyAnimationChangeListeners();
            boolswitch = true;
        }
        if(ghost.getShieldValue() > 0 && !boolswitch) {
            GameSounds.sounds[3].play();
            ghost.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(ghost.getHealthValue() > 1 && !boolswitch) {
            GameSounds.sounds[2].play();
            ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(ghost.getHealthValue() == 1) {
            GameSounds.sounds[11].play();
            ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
        }

        if(arrow.getHealthValue() > 0) {
            GameSounds.sounds[2].play();
            arrow.setNewAnimator("");
        }
    }
    
    private void DoCollisionGonG() {
        
        if(ghost.getShieldValue() > 0) {
            GameSounds.sounds[3].play();
            ghost.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
        } else if(ghost.getHealthValue() > 1) {
            GameSounds.sounds[5].play();
            ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            ghost1.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            ghost.setVelocityVectorDelta(Delta.create(-2.0, ModType.FIXED), Delta.create(2.0, ModType.FIXED));
            ghost1.setVelocityVectorDelta(Delta.create(2.0, ModType.FIXED), Delta.create(-2.0, ModType.FIXED));
        } else if(ghost.getHealthValue() == 1) {
            GameSounds.sounds[6].play();
            ghost.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            ghost1.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            ghost.setVelocityVectorDelta(Delta.create(0.75, ModType.PERCENTAGE), Delta.create(-0.75, ModType.PERCENTAGE));
            ghost1.setVelocityVectorDelta(Delta.create(-0.75, ModType.PERCENTAGE), Delta.create(0.75, ModType.PERCENTAGE));
        } else {
            ghost.removeCollider(ghost);
            ghost1.removeCollider(ghost1);
        }
    }
    
    private void DoCollisionAonA() {
        
        GameSounds.sounds[4].play();
        arrow.setVelocityVectorDelta(Delta.create(-1.0, ModType.FIXED), Delta.create(-1.0, ModType.FIXED));
    }
}
