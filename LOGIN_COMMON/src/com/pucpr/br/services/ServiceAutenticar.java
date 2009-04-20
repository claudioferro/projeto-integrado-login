package com.pucpr.br.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.pucpr.br.dto.UsuarioDTO;

public interface ServiceAutenticar extends Remote {

	public boolean autenticarUsuario(UsuarioDTO usuario)throws RemoteException;

}
