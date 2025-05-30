public class AutomationRule {
    private String deviceName;
    private String time;
    private String action;

    public AutomationRule(String deviceName, String time, String action) {
        this.deviceName = deviceName;
        this.time = time;
        this.action = action;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return deviceName + " " + time + " " + action;
    }
}