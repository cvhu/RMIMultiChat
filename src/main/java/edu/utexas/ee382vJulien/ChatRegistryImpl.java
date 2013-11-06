package edu.utexas.ee382vJulien;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatRegistryImpl implements ChatRegistry{

    @Override
    public void register() throws RemoteException {
        // TODO Auto-generated method stub
        
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
