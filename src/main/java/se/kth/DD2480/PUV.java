package se.kth.DD2480;

public class PUV {
    final int SIZE = 15;
    boolean[] arr;

    public PUV(boolean[] arr) {
        if (arr == null || arr.length != SIZE) {
            throw new IllegalArgumentException("PUV must be atleast: " + SIZE);
        }
        this.arr = arr;
    }

}