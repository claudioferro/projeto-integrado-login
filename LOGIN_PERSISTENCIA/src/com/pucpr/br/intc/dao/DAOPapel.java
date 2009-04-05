package com.pucpr.br.intc.dao;

import java.util.Collection;

import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.dto.UsuarioDTO;

public interface DAOPapel {
	
	boolean inserirPapel(PapelDTO papel);

	boolean excluirPapel(PapelDTO papel);

	boolean alterarPapel(PapelDTO papel);

	UsuarioDTO buscarPapel(PapelDTO papel);

	Collection<PapelDTO> listarPapeis();

}
