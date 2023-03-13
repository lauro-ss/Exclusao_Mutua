public class RunProcess {
    public static void main(String[] args) throws Exception {
        Process.totalProcess = 3;
        new Process(0, 7).start();
        new Process(1, 6).start();
        new Process(2, 10).start();
    }
}
