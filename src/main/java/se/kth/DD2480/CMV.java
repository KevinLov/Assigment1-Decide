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

            //r = abc/4A where r is radius of the triangles Circumcircle, a,b,c is the length of the sides between the points and A is the triangles Area
            Point a = points[i];
            Point b = points[i+1];
            Point c = points[i+2];
            double sideA = a.distance(b);
            double sideB = b.distance(c);
            double sideC = c.distance(a);
            double area = Point.triangleArea(a, b, c);
            if (area < 0.000001) {
                if (sideA/2 > RADIUS1 || sideB/2 > RADIUS1 || sideC/2 > RADIUS1) {
                    return true;
                }
            } else {
                //r = abc/4A
                double circumradius = (sideA*sideB*sideC)/(4*area);
                if (circumradius > RADIUS1) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean lic2() {
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

    boolean lic7() {
        return false;
    }

    boolean lic8() {
        return false;
    }

    boolean lic9() {
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

    boolean lic11() {
        return false;
    }

    boolean lic12() {
        return false;
    }

    boolean lic13() {
        return false;
    }

    boolean lic14() {
        return false;
    }

    boolean lic15() {
        return false;
    }



    public boolean[] verifyAllLics(Parameters p, Point[] points, int NUMPOINTS) {

        cmv[0] = lic0(points, p.LENGTH1, NUMPOINTS);
        cmv[1] = lic1(points, NUMPOINTS, p.RADIUS1);
        cmv[2] = lic2();
        cmv[3] = lic3(points, NUMPOINTS, p.AREA1);
        cmv[4] = lic4(points, NUMPOINTS, p.Q_PTS, p.QUADS);
        cmv[5] = lic5(points, NUMPOINTS);
        cmv[6] = lic6(points, NUMPOINTS, p.N_PTS, p.DIST);
        cmv[7] = lic7();
        cmv[8] = lic8();
        cmv[9] = lic9();
        cmv[10] = lic10(points,p.E_PTS,p.F_PTS,p.AREA1,NUMPOINTS);
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();
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

