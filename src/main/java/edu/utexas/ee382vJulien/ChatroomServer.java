package edu.utexas.ee382vJulien;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatroomServer extends Remote{
    public void showMessage(String message) throws RemoteException;
}
