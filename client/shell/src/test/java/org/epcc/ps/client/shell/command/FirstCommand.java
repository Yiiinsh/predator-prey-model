package org.epcc.ps.client.shell.command;

import java.util.Scanner;

import org.epcc.ps.client.service.DefaultClienGridService;
import org.epcc.ps.core.entity.environment.Landscape;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author shaohan.yin
 * Created on 16/10/2017
 */
@ShellComponent
public class FirstCommand {
	
	DefaultClienGridService clientGridService=new DefaultClienGridService();
	
    @ShellMethod("Add tow integers")
    public int add(int a, int b) {
        return a + b;
    }
    
    @ShellMethod("Read input and create map")
    public int[][] createMap(int height, int width)
    {
    	System.out.print("Enter map's width and height:\n");
    	int[][] land=new int[height][width];
    	Scanner sc = new Scanner(System.in);
    	for(int i=0;i<height;i++)
    	{
    		for(int j=0;j<width;j++)
    		{
    			System.out.println("Please input["+i+"]["+j+"] element\n");
    			land[i][j]=sc.nextInt();
    		}
    		System.out.println("Next line\n");
    	}
		return null;    	
    }
    
    @ShellMethod("Read mapfile")
    public void readMapFile(String fileName) 
    {
    	Landscape lanscape=clientGridService.getLandScape(fileName);
    }
}
