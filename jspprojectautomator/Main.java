package jspprojectautomator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ProjectInfo pi = new ProjectInfo("C:\\Users\\marcusviniv\\Desktop\\vmvini\\SitesJSP\\sismovie", "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0", "StartSisMovie");
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
