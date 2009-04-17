package com.pucpr.br.intc.dao;

import java.util.Collection;

import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.uteis.DAOException;

public interface DAOPapel {
	
	boolean inserirPapel(PapelDTO papel) throws DAOException;

	boolean excluirPapel(PapelDTO papel) throws DAOException;

	boolean alterarPapel(PapelDTO papel) throws DAOException;

	UsuarioDTO buscarPapel(PapelDTO papel) throws DAOException;

	Collection<PapelDTO> listarPapeis() throws DAOException;

}
