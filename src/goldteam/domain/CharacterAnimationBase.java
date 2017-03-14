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
import javax.imageio.*;

public abstract class CharacterAnimationBase extends AnimationBase {

    protected final String imgFilename;
    private final GameObject gameObject;
    private final Animatable animatableGameObject;

    private int numCols;
    protected int currentFrame; // current frame number
    protected int numFrames;
    protected int imgWidth;
    protected int imgHeight;
    private BufferedImage img; // for the entire image stripe
    private BufferedImage[] imgArray; // for the entire image stripe

    public CharacterAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super();
        super.setSize(preferredSize);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        
           
    }

    protected void loadImage(String imgFileName, int numRows, int numCols, AffineTransform imageTransform) {
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

        int scaleX = ((Double) (img.getWidth() * imageTransform.getScaleX())).intValue();
        int scaleY = ((Double) (img.getHeight() * imageTransform.getScaleY())).intValue();
        Image tmp = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);

        BufferedImage bimage = new BufferedImage(scaleX, scaleY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bimage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        img = bimage;

        numFrames = numRows * numCols;
        this.imgHeight = img.getHeight(null) / numRows;
        this.imgWidth = img.getWidth(null) / numCols;
        this.numCols = numCols;
        currentFrame = 0;
        this.imgArray = new BufferedImage[numFrames];
        for (int i = 0; i < numFrames; ++i) {
            int x1 = getcurrentFrameX();
            int y1 = getCurrentFrameY();
            imgArray[i] = img.getSubimage(x1, y1, imgWidth, imgHeight);
            ++currentFrame;
        }
        currentFrame = 0;
    }

    protected int getcurrentFrameX() {
        return (currentFrame % numCols) * imgWidth;
    }

    protected int getCurrentFrameY() {
        return (currentFrame / numCols) * imgHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int dx = gameObject.PositionVector().x - imgWidth / 2;
        int dy = gameObject.PositionVector().y - imgHeight / 2;
        //this.af.setTransform(1.0, 0, 0, 1.0, dx, dy);
        af.setTransform(af.getScaleX(), af.getShearY(), af.getShearX(), af.getScaleY(), dx, dy);
        g2d.drawImage(imgArray[currentFrame], af, null);
        //g2d.drawPolygon(this.gameObject.shape);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update(); // update the image
    }

}
