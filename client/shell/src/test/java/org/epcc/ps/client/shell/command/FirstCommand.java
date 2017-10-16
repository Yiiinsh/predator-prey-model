package org.epcc.ps.client.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
@ShellComponent
public class FirstCommand {
    @ShellMethod("Add tow integers")
    public int add(int a, int b) {
        return a + b;
    }
}
