package goldteam.animators;

import goldteam.domain.CharacterAnimationBase;
import goldteam.domain.GameEngine;
import goldteam.domain.GameObject;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

public class ArcherAnimationDrawing extends CharacterAnimationBase {

    private GameEngine gamedata;

    public ArcherAnimationDrawing(GameObject gameObject, GameEngine gameData, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        super.loadImage(imgFilename, 1, 8, new AffineTransform(1, 0, 0, 1, 0, 0));
        gamedata = gameData;
    }

    @Override
    protected void update() {
        if (this.gamedata != null) {
            if (!this.gamedata.getHeldMouse().isEmpty() && currentFrame == numFrames - 2) {
                return;
            }

            if (++currentFrame >= numFrames) {
                currentFrame = 0;
            }
        }
    }
}
