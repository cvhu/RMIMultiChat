package edu.utexas.ee382vJulien;

import java.util.ArrayList;

public class Chatroom {
    
    private String name;
    private String description;
    private ArrayList<ChatClient> chatClients;
    
    public Chatroom(String name, String description) {
        this.name = name;
        this.description = description;
        this.chatClients = new ArrayList<ChatClient>();
    }
    
    public Integer getClientCount() {
        return chatClients.size();
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
}
