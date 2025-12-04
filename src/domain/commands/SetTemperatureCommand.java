package domain.commands;

import domain.devices.Thermostat;

/**
 * Concrete Command - Set thermostat temperature
 */
public class SetTemperatureCommand implements Command {
    private Thermostat thermostat;
    private double newTemperature;
    private double previousTemperature;

    public SetTemperatureCommand(Thermostat thermostat, double temperature) {
        this.thermostat = thermostat;
        this.newTemperature = temperature;
    }

    @Override
    public void execute() {
        previousTemperature = thermostat.getTemperature();
        thermostat.setTemperature(newTemperature);
    }

    @Override
    public void undo() {
        thermostat.setTemperature(previousTemperature);
    }

    @Override
    public String getDescription() {
        return "Set temperature of " + thermostat.getName() + " to " + newTemperature + "°C";
    }
}

