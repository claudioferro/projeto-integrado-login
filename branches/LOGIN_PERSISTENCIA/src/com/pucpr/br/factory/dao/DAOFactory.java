package com.pucpr.br.factory.dao;

import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.intc.dao.DAOPapel;
import com.pucpr.br.intc.dao.DAOPermissao;
import com.pucpr.br.intc.dao.DAOUsuario;

public abstract class DAOFactory {

	public static final int SQL = 1;

	public static DAOFactory getFabrica(int tipoFabrica) {
		switch (tipoFabrica) {
		case SQL:
			return new DAOFactorySQL();
		default:
			throw new IllegalArgumentException("Implementação inexistente: "
					+ tipoFabrica);
		}
	}

	public abstract DAOUsuario getDAOUsuario();

	public abstract DAOPapel getDAOPapel();

	public abstract DAOPermissao getDAOPermissao();

}
