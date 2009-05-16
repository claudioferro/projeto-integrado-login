package com.pucpr.br.uteis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton que fornece conexões
 */
public class Conexao {

	private static final String ARQ_CONF = "/conexao.conf";
	/** instância do singleton */
	private static Conexao instancia;

	/** atributo pertencente ao objeto */
	private Properties configuracoes;

	/**
	 * Construtor privado
	 */
	private Conexao() {
		lerConfiguracoes();
	}

	/**
	 * Devolve a instância do singleton
	 * 
	 * @return
	 */
	public static Conexao obterInstancia() {

		if (instancia == null)
			instancia = new Conexao();

		return instancia;
	}

	/**
	 * Realiza a leitura das configurações de banco de dados
	 */
	private void lerConfiguracoes() {
		try {
		
			InputStream is = this.getClass().getResourceAsStream(ARQ_CONF);

			configuracoes = new Properties();
			configuracoes.load(is);

			is.close();

		} catch (IOException e) {
			throw new RuntimeException("Erro de E/S", e);
		}
	}

	/**
	 * Retorna uma nova conexão
	 * 
	 * @return
	 */
	public Connection obterConexao() {
		String driver = configuracoes.getProperty("driver");
		String url = configuracoes.getProperty("url");
		String usuario = configuracoes.getProperty("usuario");
		String senha = configuracoes.getProperty("senha");

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, usuario, senha);

			return con;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Classe não encontrada. "
					+ "Verifique driver no classpath", e);
		} catch (SQLException e) {
			throw new RuntimeException("Erro SQL", e);
		}
	}

}