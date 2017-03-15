/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
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
 * @author Caleb Dunham
 */
public abstract class ArrowHudAnimationBase extends AnimationBase{
    
    protected AffineTransform af;
    protected GameObject gameObject;
    private final Animatable animatableGameObject;
    private String fileName;
    protected int arrowCount;
    private BufferedImage img;
    protected int imgWidth;
    protected int imgHeight;

    public ArrowHudAnimationBase(GameObject gameObject, Dimension preferredSize, AffineTransform af, String assetFile) {
        super();
        super.setSize(preferredSize);
        this.fileName = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int dy = gameObject.PositionVector().y;
        int dx = gameObject.PositionVector().x;
        af.setTransform(1.0, 0, 0, 1.0, dx, dy);
        g2d.drawImage(img, af, null);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        
        g2d.drawString(Integer.toString(this.arrowCount), dx+img.getWidth()+5, dy+15);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
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

        this.imgHeight = img.getHeight(null);
        this.imgWidth = img.getWidth(null);
        
        
    }
}
