/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.WeaponBoostStatusBarAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Attackable;
import goldteam.domain.Boostable;
import goldteam.domain.CollectableBuilderBase;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.BoostStatusBar;

/**
 *
 * @author faaez
 */
public class WeaponBoostStatusBarBuilder extends HudBuilderBase{
    
    private BoostStatusBar bar;

    public WeaponBoostStatusBarBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new BoostStatusBar(gameData,point);
        this.bar = (BoostStatusBar) gameObject;
    }

    @Override
    protected void addAnimations() {
        WeaponBoostStatusBarAnimation csba = new WeaponBoostStatusBarAnimation(this.bar, gameData.getMapDimensions(),"assets/Arrow_HUD_Item.png");
        this.bar.addAnimator(AnimationState.DEFAULT, csba);
    }

    @Override
    protected void setWatcher() {
        this.bar.setWatcher((Boostable) this.watchable);
    }
}
