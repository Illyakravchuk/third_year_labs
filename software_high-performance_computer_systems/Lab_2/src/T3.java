import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;

public class T3 extends Thread {
    private Data data;
    final int id = 3;

    private int a3;

    private int d3;
    private int p3;
    public T3(Data d) {
        data = d;
    }

    @Override
    public void run() {
        System.out.println("T3 is started");

        try {
            data.B = data.initializeVectorWithOnes(data.B);
            data.p = data.initializeVariableToOne(data.p);

            // Сигнал задачам T1, T2, T4 про введення B, p і чекати введення даних в задачах T1, T2, T4 - бар'єр B1
            data.B1.await();

            int indexStart = (id - 1) * data.H;
            int indexEnd =  id * data.H;
            int [][] MCh = Data.subMatrix(data.MC, indexStart, indexEnd);

            // Обчислення 1
            int []Xh = Data.vectorMatrixMultiplication(data.R, MCh);
            for (int i = indexStart; i < indexEnd; i++) {
                data.X[i] += Xh[i - indexStart];
            }
            // Обчислення 2
            int []Bh = Data.subVector(data.B,indexStart, indexEnd);
            int []Zh = Data.subVector(data.Z,indexStart, indexEnd);

            a3 = data.scalarProduct(Bh, Zh);

            // доступ до спільного ресурсу - КД1
            // Обчислення 3
            data.a.updateAndGet(current -> current + a3);          // атомік-змінна a
            // сигнал про завершення обчислення
            data.S3.release(3);      // семафор S3
            // очікування на завершення обчислень а з Т1, Т2, Т4
            data.S1.acquire();             // семафор S1
            data.S2.acquire();             // семафор S2
            data.S4.acquire();             // семафор S4

            int [][] MDh = Data.subMatrix(data.MD, indexStart, indexEnd);
            // Обчислення 4
            int []Fh = Data.vectorMatrixMultiplication(data.X, MDh);
            int []Eh = Data.subVector(data.E,indexStart, indexEnd);

            // копіювання p3 = p --КД2 семафор S6
            data.S6.acquire();
            p3 = data.p;
            data.S6.release();

            // копіювання a3 = a --КД3 критична секція CS1
            a3 = data.copy_a_CS1();

            // копіювання d3 = d --КД4 семафор S7
            data.S7.acquire();
            d3 = data.d;
            data.S7.release();

            //Обчислення 5 : Aн= Fн * p + a * Eн * d
            int minLen = Math.min(Fh.length, Eh.length);
            int[] Ah = new int[minLen];

            for (int i = 0; i < minLen; i++) {
                Ah[i] = Fh[i] * p3 + a3 * Eh[i] * d3;
            }

            for (int i = indexStart; i < indexEnd; i++) {
                data.A[i] += Ah[i - indexStart];
            }
            // очікування на завершення обчислень 5 з Т1, Т2, Т4  семафор S5
            data.S5.acquire(3);
            //Вивід A
            System.out.println(Arrays.toString(data.A));

        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("T3 is finished");
        }
    }
}