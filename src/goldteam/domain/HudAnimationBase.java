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
import javax.swing.JLayeredPane;
import javax.swing.Timer;

/**
 *
 * @author gordon
 */
public abstract class HudAnimationBase extends JLayeredPane implements ActionListener {

    protected final String imgFilename;
    protected final GameObject gameObject;
    protected int count;
    protected int imgWidth;
    protected int imgHeight;
    protected BufferedImage img; // for the entire image stripe
    protected BufferedImage[] imgArray; // for the entire image stripe

    private final AffineTransform af;
    private final BufferedImageOp bio;
    private Timer timer;
    
    public HudAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super();

        this.af = new AffineTransform();
        this.bio = new  AffineTransformOp(af,null);
        //this.bio = new RescaleOp(1.0f, 0.0f, null);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.timer = new Timer(10, this);
        // Setup GUI
        super.setSize(preferredSize);
        this.timer.start();
    }

    /**
     * Helper method to load image. All frames have the same height and width
     *
     * @param imgFileName
     * @param numRows
     * @param numCols
     */
    protected void loadImage(String imgFileName, int numberOfItems) {
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

        this.imgHeight = img.getHeight(null);
        this.imgWidth = img.getWidth(null);

        this.imgArray = new BufferedImage[numberOfItems];
        for (int i = 0; i < numberOfItems; ++i) {
            imgArray[i] = img;
        }
        this.count = numberOfItems;
    }

    /**
     * Update the position based on update routine in GameData
     */
    protected abstract void update();

    /**
     * Custom painting codes on this JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i=0; i<count; i++) {
            g2d.drawImage(imgArray[i], bio, gameObject.PositionVector().x + i*imgWidth, gameObject.PositionVector().y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update(); // update the image
    }

}
