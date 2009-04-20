package com.pucpr.br.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.pucpr.br.services.ServiceAutenticar;
import com.pucpr.br.services.ServiceManterUsuario;
import com.pucpr.br.services.impl.ServiceAutenticarImpl;
import com.pucpr.br.services.impl.ServiceManterUsuarioImpl;
import com.pucpr.br.utils.ConstantsServices;

public class Server {

	public static void main(String[] args) {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			throw new RuntimeException("Erro ao encontrar o Registry", e);
		}
		servicoManterUsuario(registry);
		servicoAutenticar(registry);

	}

	private static void servicoManterUsuario(Registry registry) {
		ServiceManterUsuarioImpl serviceManterUsuario = new ServiceManterUsuarioImpl();

		try {
			ServiceManterUsuario stubManterUsuario = (ServiceManterUsuario) UnicastRemoteObject
					.exportObject(serviceManterUsuario, 0);

			registry.bind(ConstantsServices.SERVICE_MANTER_USUARIO,
					stubManterUsuario);
			System.out.println("Servi�o: Manter usuario ........ OK");

		} catch (RemoteException e) {
			throw new RuntimeException("Erro ao criar stub Manter Usuario", e);
		} catch (AlreadyBoundException e) {
			throw new RuntimeException("Erro ao criar stub Manter Usuario", e);
		}

	}

	private static void servicoAutenticar(Registry registry) {
		ServiceAutenticarImpl serviceAutenticar = new ServiceAutenticarImpl();

		try {
			ServiceAutenticar stubAutenticar = (ServiceAutenticar) UnicastRemoteObject
					.exportObject(serviceAutenticar, 0);

			registry.bind(ConstantsServices.SERVICE_AUTENTICAR, stubAutenticar);
			System.out.println("Servi�o: Autenticar ........ OK");

		} catch (RemoteException e) {
			throw new RuntimeException("Erro ao criar stub Autenticar", e);
		} catch (AlreadyBoundException e) {
			throw new RuntimeException("Erro ao criar stub Autenticar", e);
		}

	}

}
