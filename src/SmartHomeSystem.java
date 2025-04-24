import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartHomeSystem {
    private Map<String, SmartDevice> devices;
    private List<AutomationRule> rules;

    public SmartHomeSystem() {
        devices = new HashMap<>();
        rules = new ArrayList<>();
    }

    public void addDevice(String type, String name, String protocol) {
        if (devices.containsKey(name)) {
            System.out.println("duplicate device name");
            return;
        }

        if (!(type.equals("light") || type.equals("thermostat"))) {
            System.out.println("invalid input");
            return;
        }

        if (!(protocol.equals("WiFi") || protocol.equals("Bluetooth"))) {
            System.out.println("invalid input");
            return;
        }

        SmartDevice device;
        if (type.equals("light")) {
            device = new Light(name, protocol);
        } else {
            device = new Thermostat(name, protocol);
        }

        devices.put(name, device);
        System.out.println("device added successfully");
    }

    public void setDevice(String name, String property, String value) {
        if (!devices.containsKey(name)) {
            System.out.println("device not found");
            return;
        }

        try {
            devices.get(name).setProperty(property, value);
            System.out.println("device updated successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("invalid");
        }
    }

    public void removeDevice(String name) {
        if (!devices.containsKey(name)) {
            System.out.println("device not found");
            return;
        }

        devices.remove(name);
        rules.removeIf(rule -> rule.getDeviceName().equals(name));
        System.out.println("device removed successfully");
    }

    public void listDevices() {
        if (devices.isEmpty()) {
            System.out.println();
            return;
        }

        for (SmartDevice device : devices.values()) {
            System.out.println(device.getDetails());
        }
    }

    public void addRule(String name, String time, String action) {
        if (!devices.containsKey(name)) {
            System.out.println("device not found");
            return;
        }

        if (!isValidTime(time)) {
            System.out.println("invalid time");
            return;
        }

        if (!(action.equals("on") || action.equals("off"))) {
            System.out.println("invalid action");
            return;
        }

        for (AutomationRule rule : rules) {
            if (rule.getDeviceName().equals(name) && rule.getTime().equals(time)) {
                System.out.println("duplicate rule");
                return;
            }
        }

        rules.add(new AutomationRule(name, time, action));
        System.out.println("rule added successfully");
    }

    public void checkRules(String time) {
        if (!isValidTime(time)) {
            System.out.println("invalid time");
            return;
        }

        for (AutomationRule rule : rules) {
            if (rule.getTime().equals(time)) {
                SmartDevice device = devices.get(rule.getDeviceName());
                if (device != null) {
                    device.setStatus(rule.getAction());
                }
            }
        }

        System.out.println("rules checked");
    }

    public void listRules() {
        if (rules.isEmpty()) {
            System.out.println();
            return;
        }

        for (AutomationRule rule : rules) {
            System.out.println(rule.getDeviceName() + " " + rule.getTime() + " " + rule.getAction());
        }
    }

    private boolean isValidTime(String time) {
        try {
            String[] parts = time.split(":");
            if (parts.length != 2) return false;

            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            return hour >= 0 && hour < 24 && minute >= 0 && minute < 60;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
