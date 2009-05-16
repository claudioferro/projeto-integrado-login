package com.pucpr.br.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;

public interface ServiceManterPermissoes extends Remote {

	public Collection<PermissaoDTO> obterPermicoes(UsuarioDTO usuario)
			throws RemoteException;

}
