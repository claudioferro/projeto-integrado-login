package com.pucpr.br.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObserverManterUsuario extends Remote{
	
	public void atualizar() throws RemoteException;

}
