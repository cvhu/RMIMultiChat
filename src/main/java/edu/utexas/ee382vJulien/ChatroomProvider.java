package edu.utexas.ee382vJulien;

import java.awt.EventQueue;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatroomProvider {
    
    private ChatRegistry chatRegistry;
    
    public ChatroomProvider(String host) {
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            chatRegistry = (ChatRegistry) registry.lookup("ChatRegistry");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    };
    
    public ChatroomServerImpl addChatroom(Chatroom chatroom) {
        try {
            return new ChatroomServerImpl(chatroom, chatRegistry);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        final ChatroomProvider chatroomProvider = new ChatroomProvider(args[0]);
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    new WindowChatroomProvider(chatroomProvider);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
