import java.util.Scanner;

public class T1 extends Thread {

    private int N = 0;
    private double[] D;
    private double[] A;
    private double[] B;
    private double[] C;
    private double[][] MA;
    private double[][] MD;

    public T1(int N) {
        this.N = N;
    }

    public void run() {
        System.out.println("\ntask T1 is started");
        if (N < 9) {
            enterParametersFromKeyboard();
            D = F1();
            Data.displayVectorToConsole(D, "D");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("choose how to fill vectors and matrices with data\n" +
                    "1 setting all data elements to a given value.\n" +
                    "2 use a random number generator.");
            int generateMethod = scanner.nextInt();
            if (generateMethod == 1 || generateMethod == 2) {
                initializeParameters();
                double[][] vectors;
                double[][][] matrices;
                if (generateMethod == 1) {
                    vectors = specifyVectorValues();
                    matrices = specifyMatrixValues();
                } else {
                    vectors = generateVectorsRandomly();
                    matrices = generateMatrixRandomly();
                }
                saveDataToFile(vectors[0], vectors[1], vectors[2], matrices[0], matrices[1]);
                loadDataFromFile();
                D = F1();
                Data.exportResultToFile("src/text/Result_F1.txt", D);
            } else {
                throw new IllegalArgumentException("Enter the data correctly");
            }
        }
        System.out.println("task T1 is finished");
    }
    //    1.20 D = MIN(A + B) * (B + C) *(MA*MD)
    private double[] F1() {
        double[] sumAB = Data.addVectors(A, B);
        double minSumAB = Data.findMinimumInVector(sumAB);
        double[] sumBC = Data.addVectors(B, C);
        double[][] productMA_MD = Data.multiplyMatrices(MA, MD);
        double[] scalarProduct  = Data.scalarVectorMultiplication(minSumAB, sumBC);
        double[] result = Data.vectorMatrixMultiplication(scalarProduct, productMA_MD);
        return result;
    }
    private void initializeParameters() {
        A = new double[N];
        B = new double[N];
        C = new double[N];
        MA = new double[N][N];
        MD = new double[N][N];
    }

    private void enterParametersFromKeyboard() {
        initializeParameters();
        A = Data.inputVectorWithKeyboard(A, "A", N, "T1");
        B = Data.inputVectorWithKeyboard(B, "B", N, "T1");
        C = Data.inputVectorWithKeyboard(C, "C", N, "T1");
        MA = Data.inputMatrixWithKeyboard(MA, "MA", N, "T1");
        MD = Data.inputMatrixWithKeyboard(MD, "MD", N, "T1");
    }

    private double[][] generateVectorsRandomly() {
        double[] vectorA = Data.createRandomVector(new double[N], N);
        double[] vectorB = Data.createRandomVector(new double[N], N);
        double[] vectorC = Data.createRandomVector(new double[N], N);
        return new double[][]{vectorA, vectorB, vectorC};
    }

    private double[][][] generateMatrixRandomly() {
        double[][] matrixMA = Data.createRandomMatrix(new double[N][N], N);
        double[][] matrixMD = Data.createRandomMatrix(new double[N][N], N);
        return new double[][][]{matrixMA, matrixMD};
    }

    private double[][] specifyVectorValues() {
        double[] vectorA = Data.initializeVectorWithValue(new double[N], N, 1);
        double[] vectorB = Data.initializeVectorWithValue(new double[N], N, 1);
        double[] vectorC = Data.initializeVectorWithValue(new double[N], N, 1);
        return new double[][]{vectorA, vectorB, vectorC};
    }

    private double[][][] specifyMatrixValues() {
        double[][] matrixMA = Data.initializeMatrixWithValue(new double[N][N], N, 1);
        double[][] matrixMD = Data.initializeMatrixWithValue(new double[N][N], N, 1);
        return new double[][][]{matrixMA, matrixMD};
    }

    private void saveDataToFile(double[] vectorA, double[] vectorB, double[] vectorC, double[][] matrixMA, double[][] matrixMD) {
        Data.saveVectorToFile("src/text/vectorA.txt", vectorA);
        Data.saveVectorToFile("src/text/vectorB.txt", vectorB);
        Data.saveVectorToFile("src/text/vectorC.txt", vectorC);
        Data.saveMatrixToFile("src/text/matrixMA.txt", matrixMA);
        Data.saveMatrixToFile("src/text/matrixMD.txt", matrixMD);
    }

    private void loadDataFromFile() {
        A = Data.loadVectorFromFile("src/text/vectorA.txt", N);
        B = Data.loadVectorFromFile("src/text/vectorB.txt", N);
        C = Data.loadVectorFromFile("src/text/vectorC.txt", N);
        MA = Data.loadMatrixFromFile("src/text/matrixMA.txt", N);
        MD = Data.loadMatrixFromFile("src/text/matrixMD.txt", N);
    }
}
