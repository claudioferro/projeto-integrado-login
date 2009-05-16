package com.pucpr.br.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;

public interface ServiceAutenticar extends Remote {

	public boolean autenticarUsuario(UsuarioDTO usuario) throws RemoteException;

	/**
	 * Adiciona um ouvinte de modificações na base de dados de ações.
	 * 
	 * @param o
	 * @throws RemoteException
	 */
	public void adicionarOuvinte(ObserverManterUsuario o)
			throws RemoteException;

	/**
	 * Recupera a lista de usuarios
	 * 
	 * 
	 * @throws RemoteException
	 */
	public List<UsuarioDTO> obterListaUsuarios() throws RemoteException;

	public boolean removerUsuario(UsuarioDTO usuario) throws RemoteException;
}
