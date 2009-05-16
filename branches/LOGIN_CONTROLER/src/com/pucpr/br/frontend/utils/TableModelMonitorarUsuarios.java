package com.pucpr.br.frontend.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pucpr.br.dto.UsuarioDTO;

/**
 * @author Rony Sartor
 * 
 * @version 1.0
 * 
 *          Table Model para exibir a tabela com os usuarios cadastrados
 * 
 */
public class TableModelMonitorarUsuarios extends AbstractTableModel {

	private static final int QTD_COLUNAS = 4;
	private static final String[] colunas = { "Login", "Nome", "Situação",
			"Usando" };
	private static final long serialVersionUID = -1832671347224425102L;

	/** referência da lista de usuarios */
	private List<UsuarioDTO> listaUsuarios;

	/**
	 * Construtor que instancia uma nova lista
	 */
	public TableModelMonitorarUsuarios() {
		this(new ArrayList<UsuarioDTO>());
	}

	/**
	 * Construtor que recebe uma lista para carregar
	 * 
	 * @param listaUsuarios
	 */
	public TableModelMonitorarUsuarios(List<UsuarioDTO> listaUsuarios) {

		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Método que retorna a lista utilizada
	 * 
	 * @return listaUsuarios
	 */
	public List<UsuarioDTO> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Método que seta a lista utilizada na tabela
	 * 
	 * @param listaUsuarios
	 */
	public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	@Override
	public int getColumnCount() {
		return QTD_COLUNAS;
	}

	@Override
	public int getRowCount() {
		return listaUsuarios.size();
	}

	@Override
	public String getColumnName(int pos) {
		return colunas[pos];
	}

	@Override
	public Object getValueAt(int row, int column) {

		UsuarioDTO usuarios = listaUsuarios.get(row);

		switch (column) {
		case 0:
			return usuarios.getLogin();
		case 1:
			return usuarios.getNome();
		case 2:
			if (usuarios.isSituacao()) {
				return "SIM";
			} else {
				return "NÃO";
			}
		case 3:
			if (usuarios.isUsando()) {
				return "SIM";
			} else {
				return "NÃO";
			}
		default:
			throw new IllegalArgumentException("coluna inválida");
		}
	}

}
