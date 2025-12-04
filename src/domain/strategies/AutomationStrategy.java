package domain.strategies;

import domain.devices.SmartDevice;
import java.util.List;

/**
 * Strategy interface for different automation modes
 */
public interface AutomationStrategy {
    void applyStrategy(List<SmartDevice> devices);
    String getStrategyName();
}

