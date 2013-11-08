package edu.utexas.ee382vJulien;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ChatroomServerImpl extends UnicastRemoteObject implements ChatroomServer{
    
    private Chatroom chatroom;
    private ChatRegistry chatRegistry;

    protected ChatroomServerImpl(Chatroom chatroom, ChatRegistry chatRegistry) throws RemoteException {
        super();
        this.chatroom = chatroom;
        this.chatRegistry = chatRegistry;
        register();
    }

    public void register() throws RemoteException {
        chatRegistry.register(this);
    }
    
    @Override
    public void showMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    @Override
    public void registerId(String id) throws RemoteException {
        chatroom.setId(id);
    }
    

    @Override
    public String getId() throws RemoteException {
        return chatroom.getId();
    }
    
    @Override
    public String getName() throws RemoteException {
        return chatroom.getName();
    }

    @Override
    public String getDescription() throws RemoteException {
        return chatroom.getDescription();
    }

    @Override
    public Integer getClientCount() throws RemoteException {
        return chatroom.getClientCount();
    }
    

    @Override
    public void join(String clientId) throws RemoteException {
        ChatClient client = chatRegistry.getClient(clientId);
        chatroom.addClient(client);
    }

    @Override
    public void talk(String clientId, String message) throws RemoteException {
        chatroom.broadcast(chatroom.buildMessage(String.format("%s:%s", clientId, message)));
    }

    @Override
    public void leave(String clientId) throws RemoteException {
        // TODO Auto-generated method stub
        
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
