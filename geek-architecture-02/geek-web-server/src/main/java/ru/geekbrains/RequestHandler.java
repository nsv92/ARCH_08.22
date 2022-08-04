package ru.geekbrains;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    private final Socket socket;

    private final String folder;

    private RequestParser requestParser;

    private FileService fileService;

    private ResponseService responseService;

    public RequestHandler(Socket socket, String folder) {
        this.socket = socket;
        this.folder = folder;
        requestParser = new RequestParser();
        fileService = new FileService(folder);
        responseService = new ResponseService();
    }

    @Override
    public void run() {

        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            requestParser.setInput(input);
            responseService.setOutput(output);
            fileService.setOutput(output);
            String[] parts = requestParser.getRequest();

            try {
                Path path = fileService.getFile(parts[1]);
                responseService.okFileResponse(path);
                fileService.sendFile(path);
            } catch (FileNotFoundException ex) {
                responseService.fileNotFoundResponse();
                return;
            }

            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
