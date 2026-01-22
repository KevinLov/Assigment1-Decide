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
        assertFalse(cmv.lic5(null, 1));
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
    void lic10() {
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