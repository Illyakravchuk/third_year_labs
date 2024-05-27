public class ControlMonitors {
    private int a = 0;
    private int d;

    public synchronized void incrementA(int ai) {
        this.a += ai;
    }

    public synchronized void setD(int d) {
        this.d = d;
    }

    public synchronized int getA() {
        return a;
    }

    public synchronized int getD() {
        return d;
    }
}
