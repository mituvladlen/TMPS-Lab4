package domain.commands;

/**
 * Command interface for Command pattern
 */
public interface Command {
    void execute();
    void undo();
    String getDescription();
}

