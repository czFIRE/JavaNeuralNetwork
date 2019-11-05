package JavaNN;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {
    private static Random random;
    private static long seed;

    /**
     *  Sets the seed for random initialization, will probably get replaced
     *  for better method in the future.
      */
    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public static void setSeed(long seed) {
        Utils.seed = seed;
        random = new Random(seed);
    }

    public static long getSeed() {
        return seed;
    }

    public static double uniformDouble() {
        return random.nextDouble();
    }

    public static int uniformInt(int to) {
        if (to <= 0) {
            throw new IllegalArgumentException("argument should be positive");
        }
        return random.nextInt(to);
    }

    public static double[][] randomMat (int rows, int cols) {
        double[][] mat = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = uniformDouble() * 0.01; //magic constant?!
            }
        }
        return mat;
    }
}
