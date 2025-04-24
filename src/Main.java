import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem system = new SmartHomeSystem();

        int q = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < q; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];

            try {
                switch (command) {
                    case "add_device":
                        system.addDevice(parts[1], parts[2], parts[3]);
                        break;
                    case "set_device":
                        system.setDevice(parts[1], parts[2], parts[3]);
                        break;
                    case "remove_device":
                        system.removeDevice(parts[1]);
                        break;
                    case "list_devices":
                        system.listDevices();
                        break;
                    case "add_rule":
                        system.addRule(parts[1], parts[2], parts[3]);
                        break;
                    case "check_rules":
                        system.checkRules(parts[1]);
                        break;
                    case "list_rules":
                        system.listRules();
                        break;
                    default:
                        System.out.println("invalid command");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("invalid command format");
            }
        }

        scanner.close();
    }
}