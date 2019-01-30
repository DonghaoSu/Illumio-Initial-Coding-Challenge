import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Firewall {
    private static HashMap<Long, Boolean> map = new HashMap<Long, Boolean>();

    public Firewall(String file) throws FileNotFoundException {
        // Read csv file
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                // Split line into four columns
                String[] rule = line.split(",");

                // Dealing with all situation with specific or range port and IP Address
                // Port with range
                if (rule[2].contains("-")) {
                    String[] portRangeString = rule[2].split("-");
                    int minPort = Integer.parseInt(portRangeString[0]);
                    int maxPort = Integer.parseInt(portRangeString[1]);
                    int portRange = maxPort - minPort;

                    // IP address with range
                    if (rule[3].contains("-")) {
                        String[] ipAddressRangeStirng = rule[3].split("-");
                        long minIPAddress = Long.parseLong(ipAddressRangeStirng[0].replaceAll("\\.", ""));
                        long maxIPAddress = Long.parseLong(ipAddressRangeStirng[1].replaceAll("\\.", ""));
                        long ipAddressRange = maxIPAddress - minIPAddress;

                        // Adding all the possible port and IP address combination into hashmap
                        for (int i = 0; i <= portRange; i++) {
                            for (int j = 0; j <= ipAddressRange; j++) {
                                NetworkRule cur = new NetworkRule(rule[0], rule[1], minPort + i, minIPAddress + j);
                                map.put(cur.getHashCode(), Boolean.TRUE);
                            }
                        }

                    } else { // Specific IP Address
                        // Adding all the possible port with this IP address into hashmap
                        for (int i = 0; i <= portRange; i++) {
                            NetworkRule cur = new NetworkRule(rule[0], rule[1], minPort + i, rule[3]);
                            map.put(cur.getHashCode(), Boolean.TRUE);
                        }
                    }

                } else { //Specific port
                    // IP Address with range
                    if (rule[3].contains("-")) {
                        String[] ipAddressRangeStirng = rule[3].split("-");
                        long minIPAddress = Long.parseLong(ipAddressRangeStirng[0].replaceAll("\\.", ""));
                        long maxIPAddress = Long.parseLong(ipAddressRangeStirng[1].replaceAll("\\.", ""));
                        long ipAddressRange = maxIPAddress - minIPAddress;

                        // iterate through all possible ips add them to map
                        for (int i = 0; i <= ipAddressRange; i++) {
                            NetworkRule cur = new NetworkRule(rule[0], rule[1], rule[2], minIPAddress + i);
                            map.put(cur.getHashCode(), Boolean.TRUE);
                        }
                    } else { // Specific port and IP Address
                        NetworkRule cur = new NetworkRule(rule[0], rule[1], rule[2], rule[3]);
                        map.put(cur.getHashCode(), Boolean.TRUE);
                    }

                }

            }
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }

    }

    public boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
        // If the input rule is in our hashmap, return true, otherwise, return false.
        NetworkRule rule = new NetworkRule(direction, protocol, port, ipAddress);
        return map.containsKey(rule.getHashCode());
    }
}