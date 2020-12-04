package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.controlEstoque;
import com.control.controlProdutos;

import util.DB;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextArea;

public class inserirEstoque extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtData;
	private JTextField txtQuant;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			inserirEstoque dialog = new inserirEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public inserirEstoque() {
		setBounds(100, 100, 468, 323);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(152, 251, 152));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblInEstoque = new JLabel("Inserir Estoque");
		lblInEstoque.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInEstoque.setBounds(30, 11, 204, 29);
		contentPanel.add(lblInEstoque);
		
		JComboBox combxProduto = new JComboBox();
		combxProduto.setBounds(30, 72, 184, 22);
		combxProduto.addItem("");
		DB c = new DB();
		try {    
			String sql = "Select nome from Produto";
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				combxProduto.addItem(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		
		contentPanel.add(combxProduto);
		
		txtData = new JTextField();
		txtData.setBounds(246, 73, 169, 20);
		contentPanel.add(txtData);
		txtData.setColumns(10);
		
		txtQuant = new JTextField();
		txtQuant.setBounds(246, 128, 169, 20);
		contentPanel.add(txtQuant);
		txtQuant.setColumns(10);
		
		JLabel lblProduto = new JLabel("Produto *");
		lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProduto.setBounds(30, 51, 141, 29);
		contentPanel.add(lblProduto);
		
		JLabel lblData = new JLabel("Data *");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblData.setBounds(246, 51, 141, 29);
		contentPanel.add(lblData);
		
		JLabel lblQuant = new JLabel("Quantidade *");
		lblQuant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuant.setBounds(246, 107, 141, 29);
		contentPanel.add(lblQuant);
		
		JLabel lblObs = new JLabel("Observa\u00E7\u00E3o");
		lblObs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblObs.setBounds(30, 105, 141, 29);
		contentPanel.add(lblObs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 126, 184, 114);
		contentPanel.add(scrollPane);
		
		JTextArea txtObs = new JTextArea();
		scrollPane.setViewportView(txtObs);
		
		JLabel lblNewLabel = new JLabel("* Campos Obrigat\u00F3rios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(273, 11, 169, 22);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Inserir");
				okButton.addActionListener(new ActionListener() {
					controlProdutos CProdt = new controlProdutos();
					controlEstoque Cest = new controlEstoque();
					public void actionPerformed(ActionEvent e) {
					String	nome = (String) combxProduto.getSelectedItem(), 
							data = txtData.getText(),
							quant = txtQuant.getText(),
							obs = txtObs.getText();
					
					String	nome2 = null;
					
					int idProduto = CProdt.buscarIdProduto(nome);
					
					DB c = new DB();
					try {
						String sql = "Select * from estoque where idProduto='"+idProduto+"'";
						ResultSet rs = c.Select(sql);
						if(rs!=null) {
							while(rs.next()) {
								nome2 = CProdt.buscarNomeProduto(rs.getInt("idProduto"));	
							}
							if(nome.equals(nome2)) {
								JOptionPane.showMessageDialog(null,"Produto já está registrado no estoque!");
							}else {
								if(!nome.isEmpty() && !data.isEmpty() && !quant.isEmpty()) {
									Cest.Inserir(idProduto,data,quant,obs);
								}else {
									JOptionPane.showMessageDialog(null,"Insira em todos os campos obrigatórios!");
								}
							}
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}finally {
						c.close();
					}	
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						inserirEstoque.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
