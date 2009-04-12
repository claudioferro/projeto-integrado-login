package com.pucpr.br.command;

import java.util.Map;

import com.pucpr.br.frontend.utils.ConstantsFrontEnd;

public class FrontController {
	
	
	public static void executeCommand(String acao, Map<String, Object> data){
		
		if(acao.equals(ConstantsFrontEnd.LOGIN_CONFIRMAR)){
			CommandFactory.obterCommand(CommandFactory.AUTENTICAR_USUARIO).execute(data);
		}
	}

}
