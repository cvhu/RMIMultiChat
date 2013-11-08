package edu.utexas.ee382vJulien;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * The registry that provides lookup service for all chat clients and chatroom servers.
 * 
 * @author cvhu
 *
 */
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
    
    /**
     * Register a chat client.
     */
    @Override
    public void register(ChatClient client) throws RemoteException {
        chatClientsCount++;
        String clientId = "client#" + chatClientsCount;
        chatClientsMap.put(clientId, client);
        client.registerId(clientId);
        client.showMessage("registered for " + clientId);
        for (ChatroomServer server : chatroomServersMap.values()) {
            client.addChatroomServer(server);
        }
    }

    /**
     * Register a chatroom server.
     */
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
    
    /**
     * De-register a chat client.
     */
    @Override
    public void deregister(ChatClient client) throws RemoteException {
        chatClientsMap.remove(client.getId());
    }
    
    /**
     * De-register a chatroom server.
     */
    @Override
    public void deregister(ChatroomServer server) throws RemoteException {
        chatroomServersMap.remove(server.getId());
        for (ChatClient client : chatClientsMap.values()) {
            try {
                client.closeChatroom(server.getId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Get the corresponding chat client stub object based on the id.
     */
    @Override
    public ChatClient getClient(String clientId) throws RemoteException {
        return chatClientsMap.get(clientId);
    }
    
    /**
     * Update the clients count on all the associated GUI elements.
     */
    @Override
    public void updateJoins(String chatroomId, Integer count)
            throws RemoteException {
        chatroomServersMap.get(chatroomId).updateJoins(count);
        for (ChatClient client : chatClientsMap.values()) {
            try {
                client.updateJoins(chatroomId, count);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
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
