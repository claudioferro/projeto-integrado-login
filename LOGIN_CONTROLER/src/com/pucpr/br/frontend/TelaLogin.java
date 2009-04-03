package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

/**
 * @author Thiago Alves
 * 
 * @version 1.0
 * 
 *          Tela de login que contem ....
 * */

public class TelaLogin extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private JLabel lbLogin;
	private JLabel lbSenha;

	private JTextField txtLogin;
	private JTextField txtSenha;

	private JButton btnConfirmar;
	private JButton btnNovoUsuario;
	private JButton btnCancelar;

	private JPanel panelCampos;
	private JPanel panelBotoes;

	public TelaLogin() {

		inicializarComponentes();

		// Define o titulo da tela
		setTitle(ConstantsFrontEnd.LOGIN_TITULO_TELA_LOGIN);
		// Define fechamento da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Define o layout do frame
		// BorderLayout borderLayout = new BorderLayout(5,5);
		// setLayout(borderLayout);

		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2,
				400, 200);

	}

	private void inicializarComponentes() {

		// Inicializando paineis
		panelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
		panelBotoes = new JPanel(new GridLayout(1, 3, 5, 5));

		//Adiciona componentes no painel de campos
		panelCampos.add(getLabelLogin());
		panelCampos.add(getTextLogin());
		panelCampos.add(getLabelSenha());
		panelCampos.add(getTextSenha());
		
		//Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoConfirmar());
		panelBotoes.add(getBotaoCancelar());
		panelBotoes.add(getBotaoNovoUsuario());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);

	}

	private JButton getBotaoConfirmar() {

		btnConfirmar = new JButton(ConstantsFrontEnd.LOGIN_CONFIRMAR);
		return btnConfirmar;
	}

	private JButton getBotaoNovoUsuario() {

		btnNovoUsuario = new JButton(ConstantsFrontEnd.LOGIN_NOVO_USUARIO);
		return btnNovoUsuario;
	}

	private JButton getBotaoCancelar() {

		btnCancelar = new JButton(ConstantsFrontEnd.LOGIN_CANCELAR);
		return btnCancelar;
	}
	
	private JLabel getLabelLogin() {

		lbLogin = new JLabel(ConstantsFrontEnd.LOGIN_LOGIN);
		return lbLogin;
	}
	
	private JLabel getLabelSenha() {

		lbSenha = new JLabel(ConstantsFrontEnd.LOGIN_SENHA);
		return lbSenha;
	}
	
	private JTextField getTextLogin() {

		txtLogin = new JTextField();
		return txtLogin;
	}

	private JTextField getTextSenha() {

		txtSenha= new JTextField();
		return txtSenha;
	}

	public static void main(String[] args) {

		setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
					.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());

		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}

		TelaLogin login = new TelaLogin();
		login.setVisible(true);
		login.repaint();

	}

}
