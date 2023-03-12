import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Queue;

public class Process extends Thread {

    private int id;
    private int time;
    private boolean wantUsing;
    private Queue queue = new LinkedList();
    public static int totalProcess;

    public Process(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public void run() {
        byte[] b = new byte[256];
        try {
            InetAddress addr = InetAddress.getByName("239.0.0.1");
            DatagramPacket pkg;
            DatagramSocket enviarCast = new DatagramSocket();

            MulticastSocket receberCast = new MulticastSocket(6001);
            InetAddress grp = InetAddress.getByName("239.0.0.1");
            receberCast.joinGroup(grp);

            while (true) {

                if (time == 8)
                    wantUsing = true;

                if (wantUsing) {
                    b = (Integer.toString(time) + ";" + Integer.toString(id)).getBytes();
                    pkg = new DatagramPacket(b, b.length, addr, 6001);
                    enviarCast.send(pkg);

                    // Acessar zona critica
                    if (WaitForOks(receberCast)) {
                        System.out.println("Zona Crítica");
                        String str = "Escrito no tempo: " + time + " Por: " + id;
                        BufferedWriter writer = new BufferedWriter(new FileWriter("zona_critica.txt", true));
                        writer.append(str);
                        writer.append("\n\n");

                        writer.close();
                        wantUsing = false;
                        time = 9;
                    }
                } else {
                    pkg = new DatagramPacket(b, b.length, addr, 6000);
                    receberCast.receive(pkg);
                    if (pkg.getData().length > 0) {
                        // Não está usando a zona critica
                        b = "Ok".getBytes();
                        pkg = new DatagramPacket(b, b.length, addr, 6001);
                        enviarCast.send(pkg);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }

    private boolean WaitForOks(MulticastSocket receberCast) throws IOException {
        byte rec[] = new byte[256];
        DatagramPacket pkg = new DatagramPacket(rec, rec.length);
        int countProcess = 0;
        String pkgValue;
        while (true) {
            receberCast.receive(pkg);
            if (pkg.getData().length > 0) {
                pkgValue = new String(pkg.getData(), 0, pkg.getLength());
                if (pkgValue.equals("Ok")) {
                    countProcess++;
                    if (countProcess == totalProcess)
                        return true;
                }
            }
        }
    }
}
