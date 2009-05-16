package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.pucpr.br.dto.PapelDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

public class TelaEditarPapeisUsuario extends JInternalFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -4947681971406021444L;
	
	//variaveis da tela
	private JList listPapeisUsuario;
	private JList listPapeisDisponiveis;
	
	private JScrollPane painelListPapeisUsuario;
	private JScrollPane painelListPapeisDisponiveis;
	
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnMaiorSimples;
	private JButton btnMenorSimples;
	private JButton btnMaiorDuplo;
	private JButton btnMenorDuplo;
	
	private JPanel panelCampos;
	private JPanel panelBotoesListas;
	private JPanel panelBotoes;
	
	private BoxLayout boxLayout;
	
	private ListenerBotoes listenerBotoes = new ListenerBotoes();
	private ListenerListas listenerListas = new ListenerListas();
	
	private List<PapelDTO> papeisUsuario;
	private List<PapelDTO> papeisDisponiveis;
		
	public TelaEditarPapeisUsuario() {
		super(ConstantsFrontEnd.PAPEIS_USUARIO_TITULO_TELA, true, true, false, false);
		
		//inicia os componentes da janela
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
		
		panelBotoesListas = new JPanel();
		panelBotoesListas.setLayout(getBoxLayout());

		// Adiciona componentes no painel de campos
		GridBagLayoutUtils.add(panelCampos, new JLabel(ConstantsFrontEnd.PAPEIS_USUARIO), new JLabel(""), new JLabel(ConstantsFrontEnd.PAPEIS_USUARIO_DISPONIVEL));
		GridBagLayoutUtils.add(panelCampos, getPainelListPapeisUsuario(), panelBotoesListas, getPainelListPapeisDisponiveis());		
		

		// Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoSalvar());
		panelBotoes.add(getBotaoCancelar());		

		panelBotoesListas.add(getBotaoMaiorSimples());
		panelBotoesListas.add(Box.createVerticalGlue());
		panelBotoesListas.add(getBotaoMaiorDuplo());
		panelBotoesListas.add(Box.createVerticalGlue());
		panelBotoesListas.add(getBotaoMenorSimples());
		panelBotoesListas.add(Box.createVerticalGlue());
		panelBotoesListas.add(getBotaoMenorDuplo());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);
		
	}
	
	/**
	 * Instancia a lista com os Papeis do Usuario
	 * 
	 * @return JList listPapeisUsuario
	 */
	private JList getListPapeisUsuario() {
		listPapeisUsuario = new JList();		
		listPapeisUsuario.setVisibleRowCount(7);
		listPapeisUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPapeisUsuario.setName(ConstantsFrontEnd.PAPEIS_USUARIO);
		listPapeisUsuario.addMouseListener(listenerListas);
	
		listPapeisUsuario.setListData(new Vector<PapelDTO>(carregarListaPapeisUsuario()));
		listPapeisUsuario.setSelectedIndex(0);
		return listPapeisUsuario;
	}
	
	/**
	 * Instancia a lista com os Papeis disponiveis
	 * 
	 * @return JList listPapeisDisponiveis
	 */
	private JList getListPapeisDisponiveis() {
		listPapeisDisponiveis = new JList();		
		listPapeisDisponiveis.setVisibleRowCount(7);
		listPapeisDisponiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPapeisDisponiveis.setName(ConstantsFrontEnd.PAPEIS_USUARIO_DISPONIVEL);
		listPapeisDisponiveis.addMouseListener(listenerListas);
	
		listPapeisDisponiveis.setListData(new Vector<PapelDTO>(carregarListaPapeisDisponiveis()));		
		return listPapeisDisponiveis;
	}
	
	/**
	 * Instancia o scroll que contem lista com os Papeis do Usuario
	 * 
	 * @return JScrollPane painelListPapeisUsuario
	 */
	private JScrollPane getPainelListPapeisUsuario() {
		painelListPapeisUsuario = new JScrollPane(getListPapeisUsuario());
		return painelListPapeisUsuario;
	}
	
	/**
	 * Instancia o scroll que contem lista com os Papeis Disponiveis
	 * 
	 * @return JScrollPane painelListPapeisDisponiveis
	 */
	private JScrollPane getPainelListPapeisDisponiveis() {
		painelListPapeisDisponiveis = new JScrollPane(getListPapeisDisponiveis());		
		return painelListPapeisDisponiveis;
	}
	
	/**
	 * Instancia o botão Salvar para salvar os papeis do usuário
	 * 
	 * @return JButton btnSalvar
	 */
	private JButton getBotaoSalvar() {
		btnSalvar = new JButton(ConstantsFrontEnd.PAPEIS_USUARIO_SALVAR);
		btnSalvar.setActionCommand(ConstantsFrontEnd.PAPEIS_USUARIO_SALVAR);
		btnSalvar.addActionListener(listenerBotoes);
		return btnSalvar;
	}
	
	/**
	 * Instancia o botão Novo para adicionar um novo papel
	 * 
	 * @return JButton btnCancelar
	 */
	private JButton getBotaoCancelar() {
		btnCancelar = new JButton(ConstantsFrontEnd.PAPEIS_USUARIO_CANCELAR);
		btnCancelar.setActionCommand(ConstantsFrontEnd.PAPEIS_USUARIO_CANCELAR);
		btnCancelar.addActionListener(listenerBotoes);
		return btnCancelar;
	}
	
	/**
	 * Instancia o layout para exibição dos botões na vertical
	 * 
	 * @return BoxLayout BoxLayout
	 */
	private BoxLayout getBoxLayout() {
		boxLayout = new BoxLayout(panelBotoesListas, BoxLayout.Y_AXIS);
		
		return boxLayout;
	}
	
	/**
	 * Instancia o botão > que passa um item para a outra lista
	 * 
	 * @return JButton btnMaiorSimples
	 */
	private JButton getBotaoMaiorSimples() {
		btnMaiorSimples = new JButton(ConstantsFrontEnd.PAPEIS_USUARIO_MAIOR_SIMPLES);
		btnMaiorSimples.setActionCommand(ConstantsFrontEnd.PAPEIS_USUARIO_MAIOR_SIMPLES);
		btnMaiorSimples.addActionListener(listenerBotoes);		
		return btnMaiorSimples;
	}
	
	/**
	 * Instancia o botão >> que passa todos itens para a outra lista
	 * 
	 * @return JButton btnMaiorDuplo
	 */
	private JButton getBotaoMaiorDuplo() {
		btnMaiorDuplo = new JButton(ConstantsFrontEnd.PAPEIS_USUARIO_MAIOR_DUPLO);
		btnMaiorDuplo.setActionCommand(ConstantsFrontEnd.PAPEIS_USUARIO_MAIOR_DUPLO);
		btnMaiorDuplo.addActionListener(listenerBotoes);
		return btnMaiorDuplo;
	}
	
	/**
	 * Instancia o botão < que passa um item para a outra lista
	 * 
	 * @return JButton btnMenorSimples
	 */
	private JButton getBotaoMenorSimples() {
		btnMenorSimples = new JButton(ConstantsFrontEnd.PAPEIS_USUARIO_MENOR_SIMPLES);
		btnMenorSimples.setActionCommand(ConstantsFrontEnd.PAPEIS_USUARIO_MENOR_SIMPLES);
		btnMenorSimples.addActionListener(listenerBotoes);
		btnMenorSimples.setEnabled(false);
		return btnMenorSimples;
	}
	
	/**
	 * Instancia o botão << que passa todos itens para a outra lista
	 * 
	 * @return JButton btnMenorDuplo
	 */
	private JButton getBotaoMenorDuplo() {
		btnMenorDuplo = new JButton(ConstantsFrontEnd.PAPEIS_USUARIO_MENOR_DUPLO);
		btnMenorDuplo.setActionCommand(ConstantsFrontEnd.PAPEIS_USUARIO_MENOR_DUPLO);
		btnMenorDuplo.addActionListener(listenerBotoes);
		btnMenorDuplo.setEnabled(false);
		return btnMenorDuplo;
	}
	
	/**
	 * Método atualiza a lista de papeis do usuarios de acordo com o que esta cadastrado
	 * 
	 * @return List<PapelDTO> listaPapeisUsuario
	 */
	private List<PapelDTO> carregarListaPapeisUsuario() {
		papeisUsuario = new ArrayList<PapelDTO>();
		
		for(int i=0; i< 15; i++){
			PapelDTO papel = new PapelDTO();
			papel.setCodigo("" + i);
			papel.setNome("Nome do papel "+ i);
			papeisUsuario.add(papel);
		}
		//TODO: Fazer a chamada para o command para carregar a lista de Papeis do Usuario
		
		return papeisUsuario;
	}
	
	/**
	 * Método atualiza a lista de papeis disponiveis para um usuario
	 * 
	 * @return List<PapelDTO> listaPapeisDisponiveis
	 */
	private List<PapelDTO> carregarListaPapeisDisponiveis() {
		papeisDisponiveis = new ArrayList<PapelDTO>();
		
		for(int i=0; i< 15; i++){
			PapelDTO papel = new PapelDTO();
			papel.setCodigo("" + i);
			papel.setNome("Nome do papel "+ i);
			papeisDisponiveis.add(papel);
		}
		//TODO: Fazer a chamada para o command para carregar a lista de Papeis disponiveis para um Usuario
		
		return papeisDisponiveis;
	}
	
	/**
	 * Classe interna que implenta o ActionListener para as ações dos botões
	 *
	 */
	private class ListenerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(ConstantsFrontEnd.PAPEIS_USUARIO_SALVAR)) {
				
				// TODO : Fazer a chamada para o command salvar as alterações nos papéis do usuario
				
			} else if (e.getActionCommand().equals(ConstantsFrontEnd.PAPEIS_USUARIO_CANCELAR)) {
				dispose();
			} else {
				if (e.getActionCommand().equals(ConstantsFrontEnd.PAPEIS_USUARIO_MAIOR_SIMPLES)) {
						
					if (listPapeisUsuario.getSelectedValue() != null) {
						papeisDisponiveis.add((PapelDTO) listPapeisUsuario.getSelectedValue());
						papeisUsuario.remove(listPapeisUsuario.getSelectedIndex());
					}
					
				} else if (e.getActionCommand().equals(ConstantsFrontEnd.PAPEIS_USUARIO_MAIOR_DUPLO)) {
					
					for (PapelDTO papel : papeisUsuario) {
						papeisDisponiveis.add(papel);
					}
					papeisUsuario.clear();
					
				} else if (e.getActionCommand().equals(ConstantsFrontEnd.PAPEIS_USUARIO_MENOR_SIMPLES)) {
					
					if (listPapeisDisponiveis.getSelectedValue() != null) {
						papeisUsuario.add((PapelDTO) listPapeisDisponiveis.getSelectedValue());
						papeisDisponiveis.remove(listPapeisDisponiveis.getSelectedIndex());
					}
					
				} else if (e.getActionCommand().equals(ConstantsFrontEnd.PAPEIS_USUARIO_MENOR_DUPLO)) {
					
					for (PapelDTO papel : papeisDisponiveis) {
						papeisUsuario.add(papel);
					}
					papeisDisponiveis.clear();
					
				}

				listPapeisDisponiveis.setListData(new Vector<PapelDTO>(papeisDisponiveis));
				listPapeisUsuario.setListData(new Vector<PapelDTO>(papeisUsuario));
			}
		}
		
	}
	
	/**
	 * Classe interna que implenta o MouseListener para cliques nas listas
	 *
	 */
	private class ListenerListas implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {			
			if (e.getComponent().getName().equals(ConstantsFrontEnd.PAPEIS_USUARIO_DISPONIVEL)) {
				listPapeisUsuario.clearSelection();
				btnMaiorSimples.setEnabled(false);
				btnMaiorDuplo.setEnabled(false);
				btnMenorSimples.setEnabled(true);
				btnMenorDuplo.setEnabled(true);
			} else {
				listPapeisDisponiveis.clearSelection();
				btnMaiorSimples.setEnabled(true);
				btnMaiorDuplo.setEnabled(true);
				btnMenorSimples.setEnabled(false);
				btnMenorDuplo.setEnabled(false);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
		
	}

}
