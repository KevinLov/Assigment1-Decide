package se.kth.DD2480;

public class FUV {
    boolean[] arr; //vector
    boolean launch; //true if all elements in vector is true, to access use object.launch

    public FUV() {
        this.arr = new boolean[15];
        this.launch = false;
    }

    public FUV(PUM pum, boolean[] puv) { //since PUV is given as input its assumed to be an array
        assert puv.length == 15 : "PUV must be of length 15";
        assert pum.arr.length == 15 && pum.arr[0].length == 15 : "PUM must be of size 15x15";
        
        this.arr = new boolean[15];
        this.launch = true;
        
        for (int i = 0; i < 15; i++) {
            if (!puv[i]) { //false if coresponding LIC should not hold back launch
                this.arr[i] = true;
                continue;
            }
            boolean allTrue = true;
            for (int j = 0; j < 15; j++) { //the whole row in PUM must be true for FUV to be true
                if (!pum.arr[i][j] && j!=i) {
                    allTrue = false;
                    this.launch = false;
                    break;
                }
            }
            this.arr[i] = allTrue;
        }
    }
}
