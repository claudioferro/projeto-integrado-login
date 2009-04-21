package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.pucpr.br.frontend.utils.ConstantsFrontEnd;
import com.pucpr.br.frontend.utils.TableModelMonitorarUsuarios;

/**
 * @author Rony Sartor
 * 
 * @version 1.0
 * 
 *          Tela para Monitorar os Usuarios existentes na base de dados
 *          
 */
public class TelaMonitorarUsuarios extends JInternalFrame implements Observer{

	/** serialVersionUID */
	private static final long serialVersionUID = 2556954324956401021L;
	
	//variaveis da tela
	private JScrollPane painelTabelaUsuarios;
	
	private JTable tbMonitorarUsuarios;
	private TableModelMonitorarUsuarios modeloTabelaUsuarios;
		
	private JButton btnSuspender;
	private JButton btnEditarPapeis;
	private JButton btnEditar;
	
	private JPanel panelBotoes;
	
	private ListenerBotoes listenerBotoes = new ListenerBotoes();
	
	private Principal desktop;
	private TelaEditarPapeisUsuario editarPapeisUsuario;
	private TelaUsuario editarUsuario;
	
	public TelaMonitorarUsuarios(Principal desktop) {
		super(ConstantsFrontEnd.MONITORAR_USUARIOS_TITULO_TELA, true, true, false, true);
		
		//seta a tela "mae" da aplicação
		this.desktop = desktop;
						
		//inicia os componentes da janela
		inicializarComponentes();
		
		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = desktop.getSize();
		
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
		panelBotoes = new JPanel(new GridLayout(1, 3, 15, 15));

		// Adiciona componentes no painel de botoes
		panelBotoes.add(getBotaoSuspender());
		panelBotoes.add(getBotaoEditarPapeis());
		panelBotoes.add(getBotaoEditar());

		// adiciona os paineis ao frame
		this.add(panelBotoes, BorderLayout.SOUTH);
		this.add(getPainelTabelaUsuarios(), BorderLayout.CENTER);
	}
	
	/**
	 * Instancia o botão Suspender para bloquear o acesso de um usuário
	 * 
	 * @return JButton btnSuspender
	 */
	private JButton getBotaoSuspender() {
		btnSuspender = new JButton(ConstantsFrontEnd.MONITORAR_USUARIOS_SUSPENDER);
		btnSuspender.setActionCommand(ConstantsFrontEnd.MONITORAR_USUARIOS_SUSPENDER);
		btnSuspender.addActionListener(listenerBotoes);
		return btnSuspender;
	}
	
	/**
	 * Instancia o botão Editar Papéis para editar o papel de um usuário
	 * 
	 * @return JButton btnEditarPapeis
	 */
	private JButton getBotaoEditarPapeis() {
		btnEditarPapeis = new JButton(ConstantsFrontEnd.MONITORAR_USUARIOS_EDITAR_PAPEIS);
		btnEditarPapeis.setActionCommand(ConstantsFrontEnd.MONITORAR_USUARIOS_EDITAR_PAPEIS);
		btnEditarPapeis.addActionListener(listenerBotoes);
		return btnEditarPapeis;
	}
	
	/**
	 * Instancia o botão Editar para editar os dados de um usuário
	 * 
	 * @return JButton btnEditarPapeis
	 */
	private JButton getBotaoEditar() {
		btnEditar = new JButton(ConstantsFrontEnd.MONITORAR_USUARIOS_EDITAR);
		btnEditar.setActionCommand(ConstantsFrontEnd.MONITORAR_USUARIOS_EDITAR);
		btnEditar.addActionListener(listenerBotoes);
		return btnEditar;
	}
	
	/**
	 * Instancia o scroll que contem a tabela com os Usuários
	 * 
	 * @return JScrollPane painelTabelaUsuarios
	 */
	private JScrollPane getPainelTabelaUsuarios() {
		painelTabelaUsuarios = new JScrollPane(getTbMonitorarUsuarios());		
		return painelTabelaUsuarios;
	}
	
	/**
	 * Instancia a tabela MonitorarUsuarios que contem os usuarios cadastrados
	 * 
	 * @return JTable tbMonitorarUsuarios
	 */
	private JTable getTbMonitorarUsuarios() {
		tbMonitorarUsuarios = new JTable(getModeloTabelaUsuarios());		
		tbMonitorarUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		return tbMonitorarUsuarios;
	}
	
	/**
	 * Instancia o modelo de tabela que seja exibida na tela
	 * 
	 * @return TableModelMonitorarUsuarios modeloTabelaUsuarios
	 */
	private TableModelMonitorarUsuarios getModeloTabelaUsuarios() {
		modeloTabelaUsuarios = new TableModelMonitorarUsuarios();
		
		return modeloTabelaUsuarios;
	}
	
	/**
	 * Classe interna que implenta o ActionListener para as ações dos botões
	 *
	 */
	private class ListenerBotoes implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (tbMonitorarUsuarios.getSelectedRowCount() == 1){
				if (e.getActionCommand().equals(ConstantsFrontEnd.MONITORAR_USUARIOS_SUSPENDER)) {
					String msg = ConstantsFrontEnd.MONITORAR_USUARIOS_MSG_SUSPENDER.replaceAll("#", tbMonitorarUsuarios.getValueAt(tbMonitorarUsuarios.getSelectedRow(), 1).toString()); 
					int resposta = JOptionPane.showConfirmDialog(null, msg, ConstantsFrontEnd.MONITORAR_USUARIOS_TITULO_TELA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION)	{
						
						// TODO : Fazer a chamada para o command para suspender o usuario
																													
					}
					
				} else if (e.getActionCommand().equals(ConstantsFrontEnd.MONITORAR_USUARIOS_EDITAR_PAPEIS)) {
					
					if (editarPapeisUsuario == null || editarPapeisUsuario.isClosed()){
						editarPapeisUsuario = new TelaEditarPapeisUsuario();
						desktop.novaJanela(editarPapeisUsuario);
					} else
						editarPapeisUsuario.toFront();
									
				} else if (e.getActionCommand().equals(ConstantsFrontEnd.MONITORAR_USUARIOS_EDITAR)) {
					
					if (editarUsuario == null || editarUsuario.isClosed()){
						editarUsuario = new TelaUsuario(true);
						desktop.novaJanela(editarUsuario);
					} else
						editarUsuario.toFront();
					
				}
			} else 
				JOptionPane.showMessageDialog(null, ConstantsFrontEnd.MONITORAR_USUARIOS_MSG_SELECAO, ConstantsFrontEnd.MONITORAR_USUARIOS_TITULO_TELA, JOptionPane.WARNING_MESSAGE);
			
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// TODO : Fazer a chamada para o controler, carregar a lista de Usuarios
		// Exemplo abaixo...
		
		/*try {
			// Se quem chamou a tela foi a AcaoBolsaController entao continuar.
			if (o instanceof AcaoBolsaController) {
				// Realiza cast para objeto controller
				AcaoBolsaController controller = (AcaoBolsaController) o;

				// Recupera ações a partir do controller
				List<Acao> listaAcoesBolsa = controller.recuperarAcoes();
				
				// Atualiza modelo
				modeloTabelaAcoes.setListaAcoes(listaAcoesBolsa);
				
				// Provoca o model para dar um refresh na tela
				modeloTabelaAcoes.fireTableDataChanged();

			}
		} catch (ControllerException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter cotação. Continuando");
		}*/
		
	}

}
