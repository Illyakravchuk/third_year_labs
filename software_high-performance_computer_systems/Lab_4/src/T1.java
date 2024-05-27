public class T1 extends Thread {

    @Override
    public void run() {
        System.out.println("T1 started");
        // введення Z, d
        Data.Z = Data.initializeVectorWithOnes();
        Data.d = Data.initializeVariableToOne();
        Data.monitorControl.setD(Data.d);

        // сигнал задачам T2, T3, T4 про введення Z, d
        Data.monitorSync.notifyInput();
        // очікування введення даних в задачах T2, T3, T4
        Data.monitorSync.awaitInput();

        // Обчислення 1
        int[] Bh = Data.subVector(Data.B, 0, Data.H);
        int[] Zh = Data.subVector(Data.Z, 0, Data.H);

        int a1 = Data.scalarProduct(Bh, Zh);

        // доступ до спільного ресурсу --КД1
        // Обчислення 2
        Data.monitorControl.incrementA(a1);

        // сигнал про завершення обчислення
        Data.monitorSync.notifyCalculationA();
        // очікування на завершення обчислень а в потоках Т2, Т3, Т4
        Data.monitorSync.awaitCalculationA();

        // копіювання a1 = a --КД2
        a1 = Data.monitorControl.getA();

        // копіювання d1 = d --КД3
        int d1 = Data.monitorControl.getD();

        // Обчислення 3 : MXн = a1 *  (MZ * MMн) - (MR * MCн) * d1
        int[][] MMh = Data.subMatrix(Data.MM, 0, Data.H);
        int[][] MCh = Data.subMatrix(Data.MC, 0, Data.H);

        int[][] MXh = Data.calculateStepThree(MMh, MCh, a1, d1);
        // запис результату
        Data.insertSubMatrix(MXh, 0);

        // сигнал про завершення обчислення 3
        Data.monitorSync.notifyFinalOutput();
        System.out.println("T1 finished");
    }
}