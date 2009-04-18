package com.pucpr.br.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameEvent;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

public class Principal extends JFrame {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -108634806451927541L;
	
	private JDesktopPane desktop;
	private JMenu menuArquivo;
	private JMenu menuEditar;
	private JMenu menuSegurança;
	private JMenu menuAjuda;
	
	
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
		
		inicializarComponentes();
		
		TelaLogin telaLogin = new TelaLogin(this);
		novaJanela(telaLogin);
		
		//ImageIcon img = new ImageIcon("resources/bsin.png");
		//setIconImage(img.getImage());
		setMinimumSize(new Dimension(400, 630));
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
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

		menuArquivo.add(itemSair);

		// Adiciona menu Editar
		menuEditar = new JMenu("Editar");
		menuEditar.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem itemEditarUsuario = new JMenuItem("Editar Usuário");
		itemEditarUsuario.setMnemonic(KeyEvent.VK_U);
		itemEditarUsuario.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {				
				
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
				
			}

		});
		menuSegurança.setEnabled(false);
		menuSegurança.add(itemMonitorarUsuarios);
		
		JMenuItem itemManterPapeis = new JMenuItem("Manter Papéis");
		itemManterPapeis.setMnemonic(KeyEvent.VK_P);
		itemManterPapeis.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
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

		
		menuBar.add(menuArquivo);
		menuBar.add(menuEditar);
		menuBar.add(menuSegurança);
		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
		
		

	}
	
	public void novaJanela(JInternalFrame janela){		
		desktop.add(janela);		
		try {
			janela.setSelected(true);
		} catch (PropertyVetoException e) {
		}
		janela.toFront();
	}
		
	public void alterarCor(JFrame frame, Color cor){
		frame.setBackground(cor);
		frame.repaint();
	}
	
	public void fechaConversa(InternalFrameEvent internalFrame){
        /*for (int i = 0; i < telaConversas.size(); i++) {
			Conversa temp = telaConversas.get(i);
			if(internalFrame.getInternalFrame().hashCode() == temp.hashCode()){				
				for(int j=0; j < toolBarConversas.getComponents().length; j++){
					Component componentes[] = toolBarConversas.getComponents(); 
					JButton botao = (JButton) componentes[j];
					if (conversas.get(i).getCodigoJanela() == botao.hashCode()){
						toolBarConversas.remove(j);
					}
				}
				telaConversas.remove(i);
				conversas.remove(i);
				toolBarConversas.repaint();
				break;
			}						
		}
        if (telaConversas.size() == 0){
        	toolBarConversas.setVisible(false);
        }*/
	}
		
	/*public void novaConversa(Contato amigo){
		boolean estaAberta = false;
		Conversa janelaConversa = procurarJanela(amigo);
		if(janelaConversa != null){
			estaAberta = true;
		}
		
		if (estaAberta){
			janelaConversa.toFront();
		} else {			
			Conversa novaConversa = new Conversa(this, amigo, 20*conversas.size(), 22*conversas.size());		
			telaConversas.add(novaConversa);
			conversas.add(amigo);
			novaJanela(novaConversa);
			
			ThreadClient threadClient = new ThreadClient(amigo, "");
			threadClient.start();
			
			String nomeConversa = amigo.getNome(); 
			if (amigo.getNome().length() > 10){
				nomeConversa = amigo.getNome().substring(0, 8) + "...";
			}
			
			JButton botaoConversa = new JButton(nomeConversa, new ImageIcon("resources/conversa.png"));	
			amigo.setCodigoJanela(botaoConversa.hashCode());
			botaoConversa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					for (int i = 0; i < conversas.size(); i++) {
						Contato contato = conversas.get(i);											
						if(contato.getCodigoJanela() == evt.getSource().hashCode()){
							if (telaConversas.get(i).isIcon()) {
								try {
									telaConversas.get(i).setIcon(false);
									telaConversas.get(i).setSelected(true);
								} catch (PropertyVetoException e) {
									e.printStackTrace();
								}							
							}							
							telaConversas.get(i).toFront();
							try {
								telaConversas.get(i).setSelected(true);
							} catch (PropertyVetoException e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
			});
			botaoConversa.setToolTipText(amigo.getNome());			
			toolBarConversas.add(botaoConversa);		
		}	
		
		if(toolBarConversas.isVisible() == false){
			toolBarConversas.setVisible(true);
		}
	}*/	
	
	
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
}
