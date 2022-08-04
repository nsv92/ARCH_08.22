package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseService {

    private PrintWriter output;

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public void okFileResponse(Path path) throws IOException {
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
    }

    public void fileNotFoundResponse() {
        output.println("HTTP/1.1 404 NOT_FOUND");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<h1>Файл не найден!</h1>");
        output.flush();
    }
}
