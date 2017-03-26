/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.colliders;

import goldteam.domain.Collidable;
import goldteam.domain.CollisionListener;
import goldteam.domain.CollisionPlane;
import goldteam.domain.CollisionRegister;
import goldteam.domain.GameEngine;
import java.util.ArrayList;

/**
 *
 * @author gordon
 */
public class CollisionDetector implements CollisionRegister {

    private final ArrayList<CollisionListener> collisionListeners;
    private final ArrayList<Collidable> collidableObjects;
    private final GameEngine gameEngine;

    public CollisionDetector(GameEngine gameData) {
        this.collidableObjects = new ArrayList<>();
        this.collisionListeners = new ArrayList<>();
        this.gameEngine = gameData;
        this.gameEngine.addCollisionTimer((l) -> this.CheckCollisions());
    }
    
    private synchronized void CheckCollisions() {

        // do logic to find colliding objects 
        // call collision listener event on all listeners
        for (Collidable a : collidableObjects) {
            for (Collidable b : collidableObjects) {
                if (!a.equals(b)) {
                    if (a.getPolygon().getBounds2D().intersects(b.getPolygon().getBounds2D())) {

                        // Use math to figure out if collision is from top or bottom
                        // This could go either way.
                        a.setCollider(b, CollisionPlane.TOP);
                        b.setCollider(a, CollisionPlane.BOTTOM);
                     
                       
                       
                        this.notifyColliders(a, b);
                    } 
                }
            }
        }
    }

    private synchronized void notifyColliders(Collidable a, Collidable b) {
        //System.out.println(String.format("%1$s collided with %2$s", a.getClass().getName(), b.getClass().getName()));
        collisionListeners.forEach((listener) -> {
            listener.CollisionDetected(a, b);
        });
    }

    @Override
    public void addCollisionListener(CollisionListener listener) {
        this.collisionListeners.add(listener);
    }

    @Override
    public void removeCollisionListener(CollisionListener listener) {
        this.collisionListeners.remove(listener);
    }

    @Override
    public void registerCollidable(Collidable collidable) {
        this.collidableObjects.add(collidable);
    }

    @Override
    public void removeCollidable(Collidable collidable) {
        this.collidableObjects.remove(collidable);
    }

}
