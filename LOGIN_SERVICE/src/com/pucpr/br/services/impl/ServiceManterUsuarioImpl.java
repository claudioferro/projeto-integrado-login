package com.pucpr.br.services.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.services.ServiceManterUsuario;
import com.pucpr.br.uteis.DAOException;
import com.pucpr.br.utils.CriptografiaUtils;

public class ServiceManterUsuarioImpl implements ServiceManterUsuario {

	public boolean alterarUsuario(UsuarioDTO usuario) throws RemoteException {

		boolean retorno    = false;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		try {
			retorno = factory.getDAOUsuario().alterarUsuario(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) throws RemoteException {
		
		UsuarioDTO retorno = null;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		try {
			retorno = factory.getDAOUsuario().buscarUsuario(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean excluirUsuario(UsuarioDTO usuario) throws RemoteException {
		
		boolean retorno    = false;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		try {
			retorno = factory.getDAOUsuario().excluirUsuaio(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean incluirUsuario(UsuarioDTO usuario) throws RemoteException {

		boolean retorno    = false;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);
		
		try {
			retorno = factory.getDAOUsuario().inserirUsuario(usuario);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<UsuarioDTO> listarUsuarios() throws RemoteException {
		
		ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactory.SQL);
		
		try {
			listaUsuarios = (ArrayList<UsuarioDTO>) factory.getDAOUsuario()
					.listarUsuarios();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return listaUsuarios;

	}

	public static void main(String[] args) throws RemoteException {
//TODO RETIRAR ISSO
		ServiceManterUsuarioImpl impl = new ServiceManterUsuarioImpl();

		UsuarioDTO usuario = new UsuarioDTO();

		usuario.setLogin("thiago");
		usuario.setNome("thiago");
		usuario.setSenha(CriptografiaUtils.criptografar("thiago"));

		impl.incluirUsuario(usuario);

	}
}
