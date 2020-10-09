package com.tomtom.console.command.impl;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.ExecutableCommand;
import com.tomtom.console.command.HelpTextCommand;
import com.tomtom.console.command.executor.impl.HelpExecutor;
import com.tomtom.console.command.service.CommandService;
import com.tomtom.console.exception.CommandNotFoundException;

import java.util.stream.Collectors;

public class Help implements ExecutableCommand, HelpTextCommand {
    private String name;
    private CommandService commandService = CommandService.getInstance();

    public Help(String name) {
        this.name = name;
    }

    @Override
    public String getCommandName() {
        return this.name;
    }

    @Override
    public String execute(String... params) {
        if (params == null || params.length == 0) {
            return commandService.getAllCommands().stream().map(command -> command.getCommandName()).collect(Collectors.joining("\n"));
        } else {
            String commandName = params[0];
            try {
                Command command = commandService.getCommand(commandName);
                if (command instanceof HelpTextCommand) {
                    return new HelpExecutor((HelpTextCommand) command).execute();
                } else {
                    return getCommandNotFoundMessage(commandName);
                }
            } catch (CommandNotFoundException e) {
                return getCommandNotFoundMessage(commandName);
            }
        }
    }

    private String getCommandNotFoundMessage(String command) {
        return "help: no help topics match `" + command + "`.  Try `help help`.";
    }

    @Override
    public String getHelpText() {
        return "Display helpful information about commands.\n" +
                "Usage : " + this.getCommandName() + " [command]";
    }
}
