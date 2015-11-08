package jsp_servlet_automator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class XMLOperationsImpl implements XMLOperations {

    private ProjectInfo projectInfo;
    private FolderOperations folderOperations;
    private Document xmlFile;
    private Element rootElement;
    public XMLOperationsImpl(ProjectInfo projectInfo){
        this.projectInfo = projectInfo;
        folderOperations = new FolderOperationsImpl();
    }




    public void setHomePageServlet(String servletName){
        Element welcomeList = xmlFile.createElement("welcome-file-list");
        Element welcomeFile = xmlFile.createElement("welcome-file");
        welcomeFile.appendChild(xmlFile.createTextNode(servletName));
        welcomeList.appendChild(welcomeFile);
        rootElement.appendChild(welcomeList);
    }

    public void addServletConfiguration(String servletName){
        String finalServletName;
        File servletFile = new File(servletName);
        finalServletName = folderOperations.removeExtension(servletFile.getName());
        String finalServletClass = folderOperations.getReativePathTo(Paths.get(servletName), projectInfo.getClassesFolderPath());
        finalServletClass = StringOperations.changeSlashToDots(finalServletClass+"\\"+folderOperations.removeExtension(servletFile.getName()));

        Element servlet = xmlFile.createElement("servlet");

        Element servlet_cass = xmlFile.createElement("servlet-class");
        servlet_cass.appendChild(xmlFile.createTextNode( finalServletClass ));

        Element servlet_name = xmlFile.createElement("servlet-name");
        servlet_name.appendChild(xmlFile.createTextNode( finalServletName ));

        servlet.appendChild(servlet_cass);
        servlet.appendChild(servlet_name);


        rootElement.appendChild(servlet);


        Element servlet_mapping = xmlFile.createElement("servlet-mapping");

        Element servlet_name_m = xmlFile.createElement("servlet-name");
        servlet_name_m.appendChild(xmlFile.createTextNode(finalServletName));
        servlet_mapping.appendChild(servlet_name_m);

        Element url_pattern = xmlFile.createElement("url-pattern");
        url_pattern.appendChild(xmlFile.createTextNode("/"+finalServletName));
        servlet_mapping.appendChild(url_pattern);

        rootElement.appendChild(servlet_mapping);
    }

    public void addPageConfiguration(String pageName){

    }



    private void removeExistentXml() throws IOException, InterruptedException{
        File xmlFile = projectInfo.getWebXmlFilePath().toFile();
        if( xmlFile.exists())
            folderOperations.removeFolder(projectInfo.getWebXmlFilePath());
    }

    private void createXml() throws ParserConfigurationException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        xmlFile = docBuilder.newDocument();
        rootElement = xmlFile.createElement("web-app");
        //doc.appendChild(rootElement);
    }

    public void initXmlWebConf() throws IOException, ParserConfigurationException, InterruptedException{
        removeExistentXml();
        createXml();
    }


    public void saveXmlFile() throws TransformerConfigurationException, TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        xmlFile.appendChild(rootElement);
        DOMSource source = new DOMSource(xmlFile);
        StreamResult result = new StreamResult(projectInfo.getWebXmlFilePath().toFile());
        transformer.transform(source, result);

    }



}
