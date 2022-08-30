package ru.geekbrains;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.config.ServerConfigFactory;
import ru.geekbrains.handler.MethodHandlerFactory;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class RxWebServer {

    public static void main(String[] args) throws IOException {
        ServerConfig config = ServerConfigFactory.create(args);

        Observable.<Socket>create(emitter -> {
            try (ServerSocket serverSocket = new ServerSocket(config.getPort());) {
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("New connection");
                    emitter.onNext(socket);
                }
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        }).observeOn(Schedulers.io()).subscribeOn(Schedulers.newThread()).map(socket -> {
            return new SocketService(socket);
        }).subscribe(

                new Observer<>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SocketService socketService) {
                        try {
                            new Thread(new RequestHandler(socketService, new RequestParser(),
                                    MethodHandlerFactory.createAnnotated(socketService,
                                            new ResponseSerializer(), config,
                                            new FileService(config.getWww())))).start();
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Complete!");
                    }
                });
        System.out.println("Press any key");
        System.in.read();
    }
}
