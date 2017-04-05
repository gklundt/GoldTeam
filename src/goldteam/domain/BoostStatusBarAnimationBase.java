/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author faaez
 */
public abstract class BoostStatusBarAnimationBase extends AnimationBase{
    
    private GameObject gameObject;
    private final Animatable animatableGameObject;
    private int progress;
    private final int initialProgress = 100;
    protected boolean draw;
    private String fileName;
    private BufferedImage img;
    
    public BoostStatusBarAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile){
        super();
        super.setSize(preferredSize);
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.progress = initialProgress;
        this.fileName = assetFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(draw){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int dy = gameObject.PositionVector().y;
            int dx = gameObject.PositionVector().x;
            int padding = 2, marginLeft = 20, marginTop = 2;
            af.setTransform(1.0, 0, 0, 1.0, dx, dy);
            g2d.drawImage(img, af, null);
            g2d.drawRect(dx + marginLeft, dy + marginTop, 100, 10);
            g2d.fillRect((dx+padding) + marginLeft, (dy+padding) + marginTop, progress - padding, 10 - (2*padding));
        }
    }

    public void setProgress(Delta delta) {
        this.progress += delta.delta;
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public void resetProgress(){
        this.progress = initialProgress;
    }
    
        public void loadImage(String assetFile, AffineTransform transform) {
        ClassLoader cl = getClass().getClassLoader();
        URL imgUrl = cl.getResource(assetFile);
        if (imgUrl == null) {
            System.err.println("Couldn't find file: " + assetFile);
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
    }
    
}
