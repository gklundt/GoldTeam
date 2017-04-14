/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.characters.Launcher;
import goldteam.domain.Attackable;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.Delta;
import goldteam.domain.Enemy;
import goldteam.domain.GameSounds;
import goldteam.domain.ModType;

/**
 *
 * @author faaez
 */
public class ArcherCollider implements CollisionListener {

    private Collidable archer;
    private Collidable enemy;
    private final GameSounds heartHit = GameSounds.sounds[5];
    private final GameSounds shieldHit = GameSounds.sounds[3];
    private final GameSounds deathHit = GameSounds.sounds[11];

    public void DoCollision() {
        Attackable am = (Attackable) archer;

        if (am.getShieldValue() > 0) {
            shieldHit.play();
            am.setShieldDelta(Delta.create(-1.0, ModType.FIXED));
        } else {
            if (am.getHealthValue() > 1) {
                heartHit.play();
                am.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            } else {
                deathHit.play();
                am.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            }
        }
        
        if(enemy instanceof Launcher){
            Attackable l = (Attackable) enemy;
            if(l.getHealthValue() > 0){
                l.setHealthDelta(Delta.create(-1.0, ModType.FIXED));
            }
            ((ArcherMan)(am)).debuff();
        }     
    }

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof ArcherMan) && (b instanceof Enemy)) {
            this.archer = a;
            this.enemy = b;
            DoCollision();

        } else if ((b instanceof ArcherMan) && (a instanceof Enemy)) {
            this.archer = b;
            this.enemy = a;
            DoCollision();
        }
    }
}
