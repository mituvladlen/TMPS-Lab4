package domain.commands;

import domain.devices.SmartDevice;

/**
 * Concrete Command - Turn on a device
 */
public class TurnOnDeviceCommand implements Command {
    private SmartDevice device;

    public TurnOnDeviceCommand(SmartDevice device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }

    @Override
    public void undo() {
        device.turnOff();
    }

    @Override
    public String getDescription() {
        return "Turn ON device: " + device.getName();
    }
}

