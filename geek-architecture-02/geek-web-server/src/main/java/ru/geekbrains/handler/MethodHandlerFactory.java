package ru.geekbrains.handler;

import org.reflections.Reflections;
import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer,
                                       ServerConfig serverConfig, FileService fileService) {

        PutMethodHandler putMethodHandler = new PutMethodHandler(null, socketService, responseSerializer, serverConfig);
        PostMethodHandler postMethodHandler = new PostMethodHandler(putMethodHandler, socketService, responseSerializer, serverConfig);
        return new GetMethodHandler(postMethodHandler, socketService, responseSerializer, serverConfig, fileService);
    }

    public static MethodHandler createAnnotated(SocketService socketService, ResponseSerializer responseSerializer,
                                                ServerConfig serverConfig, FileService fileService) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("ru.geekbrains.handler");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Handler.class);
        /*В хэшмэп кладу ордер из аннотации и коснтруктор класса*/
        HashMap<Integer, Constructor<?>> constructorMap = new HashMap<>();
        for (Class<?> x : classes
        ) {
            Handler annotation = x.getAnnotation(Handler.class);
            String method = annotation.method();
            int order = annotation.order();
            Constructor<?> constructor;
            if (!Objects.equals(method, "GET")) {
                constructor = createConstructor(x);
            } else {
                constructor = createGetConstructor(x);
            }
            constructorMap.put(order, constructor);
        }

        /*В хэшмэп кладу ордер и экземпляр класса в обратном порядке (от пут к гет)*/
        HashMap<Integer, MethodHandler> methodHandlerMap = new HashMap<>();
        int size = constructorMap.size();

        for (int i = size - 1; i >= 0; i--) {
            if (i == size - 1) {
                methodHandlerMap.put(i, (MethodHandlerImpl) constructorMap.get(i).newInstance(null, socketService, responseSerializer, serverConfig));
            } else if (i == 0) {
                methodHandlerMap.put(i, (MethodHandlerImpl) constructorMap.get(i).newInstance(constructorMap.get(i + 1), socketService, responseSerializer, serverConfig, fileService));
            } else {
                methodHandlerMap.put(i, (MethodHandlerImpl) constructorMap.get(i).newInstance(constructorMap.get(i + 1), socketService, responseSerializer, serverConfig));
            }
        }
        return methodHandlerMap.get(0);
    }

    private static Constructor<?> createConstructor(Class<?> someClass) throws NoSuchMethodException {
        return someClass.getConstructor(MethodHandlerImpl.class,
                SocketService.class,
                ResponseSerializer.class,
                ServerConfig.class);
    }

    private static Constructor<?> createGetConstructor(Class<?> someClass) throws NoSuchMethodException {
        return someClass.getConstructor(MethodHandlerImpl.class,
                SocketService.class,
                ResponseSerializer.class,
                ServerConfig.class,
                FileService.class);
    }
}
