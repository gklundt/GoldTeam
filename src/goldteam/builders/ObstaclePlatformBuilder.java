/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.ObstaclePlatformAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.PlatformBuilderBase;
import goldteam.platforms.HorizontalPlatform;
import goldteam.platforms.ObstaclePlatform;
import java.awt.Dimension;

/**
 *
 * @author Mishal
 */
public class ObstaclePlatformBuilder extends PlatformBuilderBase {
    
    private ObstaclePlatform obstacle;
    private int width;
    private int height;

    public ObstaclePlatformBuilder(GameEngine gameData) {
        super(gameData);
       
    }
 
     @Override
    protected void createObject(int height, int width) {
        this.height = height;
        this.width = width;
        this.gameObject = new ObstaclePlatform(gameData, point, width, height);
        this.obstacle = (ObstaclePlatform) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        ObstaclePlatformAnimation op = new ObstaclePlatformAnimation(this.obstacle, gameData.getMapDimensions(), "assets/spike1.png");
        this.obstacle.addAnimator(AnimationState.DEFAULT, op);
        op.setDimensions(new Dimension(height, width));
    }   
}
