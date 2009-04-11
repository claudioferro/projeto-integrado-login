package com.pucpr.br.frontend.utils;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridBagLayoutUtils {
	
	/**
	 * Adiciona um label, um componente de edição, mais um label e outro
	 * componente de edição. Todos na mesma linha
	 * 
	 * @param label
	 *            Label 1
	 * @param componente
	 *            Componente de edição
	 * @param label2
	 *            Label 2
	 * @param componente2
	 *            Componente de edição 2
	 */
	public static void add(JPanel panel, String label, JComponent componente, String label2,
			JComponent componente2) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(4, 4, 4, 4);

		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.NORTHWEST;
		cons.weightx = 0;
		cons.gridwidth = 1;
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.BLACK);
		panel.add(jlabel, cons);

		cons.weightx = 1;
		cons.gridwidth = 1;
		cons.fill = GridBagConstraints.BOTH;
		panel.add(componente, cons);

		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		cons.gridwidth = 1;
		JLabel jlabel2 = new JLabel(label);
		jlabel2.setForeground(Color.BLACK);
		panel.add(jlabel2, cons);

		cons.weightx = 1;
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(componente2, cons);
	}
	
	/**
	 * Adiciona um label e um componente horizontalmente
	 * 
	 * @param label
	 *            String que irá aparecer no label
	 * @param componente
	 *            Componente de edição
	 */
	public static void add(JPanel panel, String label, JComponent componente) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.NORTHWEST;
		cons.insets = new Insets(4, 4, 4, 4);

		cons.weightx = 0;
		cons.gridwidth = 1;
		
		JLabel jlabel = new JLabel(label);
		jlabel.setForeground(Color.BLACK);
		panel.add(jlabel, cons);
		

		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 1;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(componente, cons);
	}


}
