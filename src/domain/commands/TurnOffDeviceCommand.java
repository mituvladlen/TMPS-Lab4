package domain.commands;

import domain.devices.SmartDevice;

/**
 * Concrete Command - Turn off a device
 */
public class TurnOffDeviceCommand implements Command {
    private SmartDevice device;

    public TurnOffDeviceCommand(SmartDevice device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }

    @Override
    public void undo() {
        device.turnOn();
    }

    @Override
    public String getDescription() {
        return "Turn OFF device: " + device.getName();
    }
}

