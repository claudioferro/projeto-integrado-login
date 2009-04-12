package com.pucpr.br.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;

public interface ServiceManterUsuario extends Remote {

	public boolean incluirUsuario(UsuarioDTO usuario) throws RemoteException;

	public boolean alterarUsuario(UsuarioDTO usuario) throws RemoteException;

	public boolean excluirUsuario(UsuarioDTO usuario) throws RemoteException;

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) throws RemoteException;

	public List<UsuarioDTO> listarUsuarios() throws RemoteException;

}
