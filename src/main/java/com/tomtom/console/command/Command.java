package com.tomtom.console.command;

public interface Command {
    String getCommandName();

    String execute(String... params);

    String getHelpText();
}
