package domain.strategies;

import domain.devices.Light;
import domain.devices.SmartDevice;
import domain.devices.Thermostat;
import utilities.Logger;

import java.util.List;

/**
 * Concrete Strategy - Eco Mode (energy saving)
 */
public class EcoModeStrategy implements AutomationStrategy {

    @Override
    public void applyStrategy(List<SmartDevice> devices) {
        Logger.log("\n=== Applying ECO MODE Strategy ===");
        for (SmartDevice device : devices) {
            if (device instanceof Light) {
                Light light = (Light) device;
                light.setBrightness(30); // Dim lights
                Logger.log("Eco Mode: Dimmed " + light.getName() + " to 30%");
            } else if (device instanceof Thermostat) {
                Thermostat thermostat = (Thermostat) device;
                thermostat.setTemperature(20.0); // Lower temperature
                Logger.log("Eco Mode: Set " + thermostat.getName() + " to 20°C");
            }
        }
        Logger.log("=== ECO MODE Applied ===\n");
    }

    @Override
    public String getStrategyName() {
        return "Eco Mode Strategy";
    }
}

