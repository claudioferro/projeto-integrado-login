package com.pucpr.br.services.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.services.ServiceManterPermissoes;
import com.pucpr.br.uteis.DAOException;
import com.pucpr.br.utils.CriptografiaUtils;

public class ServiceManterPermissoesImpl implements ServiceManterPermissoes {

	public Collection<PermissaoDTO> obterPermicoes(UsuarioDTO usuario)
			throws RemoteException {

		Collection<PermissaoDTO> p;

		// Recupera uma fabrica de DAO SQL
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		// Criptografa a senha do usuario
		usuario.setSenha(CriptografiaUtils.criptografar(usuario.getSenha()));

		// Recupera lista de permicoes
		try {
			p = factory.getDAOPermissao().buscarPermissao(usuario);
			return p;

		} catch (DAOException e) {
			new DAOException("Erro ao obter lista de permissões", e);
		}
		return null;
	}

}
