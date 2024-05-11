package filemanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;


public class DirectoryParser {

    public File directory;
    private final ArrayList<File> loadedFiles;

    public DirectoryParser(String directoryName) {
        this.directory = new File(directoryName);
        this.loadedFiles = new ArrayList<>();

        loadFiles(directory);
    }

    private void loadFiles(File currentDirectory) {
        for(File file : Objects.requireNonNull(currentDirectory.listFiles())) {
            if(file.isDirectory()) {
                loadFiles(file);
            }
            else {
                boolean hasMatchingExtension = getFileExtension(file).equals("java");
                if (hasMatchingExtension) {
                   loadedFiles.add(file);
                }
            }
        }
    }

    private String getFileExtension(File file) {
        String filename = file.toString();

        int dotPosition = filename.lastIndexOf('.');
        if (dotPosition == 0) {
            return "";
        }

        return filename.substring(dotPosition + 1);
    }

    public ArrayList<File> getAllFiles() {
        return loadedFiles;
    }
}