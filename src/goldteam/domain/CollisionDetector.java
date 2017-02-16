/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Polygon;
import java.util.ArrayList;

/**
 *
 * @author faaez
 */
public class CollisionDetector {
    
    private ArrayList<GameObject> objects;
    private GameObject archer;
    
    public void checkCollision(){
        
        try{
            for(GameObject parentObj : objects){
                if(((Collidable) parentObj).getPolygon().intersects(((Collidable) archer).getPolygon().getBounds())){
                    System.out.println("Collision detetcted at : " + ((Collidable) parentObj).getPolygon().getBounds() + " " + ((Collidable) archer).getPolygon().getBounds());

                }
            }
        } catch (Exception e){
            
        }
    }
    
    public void setObjects(ArrayList<GameObject> objects){
        this.objects = objects;
    }
    
    public void setArcher(GameObject archer){
        this.archer = archer;
    }
}
