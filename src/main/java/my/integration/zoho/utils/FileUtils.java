package my.integration.zoho.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**
     * Lists all the file paths of files in a folder
     * @param folder
     * @return
     */
    public static List<String>  listFilePathsForFolder(final File folder) {

        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilePathsForFolder(fileEntry);
            } else {
                files.add(fileEntry.getAbsolutePath());
                //System.out.println(fileEntry.getName());
            }
        }

        return files;
    }
}
