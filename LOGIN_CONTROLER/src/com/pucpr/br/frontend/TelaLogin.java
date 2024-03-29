package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.pucpr.br.command.FrontController;
import com.pucpr.br.dto.PermissaoDTO;
import com.pucpr.br.dto.UsuarioDTO;
import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.GridBagLayoutUtils;

/**
 * @author Thiago Alves, Rony Sartor
 * 
 * @version 1.2
 * 
 *          Tela de login que contem os campos para a autentica��o do usu�rio
 * 
 */

public class TelaLogin extends JInternalFrame implements ComponentListener {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	// variaveis da tela
	private Principal desktop;

	private JTextField txtLogin;
	private JPasswordField txtSenha;

	private JButton btnConfirmar;
	private JButton btnNovoUsuario;

	private JPanel panelCampos;
	private JPanel panelBotoes;

	private ListenerBotoes listenerBotoes = new ListenerBotoes();

	private TelaUsuario telaUsuario;

	public TelaLogin(Principal desktop) {
		super(ConstantsFrontEnd.LOGIN_TITULO_TELA_LOGIN, false, false, false,
				false);

		// seta a tela "mae" da aplica��o
		this.desktop = desktop;

		// inicia os componentes da janela
		inicializarComponentes();

		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posi��o do frame
		setBounds((screenSize.width - 300) / 2, (screenSize.height - 250) / 2,
				300, 150);
		addComponentListener(this);
		setVisible(true);

	}

	/**
	 * M�todo para instanciar os componentes da tela
	 */
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
	 * Instancia o bot�o Confirmar da Janela
	 * 
	 * @return JButton btnConfirmar
	 */
	private JButton getBotaoConfirmar() {

		btnConfirmar = new JButton(ConstantsFrontEnd.LOGIN_CONFIRMAR);
		btnConfirmar.setActionCommand(ConstantsFrontEnd.LOGIN_CONFIRMAR);
		// adiciona um listener para o bot�o
		btnConfirmar.addActionListener(listenerBotoes);
		return btnConfirmar;
	}

	/**
	 * Instancia o bot�o Novo Usuario da Janela
	 * 
	 * @return JButton btnNovoUsuario
	 */
	private JButton getBotaoNovoUsuario() {

		btnNovoUsuario = new JButton(ConstantsFrontEnd.LOGIN_NOVO_USUARIO);
		// adiciona um listener para o bot�o
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
		txtLogin.setFocusable(true);
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
	 * Classe interna que implenta o ActionListener para as a��es dos bot�es
	 * 
	 */
	private class ListenerBotoes implements ActionListener {

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(ConstantsFrontEnd.LOGIN_CONFIRMAR)) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put(ConstantsFrontEnd.LOGIN_LOGIN, txtLogin.getText());
				data.put(ConstantsFrontEnd.LOGIN_SENHA, new String(txtSenha
						.getPassword()));

				Map<String, Object> retorno = FrontController.executeCommand(
						ConstantsFrontEnd.LOGIN_CONFIRMAR, data);
				Boolean autenticado = true;
				if (retorno.get(ConstantsFrontEnd.AUTENTICAR_RETORNO) != autenticado) {

					JOptionPane.showMessageDialog(null,
							ConstantsFrontEnd.LOGIN_MSG_FALHA_AUTENTICAR,
							ConstantsFrontEnd.LOGIN_TITULO_TELA_LOGIN,
							JOptionPane.WARNING_MESSAGE);

				} else {
					// coloca o usuario na tela
					UsuarioDTO usuario = new UsuarioDTO();
					usuario.setLogin(txtLogin.getText());
					desktop.getData().put(ConstantsFrontEnd.USUARIO, usuario);

					Map<String, Object> ret = FrontController.executeCommand(
							ConstantsFrontEnd.LOGIN_AUTORIZAR, data);
					List<PermissaoDTO> listaPermissoes = new ArrayList<PermissaoDTO>();
					listaPermissoes = (ArrayList<PermissaoDTO>) ret
							.get(ConstantsFrontEnd.PERMISSOES_RETORNO);

					for (PermissaoDTO p : listaPermissoes) {
						if (p.getCodigoPapel() == 1) {
							desbloquearBotoesAdmin();
							break;
						}
					}

					desbloquearBotoes();
				}

			} else if (e.getActionCommand().equals(
					ConstantsFrontEnd.LOGIN_NOVO_USUARIO)) {
				// abre a janela para criar um novo usuario usando singleton
				if (telaUsuario == null || telaUsuario.isClosed()) {
					telaUsuario = new TelaUsuario(false);
					desktop.novaJanela(telaUsuario);
				} else
					telaUsuario.toFront();
			}
		}
	}

	public void desbloquearBotoes() {
		desktop.getMenuArquivo().setEnabled(true);
		desktop.getMenuEditar().setEnabled(true);

		dispose();
	}

	public void desbloquearBotoesAdmin() {
		desktop.getMenuSeguran�a().setEnabled(true);

	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posi��o do frame
		setBounds((screenSize.width - 300) / 2, (screenSize.height - 250) / 2,
				300, 150);
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}