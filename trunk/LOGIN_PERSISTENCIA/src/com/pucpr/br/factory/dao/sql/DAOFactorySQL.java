package com.pucpr.br.factory.dao.sql;

import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.intc.dao.DAOPapel;
import com.pucpr.br.intc.dao.DAOPermissao;
import com.pucpr.br.intc.dao.DAOUsuario;
import com.pucpr.br.intc.dao.sql.DAOPermissaoSQL;
import com.pucpr.br.intc.dao.sql.DAOUsuarioSQL;

public class DAOFactorySQL extends DAOFactory {

	public DAOPapel getDAOPapel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DAOPermissao getDAOPermissao() {
		// TODO Auto-generated method stub
		return new DAOPermissaoSQL();
	}

	@Override
	public DAOUsuario getDAOUsuario() {
		// TODO Auto-generated method stub
		return new DAOUsuarioSQL();
	}
	
	
	

}
