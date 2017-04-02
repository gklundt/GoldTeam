/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.HealthBoostStatusBarAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.Boostable;
import goldteam.domain.GameEngine;
import goldteam.domain.HudBuilderBase;
import goldteam.hud.BoostStatusBar;

/**
 *
 * @author faaez
 */
public class HealthBoostStatusBarBuilder extends HudBuilderBase{
    private BoostStatusBar bar;

    public HealthBoostStatusBarBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject() {
        this.gameObject = new BoostStatusBar(gameData,point);
        this.bar = (BoostStatusBar) gameObject;
    }

    @Override
    protected void addAnimations() {
        HealthBoostStatusBarAnimation csba = new HealthBoostStatusBarAnimation(this.bar, gameData.getMapDimensions(),"assets/Heart.png");
        this.bar.addAnimator(AnimationState.DEFAULT, csba);
    }

    @Override
    protected void setWatcher() {
        this.bar.setWatcher((Boostable) this.watchable);
    }
}
