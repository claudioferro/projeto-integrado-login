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
		
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaAlterarUsuario());
			
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getLogin());

			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) {

		Connection con = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaBuscarUsuario(usuario));
			
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
				try {
					if(con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return usuario;
	}

	public boolean excluirUsuaio(UsuarioDTO usuario) {
		
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaDeletarUsuario());

			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getNome());
			pst.setString(3, usuario.getSenha());

			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
		}
		return retorno;
	}

	public boolean inserirUsuario(UsuarioDTO usuario) {
		
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con  = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaInserirUsuario());

			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getNome());
			pst.setString(3, usuario.getSenha());

			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return retorno;
	}

	public Collection<UsuarioDTO> listarUsuarios() {

		Collection<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();
		Connection con = null;
		UsuarioDTO usuarioDTO = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
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
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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

	private String montaBuscarUsuario(UsuarioDTO usuario) {

		StringBuffer sql = new StringBuffer();

		if(usuario.getLogin() != null){
			sql.append("SELECT ");
			sql.append("	login, ");
			sql.append("	nome, ");
			sql.append("	senha ");
			sql.append("FROM ");
			sql.append("	usuario ");
			sql.append("WHERE");
			sql.append("	login = ?  ");
		}else{
			sql.append("SELECT ");
			sql.append("	login, ");
			sql.append("	nome, ");
			sql.append("	senha ");
			sql.append("FROM ");
			sql.append("	usuario ");
			sql.append("WHERE");
			sql.append("	usuario = ?  ");
		}

		return sql.toString();
	}
}
