package jsp_servlet_automator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Path projectPath = Paths.get("C:", "Users","Junior","Documents","NetBeansProjects","teste","src","java");
        Path tomcat = Paths.get("C:", "Program Files", "Apache Software Foundation","Tomcat 8.0");
        ProjectInfo pi = new ProjectInfo(projectPath, tomcat, "StartSisMovie");
        Automator a = new Automator(pi);
        try {
            a.run();
        }
        catch(IOException e){
            System.out.println(e);
        }
        catch(TransformerConfigurationException e){
            System.out.println(e);
        }
        catch(TransformerException e){
            System.out.println(e);
        }
        catch(ParserConfigurationException e){
            System.out.println(e);
        }
        catch(InterruptedException e){
            System.out.println(e);
        }



    }
}
