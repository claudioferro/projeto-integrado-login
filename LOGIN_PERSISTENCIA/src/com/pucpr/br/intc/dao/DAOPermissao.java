package com.pucpr.br.intc.dao;

import java.util.Collection;

import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;

public interface DAOPermissao {

	boolean inserirPermissao(PermissaoDTO permissao);

	boolean excluirPermissao(PermissaoDTO permissao);

	boolean alterarPermissao(PermissaoDTO permissao);

	UsuarioDTO buscarPermissao(PermissaoDTO permissao);

	Collection<PermissaoDTO> listarPermissoes();

}
