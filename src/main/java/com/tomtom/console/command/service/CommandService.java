package com.tomtom.console.command.service;

import com.tomtom.console.command.Command;
import com.tomtom.console.exception.CommandNotFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommandService {
    private static CommandService INSTANCE = null;
    private Map<String, Command> commands = new HashMap<>();

    private CommandService() {
    }

    public static CommandService getInstance() {
        if (INSTANCE == null) {
            synchronized (CommandService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CommandService();
                }
            }
        }
        return INSTANCE;
    }

    public void registerCommand(Command command) {
        commands.put(command.getCommandName(), command);
    }

    public Command getCommand(String name) throws CommandNotFoundException {
        if (commands.containsKey(name)) {
            return commands.get(name);
        } else {
            throw new CommandNotFoundException(name + ": command not found");
        }
    }

    public List<Command> getAllCommands() {
        return new LinkedList<>(commands.values());
    }

}
