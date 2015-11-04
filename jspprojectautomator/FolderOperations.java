package jspprojectautomator;

import java.io.IOException;
import java.util.List;

/**
 * Created by marcusviniv on 08/10/2015.
 */
public interface FolderOperations {

    void copyFileToDirectory(String filePath, String receiverDirectory);

    void copyFolderToDirectory(String folderPath, String receiverDirectory) throws IOException;

    void removeFolder(String folderPath) throws IOException, InterruptedException;

    List<String> getAllDirectoryFilePaths(String directory);

    List<String> getAllFilesOfExtension(String extension, String directory);

    void createIfNotExist(String path) throws IOException;

    String getReativePathTo(String filePath, String relativeTo );

    void removeInsideFiles(String filePath)throws IOException;

    String removeExtension(String name);



}
