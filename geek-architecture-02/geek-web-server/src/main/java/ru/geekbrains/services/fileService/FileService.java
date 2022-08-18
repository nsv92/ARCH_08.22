package ru.geekbrains.services.fileService;

public interface FileService {

    String readFile(String fileName);

    boolean exists(String filename);

    boolean isDirectory(String filename);
}
