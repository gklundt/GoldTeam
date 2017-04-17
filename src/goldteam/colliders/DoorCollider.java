/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.characters.ArcherMan;
import goldteam.characters.StationaryGhost;
import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.Delta;
import goldteam.domain.Enemy;
import goldteam.domain.ModType;
import goldteam.domain.Movable;
import goldteam.domain.Platform;
import goldteam.platforms.DoorsPlatform;
import javax.xml.stream.events.Characters;

/**
 *
 * @author Mishal
 */

public class DoorCollider implements CollisionListener{
    private Collidable door;
    private Collidable c;
    
    public DoorCollider() {
    }

    private void DoCollision()
    {
        ((ArcherMan)(c)).win();
    }
    

    @Override
    public void CollisionDetected(Collidable a, Collidable b) {
        if ((a instanceof ArcherMan) && (b instanceof DoorsPlatform)) {
            this.door = b;
            this.c = a;
            DoCollision();

        } else if ((b instanceof ArcherMan) && (a instanceof DoorsPlatform)) {
            this.c = b;
            this.door = a;
            DoCollision();
        }
    }
}
