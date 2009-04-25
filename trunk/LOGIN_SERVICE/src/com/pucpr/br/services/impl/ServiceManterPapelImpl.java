package com.pucpr.br.services.impl;

import java.util.List;

import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.services.ServiceManterPapel;
import com.pucpr.br.uteis.DAOException;

public class ServiceManterPapelImpl implements ServiceManterPapel {

	public boolean alterarPapel(PapelDTO papel) {
		return false;
	}

	public PapelDTO buscarPapel(PapelDTO papel) {
		return null;
	}

	public boolean excluirPapel(PapelDTO papel) {
		
		boolean retorno = false;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		try {
			retorno = factory.getDAOPapel().excluirPapel(papel);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean incluirPapel(PapelDTO papel) {

		boolean retorno = false;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		try {
			retorno = factory.getDAOPapel().inserirPapel(papel);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<PapelDTO> listarPapeis() {
		
		List<PapelDTO> retorno = null;
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactorySQL.SQL);

		try {
			retorno = factory.getDAOPapel().listarPapeis();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
