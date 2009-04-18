package com.pucpr.br.intc.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.intc.dao.DAOPermissao;
import com.pucpr.br.uteis.Conexao;
import com.pucpr.br.uteis.DAOException;

/**
 * Esta classe prev� acesso a base de dados para o objeto Permiss�o.
 * 
 */
public class DAOPermissaoSQL implements DAOPermissao {
	
	/**
	 * M�todo respons�vel por efetuar a altera��o dos dados de Permiss�o.
	 * 
	 * @param permissao
	 * @return boolean
	 * @throws DAOException 
	 */
	public boolean alterarPermissao(PermissaoDTO permissao) throws DAOException {
		
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaAlterarPermissao());
			
			pst.setInt(1, permissao.getCodigoPapel());
			pst.setInt(2, permissao.getCodigoPermissao());
			
			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();
			
		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execu��o do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conex�o com o banco de dados!", e);
			}
		}
		return retorno;
	}
	
	
	public Collection<PermissaoDTO> buscarPermissao(UsuarioDTO permissao) throws DAOException {
		
		Collection<PermissaoDTO> listaPemissoesUsuario = new ArrayList<PermissaoDTO>();
		Connection con = null;
		PermissaoDTO PermissaoDTO = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaBuscaPermissoes());
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				PermissaoDTO = new PermissaoDTO();

				PermissaoDTO.setCodigoPapel(rs.getInt("cod_papel"));

				listaPemissoesUsuario.add(PermissaoDTO);
			}
			
			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execu��o do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conex�o com o banco de dados!", e);
			}
		}
		return listaPemissoesUsuario;
	}

	/**
	 * M�todo respons�vel pela dele��o de uma determinada permiss�o.
	 * 
	 * @param permissao
	 * @return boolean
	 * @throws DAOException 
	 */
	public boolean excluirPermissao(PermissaoDTO permissao) throws DAOException {
		
		boolean retorno = false;
		Connection con  = null;
				
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaDeletarPermissao());
			
			pst.setInt(1, permissao.getCodigoPermissao());
			
			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();
			
		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execu��o do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conex�o com o banco de dados!", e);
			}
		}
		return retorno;
	}

	/**
	 * M�todo respons�vel para fazer a inser��o de uma nova permiss�o.
	 * 
	 * @param papel
	 * @return boolean
	 * @throws DAOException 
	 */
	public boolean inserirPermissao(PermissaoDTO permissao) throws DAOException {
		
		boolean retorno = false;
		Connection con  = null;
		
		try {
			con  = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaInserirPermissao());

			pst.setInt(1, permissao.getCodigoPapel());
			pst.setString(2, permissao.getLoginPessoa());

			if(pst.executeUpdate() > 0)
				retorno = true;
			
			pst.close();

		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execu��o do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conex�o com o banco de dados!", e);
			}
		}
		return retorno;
	}

	/**
	 * M�todo respons�vel por retornar uma lista de todos as permiss�es
	 * 
	 * @return Collection PermissaoDTO
	 * @throws DAOException 
	 */
	public Collection<PermissaoDTO> listarPermissoes() throws DAOException {
		
		Collection<PermissaoDTO> listaPemissoes = new ArrayList<PermissaoDTO>();
		Connection con = null;
		PermissaoDTO PermissaoDTO = null;
		
		try {
			con = Conexao.obterInstancia().obterConexao();
			
			PreparedStatement pst = con.prepareStatement(montaListarPermissoes());
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				PermissaoDTO = new PermissaoDTO();

				PermissaoDTO.setCodigoPermissao(rs.getInt("codigo_permissao"));
				PermissaoDTO.setCodigoPapel(rs.getInt("cod_papel"));
				PermissaoDTO.setLoginPessoa(rs.getString("login_pessoa"));

				listaPemissoes.add(PermissaoDTO);
			}
			
			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new DAOException("Ocorreu um erro com a execu��o do comando SQL!", e);
		} catch (Exception e) {
			throw new DAOException("Ocorreu um erro inesperado!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Ocorreu um erro ao tentar fechar conex�o com o banco de dados!", e);
			}
		}
		return listaPemissoes;
	}
	
	/**
	 * Monsta o sql para atualiza��o da permiss�o
	 * @return String sql
	 */
	private String montaAlterarPermissao() {

		StringBuffer sql = new StringBuffer();

		sql.append(" UPDATE ");
		sql.append(" 	permissao ");
		sql.append(" SET ");
		sql.append(" 	cod_papel  = ?");
		sql.append(" WHERE ");
		sql.append("	codigo_permissao = ?");

		return sql.toString();
	}
	
	/**
	 * Monsta o sql para dele��o da permiss�o
	 * @return String sql
	 */
	private String montaDeletarPermissao() {

		StringBuffer sql = new StringBuffer();

		sql.append(" DELETE ");
		sql.append(" 	permissao ");
		sql.append(" WHERE ");
		sql.append("	codigo_permissao = ?");

		return sql.toString();
	}
	
	/**
	 * Monsta o sql para inser��o da uma nova permiss�o
	 * @return String sql
	 */
	private String montaInserirPermissao() {

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO ");
		sql.append("	permissao");
		sql.append("	(codigo_permissao, codigo_papel,");
		sql.append("	 pessoa) ");
		sql.append("VALUES ");
		sql.append("	(permiseq.nextval,?, ?)");

		return sql.toString();
	}
	
	/**
	 * Monta o sql para retornar uma lista de permiss�es
	 * @return String sql
	 */
	public String montaListarPermissoes()
	{
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT "); 
		sql.append("     pe.codigo_permissao, pa.nome, pe.login_pessoa ");
		sql.append("   FROM "); 
		sql.append("     permissao pe, papel pa ");
		sql.append("  WHERE "); 
		sql.append("     pe.cod_papel = pa.codigo_papel ");
		sql.append("  ORDER BY ");
		sql.append("     pa.nome ");
		
		return sql.toString();
	}
	
	/**
	 * Monta o sql para retornar uma permiss�o de um determinado usu�rio
	 * @return String sql
	 */
	public String montaBuscaPermissoes()
	{
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT ");
		sql.append(" 	pa.codigo_papel ");
		sql.append(" FROM ");
		sql.append(" 	permissao pe, usuario us, papel pa ");
		sql.append(" WHERE ");
		sql.append(" 	pe.login_pessoa = us.login ");
		sql.append(" AND ");
		sql.append(" 	pe.cod_papel = pa.codigo_papel ");
		sql.append(" AND ");
		sql.append(" 	us.login = ? ");
		
		return sql.toString();
	}
}
