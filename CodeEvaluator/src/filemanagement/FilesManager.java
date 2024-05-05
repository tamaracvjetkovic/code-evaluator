package filemanagement;

import java.io.File;
import java.util.ArrayList;


public class FilesManager {

    public String directoryName;
    public DirectoryParser directoryParser;
    private final ArrayList<File> loadedFiles;

    public FilesManager(String directoryName) {
        this.directoryName = directoryName;
        this.directoryParser = new DirectoryParser(directoryName);
        this.loadedFiles = loadJavaFiles();
    }

    private ArrayList<File> loadJavaFiles() {
        return directoryParser.getAllFiles();
    }

    public ArrayList<File> getLoadedFiles() {
        return loadedFiles;
    }
}

