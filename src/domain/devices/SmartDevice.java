package domain.devices;

import domain.observers.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all smart devices
 * Implements Subject role in Observer pattern
 */
public abstract class SmartDevice {
    protected String deviceId;
    protected String name;
    protected boolean isOn;
    protected List<Observer> observers;

    public SmartDevice(String deviceId, String name) {
        this.deviceId = deviceId;
        this.name = name;
        this.isOn = false;
        this.observers = new ArrayList<>();
    }

    // Observer pattern methods
    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(this, event);
        }
    }

    // Device operations
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            notifyObservers("Device turned ON");
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            notifyObservers("Device turned OFF");
        }
    }

    public abstract String getStatus();

    // Getters
    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }
}

