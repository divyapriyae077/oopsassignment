package assignment;
class SmartDevice {
    private String deviceName;
    private boolean isOn;

    public SmartDevice(String deviceName) {
        this.deviceName = deviceName;
        this.isOn = false;
    }

    public void turnOn() throws Exception {
        if (Math.random() > 0.7) { 
            throw new Exception("Power surge detected!");
        }
        isOn = true;
        System.out.println(deviceName + " is turned ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(deviceName + " is turned OFF.");
    }
}

public class Assignment1{
    public static void main(String[] args) {
        SmartDevice fan = new SmartDevice("Ceiling Fan");

        try {
            fan.turnOn();
            System.out.println("Device is operating normally.");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            System.out.println("Attempting to recover...");
        } finally {
            fan.turnOff();
            System.out.println("Cleanup completed.");
        }
    }
}
