package org.epcc.ps.client.shell.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author shaohan.yin
 * Created on 29/10/2017
 */
@ShellComponent
public class HelperCommand extends AbstractCommand {

    @ShellMethod("Print current working directory")
    public String pwd() {
        return System.getProperty("user.dir");
    }
}
