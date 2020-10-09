package com.tomtom.console.command.service;

import com.tomtom.console.command.Command;
import com.tomtom.console.command.impl.Help;
import com.tomtom.console.command.impl.Print;
import com.tomtom.console.exception.CommandNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Collections;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class CommandServiceTest {
    private CommandService commandService;
    private Command print = new Print("print");
    private Command help = new Help("help");

    @Before
    public void setup(){
        commandService = CommandService.getInstance();
    }

    @Test(expected = CommandNotFoundException.class)
    public void test1CommandNotFound() throws CommandNotFoundException {
        Assert.assertEquals(0, commandService.getAllCommands().size());
        commandService.getCommand(print.getCommandName());
    }

    @Test
    public void test2RegisterCommand(){
        Assert.assertEquals(0, commandService.getAllCommands().size());
        commandService.registerCommand(print);
        Assert.assertEquals(1, commandService.getAllCommands().size());
    }

    @Test
    public void test3RegisterCommand(){
        Assert.assertEquals(1, commandService.getAllCommands().size());
        commandService.registerCommand(print);
        Assert.assertEquals(1, commandService.getAllCommands().size());
    }

    @Test
    public void test4RegisterCommand(){
        Assert.assertEquals(1, commandService.getAllCommands().size());
        commandService.registerCommand(help);
        Assert.assertEquals(2, commandService.getAllCommands().size());
    }

    @Test
    public void test5GetCommand() throws CommandNotFoundException {
        Command result = commandService.getCommand(help.getCommandName());
        Assert.assertEquals(help.getCommandName(), result.getCommandName());
    }

    @Test
    public void test6GetCommand() throws CommandNotFoundException {
        Command result = commandService.getCommand(print.getCommandName());
        Assert.assertEquals(print.getCommandName(), result.getCommandName());
    }

    @Test(expected = CommandNotFoundException.class)
    public void test7CommandNotFound() throws CommandNotFoundException {
        commandService.getCommand("abc");
    }

}
