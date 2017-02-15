/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.util.ArrayList;

/**
 *
 * @author faaez
 */
public class CollisionDetector {
    
    public ArrayList<GameObject> objects;
    
    public ArrayList<GameObject> checkCollision(){
        ArrayList<GameObject> intersectedObjects = new ArrayList<>();
        for(GameObject parentObj : objects){
            int counter = 1;
            for(GameObject childObj : objects){
                /*if(parentObj.getCollider().getCollider().intersects(childObj.getCollider().getCollider()) 
                        && counter > 1){
                    System.out.println("Collision Detected between : " + parentObj + " " + childObj);
                    intersectedObjects.add(parentObj);
                    intersectedObjects.add(childObj);
                    counter++;
                    System.out.println("Collision detected");
                }*/
                if(parentObj.getCollider().getCollider().y == childObj.getCollider().getCollider().y
                    ){
                    System.out.println("Dtetected===");
                    //counter++;
                }
                //System.out.println(parentObj.getCollider().getCollider() + " " + childObj.getCollider().getCollider());
            }
        }
        //System.out.println(objects);
        return intersectedObjects;
    }
    
    public void setObjects(ArrayList<GameObject> objects){
        this.objects = objects;
    }
}
