package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
	
	//variaveis da tela
	private JList listPapeis;
	
	private JScrollPane painelListPapeis;
	
	private JButton btnNovo;
	private JButton btnExcluir;
	
	private JPanel panelCampos;
	private JPanel panelBotoes;
	
	private ListenerBotoes listenerBotoes = new ListenerBotoes();
		
	public TelaManterPapeis() {
		super(ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA, false, true, false, false);
		
		//inicia os componentes da janela
		inicializarComponentes();
				
		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posi��o do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 300) / 2,
				400, 250);
				
		setVisible(true);
		
	}

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
		listPapeis.setVisibleRowCount(6);
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
	 * Instancia o bot�o Novo para adicionar um novo papel
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
	 * Instancia o bot�o Exluir para excluir um papel
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
	 * M�todo atualiza a lista de papeis de acordo com o que esta cadastrado
	 * 
	 * @return List<PapelDTO> listaPapeis
	 */
	private List<PapelDTO> carregarListaPapeis() {
		List<PapelDTO> listaPapeis = new ArrayList<PapelDTO>();
		
		//TODO: Fazer a chamada para o command para carregar a lista de Papeis
		
		return listaPapeis;
	}	
	
	
	/**
	 * Classe interna que implenta o ActionListener para as a��es dos bot�es
	 *
	 */
	private class ListenerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(ConstantsFrontEnd.MANTER_PAPEIS_NOVO)) {
				String papelDigitado = JOptionPane.showInputDialog(null, ConstantsFrontEnd.MANTER_PAPEIS_MSG_NOVO, ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA, JOptionPane.QUESTION_MESSAGE);
				if (!papelDigitado.equals("")) {
					
					// TODO : Fazer a chamada para o command adicionar papel
					
				} else {
					JOptionPane.showMessageDialog(null, ConstantsFrontEnd.MANTER_PAPEIS_MSG_NOME_VAZIO, ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA, JOptionPane.WARNING_MESSAGE);
				}
			} else {
				if (e.getActionCommand().equals(ConstantsFrontEnd.MANTER_PAPEIS_EXCLUIR)) {
					String msg = ConstantsFrontEnd.MANTER_PAPEIS_MSG_EXCLUSAO.replaceAll("#", listPapeis.getSelectedValue().toString()); 
					int resposta = JOptionPane.showConfirmDialog(null, msg, ConstantsFrontEnd.MANTER_PAPEIS_TITULO_TELA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION)	{
						
						// TODO : Fazer a chamada para o command excluir o papel selecionado
						
						carregarListaPapeis();																		
					}
				}
			}
		}
		
	}
	

}
