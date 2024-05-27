// ПЗВПКС
// Лабораторна робота №2
// Варіант 6
// A = (R*MC)*MD *p + (B*Z)*E*d
// Кравчук Ілля Володимирович ІМ-13
// Дата 30.03.2024

public class Lab2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Data data = new Data();

        T1 t1 = new T1(data);
        T2 t2 = new T2(data);
        T3 t3 = new T3(data);
        T4 t4 = new T4(data);

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
        double totalTime = (endTime - startTime) / 1_000_00.0;
        System.out.printf("Час виконання програми: %.1f мілісекунд\n", totalTime);

    }
}

