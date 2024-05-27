import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Data {

    public int N = 24;
    public int P = 4;
    public int H = N / P;
    public int d;
    public int p;

    public int[] A = new int[N];
    public int[] X = new int[N];
    public int[] B = new int[N];
    public int[] E = new int[N];
    public int[] R = new int[N];
    public int[] Z = new int[N];
    public int[][] MC = new int[N][N];
    public int[][] MD = new int[N][N];
    Semaphore S1 = new Semaphore(0);
    Semaphore S2 = new Semaphore(0);
    Semaphore S3 = new Semaphore(0);
    Semaphore S4 = new Semaphore(0);
    Semaphore S5 = new Semaphore(0);
    Semaphore S6 = new Semaphore(1);
    Semaphore S7 = new Semaphore(1);
    CyclicBarrier B1 = new CyclicBarrier(4);
    public AtomicInteger a = new AtomicInteger(0);
    private final Object CS1 = new Object();

    public int initializeVariableToOne(int variable) {
        return 1;
    }

    public int copy_a_CS1() {
        synchronized (CS1) {
            return this.a.intValue();
        }
    }

    public int[][] initializeVectorWithOnes(int[][] matrix) {
        int[][] filledMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                filledMatrix[i][j] = 1;
            }
        }
        return filledMatrix;
    }
    public int[] initializeVectorWithOnes(int[] vector) {
        int[] filledVector = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            filledVector[i] = 1;
        }
        return filledVector;
    }

    public static int[][] subMatrix(int[][] matrix, int startIndex, int endIndex) {
        int rows = matrix.length;
        if (rows == 0 || startIndex < 0 || endIndex <= startIndex || endIndex > matrix[0].length) {
            throw new IllegalArgumentException("Invalid submatrix indices");
        }

        int cols = endIndex - startIndex;
        if (cols <= 0) {
            throw new IllegalArgumentException("Invalid submatrix column range");
        }

        int[][] submatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (matrix[i].length < endIndex) {
                throw new IllegalArgumentException("Invalid row length for submatrix");
            }
            System.arraycopy(matrix[i], startIndex, submatrix[i], 0, cols);
        }

        return submatrix;
    }

    public static int[] subVector(int[] vector, int startIndex, int endIndex) {
        if (vector.length == 0 || startIndex < 0 || endIndex <= startIndex || endIndex > vector.length) {
            throw new IllegalArgumentException("Invalid subvector indices");
        }

        int size = endIndex - startIndex;
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid subvector range");
        }

        int[] subvector = new int[size];

        System.arraycopy(vector, startIndex, subvector, 0, size);

        return subvector;
    }



    public static int scalarProduct(int[] vector1, int[] vector2) {
        int minLen = Math.min(vector1.length, vector2.length);
        int result = 0;

        for (int i = 0; i < minLen; i++) {
            result += vector1[i] * vector2[i];
        }

        return result;
    }

    public static int[] vectorMatrixMultiplication(int[] vector, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[cols];

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                result[j] += vector[i] * matrix[i][j];
            }
        }

        return result;
    }
}
