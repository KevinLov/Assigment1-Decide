package se.kth.DD2480;

import java.util.HashSet;

/**
 * Represents the CMV boolean array that is used to determine which LIC conditions are met.
 */
public class CMV {

    private boolean[] cmv;


    public CMV() {
        this.cmv = new boolean[15];
    }

    boolean lic0( Point[] points, double LENGHT1, int NUMPOINTS) {
        if(points == null || NUMPOINTS < 2 ){
            return false;
        }
        for (int i = 0; i < NUMPOINTS - 1; ++i) {
            if (points[i].distance(points[i + 1]) > LENGHT1) return true;
        }
        return false;
    }

    boolean lic1(Point[] points, int NUMPOINTS, double RADIUS1) {
        if(points == null || NUMPOINTS < 3 || RADIUS1 < 0){
            return false;
        }
        for (int i = 0; i < NUMPOINTS-2; i++) {
            Point a = points[i];
            Point b = points[i+1];
            Point c = points[i+2];

            double mecRadius = (Point.minimalEnclosingCircleRadius(a, b, c));
            if (mecRadius > RADIUS1) {
                return true;
            }
        }
        return false;
    }

    boolean lic2(Point[] points, int NUMPOINTS, double PI, double EPSILON) {

        if (points == null || points.length < 3 || NUMPOINTS != points.length || EPSILON < 0 || EPSILON >= PI || PI != 3.1415926535)
            return false;

        for (int i = 1; i < NUMPOINTS - 1; i++) {
            Point a = points[i - 1];
            Point b = points[i];
            Point c = points[i + 1];

            double ab = a.distance(b);
            double cb = b.distance(c);

            // If either a or c coincides with b we skip to next triplet or points
            if (ab < 0.000001 || cb < 0.000001)
                continue;

            // Vectors
            Point u = new Point(a.x - b.x, a.y - b.y);
            Point v = new Point(c.x - b.x, c.y - b.y);

            double numerator    = u.x*v.x + u.y*v.y;
            double denominator  = ab * cb;
            double cos = numerator / denominator;

            double angle = Math.acos(cos);

            if (angle < (PI - EPSILON) || angle > (PI + EPSILON))
                return true;
        }
        return false;
    }

    boolean lic3(Point[] points, int NUMPOINTS, double AREA1) {
        if (points == null || NUMPOINTS < 3) return false;

        for (int i = 0; i <= NUMPOINTS - 3; i++) {
            double areaOfPoints = Point.triangleArea(points[i], points[i + 1], points[i + 2]);

            if (areaOfPoints > AREA1) return true;
        }

        return false;
    }

    boolean lic4(Point[] points, int NUMPOINTS, int Q_PTS, int QUADS) {
        HashSet<Integer> quadSet = new HashSet<>();
        if(points == null || Q_PTS < 2 || Q_PTS > NUMPOINTS || Q_PTS <= QUADS || QUADS < 1 || QUADS > 3) return false;
        for(int i = 0; i <= NUMPOINTS - Q_PTS; i++) {
            determineQuadrant(quadSet, points[i]);
            for(int j = i+1; j < i + Q_PTS; j++) {
                determineQuadrant(quadSet, points[j]);
            }
            if(quadSet.size() > QUADS) {
                return true;
            }
            quadSet.clear();
        }
        return false;
    }

    boolean lic5(Point[] points, int NUMPOINTS){
        if (points == null || NUMPOINTS < 2) {
            return false;
        }
        for (int i = 0; i < NUMPOINTS - 1; ++i) {
            if (points[i+1].x - (points[i].x) < 0) return true;
        }
        return false;
    }

