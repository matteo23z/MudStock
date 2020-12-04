package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

public class inicial extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicial frame = new inicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public inicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 508);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		panel.setBounds(0, 0, 838, 447);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton bttEstoque = new JButton("Estoque");
		bttEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inicial.this.dispose();
				estoque stock = new estoque();
				stock.setVisible(true);
			}
		});
		bttEstoque.setFont(new Font("Tahoma", Font.PLAIN, 36));
		bttEstoque.setBounds(48, 78, 309, 113);
		panel.add(bttEstoque);
		
		JButton bttProducao = new JButton("Produ\u00E7\u00E3o");
		bttProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial.this.dispose();
				producao prod = new producao();
				prod.setVisible(true);
			}
		});
		bttProducao.setFont(new Font("Tahoma", Font.PLAIN, 36));
		bttProducao.setBounds(48, 229, 309, 113);
		panel.add(bttProducao);
		
		JLabel lblPhoto = new JLabel("");
		lblPhoto.setBounds(463, 67, 349, 333);
		panel.add(lblPhoto);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/com/view/imgs/logo.png"));
		lblPhoto.setIcon(new ImageIcon(icon.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH)));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFuncionario = new JMenu("Funcionario");
		menuBar.add(mnFuncionario);
		
		JMenuItem mnfPerfil = new JMenuItem("Perfil");
		mnFuncionario.add(mnfPerfil);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial.this.dispose();
				login log = new login();
				log.setVisible(true);
			}
		});
		mnFuncionario.add(mntmSair);
	}
}
