package com.tomtom.console.command.impl;

import com.tomtom.console.command.ExecutableCommand;
import com.tomtom.console.command.HelpableCommand;


public class Print implements ExecutableCommand, HelpableCommand {
    private String name;

    public Print(String name) {
        this.name = name;
    }

    @Override
    public String getCommandName() {
        return this.name;
    }

    @Override
    public String execute(String... params) {
        if (params == null || params.length <= 0) {
            return "";
        }
        return getText(params);
    }

    private String getText(String[] params) {
        return String.join(" ", params).replaceAll("\"", "");
    }

    @Override
    public String getHelpText() {
        return "Print the text passed as a parameter on to standard console\n" +
                "Usage : " + this.getCommandName() + " [message]";
    }
}
