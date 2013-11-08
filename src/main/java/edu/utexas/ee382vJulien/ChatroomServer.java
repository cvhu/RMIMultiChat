package edu.utexas.ee382vJulien;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatroomServer extends Remote{
    public void registerId(String id) throws RemoteException;
    public void showMessage(String message) throws RemoteException;
    public String getId() throws RemoteException;
    public String getName() throws RemoteException;
    public String getDescription() throws RemoteException;
    public Integer getClientCount() throws RemoteException;
    public void join(String clientId) throws RemoteException;
    public void talk(String clientId, String message) throws RemoteException;
    public void leave(String clientId) throws RemoteException;
}
