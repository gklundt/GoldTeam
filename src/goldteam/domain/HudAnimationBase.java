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
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author gordon
 */
public abstract class HudAnimationBase extends AnimationBase {

    protected final String imgFilename;
    protected final GameObject gameObject;
    private final Animatable animatableGameObject;

    protected int count;
    protected int imgWidth;
    protected int imgHeight;
    protected BufferedImage img; // for the entire image stripe
    protected BufferedImage[] imgArray; // for the entire image stripe
    private final AffineTransform af;


    //HUD Items: Hearts, Shields, Lives
    public HudAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super();
        super.setSize(preferredSize);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.af = new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0, 0.0);
    }

    /**
     * Helper method to load image. All frames have the same height and width
     *
     * @param imgFileName
     * @param numRows
     * @param numCols
     */
    protected void loadImage(String imgFileName, int numberOfItems, AffineTransform transform) {
        this.count = numberOfItems;
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

        this.imgArray = new BufferedImage[count];
        for (int i = 0; i < count; ++i) {
            imgArray[i] = img;
        }
    }
    
    /**
     * Custom painting codes on this JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int dy = gameObject.PositionVector().y;
        int i = 0;
        for(BufferedImage img : imgArray){
            int dx = gameObject.PositionVector().x + i * imgWidth;
            af.setTransform(1.0, 0, 0, 1.0, dx, dy);
            if(imgArray[i]!=null)
                g2d.drawImage(imgArray[i], af, null);
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update(); // update the image
    }

}

