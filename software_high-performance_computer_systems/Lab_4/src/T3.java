public class T3 extends Thread{

    @Override
    public void run() {
        System.out.println("T3 started");
        // введення MR, B
        Data.MR = Data.initializeMatrixWithOnes();
        Data.B = Data.initializeVectorWithOnes();

        // сигнал задачам T1, T2, T4 про введення MR, B
        Data.monitorSync.notifyInput();
        // очікування введення даних в задачах T1, T2, T4
        Data.monitorSync.awaitInput();

        // Обчислення 1
        int[] Bh = Data.subVector(Data.B, Data.H * 2, Data.H * 3);
        int[] Zh = Data.subVector(Data.Z, Data.H * 2, Data.H * 3);

        int a3 = Data.scalarProduct(Bh, Zh);

        // доступ до спільного ресурсу --КД1
        // Обчислення 2
        Data.monitorControl.incrementA(a3);

        // сигнал про завершення обчислення
        Data.monitorSync.notifyCalculationA();
        // очікування на завершення обчислень а в потоках T1, Т2, T4
        Data.monitorSync.awaitCalculationA();

        // копіювання a3 = a --КД2
        a3 = Data.monitorControl.getA();

        // копіювання d3 = d --КД3
        int d3 = Data.monitorControl.getD();

        // Обчислення 3 : MXн = a3 *  (MZ * MMн) - (MR * MCн) * d3
        int[][] MMh = Data.subMatrix(Data.MM, Data.H * 2, Data.H * 3);
        int[][] MCh = Data.subMatrix(Data.MC, Data.H * 2, Data.H * 3);

        int[][] MXh = Data.calculateStepThree(MMh, MCh, a3, d3);
        // запис результату
        Data.insertSubMatrix(MXh, Data.H * 2);

        // сигнал про завершення обчислення 3
        Data.monitorSync.notifyFinalOutput();
        System.out.println("T3 finished");
    }
}
