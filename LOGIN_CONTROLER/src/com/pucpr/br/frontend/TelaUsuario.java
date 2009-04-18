package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

public class TelaUsuario extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 4379942071567806852L;
	
	private JTextField txtLogin;
	private JTextField txtNome;
	private JTextField txtSenha;
	private JTextField txtConfirmarSenha;
	
	private JPanel panelCampos;
	private JPanel panelBotoes;
	
	public TelaUsuario(boolean editar){
		inicializarComponentes(editar);
		
		// Define o titulo da tela
		if (editar)
			setTitle(ConstantsFrontEnd.USUARIO_TITULO_TELA_EDITAR_USUARIO);
		else
			setTitle(ConstantsFrontEnd.USUARIO_TITULO_TELA_NOVO_USUARIO);
		// Define fechamento da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2,
				400, 200);
	}

	private void inicializarComponentes(boolean editar) {
		// Inicializando paineis
		panelCampos = new JPanel(new GridBagLayout());

		panelBotoes = new JPanel(new GridLayout(1, 2, 5, 5));

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
		//panelBotoes.add(getBotaoConfirmar());
		//panelBotoes.add(getBotaoCancelar());
		//panelBotoes.add(getBotaoNovoUsuario());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);		
	}
	
	private JTextField getTextLogin() {

		txtLogin = new JTextField();
		return txtLogin;
	}
	
	private JTextField getTextNome() {

		txtNome = new JTextField();
		return txtNome;
	}

	private JTextField getTextSenha() {

		txtSenha = new JTextField();
		return txtSenha;
	}
	
	private JTextField getTextConfirmarSenha() {

		txtConfirmarSenha = new JTextField();
		return txtConfirmarSenha;
	}

}
