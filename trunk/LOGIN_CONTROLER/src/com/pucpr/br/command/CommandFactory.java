package com.pucpr.br.command;

import com.pucpr.br.command.impl.CommandAutenticar;
import com.pucpr.br.command.impl.CommandAutorizar;

public class CommandFactory {

	public static final int AUTENTICAR_USUARIO = 1;
	public static final int AUTORIZAR_USUARIO = 2;

	public static Command obterCommand(int command) {

		switch (command) {
		case AUTENTICAR_USUARIO:
			return new CommandAutenticar(); 
			
		case AUTORIZAR_USUARIO:
			return new CommandAutorizar();

		default:
			return null;

		}

	}

}
