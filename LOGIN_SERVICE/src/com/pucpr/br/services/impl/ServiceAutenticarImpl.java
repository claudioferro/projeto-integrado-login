package com.pucpr.br.services.impl;

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
		usuario.setSenha(CriptografiaUtils.criptografar(usuario.getSenha()));

		// Verifica se usuario existe
		try {
			if (factory.getDAOUsuario().buscarUsuario(usuario) == null) {
				return false;
			} else {
				return true;
			}

		} catch (DAOException e) {
			new DAOException("Erro ao autenticar usuario", e);
		}
		return false;
	}

}
