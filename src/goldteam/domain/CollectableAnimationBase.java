/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import goldteam.Collectables.Arrows;
import java.awt.Color;
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
public class CollectableAnimationBase extends AnimationBase{

    protected final String imgFilename;
    protected final GameObject gameObject;
    private final Animatable animatableGameObject;
    private Dimension platfromDimensions;
    private Color color;
    private BufferedImage img;
    protected int imgWidth;
    protected int imgHeight;
    
    public CollectableAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile){
        super();
        super.setSize(preferredSize);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.color = Color.BLACK;
    }
    
    public void loadImage(String imgFileName, AffineTransform transform){
        if(imgFileName != null){
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
    }
    
    @Override
    protected void update() {
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        CollectableItem a = (CollectableItem) this.gameObject; //not needed
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        if(this.imgFilename == null){
            g2d.fillRect(gameObject.positionVector.x,
                         gameObject.positionVector.y,
                         platfromDimensions.width, 
                         platfromDimensions.height
            );
        } else{
            if(a.getState() == false){
                g2d.fillRect(0, 0, 0, 0);
            } else {
                g2d.drawImage(img, this.gameObject.positionVector.x, this.gameObject.positionVector.y, this);
            }
        }
    }
}
