package edu.utexas.ee382vJulien;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ChatroomServerImpl extends UnicastRemoteObject implements ChatroomServer{


    protected ChatroomServerImpl() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    public void register(ChatRegistry registry) throws RemoteException {
        registry.register(this);
    }
    
    @Override
    public void showMessage(String message) throws RemoteException {
        System.out.println(message);
    }
    
    public static void main(String[] args) throws Exception {
        String host = args[0];
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            ChatRegistry stub = (ChatRegistry) registry.lookup("ChatRegistry");
            ChatroomServerImpl server = new ChatroomServerImpl();
            server.register(stub);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
