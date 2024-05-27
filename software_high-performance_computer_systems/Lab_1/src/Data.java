import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Data {
    public static int getInput() {
        int N;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Specify the dimensions of vectors and matrices");
        do {
            N = scanner.nextInt();
            if (N < 1) {
                System.out.println("N must be greater than 0");
            }
        } while (N < 1);
        return N;
    }

    public static double[] addVectors(double[] firstVector, double[] secondVector) {
        double[] result = new double[firstVector.length];

        for (int i = 0; i < firstVector.length; i++) {
            result[i] = firstVector[i] + secondVector[i];
        }

        return result;
    }

    public static double[] scalarVectorMultiplication(double scalar, double[] vector) {
        double[] result = new double[vector.length];

        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * scalar;
        }

        return result;
    }

    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        int N = firstMatrix.length;
        double[][] result = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return result;
    }

    public static double[] vectorMatrixMultiplication(double[] vector, double[][] matrix) {
        int N = vector.length;
        double[] result = new double[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[j] += vector[i] * matrix[i][j];
            }
        }

        return result;
    }

    public static double[][] scalarMatrixMultiplication(double scalar, double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }

        return result;
    }

    public static double findMinimumInVector(double[] vector) {
        return Arrays.stream(vector).min().getAsDouble();
    }

    public static double findMinimumInMatrix(double[][] matrix) {
        double min = matrix[0][0];
        int length = matrix.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
        }
        return min;
    }

    public static void displayVectorToConsole(double[] vector, String vectorName) {
        System.out.printf("vector %s %s\n", vectorName, Arrays.toString(vector));
    }

    public static void displayMatrixToConsole(double[][] matrix, String matrixName) {
        System.out.printf("matrix %s\n", matrixName);
        for (double[] array : matrix) {
            for (double num : array) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static double[] inputVectorWithKeyboard(double[] vector, String vectorName, int length, String thread) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < length; i++) {
            System.out.printf("Element %d in vector%s thread %s \n", i, vectorName, thread);
            vector[i] = scanner.nextDouble();
        }
        return vector;
    }

    public static double[][] inputMatrixWithKeyboard(double[][] matrix, String matrixName, int length, String thread) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.printf("Element %d %d in matrix%s thread %s \n", i, j, matrixName, thread);
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static double[] createRandomVector(double[] vector, int length) {
        for (int i = 0; i < length; i++) {
            double randomValue = Math.round((Math.random() * 200 - 100) * 10) / 10.0;   // step 0.1
            vector[i] = randomValue;
        }
        return vector;
    }

    public static double[][] createRandomMatrix(double[][] matrix, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                double randomValue = Math.round((Math.random() * 200 - 100) * 10) / 10.0; // step 0.1
                matrix[i][j] = randomValue;
            }
        }
        return matrix;
    }


    public static double[] initializeVectorWithValue(double[] vector, int length, int value) {
        for (int i = 0; i < length; i++) {
            vector[i] = value;
        }
        return vector;
    }

    public static double[][] initializeMatrixWithValue(double[][] matrix, int length, int value) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = value;
            }
        }
        return matrix;
    }

    public static void saveVectorToFile(String filename, double[] vector) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (double elem : vector) {
                writer.print(elem + " ");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void saveMatrixToFile(String filename, double[][] matrix) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (double[] array : matrix) {
                for (double elem : array) {
                    writer.print(elem + " ");
                }
                writer.println();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static double[] loadVectorFromFile(String filename, int vectorLength) {
        double[] vector = new double[vectorLength];
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int i = 0;
            while (scanner.hasNext() && i < vectorLength) {
                String nextElement = scanner.next();
                vector[i] = Double.parseDouble(nextElement);
                i++;
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return vector;
    }

    public static double[][] loadMatrixFromFile(String filename, int matrixLength) {
        double[][] matrix = new double[matrixLength][matrixLength];
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int row = 0;
            while (scanner.hasNextLine() && row < matrixLength) {
                String line = scanner.nextLine();
                String[] values = line.trim().split("\\s+");
                for (int col = 0; col < Math.min(values.length, matrixLength); col++) {
                    matrix[row][col] = Double.parseDouble(values[col]);
                }
                row++;
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return matrix;
    }


    public static void exportResultToFile(String filename, double[] result) {
        try (FileWriter writer = new FileWriter(filename)) {
            String strResult = Arrays.toString(result);
            writer.write(strResult);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void exportResultToFile(String filename, double[][] result) {
        try (FileWriter writer = new FileWriter(filename)) {
            String strResult = Arrays.deepToString(result);
            writer.write(strResult);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}