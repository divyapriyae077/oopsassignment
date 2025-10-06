package assignment;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
interface RequestHandler {
    void handle(HttpExchange exchange) throws IOException;
}
abstract class BaseHandler implements RequestHandler {
    protected void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}

class HelloHandler extends BaseHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        sendResponse(exchange, "Hello! Welcome to our REST API.");
    }
}

class UserHandler extends BaseHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        sendResponse(exchange, "User endpoint reached. Example: GET /users");
    }
}
public class Assignment4{
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Map endpoints to handlers
        server.createContext("/hello", new HttpHandler() {
            HelloHandler handler = new HelloHandler();
            public void handle(HttpExchange exchange) throws IOException {
                handler.handle(exchange);
            }
        });

        server.createContext("/users", new HttpHandler() {
            UserHandler handler = new UserHandler();
            public void handle(HttpExchange exchange) throws IOException {
                handler.handle(exchange);
            }
        });

        server.setExecutor(null);
        System.out.println("Server started at http://localhost:8080/");
        server.start();
    }
}
