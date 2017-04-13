/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.HorizontalPlatformAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.PlatformBuilderBase;
import goldteam.platforms.HorizontalPlatform;
import java.awt.Dimension;

/**
 *
 * @author Mishal
 */
public class HorizontalPlatformBuilder extends PlatformBuilderBase {

    private HorizontalPlatform platform;
    private int width;
    private int height;

    public HorizontalPlatformBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject(int width, int height) {
        this.height = height;
        this.width = width;
        this.gameObject = new HorizontalPlatform(gameData, point, width, height + 50);
        this.platform = (HorizontalPlatform) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        HorizontalPlatformAnimation hp = new HorizontalPlatformAnimation(this.platform, gameData.getMapDimensions(), "assets/grass_block.png");
        this.platform.addAnimator(AnimationState.DEFAULT, hp);
        hp.setDimensions(new Dimension(width, height));
          
    }
    
}
