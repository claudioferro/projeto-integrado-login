package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.pucpr.br.command.FrontController;
import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

/**
 * @author Rony Sartor
 * 
 * @version 1.0
 * 
 *          Tela para manter os Papeis
 * 
 */
public class TelaManterPapeis extends JInternalFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 7781991049734614240L;

	// variaveis da tela
	private JList listPapeis;

	private JScrollPane painelListPapeis;

	private JButton btnNovo;
	private JButton btnExcluir;

	private JPanel panelCampos;
	private JPanel panelBotoes;

	private ListenerBotoes listenerBotoes = new ListenerBotoes();

	public TelaManterPapeis() {
	super(ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA, false, true, false,
				false);

		// inicia os componentes da janela
		inicializarComponentes();

		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 300) / 2,
				400, 250);

		setVisible(true);

	}

	/**
	 * Método para instanciar os componentes da tela
	 */
	private void inicializarComponentes() {

		// Inicializando paineis
		panelCampos = new JPanel(new GridBagLayout());

		panelBotoes = new JPanel(new GridLayout(1, 2, 15, 15));

		// Adiciona componentes no painel de campos
		GridBagLayoutUtils.add(panelCampos, "", getPainelListPapeis());

		// Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoNovo());
		panelBotoes.add(getBotaoExcluir());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);

	}

	/**
	 * Instancia a lista com os Papeis
	 * 
	 * @return JList listPapeis
	 */
	private JList getListPapeis() {
		listPapeis = new JList();
		listPapeis.setVisibleRowCount(7);
		listPapeis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		listPapeis.setListData(new Vector<PapelDTO>(carregarListaPapeis()));
		listPapeis.setSelectedIndex(0);
		return listPapeis;
	}

	/**
	 * Instancia o scroll que contem lista com os Papeis
	 * 
	 * @return JScrollPane painelListPapeis
	 */
	private JScrollPane getPainelListPapeis() {
		painelListPapeis = new JScrollPane(getListPapeis());
		return painelListPapeis;
	}

	/**
	 * Instancia o botão Novo para adicionar um novo papel
	 * 
	 * @return JButton btnNovo
	 */
	private JButton getBotaoNovo() {
		btnNovo = new JButton(ConstantsFrontEnd.MANTER_PAPEIS_NOVO);
		btnNovo.setActionCommand(ConstantsFrontEnd.MANTER_PAPEIS_NOVO);
		btnNovo.addActionListener(listenerBotoes);
		return btnNovo;
	}

	/**
	 * Instancia o botão Exluir para excluir um papel
	 * 
	 * @return JButton btnExcluir
	 */
	private JButton getBotaoExcluir() {
		btnExcluir = new JButton(ConstantsFrontEnd.MANTER_PAPEIS_EXCLUIR);
		btnExcluir.setActionCommand(ConstantsFrontEnd.MANTER_PAPEIS_EXCLUIR);
		btnExcluir.addActionListener(listenerBotoes);
		return btnExcluir;
	}

	/**
	 * Método atualiza a lista de papeis de acordo com o que esta cadastrado
	 * 
	 * @return List<PapelDTO> listaPapeis
	 */
	private List<PapelDTO> carregarListaPapeis() {
		//List<PapelDTO> listaPapeis = new ArrayList<PapelDTO>();

		// TODO: Fazer a chamada para o command para carregar a lista de Papeis
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> retorno = FrontController
		.executeCommand(
				ConstantsFrontEnd.MANTER_PAPEIS_LISTAR,
				data);
		
		List<PapelDTO> listaPapeis = (List<PapelDTO>)retorno.get(ConstantsFrontEnd.RETORNO_LISTA_PAPEIS);
		
		return listaPapeis;
	}

	/**
	 * Classe interna que implenta o ActionListener para as ações dos botões
	 * 
	 */
	private class ListenerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Boolean incluido = true;
			if (e.getActionCommand().equals(
					ConstantsFrontEnd.MANTER_PAPEIS_NOVO)) {
				String papelDigitado = JOptionPane.showInputDialog(null,
						ConstantsFrontEnd.MANTER_PAPEIS_MSG_NOVO,
						ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA,
						JOptionPane.QUESTION_MESSAGE);
				if (!papelDigitado.equals("")) {

					Map<String, Object> data = new HashMap<String, Object>();
					data.put(ConstantsFrontEnd.MANTER_PAPEIS_NOME_PAPEL,
							papelDigitado);

					Map<String, Object> retorno = FrontController
							.executeCommand(
									ConstantsFrontEnd.USUARIO_INCLUIR_PAPEL,
									data);

					if (retorno.get(ConstantsFrontEnd.NOVOPAPEL_RETORNO) == incluido) {
						JOptionPane
								.showMessageDialog(
										null,
										ConstantsFrontEnd.MANTER_PAPEIS_MSG_INCLUIR_SUCESSO,
										ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA,
										JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										ConstantsFrontEnd.MANTER_PAPEIS_MSG_INCLUIR_ERRO,
										ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA,
										JOptionPane.WARNING_MESSAGE);
					}

				} else
					JOptionPane.showMessageDialog(null,
							ConstantsFrontEnd.MANTER_PAPEIS_MSG_NOME_VAZIO,
							ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA,
							JOptionPane.WARNING_MESSAGE);
			} else {
				if (e.getActionCommand().equals(
						ConstantsFrontEnd.MANTER_PAPEIS_EXCLUIR)) {
					if (listPapeis.getSelectedIndex() != -1) {
						String msg = ConstantsFrontEnd.MANTER_PAPEIS_MSG_EXCLUSAO
								.replaceAll("#", listPapeis.getSelectedValue()
										.toString());
						int resposta = JOptionPane.showConfirmDialog(null, msg,
								ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA,
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (resposta == JOptionPane.YES_OPTION) {


							Map<String, Object> data = new HashMap<String, Object>();
							data.put(ConstantsFrontEnd.MANTER_PAPEIS_NOME_PAPEL,
									(PapelDTO)listPapeis.getSelectedValue());

							FrontController
									.executeCommand(
											ConstantsFrontEnd.USUARIO_EXCLUIR_PAPEL,
											data);

							carregarListaPapeis();
						}
					} else
						JOptionPane.showMessageDialog(null,
								ConstantsFrontEnd.MANTER_PAPEIS_MSG_SELECAO,
								ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA,
								JOptionPane.WARNING_MESSAGE);
				}
			}
			
			
		}

	}

}
