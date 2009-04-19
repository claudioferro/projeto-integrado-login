package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.pucpr.br.command.FrontController;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

/**
 * @author Thiago Alves, Rony Sartor
 * 
 * @version 1.2
 * 
 *          Tela de login que contem os campos para a autenticação do usuário
 * */

public class TelaLogin extends JInternalFrame implements ComponentListener{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	//variaveis da tela
	private Principal desktop;

	private JTextField txtLogin;
	private JPasswordField txtSenha;

	private JButton btnConfirmar;
	private JButton btnNovoUsuario;	

	private JPanel panelCampos;
	private JPanel panelBotoes;

	private ListenerBotoes listenerBotoes = new ListenerBotoes();

	public TelaLogin(Principal desktop) {
		super(ConstantsFrontEnd.LOGIN_TITULO_TELA_LOGIN, false, false, false, false);
		
		//seta a tela "mae" da aplicação
		this.desktop = desktop;
		
		//inicia os componentes da janela
		inicializarComponentes();

		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 300) / 2, (screenSize.height - 250) / 2,
				300, 150);
		addComponentListener(this);	
		setVisible(true);

	}

	private void inicializarComponentes() {

		// Inicializando paineis
		panelCampos = new JPanel(new GridBagLayout());

		panelBotoes = new JPanel(new GridLayout(1, 2, 15, 15));

		// Adiciona componentes no painel de campos
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.LOGIN_LOGIN,
				getTextLogin());
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.LOGIN_SENHA,
				getTextSenha());

		// Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoConfirmar());		
		panelBotoes.add(getBotaoNovoUsuario());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);

	}

	/**
	 * Instancia o botão Confirmar da Janela
	 * 
	 * @return JButton btnConfirmar
	 */
	private JButton getBotaoConfirmar() {

		btnConfirmar = new JButton(ConstantsFrontEnd.LOGIN_CONFIRMAR);		
		btnConfirmar.setActionCommand(ConstantsFrontEnd.LOGIN_CONFIRMAR);
		//adiciona um listener para o botão
		btnConfirmar.addActionListener(listenerBotoes);
		return btnConfirmar;
	}

	/**
	 * Instancia o botão Novo Usuario da Janela
	 * 
	 * @return JButton btnNovoUsuario
	 */
	private JButton getBotaoNovoUsuario() {

		btnNovoUsuario = new JButton(ConstantsFrontEnd.LOGIN_NOVO_USUARIO);
		//adiciona um listener para o botão
		btnNovoUsuario.addActionListener(listenerBotoes);
		return btnNovoUsuario;
	}

	/**
	 * Instancia o campo Login da Janela
	 * 
	 * @return JTextField txtLogin
	 */
	private JTextField getTextLogin() {

		txtLogin = new JTextField();
		return txtLogin;
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
	 * Classe interna que implenta o ActionListener para as ações dos botões
	 *
	 */
	private class ListenerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(ConstantsFrontEnd.LOGIN_CONFIRMAR)) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put(ConstantsFrontEnd.LOGIN_LOGIN, txtLogin.getText());
				data.put(ConstantsFrontEnd.LOGIN_SENHA, new String (txtSenha.getPassword()));

				FrontController.executeCommand(
						ConstantsFrontEnd.LOGIN_CONFIRMAR, data);

			} else if (e.getActionCommand().equals(
						ConstantsFrontEnd.LOGIN_NOVO_USUARIO)) {
				
				//abre a janela para criar um novo usuario
				TelaUsuario telaUsuario = new TelaUsuario(false);
				desktop.novaJanela(telaUsuario);				
			}
		}

	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {
		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 300) / 2, (screenSize.height - 250) / 2,
				300, 150);
	}

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}
}