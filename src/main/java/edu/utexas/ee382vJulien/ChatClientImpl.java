package edu.utexas.ee382vJulien;

import java.awt.EventQueue;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient{
    
    private String id;
    private ChatRegistry chatRegistry;
    private WindowChatClient window;
    private HashMap<String, ChatroomServer> chatroomsMap;
    
    public ChatClientImpl(final String host) throws RemoteException {
        super();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    WindowChatClient window = new WindowChatClient(ChatClientImpl.this);
                    chatroomsMap = new HashMap<String, ChatroomServer>();
                    try{
                        Registry registry = LocateRegistry.getRegistry(host);
                        chatRegistry = (ChatRegistry) registry.lookup("ChatRegistry");
                        register();
                        window.setTitle(id);
                    } catch (NotBoundException e) {
                        e.printStackTrace();
                    } catch (AccessException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void talk(String chatroomId, String message) {
        ChatroomServer server = chatroomsMap.get(chatroomId);
        try {
            server.talk(id, message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void register() throws RemoteException {
        chatRegistry.register(this);
    }
    
    public void closeClient() {
        try {
            chatRegistry.deregister(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void showMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    @Override
    public void printMessage(String message) throws RemoteException {
        window.showMessage(message);
    }

    @Override
    public void addChatroomServer(ChatroomServer server) throws RemoteException {
        chatroomsMap.put(server.getId(), server);
        System.out.println("Added chatroom server " + server.getId());
        window.addChatroom(server);
    }
    

    @Override
    public void registerId(String id) throws RemoteException {
        this.id = id;
        System.out.println("register id: " + id);
    }
    
    public void joinChatroom(String chatroomId) {
        System.out.println("join chatroom " + chatroomId);
        ChatroomServer server = chatroomsMap.get(chatroomId);
        try {
            server.join(this.id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    public void leaveChatroom(String chatroomId) {
        System.out.println("leave chatroom " + chatroomId);
        ChatroomServer server = chatroomsMap.get(chatroomId);
        try {
            server.leave(this.id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getId() throws RemoteException{
        return this.id;
    }
   
    @Override
    public void closeChatroom(String chatroomId) throws RemoteException {
        window.removeChatroom(chatroomId);
    }
    
    public void attachWindow(WindowChatClient window) {
        this.window = window;
    }
    
    @Override
    public void updateJoins(String chatroomId, Integer count)
            throws RemoteException {
        window.updateJoins(chatroomId, count);
    }
    
    public static void main(String[] args) throws Exception {
        new ChatClientImpl(args[0]);
    }
}
