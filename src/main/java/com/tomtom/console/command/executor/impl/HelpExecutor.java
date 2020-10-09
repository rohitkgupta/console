package com.tomtom.console.command.executor.impl;

import com.tomtom.console.command.HelpableCommand;
import com.tomtom.console.command.executor.Executor;

public class HelpExecutor implements Executor {
    private HelpableCommand command;

    public HelpExecutor(HelpableCommand command) {
        this.command = command;
    }


    @Override
    public String execute() {
        return command.getHelpText();
    }
}
