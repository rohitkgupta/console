package com.tomtom.console.terminal;

import com.tomtom.console.core.Console;
import com.tomtom.console.exception.CommandNotFoundException;
import com.tomtom.console.exception.InvalidCommandException;
import com.tomtom.console.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Console.class})
public class TerminalTest {
    private Terminal terminal;
    private Console console;
    private User user;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setup() {
        this.user = new User("test");
        PowerMockito.mockStatic(Console.class);
        console = Mockito.mock(Console.class);
        PowerMockito.when(Console.getInstance()).thenReturn(console);
        System.setOut(new PrintStream(outputStreamCaptor));
        terminal = new Terminal(this.user);
    }

    @Test
    public void testCommandNotFound() throws CommandNotFoundException, InvalidCommandException {
        String command = "abc";
        String message = command + ": command not found";
        PowerMockito.when(console.executeCommand(Matchers.anyString())).thenThrow(new CommandNotFoundException(message));
        terminal.executeCommand(command);
        Assert.assertTrue(outputStreamCaptor.toString()
                .trim().contains(message));
    }

    @Test
    public void testInvalidCommandFound() throws CommandNotFoundException, InvalidCommandException {
        String command = "abc";
        PowerMockito.when(console.executeCommand(Matchers.anyString())).thenThrow(new InvalidCommandException("Invalid Command"));
        terminal.executeCommand(command);
        Assert.assertTrue(outputStreamCaptor.toString()
                .trim().contains(user.getName()));
    }

    @Test
    public void testCommandExecution() throws CommandNotFoundException, InvalidCommandException {
        String command = "abc";
        Mockito.when(console.executeCommand(command)).thenReturn("success");
        terminal.executeCommand(command);
        Assert.assertTrue(outputStreamCaptor.toString()
                .trim().contains(user.getName()));
    }

}
