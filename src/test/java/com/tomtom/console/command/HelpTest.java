package com.tomtom.console.command;

import com.tomtom.console.command.executor.builder.CommandExecutorBuilder;
import com.tomtom.console.command.impl.Help;
import com.tomtom.console.command.impl.Print;
import com.tomtom.console.command.service.CommandService;
import com.tomtom.console.exception.CommandNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.LinkedList;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CommandService.class})
public class HelpTest {
    private Help help;
    private CommandService commandService;

    @Before
    public void setup() {
        PowerMockito.mockStatic(CommandService.class);
        commandService = Mockito.mock(CommandService.class);
        PowerMockito.when(CommandService.getInstance()).thenReturn(commandService);
        help = new Help("help");
    }

    @Test
    public void testHelpWithoutParam(){
        LinkedList<Command> value = new LinkedList<>();
        value.add(help);
        Mockito.when(commandService.getAllCommands()).thenReturn(value);
        String result = help.execute();
        Assert.assertEquals(help.getCommandName(), result);
    }

    @Test
    public void testHelpWithoutParam1(){
        Mockito.when(commandService.getAllCommands()).thenReturn(new LinkedList<>());
        String result = help.execute();
        Assert.assertEquals("", result);
    }

    @Test
    public void testHelpWithParam() throws CommandNotFoundException {
        String[] params = {help.getCommandName()};
        Mockito.when(commandService.getCommand(help.getCommandName())).thenReturn(help);
        String result = help.execute(params);
        Assert.assertEquals(help.getHelpText(), result);
    }

    @Test
    public void testHelpWithParam1() throws CommandNotFoundException {
        String[] params = {help.getCommandName(), "abc"};
        Mockito.when(commandService.getCommand(help.getCommandName())).thenReturn(help);
        String result = help.execute(params);
        Assert.assertEquals(help.getHelpText(), result);
    }
    @Test
    public void testHelpWithParamCommandNotFound() throws CommandNotFoundException {
        String[] params = {"abc"};
        Mockito.when(commandService.getCommand(params[0])).thenThrow(new CommandNotFoundException(""));
        String result = help.execute(params);
        Assert.assertEquals("help: no help topics match `"+params[0]+"`.  Try `help help`.", result);
    }
}
