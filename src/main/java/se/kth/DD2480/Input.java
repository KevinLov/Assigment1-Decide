package se.kth.DD2480;

public class Input {

    private int numPoint;
    private int[][] points;
    Parameters parameters;
    LCM lcm;
    PUV puv;

    public Input(int numPoint, int[][] points, Parameters parameters, LCM lcm, PUV puv) {
        this.numPoint = numPoint;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }

}
