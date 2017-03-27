/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

/**
 *
 * @author faaez
 */
public interface CollectableItem {
    
    /**
     * Gets the state of the collectable Item
     * true is collectable?
     * false is non-collectable?
     * @return  boolean state of the collectable item
     */
    public boolean getState();
    
    /**
     * Sets the state of the collectable item
     * @param state
     */
    public void setState(boolean state);
    
    /**
     * Seems to create a new polygon ... not sure of the intended usage
     */
    public void undoCollider();
}
