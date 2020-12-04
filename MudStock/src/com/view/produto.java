package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.control.controlFuncionario;
import com.control.controlProdutos;
import com.mudstock.Produtos;
import com.mudstock.TableModel;

import util.DB;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class produto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtMarca;
	private JTextField txtDescricao;
	private JTextField txtObs;
	private JTable tableProduto;
	private JButton bttVoltar;
	private JButton bttCriar;
	private JButton bttEditar;
	private JButton bttExcluir;
	private JButton bttBuscar;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					produto frame = new produto();
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
	public produto() {
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
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblProdutos.setBounds(210, 11, 199, 36);
		panel.add(lblProdutos);
		
		txtNome = new JTextField();
		txtNome.setBounds(102, 74, 179, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JComboBox combxTpProdut = new JComboBox();
		combxTpProdut.setBounds(102, 125, 179, 22);
		combxTpProdut.addItem("");
		DB c = new DB();
		try {    
			String sql = "Select nome from tipoproduto";
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				combxTpProdut.addItem(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		panel.add(combxTpProdut);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(300, 126, 179, 20);
		panel.add(txtMarca);
		txtMarca.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(102, 175, 179, 38);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		txtObs = new JTextField();
		txtObs.setBounds(300, 176, 179, 37);
		panel.add(txtObs);
		txtObs.setColumns(10);
		
		JComboBox<String> combxSituacao = new JComboBox();
		combxSituacao.setBounds(300, 73, 179, 22);
		combxSituacao.addItem("");
		combxSituacao.addItem("Ativo");
		combxSituacao.addItem("Inativo");
		panel.add(combxSituacao);
		
		bttVoltar = new JButton("Voltar");
		bttVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produto.this.dispose();
			}
		});
		bttVoltar.setBounds(10, 26, 89, 23);
		panel.add(bttVoltar);
		
		bttCriar = new JButton("Criar");
		bttCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlProdutos Cprodt = new controlProdutos();
				
				String Nome = txtNome.getText(), Tipo = (String) combxTpProdut.getSelectedItem(), 
					 Marca = txtMarca.getText(), Situacao = (String) combxSituacao.getSelectedItem(),
					 Descr = txtDescricao.getText(), Obs = txtObs.getText();
				
				if(!Nome.isEmpty() && !Tipo.isEmpty() && !Descr.isEmpty()) {
					int idTipo = 0;
					DB c = new DB();
					try {    
						String sql = "Select id from tipoproduto where nome='"+Tipo+"'";
						ResultSet rs = c.Select(sql);
						while(rs.next()) {
							idTipo = rs.getInt("id");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						c.close();
					}
					Cprodt.Inserir(Nome,idTipo,Descr,Obs,Marca,Situacao);
					inserirTable("Select * from Produto");
				}else {
					JOptionPane.showMessageDialog(null,"Insira os dados nos campos obrigatorios");
				}
			}
		});
		bttCriar.setBounds(91, 224, 89, 23);
		panel.add(bttCriar);
		
		bttEditar = new JButton("Editar");
		bttEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProdutos Cprodt = new controlProdutos();
				if(tableProduto.getSelectedRow() != -1) {
					int linhaSel = tableProduto.getSelectedRow(); 
					int colunaSel = 0;
					int id = (int) tableProduto.getValueAt(linhaSel,colunaSel);  
					DB c = new DB();
					String sql = "Select * from Produto where id="+id;
					ResultSet rs = c.Select(sql);
					try {
						while(rs.next()) {
							String 
								nome = txtNome.getText(), 
								tipo = (String) combxTpProdut.getSelectedItem(), 
								marca = txtMarca.getText(),
								situacao = (String) combxSituacao.getSelectedItem(),
								descr = txtDescricao.getText(), 
								obs = txtObs.getText();
							
							String
								nome2 = rs.getString("nome"), 
								tipo2 = Cprodt.buscarNomeTipo(rs.getInt("idTipoProduto")), 
								marca2 = rs.getString("marca"),
								situacao2 = rs.getString("Situacao"),
								descr2 = rs.getString("descricao"), 
								obs2 = rs.getString("observacao");
								
							int idTipo = Cprodt.buscarIdTipo(tipo);
							System.out.println(idTipo);
							
							if(!nome.equals(nome2) || !tipo.equals(tipo2) || !marca.equals(marca2) || !
									situacao.equals(situacao2) || !descr.equals(descr2) || !obs.equals(obs2)) {
								if(!nome.equals(nome2)) {
									sql = "Update Produto set nome='"+nome+"' where id="+id;
									Cprodt.Alterar(sql);
								}
								if(!tipo.equals(tipo2)) {
									sql = "Update Produto set idTipoProduto="+idTipo+" where id="+id;
									Cprodt.Alterar(sql);
								}
								if(!marca.equals(marca2)) {
									sql = "Update Produto set marca='"+marca+"' where id="+id;
									Cprodt.Alterar(sql);
								}
								if(!situacao.equals(situacao2)) {
									sql = "Update Produto set situacao='"+situacao+"' where id="+id;
									Cprodt.Alterar(sql);
								}
								if(!descr.equals(descr2)) {
									sql = "Update Produto set descricao='"+descr+"' where id="+id;
									Cprodt.Alterar(sql);
								}
								if(!obs.equals(obs2)) {
									sql = "Update Produto set observacao='"+obs+"' where id="+id;
									Cprodt.Alterar(sql);
								}
								JOptionPane.showMessageDialog(null,"Alteradodo com sucesso!");
							}
						}
					} catch (Exception se) {
						JOptionPane.showMessageDialog(null,"Erro ao Preencher os campos - "+se);
					}finally {
						c.close();
						txtNome.setText(String.valueOf(""));
						txtMarca.setText(String.valueOf(""));
						txtDescricao.setText(String.valueOf(""));
						txtObs.setText(String.valueOf(""));
						combxSituacao.setSelectedItem("");
						combxTpProdut.setSelectedItem("");
						inserirTable("Select * from Produto");
					}
					
				}
			}
		});
		bttEditar.setBounds(190, 224, 89, 23);
		panel.add(bttEditar);
		
		bttExcluir = new JButton("Excluir");
		bttExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableProduto.getSelectedRow() != -1) {
					controlProdutos Cprodt = new controlProdutos();
					int linhaSel = tableProduto.getSelectedRow(); 
					int coluna = tableProduto.getSelectedColumn();
					int colunaSel = 0;
					int id = (int) tableProduto.getValueAt(linhaSel,colunaSel);  
					Cprodt.Excluir(id);
				}
			}
		});
		bttExcluir.setBounds(300, 224, 89, 23);
		panel.add(bttExcluir);
		
		bttBuscar = new JButton("Buscar");
		bttBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProdutos Cprodt = new controlProdutos();
				
				String Nome = txtNome.getText(), Tipo = (String) combxTpProdut.getSelectedItem(), 
					Marca = txtMarca.getText(), Situacao = (String) combxSituacao.getSelectedItem(),
					Descr = txtDescricao.getText(), Obs = txtObs.getText();
				
				String sql = Cprodt.checkBusca(Nome,Tipo,Marca, Situacao ,Descr, Obs);
				
				if(sql!=null) {
					inserirTable(sql);
				}else {
					inserirTable("Select * from Produto");
				}
			}
		});
		bttBuscar.setBounds(399, 224, 89, 23);
		panel.add(bttBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 272, 540, 143);
		panel.add(scrollPane);
		
		tableProduto = new JTable();
		scrollPane.setViewportView(tableProduto);
		tableProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					controlProdutos Cprodt = new controlProdutos();
					int id = (int) tableProduto.getValueAt(tableProduto.getSelectedRow(),0);  
					DB c = new DB();
					String sql = "Select * from Produto p join TipoProduto tp on p.idTipoProduto = tp.id where p.id="+id;
					ResultSet rs = c.Select(sql);
					try {
						while(rs.next()) {
							txtNome.setText(String.valueOf(rs.getString("nome")));
							txtMarca.setText(String.valueOf(rs.getString("marca")));
							txtDescricao.setText(String.valueOf(rs.getString("descricao")));
							txtObs.setText(String.valueOf(rs.getString("observacao")));
							combxSituacao.setSelectedItem(rs.getString("situacao"));
							combxTpProdut.setSelectedItem(Cprodt.buscarNomeTipo(rs.getInt("idTipoProduto")));
						}
					} catch (Exception se) {
						JOptionPane.showMessageDialog(null,"Erro ao Preencher os campos - "+se);
					}finally {
						c.close();
					}
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(102, 58, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(300, 58, 78, 14);
		panel.add(lblSituao);
		
		JLabel lblTipoProduto = new JLabel("Tipo Produto");
		lblTipoProduto.setBounds(102, 106, 131, 14);
		panel.add(lblTipoProduto);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(300, 106, 131, 14);
		panel.add(lblMarca);
		
		JLabel lblNewLabel_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_1.setBounds(102, 158, 131, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_1_1.setBounds(300, 158, 109, 14);
		panel.add(lblNewLabel_1_1);
		inserirTable("Select * from Produto");
		
	}
	
	public void  inserirTable(String sql){
		controlProdutos Cprodt = new controlProdutos();
		ArrayList<Object> dados = new ArrayList<Object>();
		String [] colunas = new String[] {"Id","Nome","Tipo","Descrição","Observação","Marca","Situação"};
		DB c = new DB();
		//String sql = "Select * from Produto";
		ResultSet rs = c.Select(sql);
		
		try {
			while(rs.next()) {
				String nomeTipo = Cprodt.buscarNomeTipo(rs.getInt("idTipoProduto"));
				dados.add(new Object[] {rs.getInt("id"),rs.getString("nome"),nomeTipo,rs.getString("descricao"),rs.getString("observacao"),rs.getString("marca"),rs.getString("situacao")});	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao Preencher a Table - "+e);
		}
		TableModel tableMod = new TableModel(dados,colunas);
		tableProduto.setModel(tableMod);
		tableProduto.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableProduto.getColumnModel().getColumn(0).setResizable(false);
		tableProduto.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableProduto.getColumnModel().getColumn(1).setResizable(false);
		tableProduto.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableProduto.getColumnModel().getColumn(2).setResizable(false);
		tableProduto.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableProduto.getColumnModel().getColumn(3).setResizable(false);
		tableProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableProduto.getColumnModel().getColumn(4).setResizable(false);
		tableProduto.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableProduto.getColumnModel().getColumn(5).setResizable(false);
		tableProduto.getTableHeader().setReorderingAllowed(false);
		tableProduto.setAutoResizeMode(tableProduto.AUTO_RESIZE_OFF);
		tableProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		c.close();
	}
}
