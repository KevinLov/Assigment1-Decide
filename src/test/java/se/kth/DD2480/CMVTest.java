package se.kth.DD2480;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CMVTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void lic0_returnsFalse_whenPointsIsNull() {
        CMV cmv = new CMV();
        assertFalse(cmv.lic0(null, 6, 0));
    }

    @Test
    void lic0_returnsFalse_whenNoDistanceGreaterThanLength() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4), // distance = 5
                new Point(6, 8)  // distance = 5
        };

        assertFalse(cmv.lic0(points, 5.0, points.length));
    }
    @Test
    void lic0_returnsTrue_whenDistanceGreaterThanLength() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(10, 0) // distance = 10
        };

        assertTrue(cmv.lic0(points, 5.0, points.length));
    }




    @Test
    void lic1() {
    }

    @Test
    void lic2() {
    }

    @Test
    void lic3() {
    }

    @Test
    void lic4() {
    }

    @Test
    void lic5_returnsFalse_whenNotEnoughPoints() {
        CMV cmv = new CMV();
        Point[] points = { new Point(1,1)};
        assertFalse(cmv.lic5(points, 1));
    }
    @Test
    void lic5_returnsFalse_whenXNeverDecreases() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0)
        };

        assertFalse(cmv.lic5(points, points.length));
    }
    @Test
    void lic5_returnsTrue_whenXDecreases() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(5, 0),
                new Point(3, 0)
        };

        assertTrue(cmv.lic5(points, points.length));
    }




    @Test
    void lic6() {
    }

    @Test
    void lic7() {
    }

    @Test
    void lic8() {
    }

    @Test
    void lic9() {
    }

    @Test
    void lic10_returnsFalse_whenParametersInvalid() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0)
        };

        assertFalse(cmv.lic10(points, 0, 0, 1.0, points.length));
    }
    @Test
    void lic10_returnsFalse_whenAllTriangleAreasTooSmall() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };

        assertFalse(cmv.lic10(points, 1, 1, 1.0, points.length));
    }
    @Test
    void lic10_returnsTrue_whenTriangleAreaGreaterThanAREA1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(6, 1),
                new Point(0, 4),
                new Point(3, 3),
                new Point(8, 0),
                new Point(0, 0)
        };

        assertTrue(cmv.lic10(points, 1, 1, 5.0, points.length));
    }



    @Test
    void lic11() {
    }

    @Test
    void lic12() {
    }

    @Test
    void lic13() {
    }

    @Test
    void lic14() {
    }

    @Test
    void lic15() {
    }

    @Test
    void verifyAllLics() {
    }
}