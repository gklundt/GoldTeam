/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JLayeredPane;

/**
 *
 * @author gordon
 */
public abstract class AnimationBase extends JLayeredPane implements ActionListener {

    protected final AffineTransform defaultTransform;
    public AffineTransform af;

    public AnimationBase() {
        super();
        super.setDoubleBuffered(true);
        this.defaultTransform = af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0, 0);
    }

    /**
     * Update the position based on update routine in GameData
     */
    protected abstract void update();

    @Override
    public abstract void actionPerformed(ActionEvent e);

}
