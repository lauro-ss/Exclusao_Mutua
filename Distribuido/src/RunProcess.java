public class RunProcess {
    public static void main(String[] args) throws Exception {

        /* Número total de processos */
        Process.totalProcess = 3;
        Process.time = 0;

        /*
         * Simulando exemplo parecido com o do slide
         * A escrita sera feito no tempo 8 e 12
         */
        new Process(0).start();
        new Process(1).start();
        new Process(2).start();
    }
}
