package se.kth.DD2480;

public class Main {

    public static void main(String[] args) {

        DECIDE();
    }

    private static void DECIDE() {
        PUV puv = PUV.createPUV(1);
        LCM lcm = LCM.createLCM(1);
        Parameters params  = Parameters.createParameters(1);
        CMV cmv = new CMV();

        Point[] points0 = {
                new Point(0, 0),   new Point(1, 0),   new Point(2, 0),  new Point(3, 0),   new Point(4, 0),   new Point(0,0),   new Point(1, 0),  new Point(0, 1),
                new Point(10,10),  new Point(20, 10), new Point(5, 5),  new Point(-1, -1), new Point(1, 0),   new Point(5, 5),   new Point(0, 1),
                new Point(6, 1),   new Point(7, 4),   new Point(8, 3),  new Point(9, 0),   new Point(0, 0),
                new Point(0,0),    new Point(0,0),    new Point(2,0),   new Point(0,0),    new Point(0,2),    new Point(0, 0), new Point(2, 0), new Point(0, 1), new Point(3, 0),
                new Point(1,0),    new Point(0, 0),   new Point(1, 0),  new Point(2, 0),   new Point(3, 0),   new Point(4, 0),
                new Point(-1, -1), new Point(1, 0),   new Point(5, 5),  new Point(0, 0),   new Point(1, 0),   new Point(3, 0),  new Point(1, 0),
                new Point(0, 0),   new Point(1, 0),   new Point(0.5, 0),new Point(0, 0),   new Point(-1, -1), new Point(10, 0),  new Point(0, 10),  new Point(0, 0),   new Point(3, 6),
                new Point(0, 1),   new Point(0, 0),   new Point(1, 0),  new Point(0,0),    new Point(1,0),    new Point(10,0), new Point(0,10), new Point(0, 0), new Point(10, 0)
        };

        Point[] points1 = {
                new Point(0,0), new Point(0,0),
                new Point(0,0), new Point(0,0),
                new Point(0,0), new Point(0,0)
        };

        boolean[] cmvArray = cmv.verifyAllLics(params, points1, points1.length);
        PUM pum = new PUM();
        boolean[][] pumArray = pum.makePUM(lcm.getArr(), cmvArray);
        FUV fuv = new FUV(pum, puv.getArray());
        if(fuv.launch) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}