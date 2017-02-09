/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author gordon
 */
public abstract class AnimationBase extends JComponent implements ActionListener {

    protected final String imgFilename;
    protected final GameObject gameObject;
    protected int currentFrame; // current frame number
    protected int numCols;
    protected int numFrames;
    protected int imgWidth;
    protected int imgHeight;
    protected BufferedImage img; // for the entire image stripe
    protected BufferedImage[] imgArray; // for the entire image stripe
    protected Timer timer;
    private final AffineTransform af;
    private final BufferedImageOp  bio;
    

    public AnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile, int frameRate) {
        super();

        this.af = new AffineTransform();
        //this.bio = new  AffineTransformOp(af,null);
        this.bio = new  RescaleOp( 1.0f, 0.0f, null);

        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.timer = new Timer(1000 / frameRate, this);
        // Setup GUI
        super.setPreferredSize(preferredSize);
        this.timer.start();

    }

    /**
     * Helper method to load image. All frames have the same height and width
     * @param imgFileName
     * @param numRows
     * @param numCols
     */
    protected void loadImage(String imgFileName, int numRows, int numCols) {
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

    /**
     * Returns the top-left x-coordinate of the given frame number.
     */
    protected int getcurrentFrameX() {
        return (currentFrame % numCols) * imgWidth;
    }

    /**
     * Returns the top-left y-coordinate of the given frame number.
     */
    protected int getCurrentFrameY() {
        return (currentFrame / numCols) * imgHeight;
    }

    /**
     * Update the position based on update routine in GameData
     */
    public abstract void update();

    /**
     * Custom painting codes on this JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d;
        g2d = (Graphics2D) g;
        System.out.println(gameObject.toString());
        g2d.drawImage(imgArray[currentFrame], bio, (int) gameObject.PositionVector().x - imgWidth / 2, (int) gameObject.PositionVector().y - imgHeight / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update(); // update the position and image
        repaint(); // Refresh the display
    }

}
