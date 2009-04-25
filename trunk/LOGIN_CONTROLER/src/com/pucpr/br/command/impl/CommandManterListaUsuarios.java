package com.pucpr.br.command.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.frontend.utils.ControllerException;
import com.pucpr.br.services.ObserverManterUsuario;
import com.pucpr.br.services.ServiceAutenticar;
import com.pucpr.br.utils.ConstantsServices;

public class CommandManterListaUsuarios extends Observable implements
		ObserverManterUsuario {
	private static CommandManterListaUsuarios instancia;

	public CommandManterListaUsuarios() throws ControllerException {

		try {
			// Recupera servico
			ServiceAutenticar servico = obterServicoAutenticar();

			// Transforma o controller em um ouvinte remoto
			ObserverManterUsuario o = (ObserverManterUsuario) UnicastRemoteObject
					.exportObject(this, 0);

			// adiciona na lista de ouvintes do servico
			servico.adicionarOuvinte(o);

		} catch (ControllerException e) {
			throw new ControllerException("Erro ao registrar controller", e);
		} catch (RemoteException e) {
			throw new ControllerException("Erro ao registrar controller", e);
		}

	}

	public static CommandManterListaUsuarios getInstance()
			throws ControllerException {
		if (instancia == null) {
			instancia = new CommandManterListaUsuarios();
		}
		return instancia;
	}

	/**
	 * Realiza a busca do serviço remoto.
	 * 
	 * @return
	 * @throws ControllerException
	 */
	public static ServiceAutenticar obterServicoAutenticar()
			throws ControllerException {

		try {
			// Busca o objeto remoto no registry
			ServiceAutenticar servico = (ServiceAutenticar) Naming
					.lookup("rmi://localhost/"
							+ ConstantsServices.SERVICE_AUTENTICAR);

			// Retorna o objeto remoto (stub) para uso
			return servico;
		} catch (MalformedURLException e) {
			throw new ControllerException(
					"Erro ao invocar servico (URL inválida)", e);
		} catch (RemoteException e) {
			throw new ControllerException(
					"Erro ao invocar serviço (Erro de execução remota)", e);
		} catch (NotBoundException e) {
			throw new ControllerException(
					"Erro ao invocar serviço (Serviço nao registrado)", e);
		}

	}

	/**
	 * Método remoto de callback que é invocado assim que o serviço de bolsa de
	 * valores descobre que uma nova linha foi adicionada.
	 * 
	 * Logo que ele recebe a notificação, ele avisa seus observadores que seus
	 * dados foram alterados.
	 */

	public void atualizar() throws RemoteException {
		setChanged();
		notifyObservers();

	}

	public synchronized void addObserver(Observer o) {
		o.update(this, null);
		super.addObserver(o);
	}

	public List<UsuarioDTO> recuperarUsuarios()
			throws ControllerException {

		try {
			ServiceAutenticar servico = obterServicoAutenticar();

			List<UsuarioDTO> listaAcoes;

			listaAcoes = servico.obterListaUsuarios();

			return listaAcoes;
		} catch (RemoteException e) {
			throw new ControllerException(
					"Erro ao executar serviço. Erro remoto geral", e);
		}

	}

}
