public class RunProcess {
    public static void main(String[] args) throws Exception {
        Process.totalProcess = 2;
        new Process(0, 8).start();
        new Process(1, 9).start();
        // new Process(2, 12).start();
    }
}
