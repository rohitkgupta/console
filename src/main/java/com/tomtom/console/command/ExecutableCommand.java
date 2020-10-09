package com.tomtom.console.command;

public interface ExecutableCommand extends Command{
    String execute(String... params);
}
