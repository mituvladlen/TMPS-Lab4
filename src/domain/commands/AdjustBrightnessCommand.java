package domain.commands;

import domain.devices.Light;

/**
 * Concrete Command - Adjust light brightness
 */
public class AdjustBrightnessCommand implements Command {
    private Light light;
    private int newBrightness;
    private int previousBrightness;

    public AdjustBrightnessCommand(Light light, int brightness) {
        this.light = light;
        this.newBrightness = brightness;
    }

    @Override
    public void execute() {
        previousBrightness = light.getBrightness();
        light.setBrightness(newBrightness);
    }

    @Override
    public void undo() {
        light.setBrightness(previousBrightness);
    }

    @Override
    public String getDescription() {
        return "Adjust brightness of " + light.getName() + " to " + newBrightness + "%";
    }
}

