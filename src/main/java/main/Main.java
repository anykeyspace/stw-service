package main;

import accounts.AccountController;
import accounts.AccountService;
import accounts.ControllerMBean;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AccountServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import servlets.chat.WebSocketChatServlet;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        try (InputStream inputStream = new FileInputStream("config.properties")) {
            properties.load(inputStream);

            System.out.println(properties.getProperty("database"));
            System.out.println(properties.getProperty("dbuser"));
            System.out.println(properties.getProperty("dbpassword"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        AccountService accountService = new AccountService();

        accountService.addNewUser("admin", "admin", "admin");
        accountService.addNewUser("test", "test", "test");

        AccountController accountController = new AccountController(accountService);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("Admin:type=AccountServerController.usersLimit");
        StandardMBean mBean = new StandardMBean(accountController, ControllerMBean.class);
        mBeanServer.registerMBean(mBean, objectName);

        AccountServlet accountServlet = new AccountServlet(accountController);
        SignUpServlet signUpServlet = new SignUpServlet(accountService);
        SignInServlet signInServlet = new SignInServlet(accountService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(accountServlet), "/admin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
