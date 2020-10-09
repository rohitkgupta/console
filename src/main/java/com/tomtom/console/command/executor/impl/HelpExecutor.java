package com.tomtom.console.command.executor.impl;

import com.tomtom.console.command.HelpTextCommand;
import com.tomtom.console.command.executor.Executor;

public class HelpExecutor implements Executor {
    private HelpTextCommand command;

    public HelpExecutor(HelpTextCommand command) {
        this.command = command;
    }


    @Override
    public String execute() {
        return command.getHelpText();
    }
}
