package com.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.controlEstoque;
import com.control.controlProdutos;
import com.mudstock.Estoque;

import util.DB;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class editarEstoque extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtData;
	private JTextField txtQuant;
	private JComboBox combxProduto;
	private JTextArea txtObs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			editarEstoque dialog = new editarEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public editarEstoque() {
		setBounds(100, 100, 457, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(152, 251, 152));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel lblEditEstoque = new JLabel("Editar Estoque");
			lblEditEstoque.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblEditEstoque.setBounds(22, 11, 182, 42);
			contentPanel.add(lblEditEstoque);

			combxProduto = new JComboBox();
			combxProduto.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					controlProdutos CProdt = new controlProdutos();
					String	nome = (String) combxProduto.getSelectedItem();
					if(!nome.isEmpty()) {
						int idProduto = CProdt.buscarIdProduto(nome);
						inserirDados(idProduto);
					}
				}
			});
			combxProduto.setBounds(20, 92, 184, 22);
			combxProduto.addItem("");
			DB c = new DB();
			
			try {
				controlProdutos CProdt = new controlProdutos();
				String sql = "Select idProduto from Estoque";
				ResultSet rs = c.Select(sql);
				while(rs.next()) {
					combxProduto.addItem(CProdt.buscarNomeProduto(rs.getInt("idProduto")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				c.close();
			}
			contentPanel.add(combxProduto);

			JLabel lblProduto = new JLabel("Produto *");
			lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblProduto.setBounds(20, 71, 141, 29);
			contentPanel.add(lblProduto);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(21, 147, 182, 112);
			contentPanel.add(scrollPane);

			JTextArea txtObs = new JTextArea();
			scrollPane.setViewportView(txtObs);

			JLabel lblObs = new JLabel("Observa\u00E7\u00E3o");
			lblObs.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblObs.setBounds(20, 125, 141, 29);
			contentPanel.add(lblObs);

			JLabel lblData = new JLabel("Data *");
			lblData.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblData.setBounds(236, 71, 141, 29);
			contentPanel.add(lblData);

			txtData = new JTextField();
			txtData.setColumns(10);
			txtData.setBounds(236, 93, 169, 20);
			contentPanel.add(txtData);
	
			txtQuant = new JTextField();
			txtQuant.setColumns(10);
			txtQuant.setBounds(236, 148, 169, 20);
			contentPanel.add(txtQuant);
	
			JLabel lblQuant = new JLabel("Quantidade *");
			lblQuant.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblQuant.setBounds(236, 127, 141, 29);
			contentPanel.add(lblQuant);
	
		
			JLabel lblNewLabel = new JLabel("* Campos Obrigat\u00F3rios");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBounds(263, 31, 169, 22);
			contentPanel.add(lblNewLabel);
			
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Editar");
				okButton.addActionListener(new ActionListener() {
						Estoque Est = new Estoque();
						controlProdutos CProdt = new controlProdutos();
						controlEstoque Cest = new controlEstoque();
						public void actionPerformed(ActionEvent e) {
						String	nome = (String) combxProduto.getSelectedItem(), 
								data = txtData.getText(),
								quant = txtQuant.getText(),
								obs = txtObs.getText();
						
						int idProduto = CProdt.buscarIdProduto(nome);
						
						DB c = new DB();
						try {
							String sql = "Select * from estoque where idProduto='"+idProduto+"'";
							ResultSet rs = c.Select(sql);
							String	data2, quant2,obs2;
							int id, n = 0;
							if(rs!=null) {
								while(rs.next()) {
									id = rs.getInt("id");
									data2 = rs.getString("DataAtualizacao");
									quant2 = rs.getString("Quantidade");
									obs2 = rs.getString("Observacao");
								
									if(!nome.isEmpty() && !data.isEmpty() && !quant.isEmpty()) {
										if(!data.equals(data2)){
											System.out.println("Test");
											sql = "Update Estoque set dataatualizacao='"+obs+"' where id="+id;
											Cest.Alterar(sql);
											n = n + 1;
										}
										if(!quant.equals(quant2)) {
											System.out.println("Test");
											sql = "Update Estoque set quantidade="+quant+" where id="+id;
											Cest.Alterar(sql);
											n = n + 1;
										}
										if(!obs.equals(obs2)) {
											System.out.println("Test");
											sql = "Update Estoque set observacao='"+obs+"' where id="+id;
											Cest.Alterar(sql);
											n = n + 1;
										}
										if(n>0) {
											JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso");
											editarEstoque.this.dispose();
										}
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void inserirDados(int idProduto) {
		DB c = new DB();
		try {
			String sql = "Select * from estoque where idProduto='"+idProduto+"'";
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
					txtData.setText(String.valueOf(rs.getString("DataAtualizacao")));
					txtQuant.setText(String.valueOf(rs.getString("Quantidade")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			c.close();
		}	
		
	}
	
	public JTextArea getTextArea() {
		return txtObs;
	}

}
