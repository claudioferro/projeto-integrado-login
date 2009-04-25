package com.pucpr.br.intc.dao;


import java.util.List;

import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.uteis.DAOException;

public interface DAOPapel {
	
	boolean inserirPapel(PapelDTO papel) throws DAOException;

	boolean excluirPapel(PapelDTO papel) throws DAOException;

	boolean alterarPapel(PapelDTO papel) throws DAOException;

	//PapelDTO buscarPapel(UsuarioDTO papel) throws DAOException;

	List<PapelDTO> listarPapeis() throws DAOException;

}
