package com.tomtom.console.command.executor.impl;

import com.tomtom.console.command.ExecutableCommand;
import com.tomtom.console.command.executor.Executor;

public class CommandExecutor implements Executor {
    private ExecutableCommand command;
    private String[] params;

    public CommandExecutor(ExecutableCommand command, String[] params) {
        this.command = command;
        this.params = params;
    }


    @Override
    public String execute() {
        return command.execute(this.params);
    }
}
