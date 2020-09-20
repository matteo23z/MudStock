package com.mudstock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import java.awt.Panel;

public class login {

	private JFrame frame;
	private JTextField txtSenha;
	private JTextField txtUser;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtSenha = new JTextField();
		txtSenha.setToolTipText("");
		txtSenha.setBounds(72, 153, 163, 20);
		frame.getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setToolTipText("User");
		txtUser.setBounds(72, 111, 163, 20);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(110, 200, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cadastro");
		btnNewButton_1.setBounds(293, 200, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}
