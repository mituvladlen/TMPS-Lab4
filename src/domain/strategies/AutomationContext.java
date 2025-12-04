package domain.strategies;

import domain.devices.SmartDevice;
import utilities.Logger;

import java.util.List;

/**
 * Context class for Strategy pattern
 * Manages and applies different automation strategies
 */
public class AutomationContext {
    private AutomationStrategy currentStrategy;
    private List<SmartDevice> devices;

    public AutomationContext(List<SmartDevice> devices) {
        this.devices = devices;
    }

    public void setStrategy(AutomationStrategy strategy) {
        this.currentStrategy = strategy;
        Logger.log("Strategy changed to: " + strategy.getStrategyName());
    }

    public void executeStrategy() {
        if (currentStrategy != null) {
            currentStrategy.applyStrategy(devices);
        } else {
            Logger.log("No strategy set!");
        }
    }

    public String getCurrentStrategyName() {
        return currentStrategy != null ? currentStrategy.getStrategyName() : "None";
    }
}

