package com.pucpr.br.command;

import com.pucpr.br.command.impl.CommandAutenticar;
import com.pucpr.br.command.impl.CommandAutorizar;
import com.pucpr.br.command.impl.CommandNovoPapel;
import com.pucpr.br.command.impl.CommandNovoUsuario;

public class CommandFactory {

	public static final int AUTENTICAR_USUARIO = 1;
	public static final int AUTORIZAR_USUARIO = 2;
	public static final int NOVO_USUARIO = 3;
	public static final int NOVO_PAPEL = 4;
	public static final int LISTAR_PAPEIS = 5;
	public static final int EXCLUIR_PAPEL = 6;

	
	public static Command obterCommand(int command) {

		switch (command) {
		case AUTENTICAR_USUARIO:
			return new CommandAutenticar();

		case AUTORIZAR_USUARIO:
			return new CommandAutorizar();

		case NOVO_USUARIO:
			return new CommandNovoUsuario();
			
		case NOVO_PAPEL:
			return new CommandNovoPapel();
			
		case LISTAR_PAPEIS:
			return null;//new CommandListarPapeis();
			
		case EXCLUIR_PAPEL:
			return null;//new CommandExcluirPapel();

		default:
			return null;

		}

	}
}
