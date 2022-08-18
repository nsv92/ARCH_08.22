package ru.geekbrains.services.fileService;

import org.junit.Assert;
import org.junit.Test;

public class FileServiceTest {

    @Test
    public void test() {
        String rootDir = "D:\\GeekBrains\\!GIT\\ARCH_08.22\\geek-architecture-02\\www";
        String fileName = "index.html";

        SimpleFileService simpleFileService = new SimpleFileService(rootDir);

        FileService simpleFileServiceFromFactory = FileServiceFactory.create(rootDir);

        String file1 = simpleFileService.readFile(fileName);
        String file2 = simpleFileServiceFromFactory.readFile(fileName);

        Assert.assertEquals(file1, file2);
    }
}
