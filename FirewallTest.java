import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;

public class FirewallTest {

    @Test
    public void firstRule() {
        try {
            Firewall fw = new Firewall("fw.csv");
            boolean res = fw.acceptPacket("inbound", "tcp", 80, "192.168.1.2");
            assertEquals(res, true);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }

    @Test
    public void secondRule() {
        try {
            Firewall fw = new Firewall("fw.csv");
            // Corner cases:
            boolean res1 = fw.acceptPacket("outbound", "tcp", 20000, "192.168.10.11");
            boolean res2 = fw.acceptPacket("outbound", "tcp", 10000, "192.168.10.11");

            // General case
            boolean res3 = fw.acceptPacket("outbound", "tcp", 12345, "192.168.10.11");
            assertEquals(res1, true);
            assertEquals(res2, true);
            assertEquals(res3, true);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    @Test
    public void thirdRule() {
        try {
            Firewall fw = new Firewall("fw.csv");
            // Corner cases:
            boolean res1 = fw.acceptPacket("inbound", "udp", 53, "192.168.1.1");
            boolean res2 = fw.acceptPacket("inbound", "udp", 53, "192.168.2.5");

            // General case
            boolean res3 = fw.acceptPacket("inbound", "udp", 53, "192.168.2.3");
            assertEquals(res1, true);
            assertEquals(res2, true);
            assertEquals(res3, true);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    @Test
    public void fourthRule() {
        try {
            Firewall fw = new Firewall("fw.csv");
            // Corner cases:
            boolean res1 = fw.acceptPacket("outbound", "udp", 2000, "52.12.48.92");
            boolean res2 = fw.acceptPacket("outbound", "udp", 1000, "52.12.66.99");

            // General case
            boolean res3 = fw.acceptPacket("outbound", "udp", 1234, "52.12.48.90");
            assertEquals(res1, true);
            assertEquals(res2, true);
            assertEquals(res3, true);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    @Test
    public void falseCases() {
        try {
            Firewall fw = new Firewall("fw.csv");
            boolean res = fw.acceptPacket("inbound", "tcp", 800, "192.168.1.2");
            boolean res1 = fw.acceptPacket("inbound", "tcp", 800, "192.168.12.2");
            boolean res2 = fw.acceptPacket("inbound", "udp", 1000, "52.12.48.92");
            boolean res3 = fw.acceptPacket("outbound", "udp", 123554, "52.12.48.92");
            assertEquals(res1, false);
            assertEquals(res2, false);
            assertEquals(res3, false);
            assertEquals(res, false);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }
}
