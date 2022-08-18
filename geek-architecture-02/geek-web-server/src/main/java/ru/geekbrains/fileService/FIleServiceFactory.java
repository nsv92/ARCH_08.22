package ru.geekbrains.service;

public final class FIleServiceFactory {

    public static FileService create(String args) {

        return  new SimpleFileService(args);
    }

}
