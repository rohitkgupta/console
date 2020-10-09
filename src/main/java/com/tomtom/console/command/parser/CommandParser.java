package com.tomtom.console.command.parser;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.ExecutableCommand;
import com.tomtom.console.command.executor.Executor;
import com.tomtom.console.command.executor.impl.*;
import com.tomtom.console.command.service.CommandService;
import com.tomtom.console.exception.CommandNotFoundException;
import org.apache.commons.lang3.StringUtils;


public class CommandParser {
    private static final String COMMAND_SEPARATOR = " ";
    private CommandService commandService = CommandService.getInstance();


    public Executor getCommandExecutor(String inputCommand) throws CommandNotFoundException {
        if (isValidCommand(inputCommand)) {
            String commandName = getCommand(inputCommand);
            String[] params = getParams(inputCommand);
            Command command = commandService.getCommand(commandName);
            if (command instanceof ExecutableCommand) {
                return new CommandExecutor((ExecutableCommand) command, params);
            } else {
                throw new CommandNotFoundException(commandName + ": command not found");
            }
        }
        return null;
    }

    private String getCommand(String inputCommand) {
        return inputCommand.split(COMMAND_SEPARATOR)[0];
    }

    private String[] getParams(String inputCommand) {
        if (inputCommand.split(COMMAND_SEPARATOR).length > 1) {
            return inputCommand.substring(inputCommand.indexOf(COMMAND_SEPARATOR) + 1).split(COMMAND_SEPARATOR);
        }
        return new String[0];
    }

    private boolean isValidCommand(String inputCommand) {
        return StringUtils.isNotBlank(inputCommand);
    }
}
