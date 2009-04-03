package com.pucpr.br.services;

import java.util.List;

import com.pucpr.br.dto.PapelDTO;

public interface ServiceManterPapel {

	public boolean incluirPapel(PapelDTO papel);

	public boolean alterarPapel(PapelDTO papel);

	public boolean excluirPapel(PapelDTO papel);

	public PapelDTO buscarPapel(PapelDTO papel);

	public List<PapelDTO> listarPapeis();

}
