package se.kth.DD2480;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Point[] points_trueForAllLics;
    Point[] points_falseForLic7;

    @BeforeEach
    void setUp() {
        this.points_trueForAllLics = new Point[] {
                new Point(0, 0),   new Point(1, 0),   new Point(2, 0),  new Point(3, 0),   new Point(4, 0),   new Point(0,0),   new Point(1, 0),  new Point(0, 1),
                new Point(10,10),  new Point(20, 10), new Point(5, 5),  new Point(-1, -1), new Point(1, 0),   new Point(5, 5),   new Point(0, 1),
                new Point(6, 1),   new Point(7, 4),   new Point(8, 3),  new Point(9, 0),   new Point(0, 0),
                new Point(0,0),    new Point(0,0),    new Point(2,0),   new Point(0,0),    new Point(0,2),    new Point(0, 0), new Point(2, 0), new Point(0, 1), new Point(3, 0),
                new Point(1,0),    new Point(0, 0),   new Point(1, 0),  new Point(2, 0),   new Point(3, 0),   new Point(4, 0),
                new Point(-1, -1), new Point(1, 0),   new Point(5, 5),  new Point(0, 0),   new Point(1, 0),   new Point(3, 0),  new Point(1, 0),
                new Point(0, 0),   new Point(1, 0),   new Point(0.5, 0),new Point(0, 0),   new Point(-1, -1), new Point(10, 0),  new Point(0, 10),  new Point(0, 0),   new Point(3, 6),
                new Point(0, 1),   new Point(0, 0),   new Point(1, 0),  new Point(0,0),    new Point(1,0),    new Point(10,0), new Point(0,10), new Point(0, 0), new Point(10, 0)
        };

        points_falseForLic7 = new Point[] {
                new Point(0,0), new Point(0,0),
                new Point(0,0), new Point(0,0),
                new Point(0,0), new Point(0,0)
        };
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * 1/3 important tests
     * Since all elements of PUV are false no LIC is considered and launch is set to true.
     * Even though Lics are wrong and LCM demands ANDD for all.
     */
    @Test
    void DECIDE_returnsTrueWhenPUVmodeIs0() {
        // Input
        Parameters params = Parameters.createParameters(1);
        LCM lcm = LCM.createLCM(1);
        PUV puv = PUV.createPUV(0); // all false, All FUV true

        // Output
        boolean launch = Main.DECIDE(points_falseForLic7, params, lcm, puv);
        assertTrue(launch);
    }

    /**
     * 2/3 important tests
     * Since the LCM says NOTUSED for all combinations, no LIC false matters and the PUM will be set to all true.
     */
    @Test
    void DECIDE_returnsTrueWhenLCMisNOTUSED() {
        // Input
        Parameters params = Parameters.createParameters(1);
        LCM lcm = LCM.createLCM(0); // ALL NOTUSED
        PUV puv = PUV.createPUV(1); // All True, all rows in PUM matter

        // Output
        boolean launch = Main.DECIDE(points_falseForLic7, params, lcm, puv);
        assertTrue(launch);
    }

    /**
     * 3/3 important tests
     * This setup uses ANDD for the LCM, and all true for the PUV, which makes it so all LICS needs to be true.
     * But since atleast LIC7 will return false based on the input points, the launch will be set to false.
     */
    @Test
    void DECIDE_returnsFalseWhenNecessaryLICisNotTrue() {
        // Input
        Parameters params = Parameters.createParameters(1);
        LCM lcm = LCM.createLCM(1); // ALL ANDD
        PUV puv = PUV.createPUV(1); // All True, all rows in PUM matter

        // Output
        boolean launch = Main.DECIDE(points_falseForLic7, params, lcm, puv);

        CMV cmv = new CMV();
        assertFalse(cmv.lic7(points_falseForLic7, points_falseForLic7.length,params.LENGTH1, params.K_PTS));
        assertFalse(launch);
    }

    /**
     *
     * All LICS return true, gives launch no matter what LCM and PUV
     */
    @Test
    void DECIDE_returnsTrueWhenAllLICSareTrue() {
        // Input
        Parameters params = Parameters.createParameters(1);
        LCM lcm = LCM.createLCM(1); // ALL ANDD
        PUV puv = PUV.createPUV(1); // All True, all rows in PUM matter

        // Output
        boolean launch = Main.DECIDE(points_trueForAllLics, params, lcm, puv);
        assertTrue(launch);
    }
}