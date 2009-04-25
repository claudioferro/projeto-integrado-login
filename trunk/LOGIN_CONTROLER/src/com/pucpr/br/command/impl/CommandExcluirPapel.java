package com.pucpr.br.command.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.pucpr.br.command.Command;
import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.ControllerException;
import com.pucpr.br.services.ServiceManterPapel;
import com.pucpr.br.utils.ConstantsServices;

public class CommandExcluirPapel implements Command {

	public Map<String, Object> execute(Map<String, Object> data) {
		Boolean removido = false;
		PapelDTO papel = new PapelDTO();
		Map<String, Object> retorno = new HashMap<String, Object>();

		papel = ((PapelDTO) data.get(ConstantsFrontEnd.MANTER_PAPEIS_PAPEL));

		try {

			removido = obterServico().excluirPapel(papel);
			retorno.put(ConstantsFrontEnd.EXCLUIR_PAPEL_RETORNO, removido);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;
	}

	private ServiceManterPapel obterServico() throws ControllerException {

		try {
			ServiceManterPapel servico = (ServiceManterPapel) Naming
					.lookup("rmi://localhost/"
							+ ConstantsServices.SERVICE_MANTER_PAPEIS);
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
