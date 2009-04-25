package com.pucpr.br.command;

import java.util.Map;

import com.pucpr.br.frontend.utils.ConstantsFrontEnd;

public class FrontController {

	public static Map<String, Object> executeCommand(String acao,
			Map<String, Object> data) {

		if (acao.equals(ConstantsFrontEnd.LOGIN_CONFIRMAR)) {
			return CommandFactory.obterCommand(
					CommandFactory.AUTENTICAR_USUARIO).execute(data);
		} else {
			if (acao.equals(ConstantsFrontEnd.LOGIN_AUTORIZAR)) {
				return CommandFactory.obterCommand(
						CommandFactory.AUTORIZAR_USUARIO).execute(data);
			} else {
				if (acao.equals(ConstantsFrontEnd.USUARIO_INCLUIR_USUARIO)) {
					return CommandFactory.obterCommand(
							CommandFactory.NOVO_USUARIO).execute(data);
				}
			}
		}

		return null;
	}

}
