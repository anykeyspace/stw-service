package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        // Создаем обработчик
        Frontend frontend = new Frontend();

        // Создаем сервер Jetty. Основной класс библиотеки Jetty.
        Server server = new Server(8080); // Указываем порт

        // Создаем сервлет-контейнер
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);

        // Передаем в него обработчик и запрос
        context.addServlet(new ServletHolder(frontend), "/mirror");

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
