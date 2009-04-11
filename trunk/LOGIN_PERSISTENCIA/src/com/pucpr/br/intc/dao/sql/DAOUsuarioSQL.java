package com.pucpr.br.intc.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.intc.dao.DAOUsuario;
import com.pucpr.br.uteis.Conexao;

public class DAOUsuarioSQL implements DAOUsuario {

	public boolean alterarUsuario(UsuarioDTO usuario) {

		Connection con = Conexao.obterInstancia().obterConexao();
		try {
			PreparedStatement pst = con.prepareStatement(montaAlterarUsuario());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getLogin());

			int result = pst.executeUpdate();

			if (result != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// TODO: fechar conexao

		}
		return false;
	}

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) {

		// TODO: melhorar a logica de busca

		Connection con = Conexao.obterInstancia().obterConexao();
		try {
			PreparedStatement pst = con.prepareStatement(montaBuscarUsuario());
			pst.setString(1, usuario.getLogin());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// TODO: fechar conexao

		}

		return usuario;

	}

	public boolean excluirUsuaio(UsuarioDTO usuario) {
		Connection con = Conexao.obterInstancia().obterConexao();
		try {
			PreparedStatement pst = con.prepareStatement(montaDeletarUsuario());

			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getNome());
			pst.setString(3, usuario.getSenha());

			int result = pst.executeUpdate();

			if (result != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// TODO: fechar conexao

		}
		return false;

	}

	public boolean inserirUsuario(UsuarioDTO usuario) {
		Connection con = Conexao.obterInstancia().obterConexao();
		try {
			PreparedStatement pst = con.prepareStatement(montaInserirUsuario());

			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getNome());
			pst.setString(3, usuario.getSenha());

			int result = pst.executeUpdate();

			if (result != 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// TODO: fechar conexao

		}
		return false;
	}

	public Collection<UsuarioDTO> listarUsuarios() {

		Connection con = Conexao.obterInstancia().obterConexao();
		Collection<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDTO;
		try {
			PreparedStatement pst = con.prepareStatement(montaListarUsuarios());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				usuarioDTO = new UsuarioDTO();

				usuarioDTO.setNome(rs.getString("nome"));
				usuarioDTO.setLogin(rs.getString("login"));
				usuarioDTO.setSenha(rs.getString("senha"));

				listaUsuarios.add(usuarioDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// TODO: fechar conexao

		}

		return listaUsuarios;
	}

	private String montaAlterarUsuario() {

		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE ");
		sql.append(" 	usuario ");
		sql.append(" SET ");
		sql.append(" 	nome  = ? ,");
		sql.append("    senha = ? ");
		sql.append(" WHERE ");
		sql.append("	login = ?");

		return sql.toString();
	}

	private String montaListarUsuarios() {

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		sql.append("	login, ");
		sql.append("	nome, ");
		sql.append("	senha ");
		sql.append("FROM ");
		sql.append("	usuario");
		return sql.toString();
	}

	private String montaInserirUsuario() {

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO ");
		sql.append("	usuario");
		sql.append("	(login, ");
		sql.append("	 nome, ");
		sql.append("	 senha) ");
		sql.append("VALUES ");
		sql.append("	(?,?,?)");
		return sql.toString();
	}

	private String montaDeletarUsuario() {

		StringBuffer sql = new StringBuffer();

		sql.append("DELETE FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	login = ? AND ");
		sql.append("	nome  = ? AND ");
		sql.append("	senha = ?");
		return sql.toString();
	}

	private String montaBuscarUsuario() {

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		sql.append("	login, ");
		sql.append("	nome, ");
		sql.append("	senha ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE");
		sql.append("	login = ?  ");

		return sql.toString();
	}

}
