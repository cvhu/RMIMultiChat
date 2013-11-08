package edu.utexas.ee382vJulien;

import java.rmi.RemoteException;
import java.util.HashMap;

public class Chatroom {

    private String id;
    private String name;
    private String description;
    private HashMap<String, ChatClient> clientsMap;
    
    public Chatroom(String name, String description) {
        this.name = name;
        this.description = description;
        this.clientsMap = new HashMap<String, ChatClient>();
    }
    
    public Integer getClientCount() {
        return clientsMap.size();
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public String toString() {
        return String.format("%s(%s)", name, id);
    }
    
    public void addClient(ChatClient client) {
        try {
            clientsMap.put(client.getId(), client);
            broadcast(buildMessage(String.format("%s joined.", client.getId())));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void removeClient(String clientId) {
        clientsMap.remove(clientId);
        broadcast(buildMessage(String.format("%s left.", clientId)));
    }
    
    public void close() {
        broadcast(buildMessage("This chatroom has been closed."));
    }
    
    public String buildMessage(String message) {
        return String.format("[%s]%s", name, message);
    }
    
    public void broadcast(String message) {
        for (ChatClient client : clientsMap.values()) {
            try {
                client.printMessage(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
