package com.pucpr.br.command.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.pucpr.br.command.Command;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.ControllerException;
import com.pucpr.br.services.ServiceAutenticar;
import com.pucpr.br.utils.ConstantsServices;

public class CommandAutenticar implements Command {

	public Map<String, Object> execute(Map<String, Object> data) {

		Boolean autenticado = false;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Map<String, Object> retorno = new HashMap<String, Object>();

		usuarioDTO.setLogin((String) data.get(ConstantsFrontEnd.LOGIN_LOGIN));
		usuarioDTO.setSenha((String) data.get(ConstantsFrontEnd.LOGIN_SENHA));

		try {

			autenticado = obterServico().autenticarUsuario(usuarioDTO);
			retorno.put(ConstantsFrontEnd.AUTENTICAR_RETORNO, autenticado);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
	}

	private ServiceAutenticar obterServico() throws ControllerException {

		try {
			ServiceAutenticar servico = (ServiceAutenticar) Naming
					.lookup("rmi://localhost/"
							+ ConstantsServices.SERVICE_AUTENTICAR);
			return servico;
		} catch (MalformedURLException e) {
			throw new ControllerException(
					"Erro ao invocar servico (URL inv�lida)", e);
		} catch (RemoteException e) {
			throw new ControllerException(
					"Erro ao invocar servi�o (Erro de rede geral)", e);
		} catch (NotBoundException e) {
			throw new ControllerException(
					"Erro ao invocar servi�o (Servico nao registrado)", e);
		}

	}

}
