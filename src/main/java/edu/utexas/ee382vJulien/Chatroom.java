package edu.utexas.ee382vJulien;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * This class handles housekeeping tasks associated with a chatroom.
 * 
 * @author cvhu
 *
 */
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
    
    /**
     * Get total joined clients count.
     * 
     * @return The joined clients count.
     */
    public Integer getClientCount() {
        return clientsMap.size();
    }
    
    /**
     * Get the chatroom name.
     * 
     * @return Name of the chatroom.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the chatroom description.
     * 
     * @return Description of the chatroom.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Set the chatroom id.
     * 
     * @param id This id is maintained by the ChatRegistry to be unique.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Get the chatroom id.
     * 
     * @return ID of the chatroom.
     */
    public String getId() {
        return id;
    }
    
    /**
     * To print out chatroom summary.
     */
    public String toString() {
        return String.format("%s(%s)", name, id);
    }
    
    /**
     * Add a new client to join.
     * 
     * @param client The client to add.
     */
    public void addClient(ChatClient client) {
        try {
            clientsMap.put(client.getId(), client);
            broadcast(buildMessage(String.format("%s joined.", client.getId())));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Remove a client to leave.
     * 
     * @param clientId The client to remove.
     */
    public void removeClient(String clientId) {
        clientsMap.remove(clientId);
        broadcast(buildMessage(String.format("%s left.", clientId)));
    }
    
    /**
     * Let all joined clients know that this chatroom has been closed.
     */
    public void close() {
        broadcast(buildMessage("This chatroom has been closed."));
    }
    
    /**
     * Build a standard message with chatroom name as prefix.
     * 
     * @param message Message content to be appended.
     * @return The build message.
     */
    public String buildMessage(String message) {
        return String.format("[%s]%s", name, message);
    }
    
    /**
     * Send the message to every joined clients.
     * 
     * @param message Message to be broadcast.
     */
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
