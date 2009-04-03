package com.pucpr.br.dto;

import java.io.Serializable;

/**
 * @author Thiago Alves
 * @version 1.0
 */

public class PermissaoDTO implements Serializable {

	private static final long serialVersionUID = -4679794231596018792L;

	private String nome;

	private int codigoPermissao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigoPermissao() {
		return codigoPermissao;
	}

	public void setCodigoPermissao(int codigoPermissao) {
		this.codigoPermissao = codigoPermissao;
	}

}
