package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.controlFuncionario;

import util.DB;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(login.class.getResource("/com/view/imgs/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		panel.setBounds(10, 11, 432, 265);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(35, 117, 161, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JButton bttEnter = new JButton("Entrar");
		bttEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField pass = txtPassword;
				controlFuncionario Cfunc = new controlFuncionario();
				if(Cfunc.checkLogin(txtUser.getText(), pass.getText())){
					JOptionPane.showMessageDialog(null, "Logado com sucesso");
					login.this.dispose();
					inicial ini = new inicial();
					ini.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Usurario ou senha não existe");
				}
			}
		});
		bttEnter.setBounds(55, 211, 122, 23);
		panel.add(bttEnter);
		
		JButton bttCadastro = new JButton("Criar Cadastro");
		bttCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.this.setVisible(false);
				cadastro cad = new cadastro();
				cad.setVisible(true);
			}
		});
		bttCadastro.setBounds(253, 211, 122, 23);
		panel.add(bttCadastro);
		
		JLabel lblUser = new JLabel("Usu\u00E1rio");
		lblUser.setBounds(34, 92, 46, 14);
		panel.add(lblUser);
		
		JLabel lblPass = new JLabel("Senha");
		lblPass.setBounds(35, 148, 46, 14);
		panel.add(lblPass);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblLogin.setBounds(54, 32, 138, 44);
		panel.add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("");
		//ImageIcon image = new ImageIcon("/com/view/imgs/Mudstock.png");
		ImageIcon icon = new ImageIcon(getClass().getResource("/com/view/imgs/logo.png"));
		lblNewLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH)));
		//image.getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(), Image.SCALE_DEFAULT);
		lblNewLabel.setBounds(222, 0, 226, 217);
		panel.add(lblNewLabel);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(35, 173, 161, 20);
		panel.add(txtPassword);
		
		JCheckBox cbxMostrar = new JCheckBox("");
		cbxMostrar.setBounds(202, 172, 21, 23);
		panel.add(cbxMostrar);
		
		cbxMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPassword.getEchoChar() == '*'){
					txtPassword.setEchoChar((char) 0);
				}else{
					txtPassword.setEchoChar('*');
				}
			}
		});
	}
}
