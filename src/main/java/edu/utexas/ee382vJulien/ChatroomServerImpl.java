package edu.utexas.ee382vJulien;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * The actual implementation of ChatroomServer.
 * 
 * @author cvhu
 *
 */
@SuppressWarnings("serial")
public class ChatroomServerImpl extends UnicastRemoteObject implements ChatroomServer{
    
    private Chatroom chatroom;
    private ChatRegistry chatRegistry;
    private PanelChatroomProvider panel;

    protected ChatroomServerImpl(Chatroom chatroom, ChatRegistry chatRegistry) throws RemoteException {
        super();
        this.chatroom = chatroom;
        this.chatRegistry = chatRegistry;
        register();
    }
    
    /**
     * Register the chatroom on the registry.
     * 
     * @throws RemoteException
     */
    public void register() throws RemoteException {
        chatRegistry.register(this);
    }
    
    /**
     * Print message to the standard output.
     */
    @Override
    public void showMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    /**
     * Set chatroom id.
     */
    @Override
    public void registerId(String id) throws RemoteException {
        chatroom.setId(id);
    }
    
    /**
     * Get chatroom id.
     */
    @Override
    public String getId() throws RemoteException {
        return chatroom.getId();
    }
    
    /**
     * Get chatroom name.
     */
    @Override
    public String getName() throws RemoteException {
        return chatroom.getName();
    }
    
    /**
     * Get chatroom description.
     */
    @Override
    public String getDescription() throws RemoteException {
        return chatroom.getDescription();
    }

    /**
     * Get chatroom clients count.
     */
    @Override
    public Integer getClientCount() throws RemoteException {
        return chatroom.getClientCount();
    }
    
    /**
     * Take a join request from a client.
     */
    @Override
    public void join(String clientId) throws RemoteException {
        ChatClient client = chatRegistry.getClient(clientId);
        chatroom.addClient(client);
        chatRegistry.updateJoins(chatroom.getId(), chatroom.getClientCount());
    }
    
    /**
     * Handle a talk request from a client.
     */
    @Override
    public void talk(String clientId, String message) throws RemoteException {
        chatroom.broadcast(chatroom.buildMessage(String.format("%s:%s", clientId, message)));
    }
    
    /**
     * Handle a leave request from the client.
     */
    @Override
    public void leave(String clientId) throws RemoteException {
        chatroom.removeClient(clientId);
        chatRegistry.updateJoins(chatroom.getId(), chatroom.getClientCount());
    }
    
    /**
     * Close the chatroom and deregister it from the registry.
     */
    public void closeChatroom() {
        try {
            chatroom.close();
            chatRegistry.deregister(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Attach the corresponding UI panel on the Chatroom Provider.
     * 
     * @param panel The Chatroom panel to display current status.
     */
    public void attachPanel(PanelChatroomProvider panel) {
        this.panel = panel;
    }
    
    /**
     * Update the clients count on the panel.
     */
    @Override
    public void updateJoins(Integer count) throws RemoteException {
        panel.updateJoins(count);
    }
    
    public static void main(String[] args) throws Exception {
        String host = args[0];
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            ChatRegistry stub = (ChatRegistry) registry.lookup("ChatRegistry");
            new ChatroomServerImpl(new Chatroom("test chatroom", "test chatroom description"), stub);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
