package ru.geekbrains.handler;

import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnnotatedMethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        Reflections reflections = new Reflections("ru.geekbrains.handler");
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));

        classes.sort(Comparator.comparingInt(AnnotatedMethodHandlerFactory::getOrder).reversed());
        MethodHandler prev = null;
        try {
            for (Class<?> clazz : classes) {
                Constructor<?> constructor = clazz.getConstructor(MethodHandler.class, SocketService.class, ResponseSerializer.class, FileService.class);
                prev = (MethodHandler) constructor.newInstance(prev, socketService, responseSerializer, fileService);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return prev;
    }

    private static int getOrder(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).order();
    }
}
