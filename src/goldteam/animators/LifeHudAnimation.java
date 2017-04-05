<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.animators;

import goldteam.domain.AttackableWatcher;
=======
package goldteam.animators;

import goldteam.domain.DepletableWatcher;
>>>>>>> dev
import goldteam.domain.GameObject;
import goldteam.domain.HudAnimationBase;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;

<<<<<<< HEAD
/**
 *
 * @author Caleb Dunham
 */
public class LifeHudAnimation extends HudAnimationBase {

    /**
     * Constructor to set up the GUI components
     *
     * @param gameObject -> HeartHudItem
     * @param preferredSize
     * @param assetFile
     * @param frameRate
     */

    private AttackableWatcher gameObj;

    public LifeHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (AttackableWatcher) gameObject;
        // Setup animation
        super.loadImage(imgFilename, this.gameObj.getWatcher().getLifeValue(), new AffineTransform(1, 0, 0, 1, 0, 0));

=======
public class LifeHudAnimation extends HudAnimationBase {

    private final DepletableWatcher gameObj;

    public LifeHudAnimation(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super(gameObject, preferredSize, assetFile);
        this.gameObj = (DepletableWatcher) gameObject;
        super.loadImage(imgFilename, this.gameObj.getWatcher().getCount(), new AffineTransform(1, 0, 0, 1, 0, 0));
>>>>>>> dev
    }

    @Override
    protected void update() {
<<<<<<< HEAD
        this.count = this.gameObj.getWatcher().getLifeValue();
=======
        this.count = this.gameObj.getWatcher().getCount();
>>>>>>> dev
    }

}
