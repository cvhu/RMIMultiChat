package edu.utexas.ee382vJulien;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ChatRegistryImpl implements ChatRegistry{
    
    private Integer chatroomServersCount;
    private HashMap<String, ChatroomServer> chatroomServersMap;
    private Integer chatClientsCount;
    private HashMap<String, ChatClient> chatClientsMap;

    protected ChatRegistryImpl() throws RemoteException {
        super();
        chatroomServersCount = 0;
        chatroomServersMap = new HashMap<String, ChatroomServer>();
        chatClientsCount = 0;
        chatClientsMap = new HashMap<String, ChatClient>();
    }

    @Override
    public void register(ChatClient client) throws RemoteException {
        chatClientsCount++;
        String clientId = "client#" + chatClientsCount;
        chatClientsMap.put(clientId, client);
        client.registerId(clientId);
        client.showMessage("registered for " + clientId);
    }

    @Override
    public void register(ChatroomServer server) throws RemoteException {
        chatroomServersCount++;
        String chatroomId = "cr#" + chatroomServersCount;
        server.registerId(chatroomId);
        server.showMessage("registered for server of " + chatroomId);
        chatroomServersMap.put(chatroomId, server);
        for (ChatClient client : chatClientsMap.values()) {
            client.addChatroomServer(server);
            client.showMessage(chatroomId + " is added");
        }
    }

    @Override
    public void deregister() throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getChatrooms() throws RemoteException {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public ChatClient getClient(String clientId) throws RemoteException {
        return chatClientsMap.get(clientId);
    }
    
    public static void main(String[] args) throws Exception {
        try{
            ChatRegistryImpl obj = new ChatRegistryImpl();
            ChatRegistry stub = (ChatRegistry) UnicastRemoteObject.exportObject(obj, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("ChatRegistry", stub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
