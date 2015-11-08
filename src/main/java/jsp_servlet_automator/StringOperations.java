package jsp_servlet_automator;

import java.io.File;

/**
 * Created by marcusviniv on 10/10/2015.
 */
public class StringOperations {

    public static String changeSlashToDots(String in){
        String out = in.replace(File.separator, ".");
        return out;
    }

}
