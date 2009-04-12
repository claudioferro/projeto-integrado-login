package com.pucpr.br.services.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.services.ServiceManterUsuario;

public class ServiceManterUsuarioImpl implements ServiceManterUsuario {

	public boolean alterarUsuario(UsuarioDTO usuario) throws RemoteException {

		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		return factory.getDAOUsuario().alterarUsuario(usuario);
	}

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) throws RemoteException {
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		return factory.getDAOUsuario().buscarUsuario(usuario);
	}

	public boolean excluirUsuario(UsuarioDTO usuario) throws RemoteException {
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		return factory.getDAOUsuario().excluirUsuaio(usuario);
	}

	public boolean incluirUsuario(UsuarioDTO usuario) throws RemoteException {

		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		return factory.getDAOUsuario().inserirUsuario(usuario);
	}

	public List<UsuarioDTO> listarUsuarios() throws RemoteException {
		ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();

		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactory.SQL);
		listaUsuarios = (ArrayList<UsuarioDTO>) factory.getDAOUsuario()
				.listarUsuarios();

		return listaUsuarios;

	}

	public static void main(String[] args) throws RemoteException {

		ServiceManterUsuarioImpl impl = new ServiceManterUsuarioImpl();

		UsuarioDTO usuario = new UsuarioDTO();

		usuario.setLogin("rony");
		usuario.setNome("rony");
		usuario.setSenha("rony");

		impl.incluirUsuario(usuario);

	}
}
