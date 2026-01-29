package se.kth.DD2480;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FUVTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * if PUV is all true, every LIC should hold back launch
     * if every row of PUM is all true, then the FUV is all true even though PUV is true
     * the method return true
     */
    @Test
    void FUVreturnTrueWhenEverythingIsTrue() {
        PUM pum = new PUM(); //row i in pum must be all true for fuv to be true in index i
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                pum.arr[i][j] = true;
            }
        }
        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = true;
        }
        FUV fuv = new FUV(pum, puv);
        assertTrue(fuv.launch);
    }

    /**
     * if PUV is all false, no LIC should hold back launch
     * so even if every row of PUM is all false, FUV is all true
     * the method return true
     */
    @Test
    void FUVreturnTrueWhenEverythingIsFalse() {
        PUM pum = new PUM();
        boolean[] puv = new boolean[15]; //an element in puv is false if the corresponding LIC should not hold back launch -> fuv true in that index
        FUV fuv = new FUV(pum, puv);
        assertTrue(fuv.launch);
    }

    /**
     * if only PUV[0] is true, only LIC 0 should hold back launch
     * but if PUM row 0 is not all true, then FUV[0] is false
     * if FUV is not all true, lauch is false
     * the method return false
     */
    @Test
    void FUVreturnFalseWhenOneElementInPUVIsTrue() {
        PUM pum = new PUM();
        boolean[] puv = new boolean[15];
        puv[0] = true; //only first LIC should hold back launch but pum row 0 is not all true
        FUV fuv = new FUV(pum, puv);
        assertFalse(fuv.launch);
    }

    /**
     * if only PUV[0] is true, only LIC 0 should hold back launch
     * but if PUM row 0 is all true except the diagonal element (PUM[i][i]), then FUV[0] is true
     * Here PUM row 0 is made all true except the diagonal element making FUV[0] true
     * the method return true because FUV is all true
     */
    @Test
    void FUVreturnTrueWhenOneElementInPUVIsTrueButRowIsTrue() {
        PUM pum = new PUM();
        boolean[] puv = new boolean[15];
        for (int j = 1; j < 15; j++) { //we skip the first element because we don't check the diagonal (pum[i][j] when j==i)
            pum.arr[0][j] = true; //make row 0 all true
        }
        puv[0] = true; //only first LIC should hold back launch but pum row 0 is not all true
        FUV fuv = new FUV(pum, puv);
        assertTrue(fuv.launch);
    }

    /**
     * The method requires 'PUV' to be of length 15.
     * If the length of 'PUV' is not 15, the method throws AssertionError
     * with message "PUV must be of length 15".
     */
    @Test
    void FUVassertThrowWhenPUVNot15Elements() {
        PUM pum = new PUM();
        boolean[] puv = new boolean[14];

        AssertionError error = assertThrows(AssertionError.class, () -> {
            new FUV(pum, puv);
        });
        assertEquals("PUV must be of length 15", error.getMessage());
    }

    /**
     * The method requires 'PUM' to be of size 15x15.
     * If the size of 'PUM' is not 15x15, the method throws AssertionError
     * with message "PUM must be of size 15x15".
     */
    @Test
    void FUVassertThrowWhenPUMNot15x15Elements() {
        PUM pum = new PUM();
        pum.arr = new boolean[14][15];
        boolean[] puv = new boolean[15];

        AssertionError error = assertThrows(AssertionError.class, () -> {
            new FUV(pum, puv);
        });
        assertEquals("PUM must be of size 15x15", error.getMessage());
    }
}