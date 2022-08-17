package ru.geekbrains;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Properties {

    private int port;

    private String rootDir;

    public Properties() throws IOException {

//        Программа видит файл по абсолютному пути
        File propertyFile = new File("D:\\GeekBrains\\!GIT\\ARCH_08.22\\geek-architecture-02\\geek-web-server\\src\\main\\resources\\server.properties");
//        По относительным путям программа не видит файл
//        File propertyFile = new File("server.properties");

//        File propertyFile = new File(getClass().getResource("server.properties").getPath());
//        File propertyFile = new File(".src/main/resources/server.properties");
        Map<String, String> allProperties = new HashMap<>();
        FileReader fr = new FileReader(propertyFile);
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(" = ", 2);
            allProperties.put(line[0], line[1]);
        }
        fr.close();

        this.port = Integer.parseInt(allProperties.get("port"));
        this.rootDir = allProperties.get("root_dir");
    }

    public int getPort() {
        return port;
    }

    public String getRootDir() {
        return rootDir;
    }
}
