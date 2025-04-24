public interface DeviceControl {
    void setStatus(String status);
    void setProperty(String property, String value) throws IllegalArgumentException;
    String getStatus();
    String getDetails();
}