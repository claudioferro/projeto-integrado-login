package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

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

	private JTextField txtLogin;
	private JTextField txtSenha;

	private JButton btnConfirmar;
	private JButton btnNovoUsuario;
	private JButton btnCancelar;

	private JPanel panelCampos;
	private JPanel panelBotoes;

	private ListnerBotoes listnerBotoes = new ListnerBotoes();

	public TelaLogin() {

		inicializarComponentes();

		// Define o titulo da tela
		setTitle(ConstantsFrontEnd.LOGIN_TITULO_TELA_LOGIN);
		// Define fechamento da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2,
				400, 200);

	}

	private void inicializarComponentes() {

		// Inicializando paineis
		panelCampos = new JPanel(new GridBagLayout());

		panelBotoes = new JPanel(new GridLayout(1, 3, 5, 5));

		// Adiciona componentes no painel de campos
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.LOGIN_LOGIN,
				getTextLogin());
		GridBagLayoutUtils.add(panelCampos, ConstantsFrontEnd.LOGIN_SENHA,
				getTextSenha());

		// Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoConfirmar());
		panelBotoes.add(getBotaoCancelar());
		panelBotoes.add(getBotaoNovoUsuario());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(panelCampos, BorderLayout.CENTER);

	}

	private JButton getBotaoConfirmar() {

		btnConfirmar = new JButton(ConstantsFrontEnd.LOGIN_CONFIRMAR);
		btnConfirmar.setActionCommand(ConstantsFrontEnd.LOGIN_CONFIRMAR);
		btnConfirmar.addActionListener(listnerBotoes);
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

	private JTextField getTextLogin() {

		txtLogin = new JTextField();
		return txtLogin;
	}

	private JTextField getTextSenha() {

		txtSenha = new JTextField();
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

	private class ListnerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(ConstantsFrontEnd.LOGIN_CONFIRMAR)) {
				System.exit(0);
			} else {
				if (e.getActionCommand().equals(
						ConstantsFrontEnd.LOGIN_CANCELAR)) {
					// TODO Chama o command
				} else {
					if (e.getActionCommand().equals(
							ConstantsFrontEnd.LOGIN_NOVO_USUARIO)) {
						// TODO Chama o command
					}
				}
			}
		}

	}
}