package com.tomtom.console.command.impl;

import com.tomtom.console.command.Command;


public class Print implements Command {
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
