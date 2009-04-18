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
import com.pucpr.br.uteis.DAOException;

/**
 * Esta classe prevê acesso a base de dados para o objeto Usuario.
 * 
 */
public class DAOUsuarioSQL implements DAOUsuario {

	/**
	 * Método responsável por efetuar a alteração dos dados do usuário.
	 * 
	 * @param usuario
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean alterarUsuario(UsuarioDTO usuario) throws DAOException {

		boolean retorno = false;
		Connection con = null;

		try {
			con = Conexao.obterInstancia().obterConexao();

			PreparedStatement pst = con.prepareStatement(montaAlterarUsuario());

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getSenha());
			pst.setString(3, usuario.getLogin());

			if (pst.executeUpdate() > 0)
				retorno = true;

			pst.close();

		} catch (SQLException e) {
			throw new DAOException(
					"Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(
						"Ocorreu um erro ao tentar fechar conexão com o banco de dados!",
						e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por buscar um usuário específico.
	 * 
	 * @param usuario
	 * @return UsuarioDTO
	 * @throws DAOException
	 */
	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) throws DAOException {

		Connection con = null;
		UsuarioDTO retorno = null;

		try {
			con = Conexao.obterInstancia().obterConexao();

			PreparedStatement pst = con.prepareStatement(montaBuscarUsuario());

			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				retorno = new UsuarioDTO();
				retorno.setNome(rs.getString("nome"));
				retorno.setLogin(rs.getString("login"));
				retorno.setSenha(rs.getString("senha"));
			}

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DAOException(
					"Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(
						"Ocorreu um erro ao tentar fechar conexão com o banco de dados!",
						e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por excluir um determinado usuário.
	 * 
	 * @param usuario
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean excluirUsuaio(UsuarioDTO usuario) throws DAOException {

		boolean retorno = false;
		Connection con = null;

		try {
			con = Conexao.obterInstancia().obterConexao();

			PreparedStatement pst = con.prepareStatement(montaDeletarUsuario());

			pst.setString(1, usuario.getLogin());

			if (pst.executeUpdate() > 0)
				retorno = true;

			pst.close();

		} catch (SQLException e) {
			throw new DAOException(
					"Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(
						"Ocorreu um erro ao tentar fechar conexão com o banco de dados!",
						e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável para fazer a inserção de um novo usuário.
	 * 
	 * @param usuario
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean inserirUsuario(UsuarioDTO usuario) throws DAOException {

		boolean retorno = false;
		Connection con = null;

		try {
			con = Conexao.obterInstancia().obterConexao();

			PreparedStatement pst = con.prepareStatement(montaInserirUsuario());

			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getNome());
			pst.setString(3, usuario.getSenha());

			if (pst.executeUpdate() > 0)
				retorno = true;

			pst.close();

		} catch (SQLException e) {
			throw new DAOException(
					"Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(
						"Ocorreu um erro ao tentar fechar conexão com o banco de dados!",
						e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por retornar uma lista de todos os usuários
	 * 
	 * @return Collection UsuarioDTO
	 * @throws DAOException
	 */
	public Collection<UsuarioDTO> listarUsuarios() throws DAOException {

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

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DAOException(
					"Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(
						"Ocorreu um erro ao tentar fechar conexão com o banco de dados!",
						e);
			}
		}
		return listaUsuarios;
	}

	/**
	 * Monta o sql para atualização do usuário
	 * 
	 * @return String sql
	 */
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

	/**
	 * Monta o sql para recuperar uma lista de usuários
	 * 
	 * @return String sql
	 */
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

	/**
	 * Monta o sql para inserção do usuário
	 * 
	 * @return String sql
	 */
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

	/**
	 * Monta o sql para deleção do usuário
	 * 
	 * @return String sql
	 */
	private String montaDeletarUsuario() {

		StringBuffer sql = new StringBuffer();

		sql.append("DELETE FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	login = ? AND ");

		return sql.toString();
	}

	/**
	 * Monta o sql para buscar um determinado usuário.
	 * 
	 * @param usuario
	 * @return String sql
	 */
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
		sql.append("AND");
		sql.append("	senha = ?  ");

		return sql.toString();
	}
}
