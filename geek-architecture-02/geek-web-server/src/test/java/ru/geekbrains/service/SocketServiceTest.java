package ru.geekbrains.service;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SocketServiceTest {

    @Test
    public void testRequestRead() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream inputStream = new ByteArrayInputStream("aaa\nbbb\nccc\n".getBytes(StandardCharsets.UTF_8));
        when(socket.getInputStream()).thenReturn(inputStream);
        SocketService socketService = new SocketService(socket);
        Deque<String> strings = socketService.readRequest();
    }
}
