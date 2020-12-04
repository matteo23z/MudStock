package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.control.controlProdutos;
import com.mudstock.TableModel;

import util.DB;

public class tipoProduto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tableProduto;
	private JButton bttVoltar;
	private JButton bttCriar;
	private JButton bttEditar;
	private JButton bttExcluir;
	private JButton bttBuscar;
	private JScrollPane scrollPane;
	private JTable tableTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tipoProduto frame = new tipoProduto();
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
	public tipoProduto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblProdutos = new JLabel("Tipo Produtos");
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblProdutos.setBounds(210, 48, 199, 36);
		panel.add(lblProdutos);
		
		txtNome = new JTextField();
		txtNome.setBounds(99, 131, 179, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JComboBox<String> combxSituacao = new JComboBox();
		combxSituacao.setBounds(300, 130, 179, 22);
		combxSituacao.addItem("");
		combxSituacao.addItem("Ativo");
		combxSituacao.addItem("Inativo");
		panel.add(combxSituacao);
		
		bttVoltar = new JButton("Voltar");
		bttVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoProduto.this.dispose();
			}
		});
		bttVoltar.setBounds(10, 26, 89, 23);
		panel.add(bttVoltar);
		
		bttCriar = new JButton("Criar");
		bttCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlProdutos Cprodt = new controlProdutos();
				
				String Nome = txtNome.getText(), Situacao = (String) combxSituacao.getSelectedItem();
				
				if(!Nome.isEmpty()  && !Situacao.isEmpty()) {
					Cprodt.InserirTipo(Nome,Situacao);
					inserirTable("Select * from TipoProduto");
				}else {
					JOptionPane.showMessageDialog(null,"Insira os dados em todos os campos!");
				}
			}
		});
		bttCriar.setBounds(91, 183, 89, 23);
		panel.add(bttCriar);
		
		bttEditar = new JButton("Editar");
		bttEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProdutos Cprodt = new controlProdutos();
				if(tableTipo.getSelectedRow() != -1) {
					int linhaSel = tableTipo.getSelectedRow(); 
					int colunaSel = 0;
					int id = (int) tableTipo.getValueAt(linhaSel,colunaSel);  
					DB c = new DB();
					String sql = "Select * from TipoProduto where id="+id;
					ResultSet rs = c.Select(sql);
					try {
						while(rs.next()) {
							String nome = rs.getString("nome"), situacao = rs.getString("situacao");
							String nome2 = txtNome.getText(), situacao2 = (String) combxSituacao.getSelectedItem();
							if(!nome2.equals(nome) || !situacao2.equals(situacao)) {
								if(!nome2.equals(nome)) {
									sql = "Update TipoProduto set nome='"+nome2+"' where id="+id;
									Cprodt.AlterarTipo(sql);
								}
								if(!situacao2.equals(situacao)) {
									sql = "Update TipoProduto set situacao='"+situacao2+"' where id="+id;
									Cprodt.AlterarTipo(sql);
								}
							}
							if(!nome2.equals(nome) && !situacao2.equals(situacao)) {
								sql = "Update TipoProduto set nome='"+nome2+"', situacao='"+situacao2+"' where id="+id;
								Cprodt.AlterarTipo(sql);
							}
						}
					} catch (Exception se) {
						JOptionPane.showMessageDialog(null,"Erro ao Preencher os campos - "+se);
					}finally {
						c.close();
						inserirTable("Select * from TipoProduto");
					}
				}
			}
		});
		bttEditar.setBounds(190, 183, 89, 23);
		panel.add(bttEditar);
		
		bttExcluir = new JButton("Excluir");
		bttExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableTipo.getSelectedRow() != -1) {
					controlProdutos Cprodt = new controlProdutos();
					int linhaSel = tableTipo.getSelectedRow(); 
					int coluna = tableTipo.getSelectedColumn();
					int colunaSel = 0;
					int id = (int)tableTipo.getValueAt(linhaSel,colunaSel);  
					Cprodt.ExcluirTipo(id);
					inserirTable("Select * from TipoProduto");
				}
			}
		});
		bttExcluir.setBounds(300, 183, 89, 23);
		panel.add(bttExcluir);
		
		bttBuscar = new JButton("Buscar");
		bttBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProdutos Cprodt = new controlProdutos();
				
				String Nome = txtNome.getText(),Situacao = (String) combxSituacao.getSelectedItem();
					
				String sql = Cprodt.checkBuscaTipo(Nome,Situacao);
				
				if(sql!=null) {
					inserirTable(sql);
				}else {
					inserirTable("Select * from TipoProduto");
				}
			}
		});
		bttBuscar.setBounds(399, 183, 89, 23);
		panel.add(bttBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 227, 540, 188);
		panel.add(scrollPane);
		
		tableTipo = new JTable();
		scrollPane.setViewportView(tableTipo);
		tableTipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					int id = (int) tableTipo.getValueAt(tableTipo.getSelectedRow(),0);  
					DB c = new DB();
					String sql = "Select * from TipoProduto where id="+id;
					ResultSet rs = c.Select(sql);
					try {
						while(rs.next()) {
							txtNome.setText(String.valueOf(rs.getString("nome")));
							combxSituacao.setSelectedItem(rs.getString("situacao"));
						}
					} catch (Exception se) {
						JOptionPane.showMessageDialog(null,"Erro ao Preencher os campos - "+se);
					}finally {
						c.close();
					}
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(99, 115, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(300, 115, 78, 14);
		panel.add(lblSituao);
		inserirTable("Select * from TipoProduto");
		
	}
	
	public void  inserirTable(String sql){
		System.out.println("Entrou no readTable");
		ArrayList<Object> dados = new ArrayList<Object>();
		String [] colunas = new String[] {"Id","Nome","Situação"};
		DB c = new DB();
		//String sql = "Select * from Produto";
		ResultSet rs = c.Select(sql);
		try {
			while(rs.next()) {
				dados.add(new Object[] {rs.getInt("id"),rs.getString("nome"),rs.getString("situacao")});	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao Preencher a Table - "+e);
		}
		TableModel tableMod = new TableModel(dados,colunas);
		System.out.println("Saindo do TableModel");
		tableTipo.setModel(tableMod);
		tableTipo.getColumnModel().getColumn(0).setPreferredWidth(215);
		tableTipo.getColumnModel().getColumn(0).setResizable(false);
		tableTipo.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableTipo.getColumnModel().getColumn(1).setResizable(false);
		tableTipo.getTableHeader().setReorderingAllowed(false);
		tableTipo.setAutoResizeMode(tableTipo.AUTO_RESIZE_OFF);
		tableTipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		c.close();
	}

}
