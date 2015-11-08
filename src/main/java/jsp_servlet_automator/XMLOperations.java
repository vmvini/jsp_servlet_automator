package jsp_servlet_automator;

import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by marcusviniv on 08/10/2015.
 */
public interface XMLOperations {


    void addServletConfiguration(String servletName) throws IOException;

    void setHomePageServlet(String servletName);

    void addPageConfiguration(String pageName) throws IOException;

    void initXmlWebConf() throws IOException, ParserConfigurationException, InterruptedException;

    void saveXmlFile() throws TransformerConfigurationException, TransformerException;


}
