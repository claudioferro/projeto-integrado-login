package com.pucpr.br.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.pucpr.br.services.ServiceManterUsuario;
import com.pucpr.br.services.impl.ServiceManterUsuarioImpl;
import com.pucpr.br.utils.ConstantsServices;

public class Server {

	public static void main(String[] args) {

		ServiceManterUsuarioImpl serviceManterUsuario = new ServiceManterUsuarioImpl();

		try {
			ServiceManterUsuario stubManterUsuario = (ServiceManterUsuario) UnicastRemoteObject
					.exportObject(serviceManterUsuario, 0);

			Registry registry = LocateRegistry.getRegistry();
			registry.bind(ConstantsServices.SERVICE_MANTER_USUARIO,
					stubManterUsuario);
			System.out.println("Serviço: Manter usuario ........ OK");

		} catch (RemoteException e) {
			throw new RuntimeException("Erro ao criar stub", e);
		} catch (AlreadyBoundException e) {
			throw new RuntimeException("Erro ao criar stub", e);
		}
	}
}
