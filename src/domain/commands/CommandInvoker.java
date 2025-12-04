package domain.commands;

import utilities.Logger;
import java.util.Stack;

/**
 * Command Invoker - Executes commands and maintains command history
 */
public class CommandInvoker {
    private Stack<Command> commandHistory;

    public CommandInvoker() {
        this.commandHistory = new Stack<>();
    }

    public void executeCommand(Command command) {
        Logger.log("Executing: " + command.getDescription());
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            Logger.log("Undoing: " + command.getDescription());
            command.undo();
        } else {
            Logger.log("No commands to undo");
        }
    }

    public void showHistory() {
        if (commandHistory.isEmpty()) {
            Logger.log("Command history is empty");
            return;
        }
        Logger.log("Command History:");
        for (int i = 0; i < commandHistory.size(); i++) {
            Logger.log("  " + (i + 1) + ". " + commandHistory.get(i).getDescription());
        }
    }
}

