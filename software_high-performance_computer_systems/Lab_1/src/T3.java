import java.util.Scanner;

public class T3 extends Thread {

    private int N = 0;
    private double[] S;
    private double[] V;
    private double[][] MO;
    private double[] R;
    private double[][] MP;
    private double[] P;
    private double[][] MS;

    public T3(int N) {
        this.N = N;
    }

    public void run() {
        System.out.println("\ntask T3 is started");
        if (N < 9) {
            enterParametersFromKeyboard();
            S = F3();
            Data.displayVectorToConsole(S, "S");
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
                saveDataToFile(vectors[0], vectors[1], vectors[2], matrices[0], matrices[1], matrices[2]);
                loadDataFromFile();
                S = F3();
                Data.exportResultToFile("src/text/Result_F3.txt", S);
            } else {
                throw new IllegalArgumentException("Unknown generation method!");
            }
        }
        System.out.println("task T3 is finished");
    }


    //    3.29 S = V*MO + R*MP + P*(MO*MS)
    private double[] F3() {
        double[] productV_MO = Data.vectorMatrixMultiplication(V, MO);
        double[] productR_MP = Data.vectorMatrixMultiplication(R, MP);
        double[][] productMO_MS = Data.multiplyMatrices(MO, MS);
        double[] productP_MO_MS = Data.vectorMatrixMultiplication(P, productMO_MS);

        double[] sum1 = Data.addVectors(productV_MO, productR_MP);
        double[] result = Data.addVectors(sum1, productP_MO_MS);
        return result;
    }

    private void initializeParameters() {
        S = new double[N];
        V = new double[N];
        R = new double[N];
        P = new double[N];
        MO = new double[N][N];
        MP = new double[N][N];
        MS = new double[N][N];
    }

    private void enterParametersFromKeyboard() {
        initializeParameters();
        V = Data.inputVectorWithKeyboard(V, "V", N, "T3");
        R = Data.inputVectorWithKeyboard(R, "R", N, "T3");
        P = Data.inputVectorWithKeyboard(P, "P", N, "T3");
        MO = Data.inputMatrixWithKeyboard(MO, "MO", N, "T3");
        MP = Data.inputMatrixWithKeyboard(MP, "MP", N, "T3");
        MS = Data.inputMatrixWithKeyboard(MS, "MS", N, "T3");
    }

    private double[][] generateVectorsRandomly() {
        double[] vectorV = Data.createRandomVector(new double[N], N);
        double[] vectorR = Data.createRandomVector(new double[N], N);
        double[] vectorP = Data.createRandomVector(new double[N], N);
        return new double[][]{vectorV, vectorR, vectorP};
    }

    private double[][][] generateMatrixRandomly() {
        double[][] matrixMO = Data.createRandomMatrix(new double[N][N], N);
        double[][] matrixMP = Data.createRandomMatrix(new double[N][N], N);
        double[][] matrixMS = Data.createRandomMatrix(new double[N][N], N);
        return new double[][][]{matrixMO, matrixMP, matrixMS};
    }

    private double[][] specifyVectorValues() {
        double[] vectorV = Data.initializeVectorWithValue(new double[N], N, 3);
        double[] vectorR = Data.initializeVectorWithValue(new double[N], N, 3);
        double[] vectorP = Data.initializeVectorWithValue(new double[N], N, 3);
        return new double[][]{vectorV, vectorR, vectorP};
    }

    private double[][][] specifyMatrixValues() {
        double[][] matrixMO = Data.initializeMatrixWithValue(new double[N][N], N, 3);
        double[][] matrixMP = Data.initializeMatrixWithValue(new double[N][N], N, 3);
        double[][] matrixMS = Data.initializeMatrixWithValue(new double[N][N], N, 3);
        return new double[][][]{matrixMO, matrixMP, matrixMS};
    }

    private void saveDataToFile(double[] vectorS, double[] vectorR, double[] vectorP, double[][] matrixMO, double[][] matrixMP, double[][] matrixMS) {
        Data.saveVectorToFile("src/text/vectorV.txt", vectorS);
        Data.saveVectorToFile("src/text/vectorR.txt", vectorR);
        Data.saveVectorToFile("src/text/vectorP.txt", vectorP);
        Data.saveMatrixToFile("src/text/matrixMO.txt", matrixMO);
        Data.saveMatrixToFile("src/text/matrixMP.txt", matrixMP);
        Data.saveMatrixToFile("src/text/matrixMS.txt", matrixMS);
    }

    private void loadDataFromFile() {
        V = Data.loadVectorFromFile("src/text/vectorV.txt", N);
        R = Data.loadVectorFromFile("src/text/vectorR.txt", N);
        P = Data.loadVectorFromFile("src/text/vectorP.txt", N);
        MO = Data.loadMatrixFromFile("src/text/matrixMO.txt", N);
        MP = Data.loadMatrixFromFile("src/text/matrixMP.txt", N);
        MS = Data.loadMatrixFromFile("src/text/matrixMS.txt", N);
    }
}
