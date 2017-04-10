/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

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
public abstract class PlatformAnimationBase extends AnimationBase {

    protected final String imgFilename;
    protected final GameObject gameObject;
    private final Animatable animatableGameObject;
    private Dimension platfromDimensions;
    private Color color;
    private BufferedImage img;
    protected int imgWidth;
    protected int imgHeight;

    public PlatformAnimationBase(GameObject gameObject, Dimension preferredSize, String assetFile) {
        super();
        super.setSize(preferredSize);
        this.imgFilename = assetFile;
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);
        this.color = Color.BLACK;
    }

    public void setDimensions(Dimension platfromDimensions) {
        this.platfromDimensions = platfromDimensions;
    }

    public Dimension getDimensions() {
        return this.platfromDimensions;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void loadImage(String imgFileName, AffineTransform transform) {
        if (imgFileName != null) {
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
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.color);
        if (this.imgFilename == null) {
            g2d.fillRect(gameObject.positionVector.x,
                    gameObject.positionVector.y,
                    platfromDimensions.width,
                    platfromDimensions.height
            );
        } else {
            int widthCounter = 1;
            int heightCounter = 1;

            for (int x = this.gameObject.positionVector.x; x < this.gameObject.positionVector.x + platfromDimensions.width; x += imgWidth) {
                heightCounter = 1;
                for (int y = this.gameObject.positionVector.y; y < this.gameObject.positionVector.y + platfromDimensions.height; y += imgHeight) {

                    if ((x + imgWidth) > this.gameObject.positionVector.x + platfromDimensions.width
                            || (y + imgHeight) > this.gameObject.positionVector.y + platfromDimensions.height) {

                        int widthDifference = 0;
                        int heightDifference = 0;

                        if ((x + imgWidth) > this.gameObject.positionVector.x + platfromDimensions.width) {
                            if (imgWidth > platfromDimensions.width) {
                                widthDifference = platfromDimensions.width;
                            } else {
                                widthDifference = platfromDimensions.width - (imgWidth * (widthCounter - 1));
                            }
                        } else {
                            widthDifference = imgWidth;
                        }

                        if ((y + imgHeight) > this.gameObject.positionVector.y + platfromDimensions.height) {
                            if (imgHeight > platfromDimensions.height) {
                                heightDifference = platfromDimensions.height;
                            } else {
                                heightDifference = platfromDimensions.height - (imgHeight * (heightCounter - 1));
                            }
                        } else {
                            heightDifference = imgHeight;
                        }

                        try {
                            BufferedImage croppedImage = img.getSubimage(0, 0, widthDifference, heightDifference);
                            g2d.drawImage(croppedImage, x, y, null);
                        } catch (Exception e) {

                        }
                    } else {
                        g2d.drawImage(img, x, y, imgWidth, imgHeight, null);
                    }
                    heightCounter++;
                }
                widthCounter++;
            }
        }
    }
}
