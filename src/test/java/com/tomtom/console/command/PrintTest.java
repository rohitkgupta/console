package com.tomtom.console.command;

import com.tomtom.console.command.impl.Print;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrintTest {
    private Print print = new Print("print");

    @Test
    public void testExecute() {
        String[] params = {"1", "2", "3"};
        String result = print.execute(params);
        Assert.assertEquals(Stream.of(params).collect(Collectors.joining(" ")), result);
    }

    @Test
    public void testExecute1() {
        String[] params = {"1"};
        String result = print.execute(params);
        Assert.assertEquals(params[0], result);
    }

    @Test
    public void testExecute2() {
        String result = print.execute();
        Assert.assertEquals("", result);
    }
}
