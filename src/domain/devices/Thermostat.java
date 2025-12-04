package domain.devices;

/**
 * Concrete Smart Thermostat device
 */
public class Thermostat extends SmartDevice {
    private double temperature; // in Celsius

    public Thermostat(String deviceId, String name) {
        super(deviceId, name);
        this.temperature = 22.0;
    }

    public void setTemperature(double temperature) {
        if (temperature >= 16.0 && temperature <= 30.0) {
            this.temperature = temperature;
            notifyObservers("Temperature set to " + temperature + "°C");
        }
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String getStatus() {
        return String.format("Thermostat '%s' [%s] - Power: %s, Target Temp: %.1f°C",
                name, deviceId, isOn ? "ON" : "OFF", temperature);
    }
}

