public class Data {
    public static int N = 1500;
    public static int P = 4;
    public static int H = N / P;

    public static int[][] MX = new int[N][N];
    public static int[][] MR = new int[N][N];
    public static int[][] MC = new int[N][N];

    public static int[][] MZ = new int[N][N];
    public static int[][] MM = new int[N][N];

    public static int[] B = new int[N];
    public static int[] Z = new int[N];

    public static ControlMonitors monitorControl = new ControlMonitors();
    public static SyncMonitors monitorSync = new SyncMonitors();

    public static int d;

    public static int initializeVariableToOne() {
        return 1;
    }

    public static int[] initializeVectorWithOnes() {
        int[] filledVector = new int[N];
        for (int i = 0; i < N; i++) {
            filledVector[i] = 1;
        }
        return filledVector;
    }

    public static int[][] initializeMatrixWithOnes() {
        int[][] filledMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                filledMatrix[i][j] = 1;
            }
        }
        return filledMatrix;
    }

    public static int scalarProduct(int[] vector1, int[] vector2) {
        int minLen = Math.min(vector1.length, vector2.length);
        int result = 0;

        for (int i = 0; i < minLen; i++) {
            result += vector1[i] * vector2[i];
        }

        return result;
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

    public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        int rowsInFirst = firstMatrix.length;
        int colsInFirst = firstMatrix[0].length;
        int colsInSecond = secondMatrix[0].length;

        int[][] result = new int[rowsInFirst][colsInSecond];

        for (int i = 0; i < rowsInFirst; i++) {
            for (int j = 0; j < colsInSecond; j++) {
                for (int k = 0; k < colsInFirst; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] multiplyMatrixByNumber(int[][] matrix, int number) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j] * number;
            }
        }

        return result;
    }

    public static int[][] subtractMatrices(int[][] firstMatrix, int[][] secondMatrix) {
        int rows = firstMatrix.length;
        int columns = firstMatrix[0].length;
        int[][] result = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
            }
        }

        return result;
    }


    public static int[][] calculateStepThree(int[][] MMh, int[][] MCh, int a, int d) {
        int[][] firstResult = multiplyMatrixByNumber(multiplyMatrices(MZ, MMh), a);
        int[][] secondResult = multiplyMatrixByNumber(multiplyMatrices(MR, MCh), d);
        int[][] finalResult = subtractMatrices(firstResult, secondResult);

        return finalResult;
    }

    public static void displayMatrix(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        System.out.println("Matrix MX:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void insertSubMatrix(int[][] subMatrix, int startColumn) {
        int rows = MX.length;
        int subMatrixColumns = subMatrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < subMatrixColumns; j++) {
                MX[i][startColumn + j] = subMatrix[i][j];
            }
        }
    }

}
