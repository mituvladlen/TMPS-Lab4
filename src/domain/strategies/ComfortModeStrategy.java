package domain.strategies;

import domain.devices.Light;
import domain.devices.SmartDevice;
import domain.devices.Thermostat;
import utilities.Logger;

import java.util.List;

/**
 * Concrete Strategy - Comfort Mode (optimal comfort)
 */
public class ComfortModeStrategy implements AutomationStrategy {

    @Override
    public void applyStrategy(List<SmartDevice> devices) {
        Logger.log("\n=== Applying COMFORT MODE Strategy ===");
        for (SmartDevice device : devices) {
            if (device instanceof Light) {
                Light light = (Light) device;
                light.setBrightness(80); // Bright lights
                Logger.log("Comfort Mode: Set " + light.getName() + " to 80%");
            } else if (device instanceof Thermostat) {
                Thermostat thermostat = (Thermostat) device;
                thermostat.setTemperature(24.0); // Comfortable temperature
                Logger.log("Comfort Mode: Set " + thermostat.getName() + " to 24°C");
            }
        }
        Logger.log("=== COMFORT MODE Applied ===\n");
    }

    @Override
    public String getStrategyName() {
        return "Comfort Mode Strategy";
    }
}