    boolean lic6(Point[] points, int NUMPOINTS, int N_PTS, double DIST){
        if (points == null || NUMPOINTS < 3 || N_PTS < 3 || N_PTS > NUMPOINTS || DIST < 0) {
            return false;
        }
        for (int i = 0; i < NUMPOINTS - N_PTS +1; ++i) {
            Point firsPoint = points[i];
            Point lastPoint = points[i + N_PTS -1];
            //if points are the same, check distance to first point
            if (firsPoint.distance(lastPoint) < 0.000001) {
                for (int j = i+1; j < i + N_PTS -1; ++j) {
                    if (points[j].distance(firsPoint) > DIST) {
                        return true;
                    }
                }
            } else { //if points are different, check distance to line
                for (int j = i+1; j < i + N_PTS -1; ++j) {
                    //equation for distance from point to line given by two points
                    double distance = Math.abs((lastPoint.y - firsPoint.y) * points[j].x - (lastPoint.x - firsPoint.x) * points[j].y + lastPoint.x * firsPoint.y - lastPoint.y * firsPoint.x) / firsPoint.distance(lastPoint);
                    if (distance > DIST) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean lic7(Point[] points, int NUMPOINTS, double LENGTH1, int K_PTS) {
        if (points == null || NUMPOINTS < 3 || points.length < NUMPOINTS || K_PTS < 1 || K_PTS > (NUMPOINTS-2))
            return false;

        for (int i = 0; i < NUMPOINTS - K_PTS - 1; i++) {
            Point a = points[i];
            Point b = points[i + K_PTS + 1];

            if (a.distance(b) > LENGTH1)
                return true;
        }
        return false;
    }

    boolean lic8(Point[] points, int NUMPOINTS, int A_PTS, int B_PTS, double RADIUS1) {
        assert points != null : "'points' must not be null";
        assert NUMPOINTS >= 5 : "'NUMPOINTS' must be >= 5";
        assert A_PTS >= 1 : "'A_PTS' must be >= 1";
        assert B_PTS >= 1 : "'B_PTS' must be >= 1";
        assert A_PTS + B_PTS <= NUMPOINTS - 3 : "A_PTS + B_PTS must be <= NUMPOINTS - 3";
        assert RADIUS1 >= 0 : "'RADIUS1' must be >= 0";

        for (int i = 0; i <= NUMPOINTS - 3 - (A_PTS + B_PTS); ++i) {
            Point a = points[i];
            Point b = points[i + A_PTS + 1];
            Point c = points[i + A_PTS + B_PTS + 2];

            double mecRadius = Point.minimalEnclosingCircleRadius(a, b, c);
            if (mecRadius > RADIUS1) return true;
        }

        return false;
    }

    boolean lic9(Point[] points, int NUMPOINTS, int C_PTS, int D_PTS, double PI, double EPSILON) {
        // point 1 --> C_PTS --> point 2 --> D_PTS --> point 3
        assert points != null : "'points' must not be null";
        assert NUMPOINTS >= 5 : "'NUMPOINTS' must be >= 5";
        assert C_PTS >= 1 : "'C_PTS' must be >= 1";
        assert D_PTS >= 1 : "'D_PTS' must be >= 1";
        assert C_PTS + D_PTS <= NUMPOINTS - 3 : "C_PTS + D_PTS must be <= NUMPOINTS - 3";
        assert EPSILON >= 0 : "'EPSILON' must be >= 0";
        assert EPSILON < PI : "'EPSILON' must be lesser than 'PI'";

        for(int i = 1; i <= NUMPOINTS - 3 - (C_PTS + D_PTS) + 1; i++) {

            Point a = points[i-1];
            Point b = points[i + C_PTS];
            Point c = points[i + C_PTS + D_PTS + 1];

            double ab = a.distance(b);
            double cb = b.distance(c);

            // If either a or c coincides with b we skip to next triplet or points
            if (ab < 0.000001 || cb < 0.000001)
                continue;

            // Vectors
            Point u = new Point(a.x - b.x, a.y - b.y);
            Point v = new Point(c.x - b.x, c.y - b.y);

            double numerator    = u.x*v.x + u.y*v.y;
            double denominator  = ab * cb;
            double cos = numerator / denominator;

            double angle = Math.acos(cos);

            if (angle < (PI - EPSILON) || angle > (PI + EPSILON))
                return true;
        }
        return false;
    }



    boolean lic10(Point[] points, int E_PTS, int F_PTS, double AREA1, int NUMPOINTS){
        if (points == null || NUMPOINTS < 5 || !(E_PTS >= 1) || !(F_PTS >= 1)
                || !(E_PTS + F_PTS <= NUMPOINTS - 3)) {
            return false;
        }
        for(int i = 0; i < NUMPOINTS - (E_PTS + F_PTS + 2 ); ++i){
            Point p1 = points[i];
            Point p2 = points[i+E_PTS+1];
            Point p3 = points[i+E_PTS+1+F_PTS+1];

            double area = Point.triangleArea(p1, p2, p3);
            if(area > AREA1) return true;
        }
        return false;
    }

    boolean lic11(Point[] points, int NUMPOINTS, int G_PTS) {
        if (points == null || NUMPOINTS < 3 || G_PTS < 1 || G_PTS > NUMPOINTS - 2) {
            return false;
        }
        for (int i = 0; i < NUMPOINTS - G_PTS-1; i++) {
            Point p1 = points[i];
            Point p2 = points[i + G_PTS + 1];

            if (p2.x - p1.x < 0) {
                return true;
            }
        }
        return false;
    }

    boolean lic12(Point[] points, int NUMPOINTS, double LENGTH1, double LENGTH2, int K_PTS) {
        if (points == null || NUMPOINTS < 3 || points.length < NUMPOINTS || K_PTS < 1 || K_PTS > NUMPOINTS - 2 || LENGTH2 < 0)
            return false;

        // Represents the two pairs of points to match greater than LENGTH1 and LENGTH2 respectively
        boolean pair1 = false;
        boolean pair2 = false;

        for (int i = 0; i < NUMPOINTS - K_PTS - 1; i++) {
            double distance = points[i].distance(points[i + K_PTS + 1]);

            if (distance > LENGTH1)
                pair1 = true;
            if (distance < LENGTH2)
                pair2 = true;

            if (pair1 && pair2)
                return true;
        }
        return false;
    }

    boolean lic13() {
        return false;
    }

    boolean lic14(Point[] points, int NUMPOINTS, int E_PTS, int F_PTS, double AREA1, double AREA2) {
        assert points != null : "'points' must not be null";
        assert NUMPOINTS >= 5 : "'NUMPOINTS' must be >= 5";
        assert E_PTS >= 1 : "'E_PTS' must be >= 1";
        assert F_PTS >= 1 : "'F_PTS' must be >= 1";
        assert E_PTS + F_PTS <= NUMPOINTS - 3 : "E_PTS + F_PTS must be <= NUMPOINTS - 3";
        assert AREA1 >= 0 : "'AREA1' must be >= 0";
        assert AREA2 >= 0 : "'AREA2' must be >= 0";

        boolean area1Condition = false;
        boolean area2Condition = false;
        for(int i = 1; i <= NUMPOINTS - 3 - (E_PTS + F_PTS) + 1; i++) {

            Point a = points[i-1];
            Point b = points[i + E_PTS];
            Point c = points[i + E_PTS + F_PTS + 1];

            double area = Point.triangleArea(a, b, c);

            if(area > AREA1) area1Condition = true;
            if(area < AREA2) area2Condition = true;
            if(area1Condition && area2Condition) return true;
        }
        return false;
    }

    boolean lic15() {
        return false;
    }



    public boolean[] verifyAllLics(Parameters p, Point[] points, int NUMPOINTS) {

        cmv[0] = lic0(points, p.LENGTH1, NUMPOINTS);
        cmv[1] = lic1(points, NUMPOINTS, p.RADIUS1);
        cmv[2] = lic2(points, NUMPOINTS, 3.1415926535, p.EPSILON); // PI might be up for change in future
        cmv[3] = lic3(points, NUMPOINTS, p.AREA1);
        cmv[4] = lic4(points, NUMPOINTS, p.Q_PTS, p.QUADS);
        cmv[5] = lic5(points, NUMPOINTS);
        cmv[6] = lic6(points, NUMPOINTS, p.N_PTS, p.DIST);
        cmv[7] = lic7(points, NUMPOINTS, p.LENGTH1, p.K_PTS);
        cmv[8] = lic8(points, NUMPOINTS, p.A_PTS, p.B_PTS, p.RADIUS1);
        cmv[9] = lic9(points, NUMPOINTS, p.C_PTS, p.D_PTS, 3.1415926535, p.EPSILON);
        cmv[10] = lic10(points,p.E_PTS,p.F_PTS,p.AREA1,NUMPOINTS);
        cmv[11] = lic11(points, NUMPOINTS, p.G_PTS);
        cmv[12] = lic12(points, NUMPOINTS, p.LENGTH1, p.LENGTH2, p.K_PTS);
        cmv[13] = lic13();
        cmv[14] = lic14(points, NUMPOINTS, p.E_PTS, p.F_PTS, p.AREA1, p.AREA2);
        cmv[15] = lic15();

        return cmv;
    }

    private void determineQuadrant(HashSet<Integer> quadSet, Point point) {
        if(isInFirstQuad(point)) {
            quadSet.add(1);
        }
        else if(isInSecondQuad(point)) {
            quadSet.add(2);
        }
        else if(isInThirdQuad(point)) {
            quadSet.add(3);
        }
        else if(isInFourthQuad(point)) {
            quadSet.add(4);
        }
    }

    private boolean isInFirstQuad(Point point) {
        double x = point.x; double y = point.y;
        if((x == 0.0 && y == 0.0) || (x > 0.0 && y == 0.0) || (x == 0.0 && y > 0.0))
            return true;
        return ((x > 0.0 && y > 0.0));
    }

    private boolean isInSecondQuad(Point point) {
        double x = point.x; double y = point.y;
        if(x < 0.0 && y == 0.0) return true;
        return (x < 0.0 && y > 0.0);
    }

    private boolean isInThirdQuad(Point point) {
        double x = point.x; double y = point.y;
        if(x == 0.0 && y < 0.0) return true;
        return (x < 0.0 && y < 0.0);
    }

    private boolean isInFourthQuad(Point point) {
        double x = point.x; double y = point.y;
        return (x > 0.0  && y < 0.0);
    }

}

