package com.tomtom.console.terminal;

import com.tomtom.console.core.Console;
import com.tomtom.console.exception.CommandNotFoundException;
import com.tomtom.console.exception.InvalidCommandException;
import com.tomtom.console.user.User;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Terminal {
    private User user;

    private String consolePrefix;
    private Console console = Console.getInstance();

    public Terminal(User user) {
        this.user = user;
        consolePrefix = getConsolePrefix(user);
        printConsolePrefix();
    }

    public void executeCommand(String input) {
        try {
            String result = console.executeCommand(input);
            printResult(result);
        } catch (CommandNotFoundException e) {
            printResult(e.getMessage());
        } catch (InvalidCommandException e) {
        }
        printConsolePrefix();
    }


    private void printConsolePrefix() {
        System.out.print(consolePrefix);
    }

    private void printResult(String result) {
        if (StringUtils.isNotBlank(result)) {
            System.out.println(result);
        }
    }

    private String getConsolePrefix(User user) {
        return getComputerName() + ": " + user.getName() + ">";
    }

    private String getComputerName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }
}
