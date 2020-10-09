package com.tomtom.console.command.impl;

import com.tomtom.console.command.ExecutableCommand;
import com.tomtom.console.command.HelpableCommand;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time implements ExecutableCommand, HelpableCommand {
    private String name;
    private Format formatter = new SimpleDateFormat("hh:mm:ss a");

    public Time(String name) {
        this.name = name;
    }

    @Override
    public String getCommandName() {
        return this.name;
    }

    @Override
    public String execute(String... params) {
        return formatter.format(new Date());
    }

    @Override
    public String getHelpText() {
        return "Print current time on to standard console\n" +
                "Usage : " + this.getCommandName();
    }
}
