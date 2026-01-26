package se.kth.DD2480;

public class PUM
{

    boolean[][] arr;

    public PUM() {
        this.arr = new boolean[15][15];
    }
    public PUM(LCM lcm, CMV cmv, Parameters p, Point[] points, int NUMPOINTS){
        this.arr = new boolean[15][15];
        boolean[] cmvArray = cmv.verifyAllLics(p, points , NUMPOINTS);
        for(int i = 0; i < 15; ++i){
            for (int j = 0; j < 15; j++) {
                switch(lcm.arr[i][j]){
                    case NOTUSED -> this.arr[i][j] = true;
                    case ORR -> this.arr[i][j] = cmvArray[i] || cmvArray[j];
                    case ANDD -> this.arr[i][j] = cmvArray[i] && cmvArray[j];
                }
            }
        }


    }

}
