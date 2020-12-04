package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.controlFuncionario;

import util.DB;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtUser;
	private JTextField txtEmail;
	private JPasswordField txtSenha1;
	private JTextField txtData;
	private JPasswordField txtSenha2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cadastro frame = new cadastro();
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
	public cadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton bttCadastrar = new JButton("Cadastrar");
		bttCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlFuncionario Cfunc = new controlFuncionario();
				
				String Nome = txtNome.getText(), User = txtUser.getText(),Email = txtEmail.getText(),
							Data = txtData.getText();
				String Senha1 = new String(txtSenha1.getPassword()); 
				String Senha2 = new String(txtSenha2.getPassword()); 
					
				if(!Nome.isEmpty() && !User.isEmpty() && !Email.isEmpty() && !Data.isEmpty() && !Senha1.isEmpty() && !Senha2.isEmpty()) {
					if(Cfunc.checkEmail(Email)){
						if(Senha1.equals(Senha2)) {
							try {
								if(Cfunc.checkCadastro(User,Email)) {
									Cfunc.Inserir(Nome,User,Email, Senha1, Data);
									cadastro.this.dispose();
									login l = new login();
									l.setVisible(true);
								}else {
									JOptionPane.showMessageDialog(null,"Usuario ou email já existe");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null,"Senhas não estão iguais!");
						}	
					}else {
						JOptionPane.showMessageDialog(null,"Insira um Email Valido");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Insira os dados em todos os campos!");
				}
			}
		});
		bttCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bttCadastrar.setBounds(152, 454, 175, 34);
		panel.add(bttCadastrar);
		
		txtNome = new JTextField();
		txtNome.setBounds(104, 109, 278, 28);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCadastro = new JLabel("Criar Cadastro");
		lblCadastro.setForeground(Color.BLACK);
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblCadastro.setBounds(104, 11, 278, 44);
		panel.add(lblCadastro);
		
		JLabel lblNome = new JLabel("Nome Completo");
		lblNome.setBounds(104, 91, 132, 14);
		panel.add(lblNome);
		
		JLabel lblNomeUser = new JLabel("Nome User");
		lblNomeUser.setBounds(104, 202, 89, 14);
		panel.add(lblNomeUser);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(104, 221, 278, 28);
		panel.add(txtUser);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(104, 260, 89, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(104, 279, 278, 28);
		panel.add(txtEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(104, 318, 89, 14);
		panel.add(lblSenha);
		
		JLabel lblRepitaSenha = new JLabel("Repita Senha");
		lblRepitaSenha.setBounds(104, 377, 89, 14);
		panel.add(lblRepitaSenha);
		
		txtSenha1 = new JPasswordField();
		txtSenha1.setColumns(10);
		txtSenha1.setBounds(104, 338, 278, 28);
		panel.add(txtSenha1);
		
		JLabel lblDataDeNacimento = new JLabel("Data de Nacimento");
		lblDataDeNacimento.setBounds(104, 144, 119, 14);
		panel.add(lblDataDeNacimento);
		
		txtData = new JTextField();
		txtData.setColumns(10);
		txtData.setBounds(104, 163, 278, 28);
		panel.add(txtData);
		
		txtSenha2 = new JPasswordField();
		txtSenha2.setColumns(10);
		txtSenha2.setBounds(104, 395, 278, 28);
		panel.add(txtSenha2);
		
		JCheckBox cbxMostrar = new JCheckBox("");
		cbxMostrar.setBounds(388, 341, 21, 23);
		panel.add(cbxMostrar);
		
		JButton bttVoltar = new JButton("Voltar");
		bttVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastro.this.dispose();
				login l = new login();
				l.setVisible(true);
			}
		});
		bttVoltar.setBounds(10, 11, 71, 23);
		panel.add(bttVoltar);
		
		cbxMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSenha1.getEchoChar() == '*'){
					txtSenha1.setEchoChar((char) 0);
				if(txtSenha2.getEchoChar() == '*'){
					txtSenha2.setEchoChar((char) 0);
				}
				}else{
					txtSenha1.setEchoChar('*');
					txtSenha2.setEchoChar('*');
				}
			}
		});
	}
}
