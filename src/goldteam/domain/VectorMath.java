/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

import java.awt.Point;

/**
 *
 * @author gordon
 */
public class VectorMath {

    public static DoubleVector getUnitVector(DoubleVector vector) {
        Double mag = Math.sqrt((Math.pow(vector.x, 2.0d) + Math.pow(vector.y, 2.0d)));
        vector.x = vector.x / mag;
        vector.y = vector.y / mag;
        return vector;
    }

    public static DoubleVector getUnitVector(Point point) {
        DoubleVector vector = new DoubleVector(point.getX(), point.getY());
        Double mag = Math.sqrt((Math.pow(vector.x, 2.0d) + Math.pow(vector.y, 2.0d)));
        vector.x = vector.x / mag;
        vector.y = vector.y / mag;
        return vector;
    }

    public static DoubleVector getScaledVector(DoubleVector vector, Double scalar) {
        vector.x = vector.x * scalar;
        vector.y = vector.y * scalar;
        return vector;
    }

    public static DoubleVector getVelocityVector(DoubleVector vector, Double velocity) {
        return getScaledVector(getUnitVector(vector), velocity);
    }

    public static DoubleVector getVelocityVector(DoubleVector vector, Integer velocity) {
        return getScaledVector(getUnitVector(vector), velocity.doubleValue());
    }

    public static DoubleVector getVelocityVector(Point point, Double velocity) {
        DoubleVector vector = new DoubleVector(point.getX(), point.getY());
        return getScaledVector(getUnitVector(vector), velocity);
    }

    public static DoubleVector getVelocityVector(Point point, Integer velocity) {
        DoubleVector vector = new DoubleVector(point.getX(), point.getY());
        return getScaledVector(getUnitVector(vector), velocity.doubleValue());
    }

}
