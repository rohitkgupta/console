package com.tomtom.console.command.executor.impl;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.executor.Executor;

public class HelpExecutor implements Executor {
    private Command command;

    public HelpExecutor(Command command) {
        this.command = command;
    }


    @Override
    public String execute() {
        return command.getHelpText();
    }
}
