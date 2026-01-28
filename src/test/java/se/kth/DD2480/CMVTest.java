package se.kth.DD2480;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CMVTest {
    CMV cmv;

    @BeforeEach
    void setUp() {
        cmv = new CMV();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void lic0_nullPoints_returnsFalse() {
        CMV cmv = new CMV();
        assertFalse(cmv.lic0(null, 1.0, 2));
    }

    @Test
    void lic0_NUMPOINTS_lessThan2_returnsFalse() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0, 0), new Point(10, 0) };
        assertFalse(cmv.lic0(pts, 1.0, 0));
        assertFalse(cmv.lic0(pts, 1.0, 1));
    }

    @Test
    void lic0_distanceStrictlyGreaterThanLength1_true() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0, 0), new Point(2, 0) };
        assertTrue(cmv.lic0(pts, 1.0, 2)); // dist=2 > 1
    }

    @Test
    void lic0_distanceEqualToLength1_false() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0, 0), new Point(2, 0) };
        assertFalse(cmv.lic0(pts, 2.0, 2)); // dist=2 NOT > 2
    }


    void lic1_anyinput() {
        CMV cmv = new CMV();
        Point p1 = new Point(0,0);
        Point p2 = new Point(10,0);
        Point[] points = {p1, p2};
        assertFalse(cmv.lic1(points, 2, 1));
    }

    @Test
    void lic1_returnsTrue_whenRadiusBiggerThanRADIUS1() {
        CMV cmv = new CMV();
        Point p1 = new Point(0,0);
        Point p2 = new Point(1,0);
        Point p3 = new Point(10,0);
        Point p4 = new Point(0,10);
        Point[] points = {p1, p2, p3, p4};
        assertTrue(cmv.lic1(points, 4, 5));
    }



    @Test
    void lic1_invalid_input() {
        CMV cmv = new CMV();
        Point p1 = new Point(0,0);
        Point p2 = new Point(10,0);
        Point p3 = new Point(0,10);
        Point[] points = {p1, p2, p3};
        assertFalse(cmv.lic1(points, 3, -1));
    }

    @Test
    void lic2_returnsTrue_whenAngleIsLessThanPiMinusEpsilon() {
        CMV cmv = new CMV();
        Point[] points90degrees = {new Point(0, 1), new Point(0, 0), new Point(1, 0)};

        assertTrue(cmv.lic2(points90degrees, 3, 3.1415926535, 0.000001)); // Points, NUMPOINTS, PI, EPSILON
    }

    @Test
    void lic2_returnsFalse_whenPointsCollide() {
        CMV cmv = new CMV();
        Point[] collidingPoints = {new Point(0, 1), new Point(0, 0), new Point(0, 0)};

        assertFalse(cmv.lic2(collidingPoints, 3, 3.1415926535, 0.000001)); // Points, NUMPOINTS, PI, EPSILON
    }

    @Test
    void lic2_throwsError_whenPointsNull() {
        CMV cmv = new CMV();

        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(null, 3, 3.1415926535, 1);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    @Test
    void lic2_throwsError_whenNUMPOINTSLessThan3() {
        CMV cmv = new CMV();
        Point[] points  = {
                new Point(0,1),
                new Point(0,0),
                new Point(1,0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 2, 3.1415926535, 1);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    @Test
    void lic2_throwsError_whenNUMPOINTSNotEqualLength() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0,1),
                new Point(0,0),
                new Point(1,0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 4, 3.1415926535, 1);
        });
        assertEquals("'NUMPOINTS' must equal points.length", error.getMessage());
    }

    @Test
    void lic2_throwsError_whenEPSILONNegative() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0,1),
                new Point(0,0),
                new Point(1,0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 3, 3.1415926535, -1);
        });
        assertEquals("'EPSILON' must be >= 0", error.getMessage());
    }

    @Test
    void lic2_throwsError_whenEPSILONTooLarge() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0,1),
                new Point(0,0),
                new Point(1,0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 3, 3.1415926535, 3.1415926535);
        });
        assertEquals("'EPSILON' must be < PI", error.getMessage());
    }

    @Test
    void lic2_throwsError_whenPIWrong() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0,1),
                new Point(0,0),
                new Point(1,0)
        };
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic2(points, 3, 3.14, 1);
        });
        assertEquals("'PI' must be equal to 3.1415926535", error.getMessage());
    }

    @Test
    void lic2_returnsFalse_whenPointsAreOnStraightLine() {
        CMV cmv = new CMV();
        Point[] straightLine = {new Point(0, 0), new Point(1, 1), new Point(2, 2)};

        assertFalse(cmv.lic2(straightLine, 3, 3.1415926535, 0.000001)); // Points, NUMPOINTS, PI, EPSILON
    }

    @Test
    void lic3_returnsFalse_whenPointsAreaLessThanArea1() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 10), new Point(0, 0), new Point(10, 0)};
        assertFalse(cmv.lic3(points, points.length, 100.0));
    }

    @Test
    void lic3_returnsFalse_whenPointsAreaEqualToArea1() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 10), new Point(10, 0)};
        assertFalse(cmv.lic3(points, points.length, 50.0));
    }

    @Test
    void lic3_throwsError_whenNumpointsIsLessThanThree() {
        CMV cvm = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 10), new Point(10, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cvm.lic3(points, 2, 0.0);
        });
        assertEquals("'NUMPOINTS' must be >= 3", error.getMessage());
    }

    @Test
    void lic3_throwsError_whenPointsIsNull() {
        CMV cvm = new CMV();
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cvm.lic3(null, 3, 10.0);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

    @Test
    void lic3_throwsError_whenArea1IsLessThanZero() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 10), new Point(10, 0)};
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic3(points, 3, -1.0);
        });
        assertEquals("'AREA1' must be >= 0", error.getMessage());
    }

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

    @Test
    void lic3_returnsTrue_whenPointsHaveValidAreaAndArea1IsZero() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(0, 1), new Point(1, 0)};
        assertTrue(cmv.lic3(points, points.length, 0.0));
    }

    @Test
    void lic4_ReturnsTrueForConsecutiveBoundaryPoints() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1,1), new Point(0,1),
        new Point(-1,0), new Point(0,-1)};
        assertTrue(cmv.lic4(points, points.length, 5, 2));
    }

    @Test
    void lic4_ReturnsFalseWhenSetIsClearedWhenItWouldHaveReturnedTrueOtherwise() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, 1), new Point(-1,-1), new Point(-2,1),
            new Point(1,-1)};
        assertFalse(cmv.lic4(points, points.length, 4, 3));
    }

    @Test
    void lic4_ReturnsFalseWhenQuad4IsExcluded() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1,1)};
        assertFalse(cmv.lic4(points, points.length, 2, 1));
    }

    @Test
    void lic4_ReturnsFalseWhenAllPointsInOneQuad() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1,1)};
        assertFalse(cmv.lic4(points, points.length, 2, 1));
    }

    @Test
    void lic4_ReturnsTrueWhenTwoConsecutiveSpreadInTwoDistinctQuads() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, -1)};
        assertTrue(cmv.lic4(points, points.length, 2, 1));
    }

    @Test
    void lic4_ReturnsTrueForConsecutivelyPlacedCorrectElementsAreFirstInPointsArray() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, -1), new Point(-1, 0), new Point(-1, 1)};
        assertTrue(cmv.lic4(points, points.length, 3, 2));
    }
    @Test
    void lic4_ReturnsTrueForConsecutivelyPlacedCorrectElementsAreLastInPointsArray() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 15), new Point(-1, 0), new Point(-1, -1)};
        assertTrue(cmv.lic4(points, points.length, 3, 2));
    }

    @Test
    void lic4_returnsFalseWhenNotSpreadInDistinctQuadsButNotConsecutively() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(-1, 15), new Point(1, 1), new Point(11, 10), new Point(10, 10),
                new Point(0, -10), new Point(1, 15)};
        assertFalse(cmv.lic4(points, points.length, 3, 2));
    }
    @Test
    void lic4TestHandlesThrowsNullCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(null, points.length, 1, 1)
        );
        assertTrue(err.getMessage().contains("'points' must not be null"));
    }
    @Test
    void lic4TestHandlesThrowsQ_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 1, 0)
        );
        assertTrue(err.getMessage().contains("'Q_PTS' must be >= 2"));
    }
    @Test
    void lic4TestHandlesThrowsQ_PTSGreaterThanNumpointsCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 5, 1)
        );
        assertTrue(err.getMessage().contains("'Q_PTS' must be <= NUMPOINTS"));
    }
    @Test
    void lic4TestHandlesThrowsQuadsLesserThanOneCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1), new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1),};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 3, 0)
        );
        assertTrue(err.getMessage().contains("'QUADS' must be >= 1"));
    }
    @Test
    void lic4TestHandlesThrowsQuadsGreaterEqThanFourPICorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 5, 4)
        );
        assertTrue(err.getMessage().contains("'QUADS' must be <= 3"));
    }
    @Test
    void lic4TestHandlesThrowsQ_PTSLesserThanQuadsFourPICorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic4(points, points.length, 2, 2)
        );
        assertTrue(err.getMessage().contains("'Q_PTS' must be > QUADS"));
    }




    @Test
    void lic5_nullPoints_returnsFalse() {
        CMV cmv = new CMV();
        assertFalse(cmv.lic5(null, 2));
    }

    @Test
    void lic5_NUMPOINTS_lessThan2_returnsFalse() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0, 0), new Point(-1, 0) };
        assertFalse(cmv.lic5(pts, 0));
        assertFalse(cmv.lic5(pts, 1));
    }

    @Test
    void lic5_decreasingX_true() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(2, 0), new Point(1, 0) };
        assertTrue(cmv.lic5(pts, 2)); // 1 - 2 < 0
    }

    @Test
    void lic5_equalX_false() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(2, 0), new Point(2, 5) };
        assertFalse(cmv.lic5(pts, 2)); // 2 - 2 = 0 NOT < 0
    }

    @Test
    void lic5_increaseThenDecrease_true() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0, 0), new Point(1, 0), new Point(0.5, 0) };
        assertTrue(cmv.lic5(pts, 3)); // 0.5 - 1 < 0
    }

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

    @Test
    void lic6_returnsFalseForNegativeDist() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };

        assertFalse(cmv.lic6(points, points.length, 3, -1));
    }

    @Test
    void lic6_returnsFalseForN_PTSBeingLargerThanNUMPOINTS() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(3, 0),
                new Point(1, 0)
        };

        assertFalse(cmv.lic6(points, points.length, 5, 1));
    }

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

    @Test
    void lic7_returnTrue_WhenTwoPointsSepByK_PTSareMoreThanLENGTH1apart() {
        CMV cmv = new CMV();
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5)};

        assertTrue(cmv.lic7(points, 3, 3, 1)); // POINTS, NUMPOINTS, LENGHT1, K_PTS
    }

    @Test
    void lic7_returnFalse_WhenTwoPointsSepByK_PTSareLENGTH1apart() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(3, 0)};

        assertFalse(cmv.lic7(points, 3, 3, 1)); // POINTS, NUMPOINTS, LENGHT1, K_PTS
    }

    @Test
    void lic7_returnFalse_WhenTwoPointsSepByK_PTSareLessThanLENGTH1apart() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        assertFalse(cmv.lic7(points, 3, 3, 1)); // POINTS, NUMPOINTS, LENGHT1, K_PTS
    }

    @Test
    void lic7_returnFalse_WhenInvalidInput() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0, 0), new Point(1, 0), new Point(2, 0)};

        assertFalse(cmv.lic7(points, 2, 3, 1)); // NUMPOINTS invalid
        assertFalse(cmv.lic7(points, 3, 3, 2)); // K_PTS invalid
        assertFalse(cmv.lic7(null, 3, 3, 2)); // POINTS invalid
    }

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
        assertEquals("A_PTS + B_PTS must be <= NUMPOINTS - 3",  error.getMessage());
    }

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

    @Test
    void lic9ReturnsTrueForMinimumCaseWherePointsAreNinetyDegrees() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(0, 1);
        Point thirdPoint = new Point(1,0);
        Point[] degreeVertices90 = {firstPoint,
                new Point(2, 0),
                secondPoint,
                new Point(3, 0),
                thirdPoint};
        assertTrue(cmv.lic9(degreeVertices90, 5, 1, 1, 3.1415926535, 0.000001));
    }

    //Forms an angle of 89.95. If epsilon is 100 degrees then expect false.
    @Test
    void lic9ReturnsFalseForMinimumCaseWhereAgnleIsNinetyDegreesAndEpsilonIs100Degrees() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0, 0);
        Point secondPoint = new Point(0, 1);
        Point thirdPoint = new Point(1,0);
        Point[] degreeVertices90 = {secondPoint,
                new Point(2, 0),
                firstPoint,
                new Point(3, 0),
                thirdPoint};
        assertFalse(cmv.lic9(degreeVertices90, 5, 1, 1, 3.1415926535, 1.80));
    }

    //Angle is 3.141592653589793 which is 180.00000000044650506
    //and the PI is 3.1415926535 which is 179.999999995302
    //We are testing 180.00000000044650506 < (179.999999995302 - 0.0174533)
    @Test
    void lic9ReturnsFalseForOppositeVectorsWith180DegreesAngleAndEpsilonIs1Degree() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0,1);
        Point secondPoint = new Point(0,0);
        Point thirdPoint = new Point(0,-1);
        Point[] degreeVertices90 = {firstPoint,
                new Point(2, 0),
                secondPoint,
                new Point(123, 0),
                thirdPoint};
        assertFalse(cmv.lic9(degreeVertices90, degreeVertices90.length, 1, 1, 3.1415926535, 0.0174533));
    }

    //Angle is 3.141592653589793 which is 179.42706130276246768
    //and the PI is 3.1415926535 which is 179.999999995302
    //We are testing 179.42706130276246768 < (179.990000329)
    // Testing the precision when values angle and (PI - Epsilon) are close
    @Test
    void lic9ReturnsTrueForOppositeVectorsWith179DegreesAngleAndEpsilonIsExtremelySmall() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0,1);
        Point secondPoint = new Point(0,0);
        Point thirdPoint = new Point(0.01,-1);
        Point[] degreeVertices90 = {firstPoint,
                new Point(2, 0),
                secondPoint,
                new Point(123, 0),
                thirdPoint};
        assertTrue(cmv.lic9(degreeVertices90, degreeVertices90.length, 1, 1, 3.1415926535, 0.008000));
    }

    @Test
    void lic9TestHandlesThrowsNullCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(null, points.length, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'points' must not be null"));
    }
    @Test
    void lic9TestHandlesThrowsNUMPOINTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, 4, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'NUMPOINTS' must be >= 5"));
    }
    @Test
    void lic9TestHandlesThrowsC_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 0, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'C_PTS' must be >= 1"));
    }
    @Test
    void lic9TestHandlesThrowsD_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 1, 0, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'D_PTS' must be >= 1"));
    }
    @Test
    void lic9TestHandlesThrowsIndexBoundaryCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 5, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("C_PTS + D_PTS must be <= NUMPOINTS - 3"));
    }
    @Test
    void lic9TestHandlesThrowsEpsilonLesserThanOneCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 1, 1, 20, -1)
        );
        assertTrue(err.getMessage().contains("'EPSILON' must be >= 0"));
    }
    @Test
    void lic9TestHandlesThrowsEpsilonGreaterEqThanPICorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic9(points, points.length, 1, 1, -1, 0.6)
        );
        assertTrue(err.getMessage().contains("'EPSILON' must be lesser than 'PI'"));
    }







    @Test
    void lic10_nullPoints_returnsFalse() {
        CMV cmv = new CMV();
        assertFalse(cmv.lic10(null, 1, 1, 1.0, 5));
    }

    @Test
    void lic10_NUMPOINTS_lessThan5_returnsFalse() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0,0), new Point(0,0), new Point(2,0), new Point(0,0),
                new Point(0,2) };
        assertFalse(cmv.lic10(pts, 1, 1, 0.1, 4));
    }

    @Test
    void lic10_EPTS_or_FPTS_lessThan1_returnsFalse() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0,0), new Point(0,0), new Point(2,0), new Point(0,0),
                new Point(0,2) };
        assertFalse(cmv.lic10(pts, 0, 1, 0.1, 5));
        assertFalse(cmv.lic10(pts, 1, 0, 0.1, 5));
        assertFalse(cmv.lic10(pts, -1, 1, 0.1, 5));
    }

    @Test
    void lic10_EplusF_greaterThan_NUMPOINTSminus3_returnsFalse() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0,0), new Point(0,0), new Point(2,0), new Point(0,0),
                new Point(0,2) };
        // NUMPOINTS=5 => NUMPOINTS-3 = 2. If E+F=3 => invalid
        assertFalse(cmv.lic10(pts, 1, 2, 0.1, 5));
    }

    @Test
    void lic10_boundary_EplusF_equalTo_NUMPOINTSminus3_isAllowed() {
        CMV cmv = new CMV();
        // NUMPOINTS=5, E=1, F=1 => E+F=2 = NUMPOINTS-3 OK
        Point[] pts = { new Point(0,0), new Point(0,0), new Point(2,0), new Point(0,0),
                new Point(0,2) };
        assertTrue(cmv.lic10(pts, 1, 1, 1.0, 5));
    }

    @Test
    void lic10_areaStrictlyGreaterThanAREA1_true_equal_false() {
        CMV cmv = new CMV();
        Point[] pts = { new Point(0,0), new Point(0,0), new Point(2,0), new Point(0,0),
                new Point(0,2) }; // area=2
        assertTrue(cmv.lic10(pts, 1, 1, 1.9999, 5));
        assertFalse(cmv.lic10(pts, 1, 1, 2.0, 5)); // NOT >
    }

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

    @Test
    void lic11_returnsFalseForG_PTSBeingLargerThanNUMPOINTSMinusTwo() {
        CMV cmv = new CMV();
        Point[] points = {
                new Point(1, 1),
                new Point(2, 4),
                new Point(3, 3),
                new Point(4, 0),
                new Point(5, 0)
        };
        
        assertFalse(cmv.lic11(points, points.length, 4));
    }

    /**
     * Tests if two separate pairs K_PTS apart fit criteria.
     * Criteria for LIC to be true: One pair more than LENGHT1 apart and one pair less than LENGTH2 apart
     */
    @Test
    void lic12_returnsTrue_whenTwoSeparatePairsOfPointsK_PTSapartFitCriteria() {
        CMV cmv = new CMV();
        // points[0] and points[2] are more than 3 apart
        // points[1] and points[3] are less than 2 apart
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1) };

        assertTrue(cmv.lic12(points, 4, 3, 2, 1)); // POINTS, NUMPOINTS, LENGHT1, LENGHT2, K_PTS
    }

    /**
     * Criteria for LIC to be true: One pair more than LENGHT1 apart and one pair less than LENGTH2 apart
     */
    @Test
    void lic12_returnsFalse_whenOnlyOnePairFitsCriteria() {
        CMV cmv = new CMV();
        // points[0] and points[2] are more than 3 apart
        // No pair of paint with 1 element in between are less than 1 distance apart.
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1) };

        assertFalse(cmv.lic12(points, 4, 3, 1, 1)); // POINTS, NUMPOINTS, LENGHT1, LENGHT2, K_PTS
    }

    @Test
    void lic12_returnsFalse_whenNoPairFitsCriteria() {
        CMV cmv = new CMV();
        // No pair of paint with 1 element in between are more than 10 distance apart.
        // No pair of paint with 1 element in between are less than 1 distance apart.
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1) };

        assertFalse(cmv.lic12(points, 4, 10, 1, 1)); // POINTS, NUMPOINTS, LENGHT1, LENGHT2, K_PTS
    }

    @Test
    void lic12_returnsFalse_whenInputParamIsInvalid() {
        CMV cmv = new CMV();
        // Valid points for LIC to return true
        Point[] points = {new Point(-1, -1), new Point(1, 0), new Point(5, 5), new Point(0, 1) };

        assertFalse(cmv.lic12(points, 2, 10, 1, 1));        // NUMPOINTS invalid
        assertFalse(cmv.lic12(points, 4, 10, -1, 1));       // LENGTH2   invalid
        assertFalse(cmv.lic12(points, 4, 10, 1, 3));        // K_PTS     invalid in regard to NUMPOINTS
        assertFalse(cmv.lic12(null, 4, 10, 1, 1));    // POINTS    invalid



    }

    @Test
    void lic13_throwsError_whenPointsIsNull() {
        CMV cmv = new CMV();
        Point[] points = null;
        AssertionError error = assertThrows(AssertionError.class, () -> {
            cmv.lic13(points, 5, 1, 1, 1.0, 3.0);
        });
        assertEquals("'points' must not be null", error.getMessage());
    }

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
        assertEquals("A_PTS + B_PTS must be <= NUMPOINTS - 3",  error.getMessage());
    }

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

    //This test tests that the comparisons with AREA1 and AREA2 works as intended.
    //We modify the values for AREA1 and AREA2 and see that their results are consistent.
    //Area is 0.5 between firstPoint, secondPoint, thirdPoint.
    @Test
    void lic14TestThatAREA1AndAREA2ConditionsWorkCorrectlyWhenFirstInArray() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0,0);
        Point secondPoint = new Point(1, 0);
        Point thirdPoint = new Point(0, 1);
        Point[] pts = {firstPoint,
                new Point(1,0),
                secondPoint,
                new Point(0,0),
                thirdPoint};
        assertFalse(cmv.lic14(pts, 5, 1, 1, 5, 10)); //0.5>AREA1 false when AREA1=5
        assertTrue(cmv.lic14(pts, 5, 1, 1, 0.4, 10)); //0.5>AREA1 true when AREA1=0.4
        assertTrue(cmv.lic14(pts, 5, 1, 1, 0.4, 0.6)); //0.6<AREA2 true AREA2=0.6
        assertFalse(cmv.lic14(pts, 5, 1, 1, 0.4, 0.4)); //0.6<AREA2 false AREA2=0.4
    }

    //Area is 0.5 between firstPoint, secondPoint, thirdPoint.
    @Test
    void lic14TestThatAREA1AndAREA2ConditionsWorkCorrectlyWhenNotFirstInArray() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0,0);
        Point secondPoint = new Point(1, 0);
        Point thirdPoint = new Point(0, 1);
        Point[] points = {
                new Point(12,12),
                firstPoint,
                new Point(12,12),
                secondPoint,
                new Point(12,12),
                thirdPoint};
        assertTrue(cmv.lic14(points, points.length, 1, 1, 0.4, 10));
        assertFalse(cmv.lic14(points, points.length, 1, 1, 0.6, 10));
    }

    //Area is 0.5 between firstPoint, secondPoint, thirdPoint.
    @Test
    void lic14TestThatAREA1AndAREA2ConditionsWorkCorrectlyWhenDifferentTriples() {
        CMV cmv = new CMV();
        Point firstPoint = new Point(0,0);
        Point secondPoint = new Point(1, 0);
        Point thirdPoint = new Point(0, 1);

        //Area=25
        Point firstPoint2 = new Point(10,10);
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
    @Test
    void lic14TestHandlesThrowsNullCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(null, points.length, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'points' must not be null"));
    }
    @Test
    void lic14TestHandlesThrowsNUMPOINTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, 4, 1, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'NUMPOINTS' must be >= 5"));
    }
    @Test
    void lic14TestHandlesThrowsIndexBoundaryCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 5, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("E_PTS + F_PTS must be <= NUMPOINTS - 3"));
    }
    @Test
    void lic14TestHandlesThrowsE_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 0, 1, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'E_PTS' must be >= 1"));
    }
    @Test
    void lic14TestHandlesThrowsF_PTSCorrectly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 1, 0, 20, 0.6)
        );
        assertTrue(err.getMessage().contains("'F_PTS' must be >= 1"));
    }
    @Test
    void lic14TestHandlesThrowsAREA1Correctly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
        AssertionError err = assertThrows(AssertionError.class, () ->
                cmv.lic14(points, points.length, 1, 1, -1, 0.6)
        );
        assertTrue(err.getMessage().contains("'AREA1' must be >= 0"));
    }
    @Test
    void lic14TestHandlesThrowsAREA2Correctly() {
        CMV cmv = new CMV();
        Point[] points = {new Point(0,0), new Point(1,0), new Point(0,1),new Point(0,1),new Point(0,1)};
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