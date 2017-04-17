/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.PitPlatformAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.PlatformBuilderBase;
import goldteam.platforms.DeathPlatform;
import java.awt.Dimension;

/**
 *
 * @author Joshua
 */
public class DeathPlatformBuilder extends PlatformBuilderBase{
    
        private DeathPlatform pit;
        private int width;
        private int height;

    public DeathPlatformBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject(int width, int height) {
        this.height = height;
        this.width = width;
        this.gameObject = new DeathPlatform (gameData, point, width, height);
        this.pit = (DeathPlatform) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        PitPlatformAnimation ppa = new PitPlatformAnimation(this.pit, gameData.getMapDimensions(), "assets/lavaTile.png");
        this.pit.addAnimator(AnimationState.DEFAULT, ppa);
        ppa.setDimensions(new Dimension(width, height));
    } 
}
