package jsp_servlet_automator;

import java.nio.file.Path;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class ProjectInfo {

    private Path projectPath;

    private Path tomcatPath;

    private String homepage;

    public ProjectInfo(Path projectPath, Path tomcatPath, String homepage){
        this.projectPath = projectPath;
        this.tomcatPath = tomcatPath;
        this.homepage = homepage;
    }

    public Path getTomcatPath(){
        return tomcatPath;
    }

    public Path getWebXmlFilePath(){
        return getProjectPath().resolve("WEB-INF").resolve("web.xml");
    }

    public Path getTomcatWebAppsPath(){
        return tomcatPath.resolve("webapps");
    }

    public String getProjectName(){
        return projectPath.getFileName().toString();

    }

    public Path getTomcatProjectPath(){
        return getTomcatWebAppsPath().resolve(getProjectName());
    }

    public Path getProjectPath(){
        return projectPath;
    }

    public String getHomePage(){
        return homepage;
    }

    public Path getServletSourcePath(){
        return this.projectPath.resolve("servlets");
    }

    public Path getCompiledServletFolderPath(){
        return this.projectPath.resolve("WEB-INF").resolve("classes").resolve("servlets");
    }

    public Path getLibraryFolderPath(){
        return this.projectPath.resolve("WEB-INF").resolve("lib");
    }

    public Path getClassesFolderPath(){
        return this.projectPath.resolve("WEB-INF").resolve("classes");
    }

    public Path getPagesPath(){
        return this.projectPath.resolve("pages");
    }

}
