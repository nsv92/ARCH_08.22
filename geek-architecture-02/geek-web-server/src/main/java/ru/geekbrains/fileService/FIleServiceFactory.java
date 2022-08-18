package ru.geekbrains.fileService;

public final class FIleServiceFactory {

    public static FileService create(String rootDir) {
        return  new SimpleFileService(rootDir);
    }

}
