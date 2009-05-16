package com.pucpr.br.intc.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.intc.dao.DAOPapel;
import com.pucpr.br.uteis.Conexao;
import com.pucpr.br.uteis.DAOException;

/**
 * Esta classe prevê acesso a base de dados para o objeto Papel.
 * 
 */
public class DAOPapelSQL implements DAOPapel {

	/**
	 * Método responsável por efetuar a alteração dos dados do papel.
	 * 
	 * @param papel
	 * @return boolean
	 * @throws DAOException 
	 */
	public boolean alterarPapel(PapelDTO papel) throws DAOException {
	
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaAlterarPapel());
			
			pst.setString(1, papel.getCodigo());
			
			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();
			
		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conexão com o banco de dados!", e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por buscar um papel específico.
	 * 
	 * @param papel
	 * @return boolean
	 * @throws DAOException 
	 */
	/*
	public PapelDTO buscarPapel(UsuarioDTO usuario) {
	
	
		
		return null;
	}
	*/

	/**
	 * Método responsável pela deleção de um determinado papel.
	 * 
	 * @param papel
	 * @return boolean
	 * @throws DAOException 
	 */
	public boolean excluirPapel(PapelDTO papel) throws DAOException {
	
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaDeletarPapel());

			pst.setString(1, papel.getCodigo());

			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();

		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conexão com o banco de dados!", e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável para fazer a inserção de um novo papel.
	 * 
	 * @param papel
	 * @return boolean
	 * @throws DAOException 
	 */
	public boolean inserirPapel(PapelDTO papel) throws DAOException {

		boolean retorno = false;
		Connection con  = null;
		
		try {
			con  = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaInserirPapel());

			pst.setString(1, papel.getNome());

			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();

		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conexão com o banco de dados!", e);
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por retornar uma lista de todos os papéis
	 * 
	 * @return Collection PapelDTO
	 * @throws DAOException 
	 */
	public List<PapelDTO> listarPapeis() throws DAOException {

		List<PapelDTO> listaPapeis = new ArrayList<PapelDTO>();
		Connection con = null;
		PapelDTO PapelDTO = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaListarPapeis());
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				PapelDTO = new PapelDTO();

				PapelDTO.setCodigo(rs.getString("codigo_papel"));
				PapelDTO.setNome(rs.getString("nome"));

				listaPapeis.add(PapelDTO);
			}
			
			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execução do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conexão com o banco de dados!", e);
			}
		}
		return listaPapeis;
	}
	
	/**
	 * Monta o sql para atualização do papel
	 * @return String sql
	 */
	private String montaAlterarPapel() {

		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE ");
		sql.append(" 	papel ");
		sql.append(" SET ");
		sql.append(" 	nome  = ? ");
		sql.append(" WHERE ");
		sql.append("	codigo_papel = ?");

		return sql.toString();
	}
	
	/**
	 * Monta o sql para deleção do papel
	 * @return String sql
	 */
	private String montaDeletarPapel() {

		StringBuffer sql = new StringBuffer();

		sql.append(" DELETE ");
		sql.append(" 	papel ");
		sql.append(" WHERE ");
		sql.append("	codigo_papel = ?");

		return sql.toString();
	}
	
	/**
	 * Monta o sql para inserção do papel
	 * @return String sql
	 */
	private String montaInserirPapel() {

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO ");
		sql.append("	papel");
		sql.append("	(codigo_papel, ");
		sql.append("	 nome) ");
		sql.append("VALUES ");
		sql.append("	(papelseq.nextval,?)");

		return sql.toString();
	}
	
	/**
	 * Monta o sql para retornar uma lista de papéis
	 * @return String sql
	 */
	private String montaListarPapeis() {

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		sql.append("	codigo_papel, ");
		sql.append("	nome ");
		sql.append("FROM ");
		sql.append("	papel");
		
		return sql.toString();
	}

}
