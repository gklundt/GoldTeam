/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;


public abstract class MapBuilderBase extends GameObjectBuilderBase {

    public MapBuilderBase(GameEngine gameData, Point point) {
        super(gameData, point);
    }

    @Override
    protected void buildTemplate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
