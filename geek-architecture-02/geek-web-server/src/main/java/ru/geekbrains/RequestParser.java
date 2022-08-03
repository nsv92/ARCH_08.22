package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    private BufferedReader input;

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public String[] getRequest() throws IOException {
        String firstLine = input.readLine();
        String[] parts = firstLine.split(" ");
        System.out.println(firstLine);
        while (input.ready()) {
            System.out.println(input.readLine());
        }
        return parts;
    }
}
