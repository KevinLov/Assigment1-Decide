package se.kth.DD2480;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mecRadius_testTriangleWithNonZeroArea() {
        Point a = new Point(0, 8);
        Point b = new Point(-4, 0);
        Point c = new Point(4, 0);

        assertEquals(5.0, Point.minimalEnclosingCircleRadius(a, b, c), 0.000001);
    }

    @Test
    void mecRadius_testTriangleWithCollinearVertices() {
        Point a = new Point(0, 0);
        Point b = new Point(10, 0);
        Point c = new Point(15, 0);

        assertEquals(7.5, Point.minimalEnclosingCircleRadius(a, b, c), 0.000001);
    }
}