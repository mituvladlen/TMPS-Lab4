package domain.devices;

/**
 * Concrete Smart Light device
 */
public class Light extends SmartDevice {
    private int brightness; // 0-100

    public Light(String deviceId, String name) {
        super(deviceId, name);
        this.brightness = 50;
    }

    public void setBrightness(int brightness) {
        if (brightness >= 0 && brightness <= 100) {
            this.brightness = brightness;
            notifyObservers("Brightness changed to " + brightness + "%");
        }
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public String getStatus() {
        return String.format("Light '%s' [%s] - Power: %s, Brightness: %d%%",
                name, deviceId, isOn ? "ON" : "OFF", brightness);
    }
}

