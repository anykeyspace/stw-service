package servlets.chat;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {
    private Set<ChatWebSocket> webSockets;

    ChatService() {
        webSockets = ConcurrentHashMap.newKeySet();
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
