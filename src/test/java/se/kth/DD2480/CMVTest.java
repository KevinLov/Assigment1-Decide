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
    void lic1_anyinput() {
        CMV cmv = new CMV();
        Point p1 = new Point(0,0);
        Point p2 = new Point(10,0);
        Point[] points = {p1, p2};
        assertFalse(cmv.lic1(points, 2, 1));
    }

    @Test
    void lic1_valid() {
        CMV cmv = new CMV();
        Point p1 = new Point(0,0);
        Point p2 = new Point(1,0);
        Point p3 = new Point(10,0);
        Point p4 = new Point(0,10);
        Point[] points = {p1, p2, p3, p4};
        assertTrue(cmv.lic1(points, 4, 10));
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
    void lic10_returnsFalse_whenPointsIsNull() {
        CMV cmv = new CMV();
        assertFalse(cmv.lic10(null, 0, 0, 0.0, 0));
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