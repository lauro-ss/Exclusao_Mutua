public class RunProcess {
    public static void main(String[] args) throws Exception {
        Process.totalProcess = 2;
        new Process(1, 8).start();
        new Process(2, 9).start();
    }
}
