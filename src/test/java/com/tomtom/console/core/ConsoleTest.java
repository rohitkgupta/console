package com.tomtom.console.core;

import com.tomtom.console.command.executor.Executor;
import com.tomtom.console.command.impl.Print;
import com.tomtom.console.command.service.CommandService;
import com.tomtom.console.command.executor.builder.*;
import com.tomtom.console.exception.CommandNotFoundException;
import com.tomtom.console.exception.InvalidCommandException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommandService.class, CommandExecutorBuilder.class})
public class ConsoleTest {
    private Console console;
    private CommandService commandService;
    private CommandExecutorBuilder executorBuilder;
    private Executor executor;
    private String command = "abc";
    private String invalidCommand = " ";
    private String notFoundCommand = "afd";
    private String expectedResult = "result";

    @Before
    public void setup() throws InvalidCommandException, CommandNotFoundException {
        PowerMockito.mockStatic(CommandService.class);
        commandService = Mockito.mock(CommandService.class);
        PowerMockito.when(CommandService.getInstance()).thenReturn(commandService);
        PowerMockito.mockStatic(CommandExecutorBuilder.class);
        executorBuilder = Mockito.mock(CommandExecutorBuilder.class);
        PowerMockito.when(CommandExecutorBuilder.getInstance()).thenReturn(executorBuilder);
        executor = Mockito.mock(Executor.class);
        console = Console.getInstance();
        Mockito.when(executorBuilder.getCommandExecutor(invalidCommand)).thenThrow(new InvalidCommandException("Invalid Command"));
        Mockito.when(executorBuilder.getCommandExecutor(notFoundCommand)).thenThrow(new CommandNotFoundException("Command not found"));
        Mockito.when(executor.execute()).thenReturn(expectedResult);
    }

    @Test
    public void testExecuteCommand() throws CommandNotFoundException, InvalidCommandException {
        Mockito.when(executorBuilder.getCommandExecutor(command)).thenReturn(executor);
        String result = console.executeCommand(command);
        Assert.assertEquals(expectedResult, result);
    }

    @Test(expected = InvalidCommandException.class)
    public void testInvalidCommand() throws CommandNotFoundException, InvalidCommandException {
        console.executeCommand(invalidCommand);
    }

    @Test(expected = CommandNotFoundException.class)
    public void testCommandNotFound() throws CommandNotFoundException, InvalidCommandException {
        console.executeCommand(notFoundCommand);
    }

    @Test
    public void testRegisterCommand() {
        Print print = new Print("print");
        console.registerCommand(print);
    }
}
