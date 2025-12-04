package client;

import domain.commands.*;
import domain.devices.*;
import domain.observers.*;
import domain.strategies.*;
import utilities.*;

/**
 * Main client class demonstrating Smart Home Automation System
 *
 * Behavioral Design Patterns Implemented:
 * 1. OBSERVER PATTERN - Devices notify observers about state changes
 * 2. COMMAND PATTERN - Device operations encapsulated as command objects with undo capability
 * 3. STRATEGY PATTERN - Different automation modes (Eco, Comfort, Security)
 */
public class Main {
    public static void main(String[] args) {
        Logger.header("SMART HOME AUTOMATION SYSTEM");

        // Initialize Device Registry
        DeviceRegistry registry = new DeviceRegistry();

        // Create Smart Devices
        Light livingRoomLight = new Light("L001", "Living Room Light");
        Light bedroomLight = new Light("L002", "Bedroom Light");
        Thermostat mainThermostat = new Thermostat("T001", "Main Thermostat");
        SecurityCamera frontCamera = new SecurityCamera("C001", "Front Door Camera");

        // Register devices
        registry.registerDevice(livingRoomLight);
        registry.registerDevice(bedroomLight);
        registry.registerDevice(mainThermostat);
        registry.registerDevice(frontCamera);

        Logger.separator();

        // ============================================================
        // OBSERVER PATTERN DEMONSTRATION
        // ============================================================
        Logger.header("OBSERVER PATTERN - Device State Monitoring");

        // Create observers
        NotificationService mobileNotifications = new NotificationService("Mobile App");
        NotificationService emailNotifications = new NotificationService("Email Service");
        DeviceMonitor systemMonitor = new DeviceMonitor("System Monitor");

        // Attach observers to devices
        livingRoomLight.attach(mobileNotifications);
        livingRoomLight.attach(systemMonitor);

        bedroomLight.attach(emailNotifications);

        mainThermostat.attach(mobileNotifications);
        mainThermostat.attach(systemMonitor);

        frontCamera.attach(mobileNotifications);
        frontCamera.attach(emailNotifications);
        frontCamera.attach(systemMonitor);

        Logger.log("Observers attached to devices\n");

        // Trigger some device changes to show Observer pattern
        Logger.log("--- Triggering device changes ---");
        livingRoomLight.turnOn();
        livingRoomLight.setBrightness(75);
        mainThermostat.turnOn();
        mainThermostat.setTemperature(23.5);
        frontCamera.turnOn();
        frontCamera.startRecording();

        Logger.separator();

        // ============================================================
        // COMMAND PATTERN DEMONSTRATION
        // ============================================================
        Logger.header("COMMAND PATTERN - Device Control with Undo");

        CommandInvoker invoker = new CommandInvoker();

        // Create and execute commands
        Logger.log("--- Executing commands ---\n");

        Command turnOnBedroom = new TurnOnDeviceCommand(bedroomLight);
        invoker.executeCommand(turnOnBedroom);

        Command adjustLivingRoomBrightness = new AdjustBrightnessCommand(livingRoomLight, 100);
        invoker.executeCommand(adjustLivingRoomBrightness);

        Command setTemp = new SetTemperatureCommand(mainThermostat, 25.0);
        invoker.executeCommand(setTemp);

        Command turnOffFrontCamera = new TurnOffDeviceCommand(frontCamera);
        invoker.executeCommand(turnOffFrontCamera);

        Logger.log("");
        invoker.showHistory();

        // Demonstrate undo functionality
        Logger.log("\n--- Undoing last 2 commands ---\n");
        invoker.undoLastCommand();
        invoker.undoLastCommand();

        Logger.log("");
        registry.showAllDevices();

        Logger.separator();

        // ============================================================
        // STRATEGY PATTERN DEMONSTRATION
        // ============================================================
        Logger.header("STRATEGY PATTERN - Automation Modes");

        AutomationContext automationContext = new AutomationContext(registry.getAllDevices());

        // Apply Eco Mode Strategy
        AutomationStrategy ecoMode = new EcoModeStrategy();
        automationContext.setStrategy(ecoMode);
        automationContext.executeStrategy();

        Logger.log("\n--- Current Device States ---");
        registry.showAllDevices();

        Logger.separator();

        // Apply Comfort Mode Strategy
        AutomationStrategy comfortMode = new ComfortModeStrategy();
        automationContext.setStrategy(comfortMode);
        automationContext.executeStrategy();

        Logger.log("\n--- Current Device States ---");
        registry.showAllDevices();

        Logger.separator();

        // Apply Security Mode Strategy
        AutomationStrategy securityMode = new SecurityModeStrategy();
        automationContext.setStrategy(securityMode);
        automationContext.executeStrategy();

        Logger.log("\n--- Current Device States ---");
        registry.showAllDevices();

        Logger.separator();

        // ============================================================
        // FINAL DEMONSTRATION - Combined Patterns
        // ============================================================
        Logger.header("COMBINED DEMONSTRATION - All Patterns Working Together");

        Logger.log("Creating and executing multiple commands while observers watch...\n");

        Command dimAllLights = new AdjustBrightnessCommand(livingRoomLight, 20);
        invoker.executeCommand(dimAllLights);

        Command adjustTemp = new SetTemperatureCommand(mainThermostat, 22.0);
        invoker.executeCommand(adjustTemp);

        Logger.log("\nSwitching to Comfort Mode automatically...");
        automationContext.setStrategy(comfortMode);
        automationContext.executeStrategy();

        Logger.log("\n--- Final Device States ---");
        registry.showAllDevices();

        Logger.separator();

        Logger.header("DEMONSTRATION COMPLETE");
        Logger.log("Successfully demonstrated:");
        Logger.log("  ✓ Observer Pattern - Device state change notifications");
        Logger.log("  ✓ Command Pattern - Encapsulated operations with undo");
        Logger.log("  ✓ Strategy Pattern - Different automation modes");
        Logger.log("\nAll behavioral design patterns working together in harmony!");
        Logger.separator();
    }
}
