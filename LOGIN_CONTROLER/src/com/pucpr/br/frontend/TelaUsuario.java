package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

/**
 * @author Rony Sartor
 * 
 * @version 1.0
 * 
 *          Tela de cadastramento e edição do Usuário
 *          
 */
public class TelaUsuario extends JInternalFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 4379942071567806852L;
	
	//variaveis da tela
	private JTextField txtLogin;
	private JTextField txtNome;
	private JPasswordField txtSenha;
	private JPasswordField txtConfirmarSenha;
	
	private JButton btnConfirmar;
	private JButton btnCancelar;	
	
	private JPanel panelCampos;
	private JPanel panelBotoes;
	
	private boolean editar;
	
	private ListenerBotoes listenerBotoes = new ListenerBotoes();
	
	/**
	 * Construtor que recebe como parametro uma variavel booleana
	 * para identificar se é um novo usuario ou se é para editar um usuario
	 * 
	 * @param boolean editar
	 */
	public TelaUsuario(boolean editar){
		super(ConstantsFrontEnd.USUARIO_TITULO_TELA_NOVO_USUARIO, false, true, false, false);
		
		this.editar = editar;
		
		//inicia os componentes da janela
		inicializarComponentes(editar);
		
		// Define o titulo da tela
		if (editar)
			setTitle(ConstantsFrontEnd.USUARIO_TITULO_TELA_EDITAR_USUARIO);
		
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
	private void inicializarComponentes(boolean editar) {
		
		// Inicializando paineis
		panelCampos = new JPanel(new GridBagLayout());

		panelBotoes = new JPanel(new GridLayout(1, 2, 15, 15));

		// Adiciona componentes no painel de campos
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.USUARIO_LOGIN,
				getTextLogin());
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.USUARIO_NOME,
				getTextNome());
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.USUARIO_SENHA,
				getTextSenha());
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.USUARIO_CONFIRMAR_SENHA,
				getTextConfirmarSenha());

		// Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoConfirmar());
		panelBotoes.add(getBotaoCancelar());		

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);		
	}
	
	/**
	 * Instancia o campo Login da Janela
	 * 
	 * @return JTextField txtLogin
	 */
	private JTextField getTextLogin() {

		txtLogin = new JTextField();
		if (editar)
			txtLogin.setEnabled(false);
		return txtLogin;
	}
	
	/**
	 * Instancia o campo Nome da Janela
	 * 
	 * @return JTextField txtNome
	 */
	private JTextField getTextNome() {

		txtNome = new JTextField();
		return txtNome;
	}

	/**
	 * Instancia o campo Senha da Janela
	 * 
	 * @return JTextField txtSenha
	 */
	private JTextField getTextSenha() {

		txtSenha = new JPasswordField();
		return txtSenha;
	}
	
	/**
	 * Instancia o campo Confirmar Senha da Janela
	 * 
	 * @return JTextField txtConfirmarSenha
	 */
	private JTextField getTextConfirmarSenha() {

		txtConfirmarSenha = new JPasswordField();
		return txtConfirmarSenha;
	}
	
	/**
	 * Instancia o botão Confirmar da Janela
	 * 
	 * @return JButton btnConfirmar
	 */
	private JButton getBotaoConfirmar() {

		btnConfirmar = new JButton(ConstantsFrontEnd.USUARIO_CONFIRMAR);
		btnConfirmar.setActionCommand(ConstantsFrontEnd.USUARIO_CONFIRMAR);
		btnConfirmar.addActionListener(listenerBotoes);
		return btnConfirmar;
	}

	/**
	 * Instancia o botão Cancelar da Janela
	 * 
	 * @return JButton btnCancelar
	 */
	private JButton getBotaoCancelar() {

		btnCancelar = new JButton(ConstantsFrontEnd.USUARIO_CANCELAR);
		btnCancelar.addActionListener(listenerBotoes);
		return btnCancelar;
	}
	
	/**
	 * Classe interna que implenta o ActionListener para as ações dos botões
	 *
	 */
	private class ListenerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(ConstantsFrontEnd.USUARIO_CONFIRMAR)) {
				
			} else {
				if (e.getActionCommand().equals(ConstantsFrontEnd.USUARIO_CANCELAR)) {
					dispose();
				}
			}
		}

	}

}
