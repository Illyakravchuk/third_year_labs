import java.util.concurrent.BrokenBarrierException;

public class T4 extends Thread {
    private Data data;
    final int id = 4;
    private int a4;

    private int d4;
    private int p4;

    public T4(Data d) {
        data = d;
    }

    @Override
    public void run() {
        System.out.println("T4 is started");

        try {
            data.R = data.initializeVectorWithOnes(data.R);
            data.Z = data.initializeVectorWithOnes(data.Z);

            // Сигнал задачам T1, T2, T3 про введення R, Z і чекати введення даних в задачах T1, T2, T3 - бар'єр B1
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

            a4 = data.scalarProduct(Bh, Zh);

            // доступ до спільного ресурсу - КД1
            // Обчислення 3
            data.a.updateAndGet(current -> current + a4);          // атомік-змінна a
            // сигнал про завершення обчислення
            data.S4.release(3);      // семафор S4
            // очікування на завершення обчислень а з Т1, Т2, Т3
            data.S1.acquire();             // семафор S1
            data.S2.acquire();             // семафор S2
            data.S3.acquire();             // семафор S3

            int [][] MDh = Data.subMatrix(data.MD, indexStart, indexEnd);
            // Обчислення 4
            int []Fh = Data.vectorMatrixMultiplication(data.X, MDh);
            int []Eh = Data.subVector(data.E,indexStart, indexEnd);

            // копіювання p4 = p --КД2 семафор S6
            data.S6.acquire();
            p4 = data.p;
            data.S6.release();

            // копіювання a4 = a --КД3 критична секція CS1
            a4 = data.copy_a_CS1();

            // копіювання d4 = d --КД4 семафор S7
            data.S7.acquire();
            d4 = data.d;
            data.S7.release();

            //Обчислення 5 : Aн= Fн * p + a * Eн * d
            int minLen = Math.min(Fh.length, Eh.length);
            int[] Ah = new int[minLen];

            for (int i = 0; i < minLen; i++) {
                Ah[i] = Fh[i] * p4 + a4 * Eh[i] * d4;
            }

            for (int i = indexStart; i < indexEnd; i++) {
                data.A[i] += Ah[i - indexStart];
            }
            // Сигнал про завершення обчислення A семафор S5
            data.S5.release();

        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("T4 is finished");
        }
    }
}