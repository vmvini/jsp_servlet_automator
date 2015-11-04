package jspprojectautomator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Created by marcusviniv on 09/10/2015.
 */
public class FolderOperationsImpl implements FolderOperations {

    public void copyFileToDirectory(String filePath, String receiverDirectory){
    }

    public void copyFolderToDirectory(String folderPath, String receiverDirectory) throws IOException{
        createIfNotExist(receiverDirectory);
        FileUtils.copyDirectory(new File(folderPath), new File(receiverDirectory));
    }

    public void removeFolder(String folderPath) throws IOException, InterruptedException{
        File file = new File(folderPath);

        while(file.exists()){
            try {
                FileUtils.forceDelete(new File(folderPath));
            }
            catch(IOException e){
                System.out.println("erro ao deletar, deletar novamente em alguns segundos.");
                Thread.sleep(1000);
            }
        }



    }


    public List<String> getAllDirectoryFilePaths(String directory){
        File folder = new File(directory);
        List<String> paths = new ArrayList<String>();
        File[]  files = folder.listFiles();
        for(File f : files){
            if (f.isFile())
                paths.add(f.getAbsolutePath());
            else{
                List<String> filePaths = getAllDirectoryFilePaths(f.getAbsolutePath());
                paths.addAll(filePaths);
            }
        }
        return paths;
    }

    public List<String> getAllFilesOfExtension(String extension, String directory){
        List<String> allFiles = getAllDirectoryFilePaths(directory);
        List<String> extensioFiles = new ArrayList<String>();
        for(String f : allFiles){
            if(f.contains(extension)){
                extensioFiles.add(f);
            }
        }
        return extensioFiles;
    }


    public void createIfNotExist(String path) throws IOException{
        File folder = new File(path);
        if(!folder.exists()){
            FileUtils.forceMkdir(folder);
        }
    }

    public String getReativePathTo(String filePath, String relativeTo ){
        //filePath = C:\Users\marcusviniv\Desktop\vmvini\SitesJSP\controledesalas\servlets\register\arquivo.java

        //relativeTo = C:\Users\marcusviniv\Desktop\vmvini\SitesJSP\controledesalas\servlets\

        File file = new File(filePath);
        File parent = file.getParentFile();
        StringBuilder parentPath = new StringBuilder(parent.getAbsolutePath());

        return parentPath.delete(0, relativeTo.length()).toString();
    }


    public void removeInsideFiles(String filePath)throws IOException{
        if(new File(filePath).exists())
            FileUtils.cleanDirectory(new File(filePath));
    }

    public String removeExtension(String name){
        String r[] = name.split("\\.");
        return r[0];
    }
}
