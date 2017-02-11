/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.hud;

import goldteam.domain.Attackable;
import goldteam.domain.GameEngine;

/**
 *
 * @author Caleb Dunham
 */
public class ShieldHudItem extends BasicHudItem {
    
    public int count;
    private Attackable watchedItem;
    
    public ShieldHudItem(GameEngine gamedata) {
        super(gamedata);
        count = 5;
    }
    
    @Override
    public void Update() {
        //graphic render here?
    }
    
    public void Update(int change) {
        if(count>0)
            this.count += change;
        System.out.println("Shield Count: " + count);
    }
    
    @Override
    public Attackable getWatcher() {
        return this.watchedItem;
    }

    @Override
    public void setWatcher(Attackable target) {
       this.watchedItem=target;
    }

    @Override
    protected void GraphicsUpdateHandler() {
        Update();
    }

    @Override
    protected void ClickHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void KeyHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void UpdateEffectHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void MapUpdateTimerHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
