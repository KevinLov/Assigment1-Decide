package se.kth.DD2480;

public class Main {

    public static void main(String[] args) {
        PUV puv = PUV.createPUV(1);
        LCM lcm = LCM.createLCM(1);
        Parameters params = Parameters.createParameters(1);

        Point[] points = {
                new Point(0,0), new Point(0,0),
                new Point(0,0), new Point(0,0),
                new Point(0,0), new Point(0,0)
        };
        boolean launch = DECIDE(points, params, lcm, puv);
        System.out.println(launch);
    }

    static boolean DECIDE(Point[] points, Parameters params, LCM lcm, PUV puv) {

        CMV cmv = new CMV();
        boolean[] cmvArray = cmv.verifyAllLics(params, points, points.length);
        PUM pum = new PUM();
        boolean[][] pumArray = pum.makePUM(lcm.getArr(), cmvArray);
        FUV fuv = new FUV(pum, puv.getArray());
        return fuv.launch;
    }
}