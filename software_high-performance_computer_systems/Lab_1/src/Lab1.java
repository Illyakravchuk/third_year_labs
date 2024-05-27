// ПЗВПКС
// Лабораторна робота ЛР1.3. ПОТОКИ В МОВІ JAVA
// F1: 1.20 D = MIN(A + B) * (B + C) *(MA*MD)
// F2: 2.28 MF = MIN(MH)*MK*ML
// F3: 3.29 S = V*MO + R*MP + P*(MO*MS)
// Кравчук Ілля Володимирович ІМ-13
// Дата 20.02.2024

public class Lab1 {
    public static void main(String[] args) {
        int N = Data.getInput();
        T1 t1 = new T1(N);
        T2 t2 = new T2(N);
        T3 t3 = new T3(N);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("All threads have completed their execution.");
    }
}
