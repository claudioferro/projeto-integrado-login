package com.pucpr.br.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.pucpr.br.dto.PapelDTO;

public interface ServiceManterPapel extends Remote {

	public boolean incluirPapel(PapelDTO papel) throws RemoteException;

	public boolean alterarPapel(PapelDTO papel) throws RemoteException;

	public boolean excluirPapel(PapelDTO papel) throws RemoteException;

	public PapelDTO buscarPapel(PapelDTO papel) throws RemoteException;

	public List<PapelDTO> listarPapeis() throws RemoteException;

}
