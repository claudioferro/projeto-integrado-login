package com.pucpr.br.services.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.server.Server;
import com.pucpr.br.services.ObserverManterUsuario;
import com.pucpr.br.services.ServiceAutenticar;
import com.pucpr.br.uteis.DAOException;
import com.pucpr.br.utils.CriptografiaUtils;

/**
 * Serviço para a autenticação de usuario.
 * 
 * @author Thiago
 * 
 * @version 1.0
 * 
 */

public class ServiceAutenticarImpl implements ServiceAutenticar {

	/** Lista de usuarios que estao logados no sistema */
	List<UsuarioDTO> listaUsuarios;

	/** lista de ouvintes do servico remoto */
	private List<ObserverManterUsuario> ouvintes = new ArrayList<ObserverManterUsuario>();

	public ServiceAutenticarImpl() {
		listaUsuarios = Server.obterInstancia().getListaUsuarios();

	}

	/**
	 * Método responsável por autenticar usuario
	 * 
	 * @param {@link UsuarioDTO}
	 * @return {@link Boolean}
	 * 
	 * */
	public boolean autenticarUsuario(UsuarioDTO usuario) {

		// Recupera uma fabrica de DAO SQL
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		// Criptografa a senha do usuario
		usuario.setSenha(CriptografiaUtils.criptografar(usuario.getSenha()));

		// Verifica se usuario existe
		try {

			UsuarioDTO u = factory.getDAOUsuario().buscarUsuario(usuario);
			if (u == null) {
				return false;
			} else {
				synchronized (listaUsuarios) {
					listaUsuarios.add(usuario);
				}
				notificarOuvintes();

				return true;
			}

		} catch (DAOException e) {
			new DAOException("Erro ao autenticar usuario", e);
		}
		return false;
	}

	public void adicionarOuvinte(ObserverManterUsuario o)
			throws RemoteException {
		synchronized (ouvintes) {
			ouvintes.add(o);
		}
	}

	public List<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<UsuarioDTO> obterListaUsuarios() throws RemoteException {

		// Recupera uma fabrica de DAO SQL
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		ArrayList<UsuarioDTO> retorno = new ArrayList<UsuarioDTO>();
		// Verifica se usuario existe
		try {

			Collection<UsuarioDTO> u = factory.getDAOUsuario().listarUsuarios();

			Iterator<UsuarioDTO> i = u.iterator();
			while (i.hasNext()) {
				UsuarioDTO usuario = i.next();

				for (UsuarioDTO logado : listaUsuarios) {
					if (logado.getLogin().equals(usuario.getLogin())) {
						usuario.setUsando(true);
						retorno.add(usuario);
					} else {
						usuario.setUsando(false);
						retorno.add(usuario);
					}
				}

			}

		} catch (DAOException e) {
			new DAOException("Erro ao autenticar usuario", e);
		}
		return retorno;
	}

	/**
	 * Notificar todos os ouvintes sobre a alteracao realizada. Observar que o
	 * acesso a esta lista é sincronizado, uma vez que o acesso pode ser
	 * concorrente.
	 */
	private void notificarOuvintes() {
		synchronized (ouvintes) {
			Iterator<ObserverManterUsuario> i = ouvintes.iterator();
			while (i.hasNext()) {
				ObserverManterUsuario o = i.next();
				try {
					o.atualizar();
				} catch (RemoteException e) {
					System.out.println("Erro ao executar um dos ouvintes"
							+ e.getMessage() + ". continuando.");
					i.remove();
				}
			}
		}

	}
}
