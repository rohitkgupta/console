package com.tomtom.console.command.executor.builder;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.executor.Executor;
import com.tomtom.console.command.executor.impl.CommandExecutor;
import com.tomtom.console.command.impl.Print;
import com.tomtom.console.command.service.CommandService;
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
@PrepareForTest({CommandService.class})
public class CommandExecutorBuilderTest {
    private CommandExecutorBuilder commandExecutorBuilder;
    private CommandService commandService;
    private Command print = new Print("print");

    @Before
    public void setup() throws CommandNotFoundException {
        PowerMockito.mockStatic(CommandService.class);
        commandService = Mockito.mock(CommandService.class);
        PowerMockito.when(CommandService.getInstance()).thenReturn(commandService);
        commandExecutorBuilder = CommandExecutorBuilder.getInstance();
        Mockito.when(commandService.getCommand(print.getCommandName())).thenReturn(print);
    }

    @Test(expected = InvalidCommandException.class)
    public void testInvalidCommand() throws InvalidCommandException, CommandNotFoundException {
        commandExecutorBuilder.getCommandExecutor("  ");
    }

    @Test(expected = InvalidCommandException.class)
    public void testInvalidCommand1() throws InvalidCommandException, CommandNotFoundException {
        commandExecutorBuilder.getCommandExecutor("");
    }

    @Test(expected = InvalidCommandException.class)
    public void testInvalidCommand2() throws InvalidCommandException, CommandNotFoundException {
        commandExecutorBuilder.getCommandExecutor(null);
    }

    @Test(expected = CommandNotFoundException.class)
    public void testCommandNotFound() throws InvalidCommandException, CommandNotFoundException {
        String command = "abc";
        Mockito.when(commandService.getCommand(command)).thenThrow(new CommandNotFoundException(""));
        commandExecutorBuilder.getCommandExecutor(command);
    }

    @Test
    public void testCommandExecutor() throws InvalidCommandException, CommandNotFoundException {
        Executor executor = commandExecutorBuilder.getCommandExecutor(print.getCommandName());
        Assert.assertEquals(true, executor instanceof CommandExecutor);
    }
}
