package se.kth.DD2480;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CMVTest {
    CMV cmv;

    @BeforeEach
    void setUp() {
        cmv = new CMV();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * The method throws AssertionError if {@code points} is {@code null}
     */
    @Test
    void lic0_throwErrorWhenPointsIsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic0(null, 1.0, 2);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method throws AssertionError if {@code NUMPOINTS < 2}
     */
    @Test
    void lic0_throwErrorWhenNUMPOINTSIsLessThan2() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic0(pts, 1.0, pts.length);
        });
        assertEquals("'NUMPOINTS' must be >= 2", error.getMessage());
    }

    /**
     * The method throws AssertionError if {@code NUMPOINTS != points.length}
     */
    @Test
    void lic0_throwErrorWhenNUMPOINTSNotSameAsNumOfPoints() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(10, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic0(pts, 1.0, 5);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method throws AssertionError if {@code LENGTH1 < 0}
     */
    @Test
    void lic0_throwErrorWhenLENGTH1IsLessThan0() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(10, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic0(pts, -1, pts.length);
        });
        assertEquals("'LENGHT1' must be >= 0", error.getMessage());
    }

    /**
     * The method returns {@code true} if the distance between two consecutive points
     * is strictly greater than {@code LENGTH1}
     */
    @Test
    void lic0_distanceStrictlyGreaterThanLength1_true() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(2, 0)};
        assertTrue(cmv.lic0(pts, 1.0, 2)); // dist=2 > 1
    }

    /**
     * The method returns {@code false} if the distance between two consecutive points
     * is equal to {@code LENGTH1}
     */
    @Test
    void lic0_distanceEqualToLength1_false() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(2, 0)};
        assertFalse(cmv.lic0(pts, 2.0, 2)); // dist=2 NOT > 2
    }

    /**
     * Given 3 consecutive points that form a triangle with circumradius larger than RADIUS1
     * The function returns true.
     */
    @Test
    void lic1_returnsTrue_whenRadiusBiggerThanRADIUS1() {
        CMV cmv = new CMV();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(10, 0);
        Point p4 = new Point(0, 10);
        Point[] points = {p1, p2, p3, p4};
        assertTrue(cmv.lic1(points, 4, 5));
    }

    /**
     * No 3 consecutive points form a triangle with circumradius larger than RADIUS1
     * The function returns false.
     */
    @Test
    void lic1_returnsFalse_whenRadiusSmallerThanRADIUS1() {
        CMV cmv = new CMV();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(10, 0);
        Point p4 = new Point(0, 10);
        Point[] points = {p1, p2, p3, p4};
        assertFalse(cmv.lic1(points, 4, 50));
    }

    /**
     * The method requires 'RADIUS1' to be a positive number.
     * If RADIUS1 < 0, the method throws AssertionError
     * with message "'RADIUS1' must be >= 0".
     */
    @Test
    void lic1_throwsErrorWhenRADIUS1LessThan0() {
        CMV cmv = new CMV();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point p3 = new Point(0, 10);
        Point[] points = {p1, p2, p3};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic1(points, 3, -1);
        });
        assertEquals("'RADIUS1' must be >= 0", error.getMessage());
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic1_throwsError_whenPointsIsNull() {
        CMV cmv = new CMV();
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic1(null, 3, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic1_throwsErrorWhenNumpointsLessThan3() {
        CMV cmv = new CMV();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point[] points = {p1, p2};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic1(points, 2, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'points.length' == 'NUMPOINTS'.
     * If 'points.length' != 'NUMPOINTS', the method throws AssertionError
     * with message "'points.length' must be == 'NUMPOINTS'".
     */
    @Test
    void lic1_throwsErrorWhenNumpointsNotSameAsNumOfPoints() {
        CMV cmv = new CMV();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 0);
        Point p3 = new Point(0, 10);
        Point[] points = {p1, p2, p3};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic1(points, 4, 1);
        });
        assertEquals("'points.length' must be == 'NUMPOINTS'", error.getMessage());
    }

    /**
     * Given three points where the angle between them is less than PI − EPSILON (180° − EPSILON),
     * the method returns true.
     * Angles greater than PI + EPSILON also return true, since the method evaluates the smaller of the two angles.
     */
    @Test
    void lic2_returnsTrue_whenAngleIsLessThanPiMinusEpsilon() {
        CMV cmv = new CMV();
        Point[] points90degrees = {new Point(0, 1), new Point(0, 0), new Point(1, 0)};

        assertTrue(cmv.lic2(points90degrees, 3, 3.1415926535, 0.000001)); // Points, NUMPOINTS, PI, EPSILON
    }
    /**
     * Given three point where two collide, the method will return false.
     */
    @Test
    void lic2_returnsFalse_whenPointsCollide() {
        CMV cmv = new CMV();
        Point[] collidingPoints = {new Point(0, 1), new Point(0, 0), new Point(0, 0)};

        assertFalse(cmv.lic2(collidingPoints, 3, 3.1415926535, 0.000001)); // Points, NUMPOINTS, PI, EPSILON
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic2_throwsError_whenPointsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(null, 3, 3.1415926535, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic2_throwsError_whenNUMPOINTSLessThan3() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 2, 3.1415926535, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' == 'points.length'.
     * If NUMPOINTS != 'points.length', the method throws AssertionError
     * with message "'NUMPOINTS' must equal points.length".
     */
    @Test
    void lic2_throwsError_whenNUMPOINTSNotEqualLength() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 4, 3.1415926535, 1);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method requires 'EPSILON' > 0.
     * If 'EPSILON' < 0, the method throws AssertionError
     * with message "'EPSILON' must be >= 0".
     */
    @Test
    void lic2_throwsError_whenEPSILONNegative() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 3, 3.1415926535, -1);
        });
        assertEquals("'EPSILON' must be >= 0", error.getMessage());
    }

    /**
     * The method requires 'EPSILON' < 'PI'.
     * If 'EPSILON' > 'PI', the method throws AssertionError
     * with message "'EPSILON' must be < PI".
     */
    @Test
    void lic2_throwsError_whenEPSILONTooLarge() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 3, 3.1415926535, 3.1415926535);
        });
        assertEquals("'EPSILON' must be < PI", error.getMessage());
    }

    /**
     * The method requires 'PI' == 3.1415926535.
     * If 'PI' != 3.1415926535, the method throws AssertionError
     * with message "'PI' must be equal to 3.1415926535".
     */
    @Test
    void lic2_throwsError_whenPIWrong() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 3, 3.14, 1);
        });
        assertEquals("'PI' must be equal to 3.1415926535", error.getMessage());
    }

    /**
     * Given three points that are on a straight line, the method should return false.
     * Since three points on a straight line does not fall outside angle < PI - EPSILON OR angle > PI + EPSILON
     */
    @Test
    void lic2_returnsFalse_whenPointsAreOnStraightLine() {
        CMV cmv = new CMV();
        Point[] straightLine = {new Point(0, 0), new Point(1, 1), new Point(2, 2)};

        assertFalse(cmv.lic2(straightLine, 3, 3.1415926535, 0.000001)); // Points, NUMPOINTS, PI, EPSILON
    }

    /**
     * Given data points that are the vertices of a triangle with area less than AREA1,
     * lic3() returns false.
     */
    @Test
    void lic3_returnsFalse_whenPointsAreaLessThanArea1() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 10), new Point(0, 0), new Point(10, 0)};
        assertFalse(cmv.lic3(points, points.length, 100.0));
    }

    /**
     * Given data points that are the vertices of a triangle with area equal to AREA1,
     * lic3() returns false.
     */
    @Test
    void lic3_returnsFalse_whenPointsAreaEqualToArea1() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 10), new Point(10, 0)};
        assertFalse(cmv.lic3(points, points.length, 50.0));
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic3_throwsError_whenNumpointsIsLessThanThree() {
        CMV cvm = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 10), new Point(10, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cvm.lic3(points, 2, 0.0);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic3_throwsError_whenPointsIsNull() {
        CMV cvm = new CMV();
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cvm.lic3(null, 3, 10.0);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'AREA1' > 0.
     * If 'AREA1' < 0, the method throws AssertionError
     * with message "'AREA1' must be >= 0".
     */
    @Test
    void lic3_throwsError_whenArea1IsLessThanZero() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 10), new Point(10, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic3(points, 3, -1.0);
        });
        assertEquals("'AREA1' must be >= 0", error.getMessage());
    }

    /**
     * Given data points that are the vertices of a triangle with area greater than AREA1,
     * where these valid data points are stored in the first three positions of the Point array,
     * lic3() returns true.
     */
    @Test
    void lic3_returnsTrue_whenFirstPointsHaveValidArea() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(10, 0),
                new Point(0, 10),
                new Point(0, 0),
                new Point(3, 6)
        };
        assertTrue(cmv.lic3(points, points.length, 49.0));
    }

    /**
     * Given data points that are the vertices of a triangle with area greater than AREA1,
     * where these valid data points are stored in the last three positions of the Point array,
     * lic3() returns true.
     */
    @Test
    void lic3_returnsTrue_whenLastPointsHaveValidArea() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(4, 2),
                new Point(20, 0),
                new Point(0, 10),
                new Point(0, 0)
        };
        assertTrue(cmv.lic3(points, points.length, 99.0));
    }

    /**
     * Given data points that are the vertices of a triangle with area greater than AREA1,
     * where AREA1 is 0.0,
     * lic3() returns true.
     */
    @Test
    void lic3_returnsTrue_whenPointsHaveValidAreaAndArea1IsZero() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(1, 0)};
        assertTrue(cmv.lic3(points, points.length, 0.0));
    }

    /**
     * Given a sequence of Q_PTS covering more than QUADS distinct quadrants then lic4 must return true.
     * Boundary conditions:
     * 1) (0,0), (0,1) is in quadrant 1.
     * 2) (-1,0) is in quadrant 2.
     * 3) (0,-1) is in quadrant 3.
     */
    @Test
    void lic4_ReturnsTrueForConsecutiveBoundaryPoints() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(0, 1),
                new Point(-1, 0), new Point(0, -1)};
        assertTrue(cmv.lic4(points, points.length, 5, 2));
    }

    /**
     * Given a sequence of Q_PTS covering more than QUADS distinct quadrants then lic4 must return true.
     * In this test, Q_PTS = 4 and QUADS = 3, meaning four consecutive points should all be lying in distinct quadrants.
     * In each outer for-loop iteration the hashSet is cleared.
     * The four elements of the first iteration cover only three quadrants, and similarly for the second iteration covers only three quadrants.
     * Combined they cover four quadrants.
     * Since the hashSet is cleared between iterations then lic4 must return false in this test.
     */
    @Test
    void lic4_ReturnsFalseWhenSetIsClearedWhenItWouldHaveReturnedTrueOtherwise() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, 1), new Point(-1, -1), new Point(-2, 1),
                new Point(1, -1)};
        assertFalse(cmv.lic4(points, points.length, 4, 3));
    }

    /**
     * Points (0,0) and (1,1) belong to the first quadrant, and can thus not cover more than 1 quadrant.
     * Since Q_PTS = 2 and QUADS = 1 in this test, then lic4 must return false.
     */
    @Test
    void lic4_ReturnsFalseWhenAllPointsInOneQuad() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 1)};
        assertFalse(cmv.lic4(points, points.length, 2, 1));
    }

    /**
     * With Q_PTS = 2 and QUADS = 1 lic4 will return true iff there exists two consecutive points that cover more than
     * 1 quadrant.
     * In this test, the two points belong to two distinct quadrants and lie consecutively, therefore lic4 must return true.
     */
    @Test
    void lic4_ReturnsTrueWhenTwoConsecutiveSpreadInTwoDistinctQuads() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, -1)};
        assertTrue(cmv.lic4(points, points.length, 2, 1));
    }

    /**
     * With Q_PTS = 3 and QUADS = 2 lic4 will return true iff there exists three consecutive points that cover more than
     * 2 quadrant.
     * In this test, the three first points belong to three distinct quadrants and lie consecutively, therefore lic4 must return true.
     */
    @Test
    void lic4_ReturnsTrueForConsecutivelyPlacedCorrectElementsAreFirstInPointsArray() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, -1), new Point(-1, 0), new Point(-1, 1)};
        assertTrue(cmv.lic4(points, points.length, 3, 2));
    }

    /**
     * With Q_PTS = 3 and QUADS = 2 lic4 will return true iff there exists two consecutive points that cover more than 2 quadrant.
     * In this test, the last three points cover three distinct quadrants, so lic4 must return true.
     */
    @Test
    void lic4_ReturnsTrueForConsecutivelyPlacedCorrectElementsAreLastInPointsArray() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 15), new Point(-1, 0), new Point(-1, -1)};
        assertTrue(cmv.lic4(points, points.length, 3, 2));
    }

    /**
     * With Q_PTS = 3, QUADS = 2 lic4 will return true iff there exists three consecutive points that cover more than 2 distinct quadrants.
     * In this test, no Q_PTS consecutive points of three cover more than two quadrants so lic 4 must return false.
     */
    @Test
    void lic4_returnsFalseWhenNotSpreadInDistinctQuadsButNotConsecutively() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, 15), new Point(1, 1), new Point(11, 10), new Point(10, 10),
                new Point(0, -10), new Point(1, 15)};
        assertFalse(cmv.lic4(points, points.length, 3, 2));
    }

    /**
     * Lic 4 requires that no points are null.
     * If points == null, the method throws an AssertionError with message "'points' must not be null".
     * In this test, points is given to lic4 as null so lic4 must throw an AssertionError with the expected message.
     */
    @Test
    void lic4TestHandlesThrowsNullCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(null, points.length, 1, 1)
        );
        assertTrue(err.getMessage().contains("'points' must not be null"));
    }

    /**
     * Lic 4 requires that Q_PTS must be >= 2.
     * If Q_PTS < 2, the method throws an AssertionError with message "'Q_PTS' must be >= 2".
     * In this test, Q_PTS is given with value 1, so lic4 must throw an AssertionError with the expected message..
     */
    @Test
    void lic4TestHandlesThrowsQ_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 1, 0)
        );
        assertTrue(err.getMessage().contains("'Q_PTS' must be >= 2"));
    }

    /**
     * Lic 4 requires that Q_PTS must be <= NUMPOINTS.
     * If Q_PTS > NUMPOINTS, the method throws an AssertionError with message "'Q_PTS' must be <= NUMPOINTS".
     * In this test, the length of points is 4 so NUMPOINTS = 4, but Q_PTS = 5 so lic4 must throw an AssertionError with the expected message.
     */
    @Test
    void lic4TestHandlesThrowsQ_PTSGreaterThanNumpointsCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 5, 1)
        );
        assertTrue(err.getMessage().contains("'Q_PTS' must be <= NUMPOINTS"));
    }

    /**
     * Lic 4 requires that QUADS must be >= 1.
     * If QUADs < 1, the method throws an AssertionError with message "'QUADS' must be >= 1".
     * In this test, QUADS is given as 0, so lic4 must throw an AssertionError with the expected message.
     */
    @Test
    void lic4TestHandlesThrowsQuadsLesserThanOneCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1),};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 3, 0)
        );
        assertTrue(err.getMessage().contains("'QUADS' must be >= 1"));
    }

    /**
     * Lic 4 requires that QUADS must be <= 3.
     * If QUADS > 3, the method throws an AssertionError with message "'QUADS' must be <= 3".
     * In this test, QUADS is given as 4, so lic4 must throw an AssertionError with the expected message.
     */
    @Test
    void lic4TestHandlesThrowsQuadsGreaterEqThanFourPICorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 5, 4)
        );
        assertTrue(err.getMessage().contains("'QUADS' must be <= 3"));
    }

    /**
     * Lic 4 requires that Q_PTS > QUADS.
     * If Q_PTS <= QUADS, the method throws an AssertionError with message "'Q_PTS' must be > QUADS".
     * In this test, Q_PTS = QUADS, so lic4 must throw an AssertionError with the expected message.
     */
    @Test
    void lic4TestHandlesThrowsQ_PTSLesserThanQuadsFourPICorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 2, 2)
        );
        assertTrue(err.getMessage().contains("'Q_PTS' must be > QUADS"));
    }

    /**
     * The method should throw AssertionError if {@code points} is {@code null}
     */
    @Test
    void lic5_throwErrorWhenPointsIsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic5(null, 2);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method should throw AssertionError if {@code NUMPOINTS} is less than 2
     */
    @Test
    void lic5_throwErrorWhenNUMPOINTSLessThan2() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(-1, 0)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic5(pts, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 2", error.getMessage());
    }

    /**
     * The method should throw AssertionError if {@code NUMPOINTS != points.length}
     */
    @Test
    void lic5_throwErrorWhenNUMPOINTSNotSameAsNumOfPoints() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(-1, 0)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic5(pts, 4);
        });
        assertEquals("'points.length' must be equal 'NUMPOINTS'", error.getMessage());
    }

    /**
     * The method should return {@code true} if there exists a pair of consecutive points
     *         such that {@code x[i+1] - x[i] < 0}
     */
    @Test
    void lic5_decreasingX_true() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(2, 0), new Point(1, 0)};
        assertTrue(cmv.lic5(pts, 2)); // 1 - 2 < 0
    }

    /**
     * The method should return {@code false} if there is only a pair of consecutive points
     *         such that {@code x[i+1] - x[i] = 0}
     */
    @Test
    void lic5_equalX_false() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(2, 0), new Point(2, 5)};
        assertFalse(cmv.lic5(pts, 2)); // 2 - 2 = 0 NOT < 0
    }

    /**
     * The method should return {@code true} if there exists a pair of consecutive points
     *         such that {@code x[i+1] - x[i] < 0}
     */
    @Test
    void lic5_increaseThenDecrease_true() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(1, 0), new Point(0.5, 0)};
        assertTrue(cmv.lic5(pts, 3)); // 0.5 - 1 < 0
    }

    /**
     * Given N_PTS, the first and last of which form a line or a point
     * returns true if atleast one point in between is further away from the line than DIST
     * the method returns true for the last 3 elements
     */
    @Test
    void lic6_returnsTrueForLastThreeElements() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };

        assertTrue(cmv.lic6(points, points.length, 3, 1));
    }

    /**
     * Given N_PTS, the first and last of which form a line or a point
     * returns true if atleast one point in between is further away from the line than DIST
     * the method returns false since no point is further away than DIST
     */
    @Test
    void lic6_returnsFalseForNoPointFurtherAwayThanDist() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 0),
                new Point(0, 3)
        };

        assertFalse(cmv.lic6(points, points.length, 3, 2.0));
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic6_throwErrorWhenPointsIsNULL() {
        CMV cmv = new CMV();
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic6(null, 3, 5, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic6_throwErrorWhenNUMPOINTSLessThan3() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic6(points, points.length, 2, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' == 'points.length'.
     * If NUMPOINTS != 'points.length', the method throws AssertionError
     * with message "'NUMPOINTS' must equal points.length".
     */
    @Test
    void lic6_throwErrorWhenNUMPOINTSNotSameAsNumOfPoints() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic6(points, 5, 3, 1);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method requires 'N_PTS' >= 3.
     * If N_PTS < 3, the method throws AssertionError
     * with message "'N_PTS' must be >= 3".
     */
    @Test
    void lic6_throwErrorWhenN_PTSIsLessThan3() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic6(points, points.length, 2, 1);
        });
        assertEquals("'N_PTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'N_PTS' <= 'NUMPOINTS'.
     * If 'N_PTS' != 'NUMPOINTS', the method throws AssertionError
     * with message "'N_PTS' must be <= 'NUMPOINTS'".
     */
    @Test
    void lic6_throwErrorWhenN_PTSIsLargerThanNUMPOINTS() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic6(points, points.length, 5, 1);
        });
        assertEquals("'N_PTS' must be <= 'NUMPOINTS'", error.getMessage());
    }

    /**
     * The method requires 'DIST' >= 0.
     * If DIST < 0, the method throws AssertionError
     * with message "'DIST' must be >= 0".
     */
    @Test
    void lic6_throwErrorWhenDistIsNegative() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic6(points, points.length, 3, -1);
        });
        assertEquals("'DIST' must be >= 0", error.getMessage());
    }

    /**
     * Given and input of points, with two point with K_PTS points in between, with a distance to eachother greater than LENGTH1 apart
     * the method will return true. In this test index 0 and index 2 have 1 points between them (K_PTS), and their distance to eachother is more than 3 (LENGTH1)
     */
    @Test
    void lic7_returnTrue_WhenTwoPointsSepByK_PTSareMoreThanLENGTH1apart() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5)};

        assertTrue(cmv.lic7(points, 3, 3, 1)); // POINTS, NUMPOINTS, LENGHT1, K_PTS
    }

    /**
     * Given and input of points, with two point with K_PTS points in between, with a distance to eachother exactly LENGTH1
     * the method will return false. In this test index 0 and index 2 have 1 points between them (K_PTS), and their distance to eachother is exactly 3.
     * For method to return true distance > 3.
     */
    @Test
    void lic7_returnFalse_WhenTwoPointsSepByK_PTSareLENGTH1apart() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(3, 0)};

        assertFalse(cmv.lic7(points, 3, 3, 1)); // POINTS, NUMPOINTS, LENGHT1, K_PTS
    }

    /**
     * Given and input of points, with two point with K_PTS points in between, with a distance to eachother smaller than LENGTH1
     * the method will return false. In this test index 0 and index 2 have 1 points between them (K_PTS), and their distance to eachother is less than 3.
     * For method to return true distance > 3.
     */
    @Test
    void lic7_returnFalse_WhenTwoPointsSepByK_PTSareLessThanLENGTH1apart() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        assertFalse(cmv.lic7(points, 3, 3, 1)); // POINTS, NUMPOINTS, LENGHT1, K_PTS
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic7_throwsError_whenPointsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic7(null, 3, 3, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic7_throwsError_whenNUMPOINTSLessThan3() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic7(points, 2, 3, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' == 'points.length'.
     * If NUMPOINTS != 'points.length', the method throws AssertionError
     * with message "'NUMPOINTS' must equal points.length".
     */
    @Test
    void lic7_throwsError_whenNUMPOINTSNotEqualLength() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(1, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic7(points, 4, 3, 1);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method requires 'K_PTS' <= 'NUMPOINTS' - 2.
     * If K_PTS > 'NUMPOINTS' - 2, the method throws AssertionError
     * with message "'K_PTS' must be <= NUMPOINTS - 2".
     */
    @Test
    void lic7_throwsError_whenKPTSTooLarge() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic7(points, 3, 3, 2); // NUMPOINTS - 2 = 1, so 2 is invalid
        });
        assertEquals("'K_PTS' must be <= NUMPOINTS - 2", error.getMessage());
    }

    /**
     * The method requires 'K_PTS' >= 1.
     * If K_PTS < 1, the method throws AssertionError
     * with message "'K_PTS' must be >= 1".
     */
    @Test
    void lic7_throwsError_whenKPTSisZero() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic7(points, 3, 3, 0); // NUMPOINTS - 2 = 1, so 2 is invalid
        });
        assertEquals("'K_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires 'LENGTH1' >= 0.
     * If 'LENGTH1' < 0, the method throws AssertionError
     * with message "'LENGTH1' must be >= 0".
     */
    @Test
    void lic7_throwsError_whenLenghtIsLessThan0() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic7(points, 3, -1, 1); // NUMPOINTS - 2 = 1, so 2 is invalid
        });
        assertEquals("'LENGTH1' must be >= 0", error.getMessage());
    }

    /**
     * Given a set of data points separated by exactly A_PTS and B_PTS consecutive intervening
     * points, respectively, that can be contained within a circle of radius RADIUS1,
     * lic8() returns false.
     */
    @Test
    void lic8_returnsFalse_whenPointsFitWithinRadius1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        assertFalse(cmv.lic8(points, points.length, 1, 1, 10.0));
    }

    /**
     * Given a set of data points separated by exactly A_PTS and B_PTS consecutive intervening
     * points, respectively, that can be contained on a circle of radius RADIUS1,
     * lic8() returns false.
     */
    @Test
    void lic8_returnsFalse_whenPointsFitOnRadius1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(2, 0),
                new Point(4, 0),
                new Point(6, 0),
                new Point(8, 0)
        };
        assertFalse(cmv.lic8(points, points.length, 1, 1, 4.0));
    }

    /**
     * The method requires 'NUMPOINTS' >= 5.
     * If NUMPOINTS < 5, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 5".
     */
    @Test
    void lic8_throwsError_whenNUMPOINTSLessThan5() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic8(points, 4, 1, 1, 1.0);
        });
        assertEquals("'NUMPOINTS' must be >= 5", error.getMessage());
    }

    /**
     * The method requires 'A_PTS' >= 1.
     * If A_PTS < 1, the method throws AssertionError
     * with message "'A_PTS' must be >= 1".
     */
    @Test
    void lic8_throwsError_whenAPTSLessThan1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic8(points, points.length, 0, 1, 1.0);
        });
        assertEquals("'A_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires 'B_PTS' >= 1.
     * If B_PTS < 1, the method throws AssertionError
     * with message "'B_PTS' must be >= 1".
     */
    @Test
    void lic8_throwsError_whenBPTSLessThan1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic8(points, points.length, 1, 0, 1.0);
        });
        assertEquals("'B_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires A_PTS + B_PTS <= NUMPOINTS - 3.
     * If A_PTS + B_PTS > NUMPOINTS - 3, the method throws AssertionError
     * with message "A_PTS + B_PTS must be <= NUMPOINTS - 3".
     */
    @Test
    void lic8_throwsError_whenSeparationTooLarge() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic8(points, points.length, 1, 2, 1.0);
        });
        assertEquals("A_PTS + B_PTS must be <= NUMPOINTS - 3", error.getMessage());
    }

    /**
     * The method requires RADIUS1 >= 0.
     * If RADIUS1 < 0, the method throws AssertionError
     * with message "'RADIUS1' must be >= 0".
     */
    @Test
    void lic8_throwsError_whenRADIUS1LessThan0() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic8(points, points.length, 1, 1, -1.0);
        });
        assertEquals("'RADIUS1' must be >= 0", error.getMessage());
    }

    /**
     * Given a set of collinear data points separated by exactly
     * A_PTS and B_PTS consecutive intervening points, respectively,
     * that cannot be contained within a circle of radius RADIUS1,
     * lic8() returns true.
     */
    @Test
    void lic8_returnsTrue_whenPointsDoNotFitWithinRadius1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        assertTrue(cmv.lic8(points, points.length, 1, 1, 1.0));
    }

    /**
     * Given a set of non-collinear data points separated by exactly
     * A_PTS and B_PTS consecutive intervening points, respectively,
     * that cannot be contained within a circle of radius RADIUS1,
     * lic8() returns true.
     */
    @Test
    void lic8_returnsTrue_whenNonCollinearPointsDoNotFitWithinRadius1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 1),
                new Point(2, 0),
                new Point(3, 0)
        };
        assertTrue(cmv.lic8(points, points.length, 1, 1, 1.0));
    }

    /**
     * Given valid inputs, lic9 returns true iff there exists at least one set of three points
     * where first and second point are separated by C_PTS consecutive intervening points,
     * and the second and third point are separated by D_PTS consecutive intervening points
     * where the three points form an angle where angle < (PI - EPSILON) or angle > (PI + EPSILON)
     *
     * In this test, the angle is calculated to 90 degrees = 1.5708 radians, PI = 3.1415926535 and EPSILON = 0.000001.
     * And since 1.5708 is less than (3.1415926535 - 0.000001), lic 9 must return true.
     */
    @Test
    void lic9ReturnsTrueForMinimumCaseWherePointsAreNinetyDegrees() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(0, 1);
        Point thirdPoint = new Point(1, 0);
        Point[] degreeVertices90 = {firstPoint,
                new Point(2, 0),
                secondPoint,
                new Point(3, 0),
                thirdPoint};
        assertTrue(cmv.lic9(degreeVertices90, 5, 1, 1, 3.1415926535, 0.000001));
    }

    /**
     * Given valid inputs, lic9 returns true iff there exists at least one set of three points
     * where first and second point are separated by C_PTS consecutive intervening points,
     * and the second and third point are separated by D_PTS consecutive intervening points
     * where the three points form an angle where angle < (PI - EPSILON) or angle > (PI + EPSILON)
     *
     * In this test, the angle is calculated to 90 degrees = 1.5708 radians, PI = 3.1415926535 and EPSILON = 1.80.
     * And since 1.5708 is not less than (3.1415926535 - 1.80), lic 9 must return false.
     */
    @Test
    void lic9ReturnsFalseForMinimumCaseWhereAgnleIsNinetyDegreesAndEpsilonIs100Degrees() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(0, 1);
        Point thirdPoint = new Point(1, 0);
        Point[] degreeVertices90 = {secondPoint,
                new Point(2, 0),
                firstPoint,
                new Point(3, 0),
                thirdPoint};
        assertFalse(cmv.lic9(degreeVertices90, 5, 1, 1, 3.1415926535, 1.80));
    }

    /**
     * Given valid inputs, lic9 returns true iff there exists at least one set of three points
     * where first and second point are separated by C_PTS consecutive intervening points,
     * and the second and third point are separated by D_PTS consecutive intervening points
     * where the three points form an angle where angle < (PI - EPSILON) or angle > (PI + EPSILON)
     *
     * In this test, the angle is calculated to 3.141592653589793 = 180.00000000044650506 degrees.
     * PI = 3.1415926535 and EPSILON = 0.0174533.
     * And since 180.00000000044650506 is not less than (179.999999995302 - 0.0174533), lic 9 must return false.
     */
    @Test
    void lic9ReturnsFalseForOppositeVectorsWith180DegreesAngleAndEpsilonIs1Degree() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 1);
        Point secondPoint = new Point(0, 0);
        Point thirdPoint = new Point(0, -1);
        Point[] degreeVertices90 = {firstPoint,
                new Point(2, 0),
                secondPoint,
                new Point(123, 0),
                thirdPoint};
        assertFalse(cmv.lic9(degreeVertices90, degreeVertices90.length, 1, 1, 3.1415926535, 0.0174533));
    }

    /**
     * Given valid inputs, lic9 returns true iff there exists at least one set of three points
     * where first and second point are separated by C_PTS consecutive intervening points,
     * and the second and third point are separated by D_PTS consecutive intervening points
     * where the three points form an angle where angle < (PI - EPSILON) or angle > (PI + EPSILON)
     *
     * In this test, the angle is calculated to 141592653589793 = 179.42706130276246768 degrees.
     * PI = 3.1415926535 and EPSILON = 0.008000.
     * And since 179.42706130276246768 is less than (179.999999995302 - 0.008000), lic 9 must return true.
     */
    @Test
    void lic9ReturnsTrueForOppositeVectorsWith179DegreesAngleAndEpsilonIsExtremelySmall() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 1);
        Point secondPoint = new Point(0, 0);
        Point thirdPoint = new Point(0.01, -1);
        Point[] degreeVertices90 = {firstPoint,
                new Point(2, 0),
                secondPoint,
                new Point(123, 0),
                thirdPoint};
        assertTrue(cmv.lic9(degreeVertices90, degreeVertices90.length, 1, 1, 3.1415926535, 0.008000));
    }

    /**
     * Lic 9 requires that no points are null.
     * If points == null, the method throws an AssertionError with message "'points' must not be null".
     * In this test, points is given to lic 9 as null so lic 9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsNullCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(null, points.length, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'points' must not be null"));
    }

    /**
     * Lic 9 requires that NUMPOINTS must be >= 5.
     * If NUMPOINTS < 5, the method throws an AssertionError with message "'NUMPOINTS' must be >= 5".
     * In this test, Q_PTS is given with value 4, so lic9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsNUMPOINTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, 4, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'NUMPOINTS' must be >= 5"));
    }

    /**
     * Lic 9 requires that C_PTS must be >= 1.
     * If C_PTS < 1, the method throws an AssertionError with message "'C_PTS' must be >= 1".
     * In this test, C_PTS is given with value 0, so lic9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsC_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 0, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'C_PTS' must be >= 1"));
    }

    /**
     * Lic 9 requires that D_PTS must be >= 1.
     * If D_PTS < 1, the method throws an AssertionError with message "'D_PTS' must be >= 1".
     * In this test, D_PTS is given with value 0, so lic9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsD_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 1, 0, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'D_PTS' must be >= 1"));
    }

    /**
     * Lic 9 requires that C_PTS + D_PTS must be <= NUMPOINTS - 3.
     * If C_PTS + D_PTS must be <= NUMPOINTS - 3, the method throws an AssertionError with message "C_PTS + D_PTS must be <= NUMPOINTS - 3".
     * In this test, C_PTS = 5, D_PTS = 1 and NUMPOINTS = 8 thus 5 + 1 > 8-3 so lic9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsIndexBoundaryCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 5, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("C_PTS + D_PTS must be <= NUMPOINTS - 3"));
    }

    /**
     * Lic 9 requires that EPSILON must be >= 0.
     * If EPSILON < 0, the method throws an AssertionError with message "'EPSILON' must be >= 0".
     * In this test, EPSILON = -1 so lic9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsEpsilonLesserThanOneCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 1, 1, 20, -1)
        );
        assertTrue(err.getMessage().contains("'EPSILON' must be >= 0"));
    }

    /**
     * Lic 9 requires that EPSILON must be < PI.
     * If EPSILON < PI, the method throws an AssertionError with message "'EPSILON' must be lesser than 'PI'".
     * In this test, EPSILON = 0.6 and PI = -1 so lic9 must throw an AssertionError with the expected message.
     */
    @Test
    void lic9TestHandlesThrowsEpsilonGreaterEqThanPICorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 1, 1, -1, 0.6)
        );
        assertTrue(err.getMessage().contains("'EPSILON' must be lesser than 'PI'"));
    }

    /**
     * The method should throw AssertionError if {@code points == null}
     */

    @Test
    void lic10_throwErrorWhenPointsIsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(null, 1, 1, 1.0, 5);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method should throw AssertionError if @code NUMPOINTS is less than 5}
     */
    @Test
    void lic10_throwErrorWhenNUMPOINTSLessThan5() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(pts, 1, 1, 0.1, 4);
        });
        assertEquals("'NUMPOINTS' must be >= 5", error.getMessage());
    }

    /**
     * The method should throw AssertionError if @code NUMPOINTS != points.length}
     */
    @Test
    void lic10_throwErrorWhenNUMPOINTSNotSameAsNumOfPoints() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(pts, 1, 1, 0.1, 6);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method should throw AssertionError if {@code E_PTS} is less than 1
     */
    @Test
    void lic10_throwErrorWhenE_PTSLessThan1() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(pts, 0, 1, 0.1, 5);
        });
        assertEquals("'E_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method should throw AssertionError if {@code F_PTS} is less than 1
     */
    @Test
    void lic10_throwErrorWhenF_PTSLessThan1() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(pts, 1, 0, 0.1, 5);
        });
        assertEquals("'F_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method should throw AssertionError if {@code E_PTS + F_PTS} is less than {@code NUMPOINT - 3}
     */
    @Test
    void lic10_throwErrorWhenE_PTSPlusF_PTSIsGreaterThanNUMPOINTSMinus3() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};
        // NUMPOINTS=5 => NUMPOINTS-3 = 2. If E+F=3 => invalid
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(pts, 1, 2, 0.1, 5);
        });
        assertEquals("E_PTS + F_PTS must be <= NUMPOINTS - 3", error.getMessage());
    }

    /**
     * The method should throw AssertionError if {@code AREA1} is less than 0
     */
    @Test
    void lic10_throwErrorWhenAREA1LessThan0() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic10(pts, 1, 1, -1, 5);
        });
        assertEquals("'AREA1' must be >= 0", error.getMessage());
    }

    /**
     * The method should allow {@code E_PTS + F_PTS = NUMPOINT - 3}
     */
    @Test
    void lic10_boundary_EplusF_equalTo_NUMPOINTSminus3_isAllowed() {
        CMV cmv = new CMV();
        // NUMPOINTS=5, E=1, F=1 => E+F=2 = NUMPOINTS-3 OK
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)};
        assertTrue(cmv.lic10(pts, 1, 1, 1.0, 5));
    }

    /**
     * The method should return {@code true} if the area formed by the 3 points is greater than {@code AREA1}
     */
    @Test
    void lic10_returnTrueWhenAreaGreaterThanAREA1() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)}; // area=2
        assertTrue(cmv.lic10(pts, 1, 1, 1.9999, 5));
    }

    /**
     * The method should return {@code true} if the area formed by the 3 points is less than {@code AREA1}
     */
    @Test
    void lic10_returnFalseWhenAreaEqualAREA1() {
        CMV cmv = new CMV();
        Point[] pts = {new Point(0, 0), new Point(0, 0), new Point(2, 0), new Point(0, 0),
                new Point(0, 2)}; // area=2
        assertFalse(cmv.lic10(pts, 1, 1, 2.0, 5)); // NOT >
    }

    /**
     * Given 2 points separated by G_PTS, returns true  if x-coordinate decreases.
     * the method returns true since the last points x-coordinate is less than the rest
     */
    @Test
    void lic11_returnsTrueForLastThreeElements() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(6, 1),
                new Point(7, 4),
                new Point(8, 3),
                new Point(9, 0),
                new Point(0, 0)
        };

        assertTrue(cmv.lic11(points, points.length, 1));
    }

    /**
     * Given 2 points separated by G_PTS, returns true  if x-coordinate decreases.
     * the method returns false since the x-coordinates are rising
     */
    @Test
    void lic11_returnsFalseForRisingXValues() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4),
                new Point(3, 3),
                new Point(4, 0),
                new Point(5, 0)
        };

        assertFalse(cmv.lic11(points, points.length, 1));
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic11_throwErrorWhenPointsIsNull() {
        CMV cmv = new CMV();
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic11(null, 3, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic11_throwErrorWhenNUMPOINTSIsLessThan3() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic11(points, points.length, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' == 'points.length'.
     * If NUMPOINTS != 'points.length', the method throws AssertionError
     * with message "'NUMPOINTS' must equal points.length".
     */
    @Test
    void lic11_throwErrorWhenNUMPOINTSNotSameAsNumOfPoints() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4),
                new Point(3, 3),
                new Point(4, 0),
                new Point(5, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic11(points, 7, 1);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method requires 'G_PTS' >= 1.
     * If 'G_PTS' < 1, the method throws AssertionError
     * with message "'G_PTS' must be >= 1".
     */
    @Test
    void lic11_throwErrorWhenG_PTSIsLessThan1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4),
                new Point(3, 3),
                new Point(4, 0),
                new Point(5, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic11(points, points.length, 0);
        });
        assertEquals("'G_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires 'G_PTS' <= NUMPOINTS - 2.
     * If 'G_PTS' > NUMPOINTS - 2, the method throws AssertionError
     * with message "'G_PTS' must be <= NUMPOINTS - 2".
     */
    @Test
    void lic11_throwErrorWhenG_PTSIsLargerThanNUMPOINTSMinusTwo() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4),
                new Point(3, 3),
                new Point(4, 0),
                new Point(5, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic11(points, points.length, 4);
        });
        assertEquals("'G_PTS' must be <= NUMPOINTS - 2", error.getMessage());
    }

    /**
     * Given points where there is a pair of point with one point in between that has a distance to each other greater than 'LENGHT1',
     * and there is a pair of points with one point in between with distance to each other of less than 'LENGHT2'
     * the method will return true.
     */
    @Test
    void lic12_returnsTrue_whenTwoSeparatePairsOfPointsK_PTSapartFitCriteria() {
        CMV cmv = new CMV();
        // points[0] and points[2] are more than 3 apart
        // points[1] and points[3] are less than 2 apart
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        assertTrue(cmv.lic12(points, 4, 3, 2, 1)); // POINTS, NUMPOINTS, LENGHT1, LENGHT2, K_PTS
    }

    /**
     * Given points where there is a pair of point with one point in between that has a distance to each other greater than 'LENGHT1',
     * but there is no pair of points with one point in between with distance to each other of less than 'LENGHT2'
     * the method will return false.
     */
    @Test
    void lic12_returnsFalse_whenOnlyOnePairFitsCriteria() {
        CMV cmv = new CMV();
        // points[0] and points[2] are more than 3 apart
        // No pair of paint with 1 element in between are less than 1 distance apart.
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        assertFalse(cmv.lic12(points, 4, 3, 1, 1)); // POINTS, NUMPOINTS, LENGHT1, LENGHT2, K_PTS
    }

    /**
     * Given points where no pair of point with one point in between has a distance to each other greater than 'LENGTH1',
     * or a distance smaller than 'LENGHT2', the method will return false.
     */
    @Test
    void lic12_returnsFalse_whenNoPairFitsCriteria() {
        CMV cmv = new CMV();
        // No pair of paint with 1 element in between are more than 10 distance apart.
        // No pair of paint with 1 element in between are less than 1 distance apart.
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        assertFalse(cmv.lic12(points, 4, 10, 1, 1)); // POINTS, NUMPOINTS, LENGHT1, LENGHT2, K_PTS
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic12_throwsError_whenPointsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(null, 4, 3, 2, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 3.
     * If NUMPOINTS < 3, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 3".
     */
    @Test
    void lic12_throwsError_whenNUMPOINTSLessThan3() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(points, 2, 3, 2, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' == 'points.length'.
     * If NUMPOINTS != 'points.length', the method throws AssertionError
     * with message "'NUMPOINTS' must equal points.length".
     */
    @Test
    void lic12_throwsError_whenNUMPOINTSNotEqualPointsLength() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(points, 3, 3, 2, 1);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    /**
     * The method requires 'K_PTS' must be >= 1.
     * If 'K_PTS' < 1, the method throws AssertionError
     * with message "'K_PTS' must be >= 1".
     */
    @Test
    void lic12_throwsError_whenKPTSLessThan1() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(points, 4, 3, 2, 0);
        });
        assertEquals("'K_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires 'K_PTS' <= NUMPOINTS - 2.
     * If 'K_PTS' > NUMPOINTS - 2, the method throws AssertionError
     * with message "'K_PTS' must be <= NUMPOINTS - 2".
     */
    @Test
    void lic12_throwsError_whenKPTSTooLarge() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(points, 4, 3, 2, 3); // K_PTS must be 2 or 1 for it to return true
        });
        assertEquals("'K_PTS' must be <= NUMPOINTS - 2", error.getMessage());
    }

    /**
     * The method requires 'LENGTH1' >= 0.
     * If 'LENGTH1' < 0, the method throws AssertionError
     * with message "'LENGTH1' must be >= 0".
     */
    @Test
    void lic12_throwsError_whenLENGTH1Negative() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(points, 4, -1, 2, 1);
        });
        assertEquals("'LENGTH1' must be >= 0", error.getMessage());
    }

    /**
     * The method requires 'LENGTH2' >= 0.
     * If 'LENGTH2' < 0, the method throws AssertionError
     * with message "'LENGTH2' must be >= 0".
     */
    @Test
    void lic12_throwsError_whenLENGTH2Negative() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1)};

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic12(points, 4, 2, -1, 1);
        });
        assertEquals("'LENGTH2' must be >= 0", error.getMessage());
    }

    /**
     * The method requires 'points' to be non-null.
     * If points == null, the method throws AssertionError
     * with message "'points' must not be null".
     */
    @Test
    void lic13_throwsError_whenPointsIsNull() {
        CMV cmv = new CMV();
        Point[] points = null;
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, 5, 1, 1, 1.0, 3.0);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    /**
     * The method requires 'NUMPOINTS' >= 5.
     * If NUMPOINTS < 5, the method throws AssertionError
     * with message "'NUMPOINTS' must be >= 5".
     */
    @Test
    void lic13_throwsError_whenNUMPOINTSLessThan5() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, 4, 1, 1, 1.0, 3.0);
        });
        assertEquals("'NUMPOINTS' must be >= 5", error.getMessage());
    }

    /**
     * The method requires 'A_PTS' >= 1.
     * If A_PTS < 1, the method throws AssertionError
     * with message "'A_PTS' must be >= 1".
     */
    @Test
    void lic13_throwsError_whenAPTSLessThan1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, points.length, 0, 1, 1.0, 3.0);
        });
        assertEquals("'A_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires 'B_PTS' >= 1.
     * If B_PTS < 1, the method throws AssertionError
     * with message "'B_PTS' must be >= 1".
     */
    @Test
    void lic13_throwsError_whenBPTSLessThan1() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, points.length, 1, 0, 1.0, 3.0);
        });
        assertEquals("'B_PTS' must be >= 1", error.getMessage());
    }

    /**
     * The method requires A_PTS + B_PTS <= NUMPOINTS - 3.
     * If A_PTS + B_PTS > NUMPOINTS - 3, the method throws AssertionError
     * with message "A_PTS + B_PTS must be <= NUMPOINTS - 3".
     */
    @Test
    void lic13_throwsError_whenSeparationTooLarge() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, points.length, 1, 2, 1.0, 3.0);
        });
        assertEquals("A_PTS + B_PTS must be <= NUMPOINTS - 3", error.getMessage());
    }

    /**
     * The method requires RADIUS1 >= 0.
     * If RADIUS1 < 0, the method throws AssertionError
     * with message "'RADIUS1' must be >= 0".
     */
    @Test
    void lic13_throwsError_whenRADIUS1LessThan0() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, points.length, 1, 1, -1.0, 3.0);
        });
        assertEquals("'RADIUS1' must be >= 0", error.getMessage());
    }

    /**
     * The method requires RADIUS2 >= 0.
     * If RADIUS2 < 0, the method throws AssertionError
     * with message "'RADIUS2' must be >= 0".
     */
    @Test
    void lic13_throwsError_whenRADIUS2LessThan0() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, points.length, 1, 1, 1.0, -1.0);
        });
        assertEquals("'RADIUS2' must be >= 0", error.getMessage());
    }

    /**
     * Given an array of data points, where:
     *    - there exists a set of three data points that cannot be contained
     *      within or on a circle of radius RADIUS1,
     *    - there does not exist a set of three data points that can be
     *      contained within or on a circle of radius RADIUS2, and
     *    - the three data points are separated by exactly A_PTS and B_PTS
     *      consecutive intervening points, respectively, in both sets,
     * lic13() returns false since no set of data points can be contained in a circle of RADIUS2.
     */
    @Test
    void lic13_returnsFalse_radius1CannotContain_radius2CannotContain() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        assertFalse(cmv.lic13(points, points.length, 1, 1, 1.0, 1.0));
    }

    /**
     * Given an array of data points, where:
     *    - there does not exist a set of three data points that cannot
     *      be contained within or on a circle of radius RADIUS1,
     *    - there exists a set of three data points that can be
     *      contained within or on a circle of radius RADIUS2, and
     *    - the three data points are separated by exactly A_PTS and B_PTS
     *      consecutive intervening points, respectively, in both sets,
     * lic13() returns false since all set of data points can be contained in a circle of RADIUS1.
     */
    @Test
    void lic13_returnsFalse_radius1CanContain_radius2CanContain() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        assertFalse(cmv.lic13(points, points.length, 1, 1, 3.0, 3.0));
    }

    /**
     * Given an array of data points, where:
     *    - there does not exist a set of three data points that cannot
     *      be contained within or on a circle of radius RADIUS1,
     *    - there does not exist a set of three data points that can be
     *      contained within or on a circle of radius RADIUS2, and
     *    - the three data points are separated by exactly A_PTS and B_PTS
     *      consecutive intervening points, respectively, in both sets,
     * lic13() returns false since all set of data points can be contained in a circle of RADIUS1
     * and no set of data points can be contained in a circle of RADIUS2.
     */
    @Test
    void lic13_returnsFalse_radius1CanContain_radius2CannotContain() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        assertFalse(cmv.lic13(points, points.length, 1, 1, 3.0, 1.0));
    }

    /**
     * Given an array of data points, where:
     *    - there exists a set of three data points that cannot
     *      be contained within or on a circle of radius RADIUS1,
     *    - there exists a set of three data points that can be
     *      contained within or on a circle of radius RADIUS2,
     *    - the same set of three data points satisfies both conditions, and
     *    - the three data points in the set are separated by exactly
     *      A_PTS and B_PTS consecutive intervening points, respectively
     * lic13() returns true since the same set of data points satisfies both conditions.
     */
    @Test
    void lic13_returnsTrue_sameTripletSatisfiesBothConditions() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(3, 0),
                new Point(4, 0)
        };
        assertTrue(cmv.lic13(points, points.length, 1, 1, 1.0, 3.0));
    }

    /**
     * Given an array of data points, where:
     *    - there exists a set of three data points that cannot
     *      be contained within or on a circle of radius RADIUS1,
     *    - there exists a set of three data points that can be
     *      contained within or on a circle of radius RADIUS2,
     *    - different sets of three data points satisfy the conditions, and
     *    - the three data points are separated by exactly A_PTS and B_PTS
     *      consecutive intervening points, respectively, in both sets,
     * lic13() returns true since the conditions are satisfied by different sets of data points.
     */
    @Test
    void lic13_returnsTrue_differentTripletsSatisfyBothConditions() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(0, 0),
                new Point(2, 0),
                new Point(100, 0),
                new Point(4, 0),
                new Point(200, 0)
        };
        assertTrue(cmv.lic13(points, points.length, 1, 1, 99.0, 2.0));
    }


    /**
     * Given valid inputs, lic 14 returns true iff there exists at least one set of three points,
     * where first and second point are separated by E_PTS consecutive intervening points,
     * and the second and third point are separated by F_PTS consecutive intervening points.
     * The points form the vertices of a triangle greater than AREA1.
     * Additionally, there exists a similar triangle but with area lesser than AREA2.
     *
     * In this test, the selected points (firstPoint, secondPoint, thirdPoint) form a triangle with area = 0.5
     * Therefore:
     * - When AREA1 = 5 and AREA2 = 10, then 0.5 > AREA1 is false so lic 9 must return false.
     * - When AREA1 = 0.4 and AREA2 = 10, then both 0.5 > AREA1 and 0.5 < AREA2 is true so lic 9 must return true.
     * - When AREA1 = 0.4 and AREA2 = 0.6, then both 0.5 > AREA1 and 0.5 < AREA2 is true so lic 9 must return true.
     * - When AREA2 = 0.4 and AREA2 = 0.6, then 0.5 < AREA2 is false so lic 9 must return false.
     */
    @Test
    void lic14TestThatAREA1AndAREA2ConditionsWorkCorrectlyWhenFirstInArray() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(1, 0);
        Point thirdPoint = new Point(0, 1);
        Point[] pts = {firstPoint,
                new Point(1, 0),
                secondPoint,
                new Point(0, 0),
                thirdPoint};
        assertFalse(cmv.lic14(pts, 5, 1, 1, 5, 10));
        assertTrue(cmv.lic14(pts, 5, 1, 1, 0.4, 10));
        assertTrue(cmv.lic14(pts, 5, 1, 1, 0.4, 0.6));
        assertFalse(cmv.lic14(pts, 5, 1, 1, 0.4, 0.4));
    }

    /**
     * Given valid inputs, lic 14 returns true iff there exists at least one set of three points,
     * where first and second point are separated by E_PTS consecutive intervening points,
     * and the second and third point are separated by F_PTS consecutive intervening points.
     * The points form the vertices of a triangle greater than AREA1.
     * Additionally, there exists a similar triangle but with area lesser than AREA2.
     *
     * In this test, the selected points (firstPoint, secondPoint, thirdPoint) form a triangle with area = 0.5
     * Therefore:
     * - When AREA1 = 0.4 and AREA2 = 10, then both 0.5 > AREA1 and 0.5 < AREA2 is true so lic 9 must return true.
     * - When AREA1 = 0.6 and AREA2 = 10, then 0.5 > AREA1 is false so lic 9 must return false.
     */
    @Test
    void lic14TestThatAREA1AndAREA2ConditionsWorkCorrectlyWhenNotFirstInArray() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(1, 0);
        Point thirdPoint = new Point(0, 1);
        Point[] points = {
                new Point(12, 12),
                firstPoint,
                new Point(12, 12),
                secondPoint,
                new Point(12, 12),
                thirdPoint};
        assertTrue(cmv.lic14(points, points.length, 1, 1, 0.4, 10));
        assertFalse(cmv.lic14(points, points.length, 1, 1, 0.6, 10));
    }

    /**
     * Given valid inputs, lic 14 returns true iff there exists at least one set of three points,
     * where first and second point are separated by E_PTS consecutive intervening points,
     * and the second and third point are separated by F_PTS consecutive intervening points.
     * The points form the vertices of a triangle greater than AREA1.
     * Additionally, there exists a similar triangle but with area lesser than AREA2.
     *
     * In this test:
     * - The selected points (firstPoint, secondPoint, thirdPoint) form a triangle with area = 0.5
     * - The selected points (firstPoint2, secondPoint2, thirdPoint2) form a triangle with area = 25
     * They are placed pairwise such that with E_PTS = 1 and F_PTS = 1 the only possible configurations are from
     * the triplets (firstPoint, secondPoint, thirdPoint) and (firstPoint2, secondPoint2, thirdPoint2).
     * Therefore:
     * - When AREA1 = 20 and AREA2 = 0.6, then 25 > AREA1 and 0.5 < AREA2 is true so lic 9 must return true.
     * - When AREA1 = 25 and AREA2 = 0.6, then both 0.5 > AREA1 and 25 > AREA1 is false so lic 9 must return false.
     * - When AREA1 = 20 and AREA2 = 0.5, then both 0.5 < AREA2 and 25 < AREA2 is false so lic 9 must return false.
     */
    @Test
    void lic14TestThatAREA1AndAREA2ConditionsWorkCorrectlyWhenDifferentTriples() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(1, 0);
        Point thirdPoint = new Point(0, 1);

        //Area=25
        Point firstPoint2 = new Point(10, 10);
        Point secondPoint2 = new Point(20, 10);
        Point thirdPoint2 = new Point(5, 5);

        Point[] points = {
                firstPoint2,
                firstPoint,
                secondPoint2,
                secondPoint,
                thirdPoint2,
                thirdPoint};
        assertTrue(cmv.lic14(points, points.length, 1, 1, 20, 0.6));

        assertFalse(cmv.lic14(points, points.length, 1, 1, 25, 0.6)); //Boundary condition, fails because 25>25 returns false
        assertFalse(cmv.lic14(points, points.length, 1, 1, 20, 0.5)); //Boundary condition, fails because 0.5<0.5 returns false
    }

    /**
     * Lic 14 requires that no points are null.
     * If points == null, the method throws an AssertionError with message "'points' must not be null".
     * In this test, points is given to lic 14 as null so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsNullCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(null, points.length, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'points' must not be null"));
    }

    /**
     * Lic 14 requires that NUMPOINTS must be >= 5.
     * If NUMPOINTS < 5, the method throws an AssertionError with message "'NUMPOINTS' must be >= 5".
     * In this test, NUMPOINTS is given with value 4, so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsNUMPOINTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, 4, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'NUMPOINTS' must be >= 5"));
    }

    /**
     * Lic 14 requires that E_PTS + F_PTS must be <= NUMPOINTS - 3.
     * If E_PTS + F_PTS must be <= NUMPOINTS - 3, the method throws an AssertionError with message "E_PTS + F_PTS must be <= NUMPOINTS - 3".
     * In this test, E_PTS = 5, F_PTS = 1 and NUMPOINTS = 8 thus 5 + 1 > 8-3 so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsIndexBoundaryCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 5, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("E_PTS + F_PTS must be <= NUMPOINTS - 3"));
    }

    /**
     * Lic 14 requires that E_PTS must be >= 1.
     * If E_PTS < 1, the method throws an AssertionError with message "'E_PTS' must be >= 1".
     * In this test, E_PTS is given with value 0, so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsE_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 0, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'E_PTS' must be >= 1"));
    }

    /**
     * Lic 14 requires that F_PTS must be >= 1.
     * If F_PTS < 1, the method throws an AssertionError with message "'F_PTS' must be >= 1".
     * In this test, F_PTS is given with value 0, so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsF_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 1, 0, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'F_PTS' must be >= 1"));
    }

    /**
     * Lic 14 requires that AREA1 must be >= 0.
     * If AREA1 < 0, the method throws an AssertionError with message "'AREA1' must be >= 0".
     * In this test, AREA1 is given with value -1, so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsAREA1Correctly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 1, 1, -1, 0.6)
        );
        assertTrue(err.getMessage().contains("'AREA1' must be >= 0"));
    }

    /**
     * Lic 14 requires that AREA2 must be >= 0.
     * If AREA2 < 0, the method throws an AssertionError with message "'AREA2' must be >= 0".
     * In this test, AREA2 is given with value -1, so lic 14 must throw an AssertionError with the expected message.
     */
    @Test
    void lic14TestHandlesThrowsAREA2Correctly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 1), new Point(0, 1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 1, 1, 20, -1)
        );
        assertTrue(err.getMessage().contains("'AREA2' must be >= 0"));
    }

    @Test
    void lic15() {
    }

    @Test
    void verifyAllLics() {
    }
}
