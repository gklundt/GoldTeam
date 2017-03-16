/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.AttackableWatcher;
import goldteam.domain.GameObject;
import goldteam.domain.TestHudAnimationBase;
import java.awt.Dimension;

/**
 *
 * @author faaez
 */
public class TestHudAnimation extends TestHudAnimationBase{
    
    private AttackableWatcher gameObj;

    public TestHudAnimation(GameObject gameObject, Dimension preferredSize) {
        super(gameObject, preferredSize);
        this.gameObj = (AttackableWatcher) gameObject;
    }

    @Override
    protected void update() {
        this.count = this.gameObj.getWatcher().getHealthValue();
    }
    
}