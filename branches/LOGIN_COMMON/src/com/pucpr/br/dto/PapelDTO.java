package com.pucpr.br.dto;

import java.io.Serializable;

public class PapelDTO implements Serializable {

	private static final long serialVersionUID = -2469386659300202737L;
	private String nome;
	private String codigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
