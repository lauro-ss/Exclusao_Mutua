public class RunProcess {
    public static void main(String[] args) throws Exception {

        /* Número total de processos */
        Process.totalProcess = 3;
        Process.time = 0;

        new Process(0).start();
        new Process(1).start();
        new Process(2).start();
    }
}
