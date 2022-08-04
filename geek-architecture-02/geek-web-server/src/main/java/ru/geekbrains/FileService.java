package ru.geekbrains;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    private final String folder;

    private PrintWriter output;

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public FileService(String folder) {
        this.folder = folder;
    }

    public Path getFile(String fileName) throws FileNotFoundException {
        Path path = Paths.get(folder, fileName);
        if (!Files.exists(path) || Files.isDirectory(path)) {
            throw new FileNotFoundException();
        } else
            return path;
    }

    public void sendFile(Path path) throws IOException {
        Files.newBufferedReader(path).transferTo(output);
    }
}
