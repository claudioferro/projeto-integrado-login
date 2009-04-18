package com.pucpr.br.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameEvent;

import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;

public class Principal extends JFrame {
	
	private static final long serialVersionUID = -108634806451927541L;
		private JDesktopPane desktop;
	
	private JToolBar toolBarConversas;
			
	@SuppressWarnings("serial")
	public Principal(){
		super("BSIN Messenger");
		
		// Recupera o tamanho da tela
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		// Define o tamanho e posição do frame
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 200) / 2,
				400, 200);
									
		desktop = new JDesktopPane();
		
		getContentPane().add(desktop);
				
		criarMenus();
		
		//ImageIcon img = new ImageIcon("resources/bsin.png");
		//setIconImage(img.getImage());
		setMinimumSize(new Dimension(400, 630));
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
	private void criarMenus() {

		JMenuBar menuBar = new JMenuBar();		

		JMenu menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);

		menuArquivo.setMnemonic(KeyEvent.VK_A);
		JMenuItem itemSalvar = new JMenuItem("Salvar");
		itemSalvar.setMnemonic(KeyEvent.VK_V);
		itemSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {				
				JOptionPane.showMessageDialog(null, "Contatos salvos com sucesso.", "Salvar", JOptionPane.INFORMATION_MESSAGE);
			}

		});
		menuArquivo.add(itemSalvar);
		
		JMenuItem itemSair = new JMenuItem("Sair");
		itemSair.setMnemonic(KeyEvent.VK_S);
		itemSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {				
				System.exit(0);
			}

		});

		menuArquivo.add(itemSair);
		
		// Adiciona menu de operacoes
		JMenu menuContato = new JMenu("Contatos");
		menuArquivo.setMnemonic(KeyEvent.VK_C);
		
		JMenu menuAmigos = new JMenu("Lista de Amigos");
		
		JMenuItem itemAdicionar = new JMenuItem("Adicionar Contato");
		itemAdicionar.setMnemonic(KeyEvent.VK_D);
		itemAdicionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				adicionarContato();
			}

		});
		menuAmigos.add(itemAdicionar);
		
		JMenuItem itemExcluir = new JMenuItem("Excluir Contato");
		itemExcluir.setMnemonic(KeyEvent.VK_E);
		itemExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
			}

		});
		menuAmigos.add(itemExcluir);
		
		JMenuItem itemMoverNegra = new JMenuItem("Mover para Lista Negra");
		itemMoverNegra.setMnemonic(KeyEvent.VK_M);
		itemMoverNegra.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
			}

		});
		menuAmigos.add(itemMoverNegra);
		menuContato.add(menuAmigos);
		
		JMenu menuNegra = new JMenu("Lista Negra");
		
		JMenuItem itemExcluirNegra = new JMenuItem("Excluir Contato");
		itemExcluirNegra.setMnemonic(KeyEvent.VK_E);
		itemExcluirNegra.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
			}

		});
		menuNegra.add(itemExcluirNegra);
		
		JMenuItem itemMoverAmigo = new JMenuItem("Mover para Lista de Amigos");
		itemMoverAmigo.setMnemonic(KeyEvent.VK_M);
		itemMoverAmigo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
			}

		});
		menuNegra.add(itemMoverAmigo);
		menuContato.add(menuNegra);	
		menuBar.add(menuContato);

		// Adiciona menu de operacoes
		JMenu menuJanela = new JMenu("Janela");
		JMenuItem itemListaAmigos = new JMenuItem("Lista de Amigos");
		//adicionarAcaoMenuJanela(itemListaAmigos, listaAmigos);

		menuJanela.add(itemListaAmigos);
		JMenuItem itemListaNegra = new JMenuItem("Lista Negra");
		menuJanela.add(itemListaNegra);
		//adicionarAcaoMenuJanela(itemListaNegra,	listaNegra);

		menuJanela.addSeparator();

		JMenuItem itemIconificar = new JMenuItem("Minimizar janelas");
		/*itemIconificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				for (Conversa conversa : telaConversas) {
					try {
						conversa.setIcon(true);
					} catch (PropertyVetoException e) {
						e.printStackTrace();
					}					
				}
				try {
					listaAmigos.setIcon(true);
					listaNegra.setIcon(true);					
				} catch (PropertyVetoException e) {
					throw new RuntimeException(e);
				}
			}
		});*/
		menuJanela.add(itemIconificar);

		menuBar.add(menuJanela);
		
		JMenu menuAjuda = new JMenu("Ajuda");
		JMenuItem itemSobre = new JMenuItem("Sobre");
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JanelaSobre janela = new JanelaSobre();
				//novaJanela(janela);
			}
		});
		
		menuAjuda.add(itemSobre);
		menuBar.add(menuAjuda);

		setJMenuBar(menuBar);
		
		//ToolBar
		JToolBar toolBar = new JToolBar();
		JButton adicionar = new JButton(new ImageIcon("resources/adicionar.png"));
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				adicionarContato();			
			}
		});
		adicionar.setToolTipText("Adicionar Contato");
		toolBar.add(adicionar);
		
		JButton cores = new JButton(new ImageIcon("resources/cores.png"));
		cores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//AlterarCor cor = new AlterarCor(desktop, repositorio);
				//novaJanela(cor);
			}
		});
		cores.setToolTipText("Alterar Cor");
		toolBar.add(cores);
		
		toolBarConversas = new JToolBar("Conversas");
		toolBarConversas.setLayout(new GridLayout(25,1));
		
		toolBarConversas.setOrientation(JToolBar.VERTICAL);
		toolBarConversas.setVisible(false);
		getContentPane().add(toolBarConversas,BorderLayout.EAST);
		
		getContentPane().add(toolBar,BorderLayout.PAGE_START);

	}
	
	private void adicionarAcaoMenuJanela(JMenuItem item, JInternalFrame frame) {
		item.addActionListener(new AcaoMenuJanela(frame));
	}
		
	private class AcaoMenuJanela implements ActionListener {

		private JInternalFrame frame;

		public AcaoMenuJanela(JInternalFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent evt) {
			try {
				if(frame.isClosed()) {
					desktop.add(frame);
					frame.show();
				}

				if (frame.isIcon()) {
					frame.setIcon(false);
				}
			} catch (PropertyVetoException e) {
				throw new RuntimeException(e);
			}
			frame.toFront();
		}

	}
	
	public void novaJanela(JInternalFrame janela){		
		desktop.add(janela);		
		try {
			janela.setSelected(true);
		} catch (PropertyVetoException e) {
		}
		janela.toFront();
	}
	
	private void adicionarContato(){
		//AdicionaContato adicionar = new AdicionaContato(this, repositorio);
		//novaJanela(adicionar);
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
