package com.pucpr.br.dto;

import java.io.Serializable;

/**
 * @author Thiago Alves
 * @version 1.0
 */
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = -2441705801679003231L;

	private String nome;

	private String login;

	private String senha;

	private boolean situacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

}
