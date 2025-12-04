# Topic: Behavioral Design Patterns

**Author:** Mitu Vladlen  
**Group:** FAF-232

---

## Objectives

* Study and understand Behavioral Design Patterns
* Implement at least 3 behavioral design patterns in a practical project
* Demonstrate how behavioral patterns improve communication between objects
* Create a well-structured project with clear separation of concerns

---

## Used Design Patterns

### 1. Observer Pattern
The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. This pattern is useful for implementing distributed event handling systems.

**Key Components:**
- **Subject (Observable)**: Maintains a list of observers and notifies them of state changes
- **Observer**: Interface that defines the update method
- **ConcreteObserver**: Implements the Observer interface and maintains a reference to the Subject

### 2. Command Pattern
The Command pattern encapsulates a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

**Key Components:**
- **Command Interface**: Declares an interface for executing operations
- **ConcreteCommand**: Implements the Command interface and defines the binding between a Receiver and an action
- **Invoker**: Asks the command to carry out the request
- **Receiver**: Knows how to perform the operations

### 3. Strategy Pattern
The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

**Key Components:**
- **Strategy Interface**: Common interface for all supported algorithms
- **ConcreteStrategy**: Implements the algorithm using the Strategy interface
- **Context**: Maintains a reference to a Strategy object and delegates algorithm execution to it

---

## Implementation

### 1. Observer Pattern Implementation

The Observer pattern is implemented through the `SmartDevice` abstract class acting as the Subject, and various observer implementations.

**SmartDevice.java (Subject):**
```java
public abstract class SmartDevice {
    protected String deviceId;
    protected String name;
    protected boolean isOn;
    protected List<Observer> observers;

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
}
```

**Observer Interface:**
```java
public interface Observer {
    void update(SmartDevice device, String event);
}
```

**Concrete Observers:**
- `NotificationService`: Sends notifications to users when devices change state
- `DeviceMonitor`: Logs and monitors device activities

**Usage Example:**
```java
Light livingRoomLight = new Light("L001", "Living Room Light");
NotificationService mobileNotifications = new NotificationService("Mobile App");
livingRoomLight.attach(mobileNotifications);
livingRoomLight.turnOn(); // Observer gets notified
```

---

### 2. Command Pattern Implementation

The Command pattern encapsulates device operations as command objects with support for undo functionality.

**Command Interface:**
```java
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
```

**Concrete Commands:**
- `TurnOnDeviceCommand` / `TurnOffDeviceCommand`: Control device power
- `AdjustBrightnessCommand`: Adjust light brightness
- `SetTemperatureCommand`: Set thermostat temperature

**Command Invoker:**
```java
public class CommandInvoker {
    private Stack<Command> commandHistory;

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
        }
    }
}
```

**Usage Example:**
```java
CommandInvoker invoker = new CommandInvoker();
Command turnOn = new TurnOnDeviceCommand(light);
invoker.executeCommand(turnOn);
invoker.undoLastCommand(); // Undoes the operation
```

---

### 3. Strategy Pattern Implementation

The Strategy pattern allows switching between different automation modes dynamically.

**Strategy Interface:**
```java
public interface AutomationStrategy {
    void applyStrategy(List<SmartDevice> devices);
    String getStrategyName();
}
```

**Concrete Strategies:**
- `EcoModeStrategy`: Energy-saving mode (dim lights, lower temperature)
- `ComfortModeStrategy`: Optimal comfort mode (bright lights, comfortable temperature)
- `SecurityModeStrategy`: Security mode (maximum brightness, continuous camera recording)

**Context:**
```java
public class AutomationContext {
    private AutomationStrategy currentStrategy;
    private List<SmartDevice> devices;

    public void setStrategy(AutomationStrategy strategy) {
        this.currentStrategy = strategy;
    }

    public void executeStrategy() {
        currentStrategy.applyStrategy(devices);
    }
}
```

**Usage Example:**
```java
AutomationContext context = new AutomationContext(devices);
context.setStrategy(new EcoModeStrategy());
context.executeStrategy(); // Applies eco mode to all devices

context.setStrategy(new ComfortModeStrategy());
context.executeStrategy(); // Switches to comfort mode
```

---

## How Patterns Work Together

The three behavioral patterns work in harmony within the Smart Home Automation System:

1. **Observer Pattern** provides real-time notifications when devices change state
2. **Command Pattern** executes device operations with undo capability
3. **Strategy Pattern** applies different automation modes based on user preferences

### Example Flow:
```
User → Strategy (Comfort Mode) → Commands (Adjust devices) → Devices (Change state) → Observers (Get notified)
```

When a user selects Comfort Mode:
- The **Strategy** determines what settings to apply
- Multiple **Commands** are created and executed
- Devices change their state
- All **Observers** are notified of the changes

---

## Results

*[Insert screenshot here]*

---

## Conclusions

This laboratory work successfully demonstrated the implementation and practical application of three key behavioral design patterns: Observer, Command, and Strategy. Through the development of a Smart Home Automation System, I gained deep understanding of how these patterns facilitate flexible communication between objects.

The Observer pattern proved invaluable for implementing a notification system where multiple components react to device state changes without tight coupling. The Command pattern elegantly encapsulated device operations as objects, enabling command history tracking and undo/redo functionality. The Strategy pattern showcased how different automation behaviors can be swapped dynamically at runtime, allowing the system to easily switch between Eco, Comfort, and Security modes.

Working with behavioral patterns reinforced important software engineering principles such as single responsibility, open/closed principle, and dependency inversion. The combination of all three patterns created a robust, flexible system where objects communicate through well-defined protocols rather than direct method calls, making the system more modular and easier to maintain. This practical experience will be directly applicable to real-world software development, particularly in event-driven systems and complex business logic implementations.

