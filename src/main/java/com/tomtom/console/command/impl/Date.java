package com.tomtom.console.command.impl;

import com.tomtom.console.command.Command;

import java.text.Format;
import java.text.SimpleDateFormat;

public class Date implements Command {
    private String name;
    private Format formatter = new SimpleDateFormat("dd MMM yyyy");

    public Date(String name) {
        this.name = name;
    }

    @Override
    public String getCommandName() {
        return this.name;
    }

    @Override
    public String execute(String... params) {
        return formatter.format(new java.util.Date());
    }

    @Override
    public String getHelpText() {
        return "Print current date to standard console\n" +
                "Usage : " + this.getCommandName();
    }
}
