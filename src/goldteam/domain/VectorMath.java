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

    /**
     * Returns the unit vector for a given DoubleVector
     *
     * @param vector
     * @return
     */
    public static DoubleVector getUnitVector(DoubleVector vector) {
        Double mag = Math.sqrt((Math.pow(vector.x, 2.0d) + Math.pow(vector.y, 2.0d)));

        vector.x = mag == 0d ? 0 : vector.x / mag;
        vector.y = mag == 0d ? 0 : vector.y / mag;
        return vector;
    }

    /**
     * Gets the unit vector between two points
     *
     * @param pointA
     * @param pointB
     * @return
     */
    public static DoubleVector getUnitVector(Point pointA, Point pointB) {
        double dx = pointA.getX() - pointB.getX();
        double dy = pointA.getY() - pointB.getY();
        DoubleVector vector = new DoubleVector(dx, dy);
        return getUnitVector(vector);
    }

    /**
     * Scales a vector by some scalar integer
     *
     * @param vector
     * @param scalar
     * @return
     */
    public static DoubleVector getScaledVector(DoubleVector vector, Double scalar) {
        vector.x = vector.x * scalar;
        vector.y = vector.y * scalar;
        return vector;
    }

    /**
     * Gets a velocity vector by first calculating the unit vector of the
     * provided vector, then scaling it by the provided velocity.
     *
     * @param vector
     * @param velocity
     * @return
     */
    public static DoubleVector getVelocityVector(DoubleVector vector, Double velocity) {
        return getScaledVector(getUnitVector(vector), velocity);
    }

    /**
     * Gets a velocity vector by first calculating the unit vector of the
     * provided vector, then scaling it by the provided velocity.
     *
     * @param vector
     * @param velocity
     * @return
     */
    public static DoubleVector getVelocityVector(DoubleVector vector, Integer velocity) {
        return getScaledVector(getUnitVector(vector), velocity.doubleValue());
    }

    /**
     * Gets the velocity vector between two points
     * @param pointA
     * @param pointB
     * @param velocity
     * @return
     */
    public static DoubleVector getVelocityVector(Point pointA, Point pointB, Double velocity) {
        double dx = pointB.getX() - pointA.getX();
        double dy = pointB.getY() - pointA.getY();
        DoubleVector vector = new DoubleVector(dx,dy);
        return getScaledVector(getUnitVector(vector), velocity);
    }

    /**
     * Gets the velocity vector between two points
     * @param point
     * @param velocity
     * @return
     */
    public static DoubleVector getVelocityVector(Point pointA, Point pointB, Integer velocity) {
        double dx = pointB.getX() - pointA.getX();
        double dy = pointB.getY() - pointA.getY();
        DoubleVector vector = new DoubleVector(dx, dy);
        return getScaledVector(getUnitVector(vector), velocity.doubleValue());
    }

    /**
     * Returns the length of a vector
     * @param vector
     * @return
     */
    public static double getMagnitude(DoubleVector vector) {
        return Math.sqrt(vector.x * vector.x + vector.y * vector.y);
    }
    
    /**
     * Returns the distance between two points
     * @param pointA
     * @param pointB
     * @return
     */
    public static double getMagnitude(Point pointA, Point pointB) {
        double dx = pointB.getX() - pointA.getX();
        double dy = pointB.getY() - pointA.getY();
        DoubleVector vector = new DoubleVector(dx, dy);
        return Math.sqrt(vector.x * vector.x + vector.y * vector.y);
    }

}
