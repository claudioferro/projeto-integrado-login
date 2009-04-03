package com.pucpr.br.services;

import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;

public interface ServiceManterUsuario {

	public boolean incluirUsuario(UsuarioDTO usuario);

	public boolean alterarUsuario(UsuarioDTO usuario);

	public boolean excluirUsuario(UsuarioDTO usuario);

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario);

	public List<UsuarioDTO> listarUsuarios();

}
