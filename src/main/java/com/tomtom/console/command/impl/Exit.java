package com.tomtom.console.command.impl;

import com.tomtom.console.command.ExecutableCommand;
import com.tomtom.console.command.HelpableCommand;


public class Exit implements ExecutableCommand, HelpableCommand {
    private String name;

    public Exit(String name) {
        this.name = name;
    }

    @Override
    public String getCommandName() {
        return this.name;
    }

    @Override
    public String execute(String... params) {
        System.exit(1);
        return null;
    }

    @Override
    public String getHelpText() {
        return "Exit from console\n" +
                "Usage : " + this.getCommandName();
    }
}
