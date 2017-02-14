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
    public ArrayList<GameObject> checkCollision(ArrayList<GameObject> objects){
        for(GameObject parentObj : objects){
            for(GameObject childObj : objects){
                //Do something
            }
        }
        return null;
    }
}
