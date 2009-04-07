package com.pucpr.br.intc.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.intc.dao.DAOUsuario;
import com.pucpr.br.uteis.Conexao;

public class DAOUsuarioSQL implements DAOUsuario {

	public boolean alterarUsuario(UsuarioDTO usuario) {

		Connection con = Conexao.obterInstancia().obterConexao();
		try {
			PreparedStatement pst = con.prepareStatement(montaAlterarUsuario());
			
			int result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public UsuarioDTO buscarUsuario(UsuarioDTO usuario) {
		return null;
	}

	public boolean excluirUsuaio(UsuarioDTO usuario) {
		return false;
	}

	public boolean inserirUsuario(UsuarioDTO usuario) {
		return false;
	}

	public Collection<UsuarioDTO> listarUsuarios() {
		return null;
	}

	private String montaAlterarUsuario() {

		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE ");
		sql.append(" PAPEL( ");
		sql.append(" 	codigo_papel,");
		sql.append(" 	nome)");
		sql.append(" VALUES(?,?) ");

		return sql.toString();
	}
}
