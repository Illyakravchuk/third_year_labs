public class SyncMonitors {
    private int inputProcessedCount  = 0;
    private int calculationACount   = 0;
    private int finalizationCount  = 0;

    public synchronized void awaitInput() {
        if (inputProcessedCount != 4) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void notifyInput() {
        inputProcessedCount++;
        if (inputProcessedCount == 4) notifyAll();
    }

    public synchronized void awaitCalculationA() {
        if (calculationACount   != 4) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void notifyCalculationA() {
        calculationACount  ++;
        if (calculationACount   == 4) notifyAll();
    }

    public synchronized void awaitFinalOutput() {
        if (finalizationCount  != 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void notifyFinalOutput() {
        finalizationCount ++;
        if (finalizationCount  == 3) notify();
    }
}
