package com.tomtom.console.core;

import com.tomtom.console.command.impl.*;

public class ConsoleInitializer {
    private ConsoleInitializer() {
    }


    public static void initializeConsole() {
        Console.getInstance().registerCommand(new Print("print"));
        Console.getInstance().registerCommand(new Time("time"));
        Console.getInstance().registerCommand(new Exit("exit"));
        Console.getInstance().registerCommand(new Help("help"));
        Console.getInstance().registerCommand(new Date("date"));
    }
}
