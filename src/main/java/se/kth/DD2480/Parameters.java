package se.kth.DD2480;

public class Parameters {

    //fields for parameters.

    double LENGTH1;
    double LENGTH2;
    double RADIUS1;
    double RADIUS2;
    double EPSILON;
    double AREA1;
    double AREA2;
    int Q_PTS;
    int QUADS;
    double DIST;
    int N_PTS;
    int K_PTS;
    int A_PTS;
    int B_PTS;
    int C_PTS;
    int D_PTS;
    int E_PTS;
    int F_PTS;
    int G_PTS;

    public Parameters(double LENGTH1, double LENGTH2, double RADIUS1, double RADIUS2, double EPSILON, double AREA1, double AREA2, int q_PTS, int QUADS, double DIST, int n_PTS, int k_PTS, int a_PTS, int b_PTS, int c_PTS, int d_PTS, int e_PTS, int f_PTS, int g_PTS) {
        this.LENGTH1 = LENGTH1;
        this.LENGTH2 = LENGTH2;
        this.RADIUS1 = RADIUS1;
        this.RADIUS2 = RADIUS2;
        this.EPSILON = EPSILON;
        this.AREA1 = AREA1;
        this.AREA2 = AREA2;
        Q_PTS = q_PTS;
        this.QUADS = QUADS;
        this.DIST = DIST;
        N_PTS = n_PTS;
        K_PTS = k_PTS;
        A_PTS = a_PTS;
        B_PTS = b_PTS;
        C_PTS = c_PTS;
        D_PTS = d_PTS;
        E_PTS = e_PTS;
        F_PTS = f_PTS;
        G_PTS = g_PTS;
    }


    /**
     * A static factory method.
     * @param mode The mode which determines the parameter values.
     * @return An instance of the Parameters class initialized to values based on the given mode.
     * @throws IllegalArgumentException When a mode that is not supported is given as parameter.
     */
        public static Parameters createParameters(int mode) {
        return switch (mode) {
            case 1 -> new Parameters(
                    0,0,
                    0,0,0,0,
                    0,0,0,0,0,
                    0,0,0,0,0,
                    0,0,0);
            case 2 -> new Parameters(1,1,
                    1,1,1,0,
                    0,0,0,0,0,
                    0,0,0,0,0,
                    0,0,0);
            default -> throw new IllegalArgumentException("Please enter a valid mode, between 1-2");
        };
    }
}
