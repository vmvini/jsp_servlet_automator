package jsp_servlet_automator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class SystemCommandsImpl implements SystemCommands {


    private ProjectInfo projectInfo;

    public SystemCommandsImpl(ProjectInfo projectInfo){
       this.projectInfo = projectInfo;
    }

    public void compileServlet(Path servletPath) throws IOException, InterruptedException{
        String jarsPaths = "";
        String servletPackagesPath = "";
        FolderOperations fo = new FolderOperationsImpl();
        servletPackagesPath = fo.getReativePathTo(servletPath, projectInfo.getProjectPath());

        Path targetDirectory = projectInfo.getClassesFolderPath();
        fo.createIfNotExist(targetDirectory);
        for(String f : fo.getAllFilesOfExtension(".jar", projectInfo.getLibraryFolderPath())){
            jarsPaths = jarsPaths + ";" + f;
        }
        String command = "javac -cp " + projectInfo.getClassesFolderPath().toString()+ jarsPaths + " -d " + targetDirectory.toString() + " " + servletPath.toString();

        Process process = Runtime.getRuntime().exec(command);
        //show error messages
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));
        String s = null;
        while((s = stdError.readLine()) != null){
            System.out.println(s);
        }



        //verificando se o servlet compilado est� pronto.
        File servlet = servletPath.toFile();
        String servletName = fo.removeExtension(servlet.getName());

        File compiledFile = new File(targetDirectory.resolve(servletPackagesPath).resolve(servletName).toString()+".class");
        //Não consegui achar o problema nessa parte, não entendi muito bem o comando.
//        while(!compiledFile.canWrite()){
//
//             Thread.sleep(1000);
//        }
        /*
        * javac -cp library;jarPath1;jarPath2 -d targetDirectory  servletPath
        */
    }


}
