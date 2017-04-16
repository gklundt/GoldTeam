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
import goldteam.platforms.PitPlatform;
import java.awt.Dimension;

/**
 *
 * @author Mishal
 */
public class PitPlatformBuilder extends PlatformBuilderBase{
    
        private PitPlatform pit;
        private int width;
        private int height;

    public PitPlatformBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject(int width, int height) {
        this.height = height;
        this.width = width;
        this.gameObject = new PitPlatform (gameData, point, width, height);
        this.pit = (PitPlatform) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        PitPlatformAnimation ppa = new PitPlatformAnimation(this.pit, gameData.getMapDimensions(), "assets/emptyPit.png");
        this.pit.addAnimator(AnimationState.DEFAULT, ppa);
        ppa.setDimensions(new Dimension(width, height));
    } 
}
