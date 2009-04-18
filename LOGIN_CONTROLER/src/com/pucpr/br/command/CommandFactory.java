package com.pucpr.br.command;

import com.pucpr.br.command.impl.CommandAutenticar;

public class CommandFactory {

	public static final int AUTENTICAR_USUARIO = 1;

	public static Command obterCommand(int command) {

		switch (command) {
		case AUTENTICAR_USUARIO:
			return new CommandAutenticar();

		default:
			return null;

		}

	}

}
