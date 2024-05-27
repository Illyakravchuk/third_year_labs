public class T4 extends Thread {

    @Override
    public void run() {
        System.out.println("T4 started");
        // введення MC, MZ
        Data.MC = Data.initializeMatrixWithOnes();
        Data.MZ = Data.initializeMatrixWithOnes();

        // сигнал задачам T1, T2, T3 про введення MC, MZ
        Data.monitorSync.notifyInput();
        // очікування введення даних в задачах T1, T2, T3
        Data.monitorSync.awaitInput();

        // Обчислення 1
        int[] Bh = Data.subVector(Data.B, Data.H * 3, Data.N);
        int[] Zh = Data.subVector(Data.Z, Data.H * 3, Data.N);

        int a4 = Data.scalarProduct(Bh, Zh);

        // доступ до спільного ресурсу --КД1
        // Обчислення 2
        Data.monitorControl.incrementA(a4);

        // сигнал про завершення обчислення
        Data.monitorSync.notifyCalculationA();
        // очікування на завершення обчислень а в потоках T1, Т2, T3
        Data.monitorSync.awaitCalculationA();

        // копіювання a4 = a --КД2
        a4 = Data.monitorControl.getA();

        // копіювання d4 = d --КД3
        int d4 = Data.monitorControl.getD();

        // Обчислення 3 : MXн = a4 *  (MZ * MMн) - (MR * MCн) * d4
        int[][] MMh = Data.subMatrix(Data.MM, Data.H * 3, Data.N);
        int[][] MCh = Data.subMatrix(Data.MC, Data.H * 3, Data.N);

        int[][] MXh = Data.calculateStepThree(MMh, MCh, a4, d4);
        // запис результату
        Data.insertSubMatrix(MXh, Data.H * 3);

        // сигнал про завершення обчислення 3
        Data.monitorSync.notifyFinalOutput();
        System.out.println("T4 finished");
    }
}
