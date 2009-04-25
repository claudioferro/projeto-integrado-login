package com.pucpr.br.command.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.pucpr.br.command.Command;
import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.ControllerException;
import com.pucpr.br.services.ServiceManterPermissoes;
import com.pucpr.br.utils.ConstantsServices;

public class CommandAutorizar implements Command {

	public Map<String, Object> execute(Map<String, Object> data) {

		Collection<PermissaoDTO> permissoes;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Map<String, Object> retorno = new HashMap<String, Object>();

		usuarioDTO.setLogin((String) data.get(ConstantsFrontEnd.LOGIN_LOGIN));
		usuarioDTO.setSenha((String) data.get(ConstantsFrontEnd.LOGIN_SENHA));

		try {

			permissoes = obterServico().obterPermicoes(usuarioDTO);
			retorno.put(ConstantsFrontEnd.PERMISSOES_RETORNO, permissoes);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	private ServiceManterPermissoes obterServico() throws ControllerException {

		try {
			ServiceManterPermissoes servico = (ServiceManterPermissoes) Naming
					.lookup("rmi://localhost/"
							+ ConstantsServices.SERVICE_MANTER_PERMISSOES);
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
