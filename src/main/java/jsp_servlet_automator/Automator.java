package jsp_servlet_automator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by marcusviniv on 08/10/2015.
 */
public class Automator {



    private FolderOperations folderOperations;
    private XMLOperations xmlOperations;
    private SystemCommands systemCommands;

    private ProjectInfo projectInfo;

    public Automator(ProjectInfo projectInfo){
        systemCommands = new SystemCommandsImpl(projectInfo);
        this.projectInfo = projectInfo;
        folderOperations = new FolderOperationsImpl();
        xmlOperations = new XMLOperationsImpl(projectInfo);
    }




    public void compileAllServlets() throws IOException, InterruptedException{

        folderOperations.removeInsideFiles(projectInfo.getCompiledServletFolderPath());
        List<String> filePaths = folderOperations.getAllFilesOfExtension(".java", projectInfo.getServletSourcePath());
        for(String f : filePaths){
            systemCommands.compileServlet(Paths.get(f));
        }

    }

    public void addHomePageXMl(String name){
        xmlOperations.setHomePageServlet(name);
    }

    public void addPagesToXML() throws IOException{
        List<String> pagesPaths = folderOperations.getAllDirectoryFilePaths(projectInfo.getPagesPath());
        for(String p : pagesPaths){
            xmlOperations.addPageConfiguration(p);
        }
    }


    public void addServletsToXML() throws IOException{

        List<String> filePaths = folderOperations.getAllFilesOfExtension(".class", projectInfo.getCompiledServletFolderPath());
        for(String f : filePaths){
            xmlOperations.addServletConfiguration(f);
        }
    }


    public void deleteTomcatProjectFolder() throws IOException, InterruptedException{
        folderOperations.removeFolder(projectInfo.getTomcatProjectPath());
    }

    public void sendProjectToTomcatFolder() throws IOException{

        Path from = projectInfo.getProjectPath();
        Path to = projectInfo.getTomcatWebAppsPath().resolve(projectInfo.getProjectName());
        folderOperations.copyFolderToDirectory(projectInfo.getProjectPath(), projectInfo.getTomcatWebAppsPath().resolve(projectInfo.getProjectName()));
    }


    public void run() throws IOException, ParserConfigurationException, TransformerException, TransformerConfigurationException, InterruptedException{

        compileAllServlets();

        xmlOperations.initXmlWebConf();
        addServletsToXML();
        addHomePageXMl(projectInfo.getHomePage());
        //addPagesToXML();
        xmlOperations.saveXmlFile();
        deleteTomcatProjectFolder();
        sendProjectToTomcatFolder();

    }



}
