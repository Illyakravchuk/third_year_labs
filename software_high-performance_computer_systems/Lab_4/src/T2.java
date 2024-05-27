public class T2 extends Thread{

    @Override
    public void run() {
        System.out.println("T2 started");
        // введення  MM
        Data.MM = Data.initializeMatrixWithOnes();


        // сигнал задачам T1, T3, T4 про введення  MM
        Data.monitorSync.notifyInput();
        // очікування введення даних в задачах T1, T3, T4
        Data.monitorSync.awaitInput();

        // Обчислення 1
        int[] Bh = Data.subVector(Data.B, Data.H, Data.H * 2);
        int[] Zh = Data.subVector(Data.Z, Data.H, Data.H * 2);

        int a2 = Data.scalarProduct(Bh, Zh);

        // доступ до спільного ресурсу --КД1
        // Обчислення 2
        Data.monitorControl.incrementA(a2);

        // сигнал про завершення обчислення
        Data.monitorSync.notifyCalculationA();
        // очікування на завершення обчислень а в потоках T1, Т3, T4
        Data.monitorSync.awaitCalculationA();

        // копіювання a2 = a --КД2
        a2 = Data.monitorControl.getA();

        // копіювання d2 = d --КД3
        int d2 = Data.monitorControl.getD();

        // Обчислення 3 : MXн = a2 *  (MZ * MMн) - (MR * MCн) * d2
        int[][] MMh = Data.subMatrix(Data.MM, Data.H, Data.H * 2);
        int[][] MCh = Data.subMatrix(Data.MC, Data.H, Data.H * 2);

        int[][] MXh = Data.calculateStepThree(MMh, MCh, a2, d2);
        // запис результату
        Data.insertSubMatrix(MXh, Data.H);

        // очікування на завершення обчислень 3 в T1,T3,T4
        Data.monitorSync.awaitFinalOutput();

        // вивід MX
        Data.displayMatrix(Data.MX);
        System.out.println("T2 finished");
    }
}
