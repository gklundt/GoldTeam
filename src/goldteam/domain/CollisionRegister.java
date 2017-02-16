/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.event.ActionListener;

/**
 *
 * @author faaez
 */
public interface CollisionRegister {
    
    
    public void addCollisionListener(ActionListener listener);
    
    public void registerCollidable(Collidable collidable);
    
    public void removeCollidable(Collidable collidable);
    
}
