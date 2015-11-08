package jsp_servlet_automator;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class ProjectInfo {

    private String projectPath;

    private String tomcatPath;

    private String homepage;

    public ProjectInfo(String projectPath, String tomcatPath, String homepage){
        this.projectPath = projectPath;
        this.tomcatPath = tomcatPath;
        this.homepage = homepage;
    }

    public String getTomcatPath(){
        return tomcatPath;
    }

    public String getWebXmlFilePath(){
        return getProjectPath() + "\\WEB-INF\\web.xml";
    }

    public String getTomcatWebAppsPath(){
        return tomcatPath + "\\webapps";
    }

    public String getProjectName(){
        String[] a = projectPath.split("\\\\");
        int lastIndex = a.length -1;
        return a[lastIndex];

    }

    public String getTomcatProjectPath(){
        return getTomcatWebAppsPath() +"\\"+ getProjectName();
    }

    public String getProjectPath(){
        return projectPath;
    }

    public String getHomePage(){
        return homepage;
    }

    public String getServletSourcePath(){
        return this.projectPath + "\\servlets";
    }

    public String getCompiledServletFolderPath(){
        return this.projectPath + "\\WEB-INF\\classes\\servlets";
    }

    public String getLibraryFolderPath(){
        return this.projectPath + "\\WEB-INF\\lib";
    }

    public String getClassesFolderPath(){
        return this.projectPath + "\\WEB-INF\\classes";
    }

    public String getPagesPath(){
        return this.projectPath + "\\pages";
    }

}
