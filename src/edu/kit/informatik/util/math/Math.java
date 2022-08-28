package edu.kit.informatik.util.math;


public class Math {
    private Math() { }

    public static boolean isOdd(int i) {
        return (i & 1) == 1;
    }
}
