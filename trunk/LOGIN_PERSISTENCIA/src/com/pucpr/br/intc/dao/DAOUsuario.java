package com.pucpr.br.intc.dao;

import java.util.Collection;

import com.pucpr.br.dto.UsuarioDTO;

public interface DAOUsuario {

	boolean inserirUsuario(UsuarioDTO usuario);

	boolean excluirUsuaio(UsuarioDTO usuario);

	boolean alterarUsuario(UsuarioDTO usuario);

	UsuarioDTO buscarUsuario(UsuarioDTO usuario);

	Collection<UsuarioDTO> listarUsuarios();

}
