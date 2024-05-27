// ПЗВПКС
// Лабораторна робота №4
// Варіант 18
// MX = (B*Z)*(MZ*MM) - (MR*MC)*d
// Кравчук Ілля Володимирович ІМ-13
// Дата 13.05.2024
public class Lab4 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        T1 t1 = new T1();
        T2 t2 = new T2();
        T3 t3 = new T3();
        T4 t4 = new T4();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        long endTime = System.nanoTime();
      double totalTime = (endTime - startTime) / 1_000_000.0; //1мсек=1_000_000нсек
        System.out.printf("Час виконання програми: %.1f мілісекунд\n", totalTime);
    }
}