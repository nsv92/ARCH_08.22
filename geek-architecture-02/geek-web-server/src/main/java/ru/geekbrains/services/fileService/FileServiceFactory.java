package ru.geekbrains.services.fileService;

public final class FileServiceFactory {

    public static FileService create(String rootDir) {
        return  new SimpleFileService(rootDir);
    }

}
