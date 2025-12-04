package domain.strategies;

import domain.devices.Light;
import domain.devices.SecurityCamera;
import domain.devices.SmartDevice;
import utilities.Logger;

import java.util.List;

/**
 * Concrete Strategy - Security Mode (enhanced security)
 */
public class SecurityModeStrategy implements AutomationStrategy {

    @Override
    public void applyStrategy(List<SmartDevice> devices) {
        Logger.log("\n=== Applying SECURITY MODE Strategy ===");
        for (SmartDevice device : devices) {
            if (device instanceof Light) {
                Light light = (Light) device;
                light.turnOn();
                light.setBrightness(100); // Maximum brightness
                Logger.log("Security Mode: Maximized " + light.getName() + " to 100%");
            } else if (device instanceof SecurityCamera) {
                SecurityCamera camera = (SecurityCamera) device;
                camera.turnOn();
                camera.setRecordingMode("continuous");
                camera.startRecording();
                Logger.log("Security Mode: Activated " + camera.getName() + " with continuous recording");
            }
        }
        Logger.log("=== SECURITY MODE Applied ===\n");
    }

    @Override
    public String getStrategyName() {
        return "Security Mode Strategy";
    }
}

