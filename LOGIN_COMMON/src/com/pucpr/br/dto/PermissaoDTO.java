package com.pucpr.br.dto;

import java.io.Serializable;

/**
 * @author Thiago Alves
 * @version 1.0
 * 
 * Atualização (Maicon Zotto)
 */

public class PermissaoDTO implements Serializable {

	private static final long serialVersionUID = -4679794231596018792L;

	private int codigoPapel;
	private int codigoPermissao;
	private String loginPessoa;
	
	public int getCodigoPapel() {
		return codigoPapel;
	}

	public void setCodigoPapel(int codigoPapel) {
		this.codigoPapel = codigoPapel;
	}

	public int getCodigoPermissao() {
		return codigoPermissao;
	}

	public void setCodigoPermissao(int codigoPermissao) {
		this.codigoPermissao = codigoPermissao;
	}

	public String getLoginPessoa() {
		return loginPessoa;
	}

	public void setLoginPessoa(String loginPessoa) {
		this.loginPessoa = loginPessoa;
	}
}
