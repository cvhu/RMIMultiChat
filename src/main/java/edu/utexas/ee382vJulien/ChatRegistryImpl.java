package edu.utexas.ee382vJulien;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatRegistryImpl implements ChatRegistry{

    protected ChatRegistryImpl() throws RemoteException {
//        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void register(ChatClient client) throws RemoteException {
        // TODO Auto-generated method stub
        client.showMessage("registered for client");
    }

    @Override
    public void register(ChatroomServer server) throws RemoteException {
        // TODO Auto-generated method stub
        server.showMessage("registered for server");
    }

    @Override
    public void deregister() throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getChatrooms() throws RemoteException {
        // TODO Auto-generated method stub
        
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
