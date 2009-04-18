package com.pucpr.br.services.impl;

import java.security.NoSuchAlgorithmException;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
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
		try {
			usuario
					.setSenha(CriptografiaUtils
							.criptografar(usuario.getSenha()));
		} catch (NoSuchAlgorithmException e1) {
			throw new RuntimeException("Erro ao criptografar senha", e1);
		}

		try {
			if (factory.getDAOUsuario().buscarUsuario(usuario) == null) {
				return false;
			} else {
				return true;
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {

		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setLogin("thiago");
		usuario.setNome("Thiago");
		usuario.setSenha("thiago");

		ServiceAutenticarImpl autenticarImpl = new ServiceAutenticarImpl();

		System.out.println(autenticarImpl.autenticarUsuario(usuario));

	}
}
