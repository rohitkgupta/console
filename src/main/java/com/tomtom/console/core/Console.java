package com.tomtom.console.core;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.executor.builder.*;
import com.tomtom.console.command.service.CommandService;
import com.tomtom.console.exception.CommandNotFoundException;
import com.tomtom.console.exception.InvalidCommandException;

public class Console {
    private static Console INSTANCE = null;
    private CommandService commandService = CommandService.getInstance();
    private CommandExecutorBuilder executorBuilder = CommandExecutorBuilder.getInstance();

    private Console() {
    }

    public static Console getInstance() {
        if (INSTANCE == null) {
            synchronized (Console.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Console();
                }
            }
        }
        return INSTANCE;
    }

    public void registerCommand(Command command) {
        commandService.registerCommand(command);
    }

    public String executeCommand(String command) throws CommandNotFoundException, InvalidCommandException {
        return executorBuilder.getCommandExecutor(command).execute();
    }

}
