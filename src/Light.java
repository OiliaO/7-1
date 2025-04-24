public class Light extends SmartDevice {
    private int brightness;

    public Light(String name, String protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    @Override
    public void setProperty(String property, String value) {
        if (property.equals("brightness")) {
            try {
                int brightnessValue = Integer.parseInt(value);
                if (brightnessValue < 0 || brightnessValue > 100) {
                    throw new IllegalArgumentException("invalid brightness value");
                }
                this.brightness = brightnessValue;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid brightness value");
            }
        } else if (property.equals("status")) {
            setStatus(value);
        } else {
            throw new IllegalArgumentException("invalid property for light");
        }
    }

    @Override
    public String getDetails() {
        return name + " " + status + " " + brightness + "% " + protocol;
    }
}