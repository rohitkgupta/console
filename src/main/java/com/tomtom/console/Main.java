package com.tomtom.console;

import com.tomtom.console.core.ConsoleInitializer;
import com.tomtom.console.terminal.Terminal;
import com.tomtom.console.user.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsoleInitializer.initializeConsole();
        System.out.print("Please enter your username to open terminal:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        Terminal terminal = new Terminal(new User(userName));
        while (scanner.hasNextLine()) {
            terminal.executeCommand(scanner.nextLine());
        }
    }
}
