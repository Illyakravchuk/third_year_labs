import java.util.Scanner;

public class T2 extends Thread {

    private int N = 0;
    private double[][] MF;
    private double[][] MH;
    private double[][] MK;
    private double[][] ML;

    public T2(int N) {
        this.N = N;
    }

    public void run() {
        System.out.println("\ntask T2 is started");
        if (N < 9) {
            enterParametersFromKeyboard();
            MF = F2();
            Data.displayMatrixToConsole(MF, "MF");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("choose how to fill vectors and matrices with data\n" +
                    "1 setting all data elements to a given value.\n" +
                    "2 use a random number generator.");
            int generateMethod = scanner.nextInt();
            if (generateMethod == 1 || generateMethod == 2) {
                initializeParameters();
                double[][][] matrices;
                if (generateMethod == 1) {
                    matrices = specifyMatrixValues();
                } else {
                    matrices = generateMatrixRandomly();
                }
                saveDataToFile(matrices[0], matrices[1], matrices[2]);
                loadDataFromFile();
                MF = F2();
                Data.exportResultToFile("src/text/Result_F2.txt", MF);
            } else {
                throw new IllegalArgumentException("Unknown generation method!");
            }
        }
        System.out.println("task T2 is finished");
    }
    //2.28 MF = MIN(MH)*MK*ML
    private double[][] F2() {
        double minMH = Data.findMinimumInMatrix(MH);
        double[][] scalarProduct = Data.scalarMatrixMultiplication(minMH, MK);
        double[][] result = Data.multiplyMatrices(scalarProduct, ML);
        return result;
    }

    private void initializeParameters() {
        MF = new double[N][N];
        MH = new double[N][N];
        MK = new double[N][N];
        ML = new double[N][N];
    }

    private void enterParametersFromKeyboard() {
        initializeParameters();
        MH = Data.inputMatrixWithKeyboard(MH, "MH", N, "T2");
        MK = Data.inputMatrixWithKeyboard(MK, "MK", N, "T2");
        ML = Data.inputMatrixWithKeyboard(ML, "ML", N, "T2");
    }

    private double[][][] specifyMatrixValues() {
        double[][] matrixMH = Data.initializeMatrixWithValue(new double[N][N], N, 2);
        double[][] matrixMK = Data.initializeMatrixWithValue(new double[N][N], N, 2);
        double[][] matrixML = Data.initializeMatrixWithValue(new double[N][N], N, 2);
        return new double[][][]{matrixMH, matrixMK, matrixML};
    }

    private double[][][] generateMatrixRandomly() {
        double[][] matrixMH = Data.createRandomMatrix(new double[N][N],N);
        double[][] matrixMK = Data.createRandomMatrix(new double[N][N],N);
        double[][] matrixML = Data.createRandomMatrix(new double[N][N],N);
        return new double[][][]{matrixMH, matrixMK, matrixML};
    }

    private void saveDataToFile(double[][] matrixMH, double[][] matrixMK, double[][] matrixML) {
        Data.saveMatrixToFile("src/text/matrixMH.txt", matrixMH);
        Data.saveMatrixToFile("src/text/matrixMK.txt", matrixMK);
        Data.saveMatrixToFile("src/text/matrixML.txt", matrixML);
    }

    private void loadDataFromFile() {
        MH = Data.loadMatrixFromFile("src/text/matrixMH.txt", N);
        MK = Data.loadMatrixFromFile("src/text/matrixMK.txt", N);
        ML = Data.loadMatrixFromFile("src/text/matrixML.txt", N);
    }

}
