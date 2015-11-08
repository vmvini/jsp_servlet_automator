package jsp_servlet_automator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class FolderOperationsImpl implements FolderOperations {

    public void copyFileToDirectory(Path filePath, Path receiverDirectory){
    }

    public void copyFolderToDirectory(Path folderPath, Path receiverDirectory) throws IOException{
        createIfNotExist(receiverDirectory);
        FileUtils.copyDirectory(folderPath.toFile(), receiverDirectory.toFile());
    }

    public void removeFolder(Path folderPath) throws IOException, InterruptedException{
        File file = folderPath.toFile();

        while(file.exists()){
            try {
                FileUtils.forceDelete(file);
            }
            catch(IOException e){
                System.out.println("erro ao deletar, deletar novamente em alguns segundos.");
                Thread.sleep(1000);
            }
        }



    }


    public List<String> getAllDirectoryFilePaths(Path directory){
        File folder = directory.toFile();
        List<String> paths = new ArrayList<>();
        File[]  files = folder.listFiles();
        if(files != null){
            for(File f : files){
                if (f.isFile())
                    paths.add(f.getAbsolutePath());
                else{
                    List<String> filePaths = getAllDirectoryFilePaths(f.toPath().toAbsolutePath());
                    paths.addAll(filePaths);
                }
            }
        }
        return paths;
    }

    public List<String> getAllFilesOfExtension(String extension, Path directory){
        List<String> allFiles = getAllDirectoryFilePaths(directory);
        List<String> extensioFiles = new ArrayList<>();
        for(String f : allFiles){
            if(f.contains(extension)){
                extensioFiles.add(f);
            }
        }
        return extensioFiles;
    }


    public void createIfNotExist(Path path) throws IOException{
        File folder = path.toFile();
        if(!folder.exists()){
            FileUtils.forceMkdir(folder);
        }
    }

    public String getReativePathTo(Path filePath, Path relativeTo ){
        return relativeTo.relativize(filePath).getParent().toString();
    }


    public void removeInsideFiles(Path filePath)throws IOException{
        if(filePath.toFile().exists())
            FileUtils.cleanDirectory(filePath.toFile());
    }

    public String removeExtension(String name){
        String r[] = name.split("\\.");
        return r[0];
    }
    
    
}
