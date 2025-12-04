package domain.observers;

import domain.devices.SmartDevice;

/**
 * Observer interface for Observer pattern
 */
public interface Observer {
    void update(SmartDevice device, String event);
}

