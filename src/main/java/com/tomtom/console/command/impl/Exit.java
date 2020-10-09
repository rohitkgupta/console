package com.tomtom.console.command.impl;

import com.tomtom.console.command.Command;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exit implements Command {
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
