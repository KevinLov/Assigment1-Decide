package se.kth.DD2480;

public class PUM
{
    boolean[][] arr;

    public PUM(int n) {
        this.arr = new boolean[n][n];
    }
    public PUM(int n, LCM lcm, CMV cmv){
        this.arr = new boolean[n][n];
        boolean[] cmvArray = cmv.verifyAllLics(0.0, null, 0, 0, 0, 0.0);
        for(int i = 0; i < n; ++i){
            for (int j = 0; j < n; j++) {
                switch(lcm.arr[i][j]){
                    case NOTUSED -> this.arr[i][j] = true;
                    case ORR -> this.arr[i][j] = cmvArray[i] || cmvArray[j];
                    case ANDD -> this.arr[i][j] = cmvArray[i] && cmvArray[j];
                }
            }
        }


    }
}
