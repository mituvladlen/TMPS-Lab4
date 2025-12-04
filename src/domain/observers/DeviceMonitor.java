package domain.observers;

import domain.devices.SmartDevice;
import utilities.Logger;

/**
 * Concrete Observer - Monitors device status changes
 */
public class DeviceMonitor implements Observer {
    private String monitorName;

    public DeviceMonitor(String monitorName) {
        this.monitorName = monitorName;
    }

    @Override
    public void update(SmartDevice device, String event) {
        Logger.log(String.format("[%s] MONITOR: Device '%s' [%s] - Event: %s",
                monitorName, device.getName(), device.getDeviceId(), event));
    }
}

