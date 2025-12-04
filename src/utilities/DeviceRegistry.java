package utilities;

import domain.devices.SmartDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry to manage all smart devices
 */
public class DeviceRegistry {
    private Map<String, SmartDevice> devices;

    public DeviceRegistry() {
        this.devices = new HashMap<>();
    }

    public void registerDevice(SmartDevice device) {
        devices.put(device.getDeviceId(), device);
        Logger.log("Device registered: " + device.getName() + " [" + device.getDeviceId() + "]");
    }

    public SmartDevice getDevice(String deviceId) {
        return devices.get(deviceId);
    }

    public List<SmartDevice> getAllDevices() {
        return new ArrayList<>(devices.values());
    }

    public void showAllDevices() {
        Logger.log("Registered Devices:");
        for (SmartDevice device : devices.values()) {
            Logger.log("  - " + device.getStatus());
        }
    }
}

