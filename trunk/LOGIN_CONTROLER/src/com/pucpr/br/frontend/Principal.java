package com.pucpr.br.frontend;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

/**
 * @author Rony Sartor
 * 
 * @version 1.0
 * 
 *          Classe principal que contem a JFrame principal do programa
 *          
 */
public class Principal extends JFrame {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -108634806451927541L;
	
	//variaveis da tela
	private JDesktopPane desktop;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenu menuSegurança;
	private JMenu menuAjuda;
	
	private Principal principal;
	private TelaManterPapeis manterPapeis;
	private TelaMonitorarUsuarios monitorarUsuarios;
	
	private Map<String, Object> data;
	
	public Principal(){
		super(":: PROJETO INTEGRADO ::");
		
		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2,
				400, 200);
									
		desktop = new JDesktopPane();
		
		getContentPane().add(desktop);
		
		this.principal = this;
		
		inicializarComponentes();
		
		TelaLogin telaLogin = new TelaLogin(principal);
		novaJanela(telaLogin);
		
		//ImageIcon img = new ImageIcon("resources/bsin.png");
		//setIconImage(img.getImage());
		setMinimumSize(new Dimension(600, 500));
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
	/**
	 * Método para instanciar os componentes da tela
	 */
	private void inicializarComponentes() {

		//adiciona os menus
		JMenuBar menuBar = new JMenuBar();		

		// Adiciona menu Arquivo
		menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);

		menuArquivo.setMnemonic(KeyEvent.VK_A);
				
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.setMnemonic(KeyEvent.VK_R);
		itemSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {				
				System.exit(0);
			}

		});
		menuArquivo.setEnabled(false);
		menuArquivo.add(itemSair);

		// Adiciona menu Editar
		menuEditar = new JMenu("Editar");
		menuEditar.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem itemEditarUsuario = new JMenuItem("Editar Usuário");
		itemEditarUsuario.setMnemonic(KeyEvent.VK_U);
		itemEditarUsuario.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {				
				TelaUsuario telaUsuario = new TelaUsuario(true);
				novaJanela(telaUsuario);
			}

		});
		menuEditar.setEnabled(false);
		menuEditar.add(itemEditarUsuario);
		
		// Adiciona menu Segurança
		menuSegurança = new JMenu("Segurança");
		menuSegurança.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem itemMonitorarUsuarios = new JMenuItem("Monitorar Usuários");
		itemMonitorarUsuarios.setMnemonic(KeyEvent.VK_M);
		itemMonitorarUsuarios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (monitorarUsuarios == null || monitorarUsuarios.isClosed()){
					monitorarUsuarios = new TelaMonitorarUsuarios(principal);
					novaJanela(monitorarUsuarios);
				} else {					
					monitorarUsuarios.toFront();
					try {
						monitorarUsuarios.setIcon(false);
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}
				}
			}

		});
		menuSegurança.setEnabled(false);
		menuSegurança.add(itemMonitorarUsuarios);
		
		JMenuItem itemManterPapeis = new JMenuItem("Manter Papéis");
		itemManterPapeis.setMnemonic(KeyEvent.VK_P);
		itemManterPapeis.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (manterPapeis == null || manterPapeis.isClosed()){
					manterPapeis = new TelaManterPapeis();
					novaJanela(manterPapeis);
				} else
					manterPapeis.toFront();
			}

		});
		menuSegurança.add(itemManterPapeis);
		
		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem itemSobre = new JMenuItem("Sobre");
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JanelaSobre janela = new JanelaSobre();
				//novaJanela(janela);
			}
		});
		
		menuAjuda.add(itemSobre);

		//adicionar os menus na barra de menu
		menuBar.add(menuArquivo);
		menuBar.add(menuEditar);
		menuBar.add(menuSegurança);
		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
		
		

	}
	
	/**
	 * Método que adiciona no JInternalFrame na JFrame principal, 
	 * 
	 * @param JInternalFrame janela
	 */
	public void novaJanela(JInternalFrame janela){		
		desktop.add(janela);		
		try {
			janela.setSelected(true);
		} catch (PropertyVetoException e) {
		}
		janela.toFront();
	}
	
	/**
	 * Main para execução da frame principal
	 * 
	 * @param args
	 */	
	public static void main(String[] args) {

		setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
					.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());

		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}

		Principal principal = new Principal();
		principal.setVisible(true);
		principal.repaint();

	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public JMenu getMenuArquivo() {
		return menuArquivo;
	}

	public void setMenuArquivo(JMenu menuArquivo) {
		this.menuArquivo = menuArquivo;
	}

	public JMenu getMenuEditar() {
		return menuEditar;
	}

	public void setMenuEditar(JMenu menuEditar) {
		this.menuEditar = menuEditar;
	}

	public JMenu getMenuSegurança() {
		return menuSegurança;
	}

	public void setMenuSegurança(JMenu menuSegurança) {
		this.menuSegurança = menuSegurança;
	}	
}
