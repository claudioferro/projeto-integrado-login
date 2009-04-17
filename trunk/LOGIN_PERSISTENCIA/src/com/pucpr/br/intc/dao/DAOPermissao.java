package com.pucpr.br.intc.dao;

import java.util.Collection;

import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.uteis.DAOException;

public interface DAOPermissao {

	boolean inserirPermissao(PermissaoDTO permissao) throws DAOException;

	boolean excluirPermissao(PermissaoDTO permissao) throws DAOException;

	boolean alterarPermissao(PermissaoDTO permissao) throws DAOException;

	UsuarioDTO buscarPermissao(PermissaoDTO permissao) throws DAOException;

	Collection<PermissaoDTO> listarPermissoes() throws DAOException;
}
