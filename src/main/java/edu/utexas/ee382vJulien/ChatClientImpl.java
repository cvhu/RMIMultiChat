package edu.utexas.ee382vJulien;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient{
    
    private ChatRegistry chatRegistry;
    
    public ChatClientImpl() throws RemoteException {
        super();
    }
    
    protected void launch() {
        while (true) {
            
        }
    }

    public void register(ChatRegistry registry) throws RemoteException {
        chatRegistry = registry;
        chatRegistry.register(this);
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
            ChatClientImpl client = new ChatClientImpl();
            client.register(stub);
            client.launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
