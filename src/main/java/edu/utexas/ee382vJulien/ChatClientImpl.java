package edu.utexas.ee382vJulien;

import java.awt.EventQueue;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * This class implements the actual chat clients.
 * 
 * @author cvhu
 *
 */
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
    
    /**
     * Talk a message.
     * 
     * @param chatroomId Chatroom to talk to.
     * @param message Content of the talk.
     */
    public void talk(String chatroomId, String message) {
        ChatroomServer server = chatroomsMap.get(chatroomId);
        try {
            server.talk(id, message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Register the client on the chat registry.
     * 
     * @throws RemoteException
     */
    public void register() throws RemoteException {
        chatRegistry.register(this);
    }
    
    /**
     * Deregister the client when closing.
     */
    public void closeClient() {
        try {
            chatRegistry.deregister(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Show message on the console.
     */
    @Override
    public void showMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    /**
     * Print out message at the GUI.
     */
    @Override
    public void printMessage(String message) throws RemoteException {
        window.showMessage(message);
    }

    /**
     * Add a new chatroom server to both the GUI and a Map.
     */
    @Override
    public void addChatroomServer(ChatroomServer server) throws RemoteException {
        chatroomsMap.put(server.getId(), server);
        System.out.println("Added chatroom server " + server.getId());
        window.addChatroom(server);
    }
    
    /**
     * Set the unique id specified by the chat registry.
     */
    @Override
    public void registerId(String id) throws RemoteException {
        this.id = id;
        System.out.println("register id: " + id);
    }
    
    /**
     * Send a join request to the chatroom server and handle the GUI correctly.
     * 
     * @param chatroomId
     */
    public void joinChatroom(String chatroomId) {
        System.out.println("join chatroom " + chatroomId);
        ChatroomServer server = chatroomsMap.get(chatroomId);
        try {
            window.joinChatroom(server);
            server.join(this.id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Send a leave request to the chatroom server and delegate the event to the GUI.
     * 
     * @param chatroomId
     */
    public void leaveChatroom(String chatroomId) {
        System.out.println("leave chatroom " + chatroomId);
        ChatroomServer server = chatroomsMap.get(chatroomId);
        try {
            window.leaveChatroom(chatroomId);
            server.leave(this.id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ID getter.
     */
    @Override
    public String getId() throws RemoteException{
        return this.id;
    }
   
    /**
     * Handle the close chatroom event.
     */
    @Override
    public void closeChatroom(String chatroomId) throws RemoteException {
        window.removeChatroom(chatroomId);
    }
    
    /**
     * Set the GUI window object.
     * 
     * @param window The corresponding chat client GUI window.
     */
    public void attachWindow(WindowChatClient window) {
        this.window = window;
    }
    
    /**
     * Update the clients count to a specific chatroom.
     */
    @Override
    public void updateJoins(String chatroomId, Integer count)
            throws RemoteException {
        window.updateJoins(chatroomId, count);
    }
    
    public static void main(String[] args) throws Exception {
        new ChatClientImpl(args[0]);
    }
}
