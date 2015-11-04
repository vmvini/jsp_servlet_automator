package jspprojectautomator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class SystemCommandsImpl implements SystemCommands {


    private ProjectInfo projectInfo;

    public SystemCommandsImpl(ProjectInfo projectInfo){
       this.projectInfo = projectInfo;
    }

    public void compileServlet(String servletPath) throws IOException, InterruptedException{
        String jarsPaths = "";
        String servletPackagesPath = "";
        FolderOperations fo = new FolderOperationsImpl();
        servletPackagesPath = fo.getReativePathTo(servletPath, projectInfo.getProjectPath());

        String targetDirectory = projectInfo.getClassesFolderPath();
        fo.createIfNotExist(targetDirectory);
        for(String f : fo.getAllFilesOfExtension(".jar", projectInfo.getLibraryFolderPath())){
            jarsPaths = jarsPaths + ";" + f;
        }
        String command = "javac -cp " + projectInfo.getClassesFolderPath() + jarsPaths + " -d " + targetDirectory + " " + servletPath;

        Process process = Runtime.getRuntime().exec(command);
        //show error messages
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));
        String s = null;
        while((s = stdError.readLine()) != null){
            System.out.println(s);
        }



        //verificando se o servlet compilado está pronto.
        File servlet = new File(servletPath);
        String servletName = fo.removeExtension(servlet.getName());

        File compiledFile = new File(targetDirectory + servletPackagesPath +"\\"+ servletName + ".class");

        while(!compiledFile.canWrite()){

             Thread.sleep(1000);
        }
        /*
        * javac -cp library;jarPath1;jarPath2 -d targetDirectory  servletPath
        */
    }


}
