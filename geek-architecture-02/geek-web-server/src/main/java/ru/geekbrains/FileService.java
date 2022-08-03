package ru.geekbrains;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

//    private String fileName;

    private final String folder;

    public FileService(String folder) {
        this.folder = folder;
    }

    public Path getFile(String fileName) throws FileNotFoundException {
        Path path = Paths.get(folder, fileName);
        if (!Files.exists(path)) {
            throw new FileNotFoundException();
        } else
            return path;
    }
}
