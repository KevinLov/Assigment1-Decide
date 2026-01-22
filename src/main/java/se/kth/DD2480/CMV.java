package se.kth.DD2480;

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

    boolean lic1() {
        return false;
    }

    boolean lic2() {
        return false;
    }

    boolean lic3() {
        return false;
    }

    boolean lic4() {
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

    boolean lic6() {
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

    boolean lic10() {
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



    public boolean[] verifyAllLics(double LENGTH1, Point[] points, int NUMPOINTS) {

        cmv[0] = lic0(points, LENGTH1, NUMPOINTS);
        cmv[1] = lic1();
        cmv[2] = lic2();
        cmv[3] = lic3();
        cmv[4] = lic4();
        cmv[5] = lic5(points, NUMPOINTS);
        cmv[6] = lic6();
        cmv[7] = lic7();
        cmv[8] = lic8();
        cmv[9] = lic9();
        cmv[10] = lic10();
        cmv[11] = lic11();
        cmv[12] = lic12();
        cmv[13] = lic13();
        cmv[14] = lic14();
        cmv[15] = lic15();

        return cmv;


    }

}

