package com.pucpr.br.command.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Map;

import com.pucpr.br.command.Command;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.ControllerException;
import com.pucpr.br.services.ServiceAutenticar;
import com.pucpr.br.utils.ConstantsServices;
import com.pucpr.br.utils.RMIProperties;

public class CommandAutenticar implements Command {

	public void execute(Map<String, Object> data) {

		boolean autenticado = false;
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setLogin((String) data.get(ConstantsFrontEnd.LOGIN_LOGIN));
		usuarioDTO.setSenha((String) data.get(ConstantsFrontEnd.LOGIN_SENHA));

		try {

			autenticado = obterServico().autenticarUsuario(usuarioDTO);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ServiceAutenticar obterServico() throws ControllerException {

		try {
						
			ServiceAutenticar servico = (ServiceAutenticar) Naming
					.lookup(RMIProperties.obterInstacia().getURL()
							+ ConstantsServices.SERVICE_AUTENTICAR);
			return servico;
		} catch (MalformedURLException e) {
			throw new ControllerException(
					"Erro ao invocar servico (URL inválida)", e);
		} catch (RemoteException e) {
			throw new ControllerException(
					"Erro ao invocar serviço (Erro de rede geral)", e);
		} catch (NotBoundException e) {
			throw new ControllerException(
					"Erro ao invocar serviço (Servico nao registrado)", e);
		}

	}

}
