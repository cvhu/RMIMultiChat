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
    
    public ChatClientImpl(String host) throws RemoteException {
        super();
        chatroomsMap = new HashMap<String, ChatroomServer>();
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            chatRegistry = (ChatRegistry) registry.lookup("ChatRegistry");
            register();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
    
    @Override
    public String getId() throws RemoteException{
        return this.id;
    }
    
    public void attachWindow(WindowChatClient window) {
        this.window = window;
    }
    
    public static void main(String[] args) throws Exception {
        final ChatClientImpl chatClient = new ChatClientImpl(args[0]);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new WindowChatClient(chatClient);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
