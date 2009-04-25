package com.pucpr.br.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.pucpr.br.services.ServiceAutenticar;
import com.pucpr.br.services.ServiceManterPermissoes;
import com.pucpr.br.services.ServiceManterUsuario;
import com.pucpr.br.services.impl.ServiceAutenticarImpl;
import com.pucpr.br.services.impl.ServiceManterPermissoesImpl;
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
		servicoManterPermissoes(registry);

	}

	private static void servicoManterUsuario(Registry registry) {
		ServiceManterUsuarioImpl serviceManterUsuario = new ServiceManterUsuarioImpl();

		try {
			ServiceManterUsuario stubManterUsuario = (ServiceManterUsuario) UnicastRemoteObject
					.exportObject(serviceManterUsuario, 0);

			registry.bind(ConstantsServices.SERVICE_MANTER_USUARIO,
					stubManterUsuario);
			System.out.println("Serviço: Manter usuario ........ OK");

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
			System.out.println("Serviço: Autenticar ........ OK");

		} catch (RemoteException e) {
			throw new RuntimeException("Erro ao criar stub Autenticar", e);
		} catch (AlreadyBoundException e) {
			throw new RuntimeException("Erro ao criar stub Autenticar", e);
		}

	}

	private static void servicoManterPermissoes(Registry registry) {
		ServiceManterPermissoesImpl serviceManterPermissoes = new ServiceManterPermissoesImpl();

		try {
			ServiceManterPermissoes stubManterPermissoes = (ServiceManterPermissoes) UnicastRemoteObject
					.exportObject(serviceManterPermissoes, 0);

			registry.bind(ConstantsServices.SERVICE_MANTER_PERMISSOES,
					stubManterPermissoes);
			System.out.println("Serviço: Manter Permissões ........ OK");

		} catch (RemoteException e) {
			throw new RuntimeException("Erro ao criar stub Manter Permissões",
					e);
		} catch (AlreadyBoundException e) {
			throw new RuntimeException("Erro ao criar stub Manter Permissões",
					e);
		}

	}

}
