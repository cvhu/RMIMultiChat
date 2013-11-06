package edu.utexas.ee382vJulien;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote{
    public void register() throws RemoteException;
    public void deregister() throws RemoteException;
    public void getChatrooms() throws RemoteException;
}
