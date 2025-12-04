package domain.observers;

import domain.devices.SmartDevice;
import utilities.Logger;

/**
 * Concrete Observer - Sends notifications when device state changes
 */
public class NotificationService implements Observer {
    private String serviceName;

    public NotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void update(SmartDevice device, String event) {
        Logger.log(String.format("[%s] NOTIFICATION: Device '%s' - %s",
                serviceName, device.getName(), event));
    }
}

