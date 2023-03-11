import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Process_01 extends Thread {

    private int id;
    private int time;

    public Process_01(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public void run() {
        byte[] b = new byte[254];
        try {
            InetAddress addr = InetAddress.getByName("239.0.0.1");
            DatagramPacket pkg;
            DatagramSocket enviarCast = new DatagramSocket();

            MulticastSocket receberCast = new MulticastSocket(6001);
            InetAddress grp = InetAddress.getByName("239.0.0.1");
            receberCast.joinGroup(grp);

            while (true) {

                if (time == 8) {
                    b = Integer.toString(time).getBytes();
                    pkg = new DatagramPacket(b, b.length, addr, 6000);
                    enviarCast.send(pkg);
                }
                receberCast.receive(pkg);
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
}
