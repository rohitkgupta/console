package com.tomtom.console.core;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.executor.Executor;
import com.tomtom.console.command.parser.CommandParser;
import com.tomtom.console.command.service.CommandService;
import com.tomtom.console.exception.CommandNotFoundException;

public final class Console {
    private static final Console INSTANCE = new Console();
    private CommandService commandService = CommandService.getInstance();
    private CommandParser commandParser = new CommandParser();

    private Console() {
    }

    public static Console getInstance() {
        return INSTANCE;
    }

    public void registerCommand(Command command) {
        commandService.registerCommand(command);
    }

    public String executeCommand(String command) throws CommandNotFoundException {
        Executor executor = commandParser.getCommandExecutor(command);
        if (executor != null) {
            return executor.execute();
        } else {
            return null;
        }
    }

}
