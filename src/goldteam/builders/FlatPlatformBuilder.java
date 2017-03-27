/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.FlatPlatformAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.PlatformBuilderBase;
import goldteam.platforms.FlatPlatform;
import java.awt.Dimension;

public class FlatPlatformBuilder extends PlatformBuilderBase {

    private FlatPlatform platform;
    private int width;
    private int height;

    public FlatPlatformBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject(int height, int width) {
        this.height = height;
        this.width = width;
        this.gameObject = new FlatPlatform(gameData, point, width, height);
        this.platform = (FlatPlatform) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        FlatPlatformAnimation fpa = new FlatPlatformAnimation(this.platform, gameData.getMapDimensions(), "assets/platformTile.jpg");
        this.platform.addAnimator(AnimationState.DEFAULT, fpa);
        fpa.setDimensions(new Dimension(height, width));
    }
}
