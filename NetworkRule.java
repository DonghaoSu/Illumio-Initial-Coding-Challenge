public class NetworkRule {
    private String direction;
    private String protocol;
    private int port;
    private long ipAddress;
    long hashCode;

    public NetworkRule(String direction, String protocol, String port, String ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = Integer.parseInt(port);
        this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", ""));
        this.hashCode = 31 * (this.ipAddress + this.port + direction.hashCode() + protocol.hashCode());
    }

    public NetworkRule(String direction, String protocol, String port, long ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = Integer.parseInt(port);
        this.ipAddress = ipAddress;
        this.hashCode = 31 * (this.ipAddress + this.port + direction.hashCode() + protocol.hashCode());
    }

    public NetworkRule(String direction, String protocol, int port, long ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddress = ipAddress;
        this.hashCode = 31 * (this.ipAddress + this.port + direction.hashCode() + protocol.hashCode());
    }

    public NetworkRule(String direction, String protocol, int port, String ipAddress) {
        this.direction = direction;
        this.protocol = protocol;
        this.port = port;
        this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", ""));
        this.hashCode = 31 * (this.ipAddress + this.port + direction.hashCode() + protocol.hashCode());
    }

    public long getHashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof NetworkRule))
            return false;
        NetworkRule networkRule = (NetworkRule) object;
        return direction.equals(networkRule.direction) && protocol.equals(networkRule.protocol) && port == networkRule.port
                && ipAddress == networkRule.ipAddress;
    }

    @Override
    public String toString() {
        return this.direction + ", " + this.protocol + ", " + Integer.toString(this.port) + ", "
                + Long.toString(this.ipAddress);
    }

    public int hashCode() {
        long hash = 31 * (this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode());
        return Long.valueOf(hash).hashCode();
    }
}
