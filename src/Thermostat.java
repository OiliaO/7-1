public class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String name, String protocol) {
        super(name, protocol);
        this.temperature = 20;
    }

    @Override
    public void setProperty(String property, String value) {
        if (property.equals("temperature")) {
            try {
                int tempValue = Integer.parseInt(value);
                if (tempValue < 10 || tempValue > 30) {
                    throw new IllegalArgumentException("invalid temperature value");
                }
                this.temperature = tempValue;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid temperature value");
            }
        } else if (property.equals("status")) {
            setStatus(value);
        } else {
            throw new IllegalArgumentException("invalid property for thermostat");
        }
    }

    @Override
    public String getDetails() {
        return name + " " + status + " " + temperature + "C " + protocol;
    }
}