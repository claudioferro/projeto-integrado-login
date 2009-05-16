package com.pucpr.br.frontend.utils;

public class ControllerException extends Exception {

	private static final long serialVersionUID = 6752100713586465509L;

	public ControllerException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
