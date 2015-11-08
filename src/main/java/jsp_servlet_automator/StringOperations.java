package jsp_servlet_automator;

/**
 * Created by marcusviniv on 10/10/2015.
 */
public class StringOperations {

    public static String changeSlashToDots(String in){
        String out = in.replace("\\", ".");
        StringBuilder outSb = new StringBuilder(out);
        outSb.deleteCharAt(0);
        return outSb.toString();
    }

}
