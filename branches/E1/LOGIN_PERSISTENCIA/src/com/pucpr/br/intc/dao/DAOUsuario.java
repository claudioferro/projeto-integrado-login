package com.pucpr.br.intc.dao;

import java.util.Collection;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.uteis.DAOException;

public interface DAOUsuario {

	boolean inserirUsuario(UsuarioDTO usuario) throws DAOException;

	boolean excluirUsuaio(UsuarioDTO usuario) throws DAOException;

	boolean alterarUsuario(UsuarioDTO usuario) throws DAOException;

	UsuarioDTO buscarUsuario(UsuarioDTO usuario) throws DAOException;

	Collection<UsuarioDTO> listarUsuarios() throws DAOException;

}
