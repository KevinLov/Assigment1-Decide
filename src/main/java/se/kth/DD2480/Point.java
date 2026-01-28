package se.kth.DD2480;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance (Point p2){
        return Math.sqrt(Math.pow(this.x - p2.x, 2) + Math.pow(this.y - p2.y, 2));
    }

    static double triangleArea(Point a, Point b, Point c) {
        return 0.5 * Math.abs(
                a.x * (b.y - c.y)
                        + b.x * (c.y - a.y)
                        + c.x * (a.y - b.y)
        );
    }

    /**
     * Calculates the minimal enclosing circle radius for the triangle created by
     * connecting three Points.
     * <p>
     * For triangles with a non-zero area, this is done by calculating the radius
     * of the triangle's circumcircle (Side A * Side B * Side C) / (4 * Area).
     * When the points are collinear, the minimal enclosing circle has a radius
     * equal to the longest side divided by 2.
     * <p>
     * A threshold of 0.000001 is used for the area when determining collinearity.
     *
     * @param a Point A
     * @param b Point B
     * @param c Point C
     * @return the radius of the minimal enclosing circle for the three points
     */
     static double minimalEnclosingCircleRadius(Point a, Point b, Point c) {
        double sideA = a.distance(b);
        double sideB = b.distance(c);
        double sideC = c.distance(a);
        double area = Point.triangleArea(a, b, c);

        if (area > 0.000001) {
            return (sideA * sideB * sideC) / (4 * area);
        } else {
            return Math.max(sideA, Math.max(sideB, sideC)) / 2;
        }
    }
}
