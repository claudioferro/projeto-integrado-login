package com.pucpr.br.uteis;

public class DAOException extends Exception {

	private static final long serialVersionUID = 1357833239104980417L;

	public DAOException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
