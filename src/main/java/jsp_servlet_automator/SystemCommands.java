package jsp_servlet_automator;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by marcusviniv on 08/10/2015.
 */
public interface SystemCommands {

    void compileServlet(Path servletPath) throws IOException, InterruptedException;



}
