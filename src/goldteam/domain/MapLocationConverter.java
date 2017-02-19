/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;

/**
 *
 * @author gordon
 */
public interface MapLocationConverter {

    void getMapLocation(Point visableLocation, Point refLocation);

    void getVisibleLocation(Point mapLocation, Point refLocation);
    
}
