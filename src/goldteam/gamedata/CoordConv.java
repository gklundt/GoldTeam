package goldteam.gamedata;

import goldteam.domain.*;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Class to convert map coordinates relative to the visible window.
 *
 * @author gordon
 */
public class CoordConv implements MapLocationConverter {

    private final double xScale;
    private final double yScale;

    public CoordConv(Dimension mapDimension, Dimension visibleDimension) {
        double x1, x2, y1, y2;

        x1 = ((Integer) mapDimension.width).doubleValue();
        y1 = ((Integer) mapDimension.height).doubleValue();

        x2 = ((Integer) visibleDimension.width).doubleValue();
        y2 = ((Integer) visibleDimension.height).doubleValue();

        this.xScale = x1 / x2;
        this.yScale = y1 / y2;
    }

    @Override
    public void getMapLocation(Point visableLocation, Point refLocation) {
        Double x = visableLocation.getX() * xScale;
        Double y = visableLocation.getY() * yScale;
        refLocation.setLocation(x, y);
    }

    @Override
    public void getVisibleLocation(Point mapLocation, Point refLocation) {
        Double x = mapLocation.getX() / xScale;
        Double y = mapLocation.getY() / yScale;
        refLocation.setLocation(x, y);
    }

}
