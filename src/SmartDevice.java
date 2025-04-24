public abstract class SmartDevice implements DeviceControl {
    protected String name;
    protected String protocol;
    protected String status;

    public SmartDevice(String name, String protocol) {
        this.name = name;
        this.protocol = protocol;
        this.status = "off";
    }

    public String getName() {
        return name;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setStatus(String status) {
        if (status.equals("on") || status.equals("off")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("invalid status");
        }
    }

    @Override
    public String getStatus() {
        return status;
    }
}