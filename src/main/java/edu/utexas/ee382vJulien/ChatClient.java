package edu.utexas.ee382vJulien;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote{
    public void registerId(String id) throws RemoteException;
    public void addChatroomServer(ChatroomServer server) throws RemoteException;
    public void showMessage(String message) throws RemoteException;
    public void printMessage(String message) throws RemoteException;
    public void closeChatroom(String chatroomId) throws RemoteException;
    public void updateJoins(String chatroomId, Integer count) throws RemoteException;
    public String getId() throws RemoteException;
}
