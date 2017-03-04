/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author gordon
 */
public abstract class GameStageAnimationBase extends AnimationBase {

    protected final String imgFilename;
    protected final GameObject gameObject;
    private final Animatable animatableGameObject;

    protected int imgWidth;
    protected int imgHeight;
    protected BufferedImage img; // for the entire image stripe
    private final AffineTransform af;
    protected float alpha;

    public GameStageAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super();
        super.setSize(preferredSize);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0);
        this.alpha = 0.01f;
    }

    /**
     * Helper method to load image. All frames have the same height and width
     *
     * @param imgFileName
     * @param transform
     */
    protected void loadImage(String imgFileName, AffineTransform transform) {
        ClassLoader cl = getClass().getClassLoader();
        URL imgUrl = cl.getResource(imgFileName);
        if (imgUrl == null) {
            System.err.println("Couldn't find file: " + imgFileName);
        } else {
            try {
                img = ImageIO.read(imgUrl); // load image via URL
            } catch (IOException ex) {
            }
        }

        int scaleX = ((Double) (img.getWidth() * transform.getScaleX())).intValue();
        int scaleY = ((Double) (img.getHeight() * transform.getScaleY())).intValue();
        Image tmp = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);

        BufferedImage bimage = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bimage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        img = bimage;

        this.imgHeight = img.getHeight(null);
        this.imgWidth = img.getWidth(null);
    }

    /**
     * Custom painting codes on this JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);

        af.setTransform(alpha, 0, 0, alpha, ((1.0 - alpha) * imgWidth)/2, ((1.0 - alpha) * imgHeight)/2);
        if (this.alpha >= 1.0) {
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.75f));
            g2d.fill(new Rectangle(this.imgWidth, this.imgHeight));
            g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));
            g2d.drawImage(this.img, af, null);
        } else {
//            g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
//            g2d.fill(new Rectangle(this.imgWidth, this.imgHeight));

//            g2d.setComposite(AlphaComposite.SrcOver.derive(1f - alpha));
            g2d.drawImage(this.img, af, null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update(); // update the image
    }
}
