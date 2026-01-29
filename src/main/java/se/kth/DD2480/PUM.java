package se.kth.DD2480;

public class PUM
{

    boolean[][] arr;

    public PUM() {
        this.arr = new boolean[15][15];
    }

    public boolean[][] makePUM(CONNECTORS[][] lcm, boolean[] cmvArray) {
        for(int i = 0; i < 15; ++i){
            for (int j = 0; j < 15; j++) {
                switch(lcm[i][j]){
                    case NOTUSED -> this.arr[i][j] = true;
                    case ORR -> this.arr[i][j] = cmvArray[i] || cmvArray[j];
                    case ANDD -> this.arr[i][j] = cmvArray[i] && cmvArray[j];
                }
            }
        }
        return this.arr;
    }
}
