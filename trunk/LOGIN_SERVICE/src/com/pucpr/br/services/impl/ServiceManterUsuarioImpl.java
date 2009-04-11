package com.pucpr.br.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.factory.dao.DAOFactory;
import com.pucpr.br.factory.dao.sql.DAOFactorySQL;
import com.pucpr.br.services.ServiceManterUsuario;

public class ServiceManterUsuarioImpl implements ServiceManterUsuario {

	public boolean alterarUsuario(UsuarioDTO usuario) {

		return false;
	}

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) {
		return null;
	}

	public boolean excluirUsuario(UsuarioDTO usuario) {
		return false;
	}

	public boolean incluirUsuario(UsuarioDTO usuario) {
		return false;
	}

	public List<UsuarioDTO> listarUsuarios() {
		ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
		
		DAOFactory factory = DAOFactorySQL.getFabrica(DAOFactory.SQL);
		listaUsuarios = (ArrayList<UsuarioDTO>) factory.getDAOUsuario().listarUsuarios();
		
		
		return listaUsuarios;

	}
	
	public static void main(String[] args) {
		ServiceManterUsuarioImpl impl = new ServiceManterUsuarioImpl();
		
		impl.listarUsuarios();
	}

}
