/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.builders;

import goldteam.animators.MapDoorsAnimation;
import goldteam.domain.AnimationState;
import goldteam.domain.GameEngine;
import goldteam.domain.PlatformBuilderBase;
import goldteam.platforms.DoorsPlatform;
import java.awt.Dimension;

/**
 *
 * @author Mishal
 */
public class MapDoorsBuilder extends PlatformBuilderBase {
    
    private DoorsPlatform door;
    private int width;
    private int height;

    public MapDoorsBuilder(GameEngine gameData) {
        super(gameData);
    }

    @Override
    protected void createObject(int height, int width) {
        this.height = height;
        this.width = width;
        this.gameObject = new DoorsPlatform (gameData, point, width, height);
        this.door = (DoorsPlatform) this.gameObject;
    }

    @Override
    protected void addAnimations() {
        MapDoorsAnimation md = new MapDoorsAnimation(this.door, gameData.getMapDimensions(), "assets/S_door.png");
        this.door.addAnimator(AnimationState.DEFAULT, md);
        md.setDimensions(new Dimension(height, width));
    }
    
}
