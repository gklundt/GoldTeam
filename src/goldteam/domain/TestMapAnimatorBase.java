package goldteam.domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

public abstract class TestMapAnimatorBase extends AnimationBase {

    protected final GameObject gameObject;
    protected final Animatable animatableGameObject;
    private final Rectangle rectangle;
    private final GradientPaint paint;

    public TestMapAnimatorBase(GameObject gameObject, Dimension preferredSize) {
        super();
        super.setSize(preferredSize);
        this.gameObject = gameObject;
        this.animatableGameObject = (Animatable) gameObject;
        this.animatableGameObject.addAnimationTimerListener(this);

        Point gs = new Point(0, 0);
        Point ge = new Point(preferredSize.width/2, preferredSize.height/2);

        this.paint = new GradientPaint(gs, Color.red, ge, Color.blue);
        this.rectangle = this.getBounds();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int dy = gameObject.PositionVector().y;
        int dx = gameObject.PositionVector().x;
        g2d.setPaint(paint);
        g2d.fillRect(dx, dy, this.getWidth(), this.getHeight());

        int dw = 2200;
        int dh = 1000;
        int cx = 0;
        int cy = 0;

        while (dh > 0) {
            if (dw % 400 == 0) {
                g2d.setPaint(Color.black);
            } else if (dw % 300 == 0) {
                g2d.setPaint(Color.red);
            } else if (dw % 200 == 0) {
                g2d.setPaint(Color.green);
            } else {
                g2d.setPaint(Color.yellow);
            }

            cx = dx + ((getWidth() / 2) - (dw / 2));
            cy = dy + ((getHeight() / 2) - (dh / 2));

            g2d.fillRect(cx, cy, dw, dh);
            dh -= 100;
            dw -= 100;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
