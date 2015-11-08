package jsp_servlet_automator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by marcusviniv on 08/10/2015.
 */
public interface FolderOperations {

    void copyFileToDirectory(Path filePath, Path receiverDirectory);

    void copyFolderToDirectory(Path folderPath, Path receiverDirectory) throws IOException;

    void removeFolder(Path folderPath) throws IOException, InterruptedException;

    List<String> getAllDirectoryFilePaths(Path directory);

    List<String> getAllFilesOfExtension(String extension, Path directory);

    void createIfNotExist(Path path) throws IOException;

    String getReativePathTo(Path filePath, Path relativeTo );

    void removeInsideFiles(Path filePath)throws IOException;

    String removeExtension(String name);



}
