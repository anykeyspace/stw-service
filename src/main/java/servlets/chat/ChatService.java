package servlets.chat;

import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.Set;

public class ChatService {
    private Set<ChatWebSocket> webSockets;

    ChatService() {
        webSockets = new ConcurrentHashSet<>();
    }

    public void sendMessage(String message) {
        for (ChatWebSocket user : webSockets) {
            user.sendString(message);
        }
    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }
}
