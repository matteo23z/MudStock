package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.control.controlFuncionario;
import com.control.controlProducao;
import com.control.controlProdutos;
import com.mudstock.Funcionario;
import com.mudstock.Producao;
import com.mudstock.TableModel;

import util.DB;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class producao extends JFrame {

	private JPanel contentPane;
	private JTextField txtDatai;
	private JTextField txtDataf;
	private JTable tableProducao;
	private JTextField txtBuscar;
	private JTextField txtNome;
	private JTextArea txtObs;
	private JComboBox combxMuda;
	private JComboBox combxFuncionario;
	private JComboBox combxStatus;
	private JComboBox combxFiltro;
	private int idProducao;
	private JTextField txtQuant;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					producao frame = new producao();
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
	public producao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 671);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton bttBuscar = new JButton("Buscar");
		bttBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		bttBuscar.setBounds(671, 361, 156, 29);
		panel.add(bttBuscar);
		
		JButton bttCriar = new JButton("+ Criar");
		bttCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProducao Cp = new controlProducao();
				controlProdutos Cprodt = new controlProdutos();
				
				String	nome = (String) txtNome.getText(), 
						muda = (String) combxMuda.getSelectedItem(),
						status = (String) combxStatus.getSelectedItem(), 
						datai = txtDatai.getText(),
						dataf = txtDataf.getText(),
						quant = txtQuant.getText(),
						obs = txtObs.getText();
				
				int idProduto = Cprodt.buscarIdProduto(muda);
				int func = (int) combxFuncionario.getSelectedItem();
				
				if(!nome.isEmpty() && !muda.isEmpty() && func!=0 && !datai.isEmpty() && !quant.isEmpty()) {
					Cp.Inserir(nome,idProduto,func,datai,dataf,quant,status,obs);
					inserirTable("");
				}else {
					JOptionPane.showMessageDialog(null,"Insira os dados nos campos obrigatorios");
				}
			}
		});
		bttCriar.setBounds(54, 91, 120, 72);
		panel.add(bttCriar);
		
		combxStatus = new JComboBox<String>();
		combxStatus.setBounds(252, 224, 240, 35);
		panel.add(combxStatus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(533, 153, 295, 106);
		panel.add(scrollPane);
		
		txtObs = new JTextArea();
		scrollPane.setViewportView(txtObs);
		txtObs.setLineWrap(true);
		
		combxMuda = new JComboBox<String>();
		combxMuda.setBounds(252, 153, 240, 35);
		panel.add(combxMuda);
		combxMuda.addItem("");
		DB c = new DB();
		try {
			String sql = "Select p.nome from Produto p inner join Estoque e on p.id = e.idProduto "+
					"inner join tipoProduto tp on tp.id = p.idTipoProduto where tp.nome='Muda'";
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				combxMuda.addItem(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		combxStatus.addItem("");
		combxStatus.addItem("Em andamento");
		combxStatus.addItem("Pausado");
		combxStatus.addItem("Finalizado");
		
		txtDatai = new JTextField();
		txtDatai.setBounds(533, 288, 137, 35);
		panel.add(txtDatai);
		txtDatai.setColumns(10);
		
		txtDataf = new JTextField();
		txtDataf.setToolTipText("");
		txtDataf.setColumns(10);
		txtDataf.setBounds(690, 288, 137, 35);
		panel.add(txtDataf);
		
		JLabel lblMuda = new JLabel("Muda");
		lblMuda.setBounds(252, 128, 199, 14);
		panel.add(lblMuda);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o");
		lblObservao.setBounds(532, 128, 295, 14);
		panel.add(lblObservao);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(532, 263, 121, 14);
		panel.add(lblDataInicial);
		
		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(690, 263, 121, 14);
		panel.add(lblDataFinal);
		
		JButton bttRegistros = new JButton("Registros");
		bttRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableProducao.getSelectedRow() != -1) {
					int linhaSel = tableProducao.getSelectedRow(); 
					int colunaSel = 0;
					int id = (int) tableProducao.getValueAt(linhaSel,colunaSel);  
					//producao.this.dispose();
					producao p = new producao();
					registro reg = new registro();
					System.out.println("id: "+id);
					reg.setVisible(true);
					p.setId(id);
					reg.setProdc(p.getId());
				}
			}
		});
		bttRegistros.setBounds(35, 462, 172, 120);
		panel.add(bttRegistros);
		
		JButton bttEditar = new JButton("Editar");
		bttEditar.addActionListener(new ActionListener() {
			private int quant2;

			public void actionPerformed(ActionEvent e) {
				if(tableProducao.getSelectedRow() != -1) {
					controlProducao Cp = new controlProducao();
					controlProdutos Cprodt = new controlProdutos();
					
					int linhaSel = tableProducao.getSelectedRow(); 
					int colunaSel = 0;
					int id = (int) tableProducao.getValueAt(linhaSel,colunaSel);  
					DB c = new DB();
					String sql = "Select * from Producao pr inner join Produto p on pr.idProduto = p.id"+
							" inner join Funcionario f on pr.idFuncionario = f.id where pr.id="+id;
					ResultSet rs = c.Select(sql);
					try {
						while(rs.next()) {
							String	nome = (String) txtNome.getText(), 
									muda = (String) combxMuda.getSelectedItem(),
									status = (String) combxStatus.getSelectedItem(),
									datai = txtDatai.getText(),
									dataf = txtDataf.getText(),	
									obs = txtObs.getText();
							
							
							int quant = Integer.parseInt(txtQuant.getText());
							int func = (int) combxFuncionario.getSelectedItem();
							int idProduto = Cprodt.buscarIdProduto(muda);
							
							String	nome2 = rs.getString("nome"),
									status2 = rs.getString("pstatus"), 
									datai2 = rs.getString("datainicial"),
									dataf2 = rs.getString("datafinal"),
									obs2 = rs.getString("observacao");
							
							int quant2 = rs.getInt("quantidade");
							int func2 = rs.getInt("idFuncionario");
							int idProduto2 = rs.getInt("idProduto");							
							
						if(!nome.isEmpty() && !muda.isEmpty() && func!=0 && !datai.isEmpty() && quant>0) {
							if(!nome.equals(nome2) || idProduto!=idProduto2 || func!=func2 || 
									!status.equals(status2) || !datai.equals(datai2) || !dataf.equals(dataf2) || 
									quant!=quant2 || !obs.equals(obs2)) {

								if(!nome.equals(nome2)) {
									System.out.println("nome");
									sql = "Update Producao set nome='"+nome+"' where id="+id;
									Cp.Alterar(sql);
								}
								if(idProduto!=idProduto2) {
									System.out.println("pdrodt");
									sql = "Update Producao set idProduto="+idProduto+" where id="+id;
									Cp.Alterar(sql);
								}
								if(func!=func2) {
									System.out.println("func");
									sql = "Update Producao set idFuncionario="+func+" where id="+id;
									Cp.Alterar(sql);
								}
								if(!status.equals(status2)) {
									System.out.println("s");
									sql = "Update Producao set pstatus='"+status+"' where id="+id;
									Cp.Alterar(sql);
								}
								if(!datai.equals(datai2)) {
									System.out.println("di");
									sql = "Update Producao set datainicial='"+datai+"' where id="+id;
									Cp.Alterar(sql);
								}
								if(!dataf.equals(dataf2)) {
									System.out.println("df");
									sql = "Update Producao set datafinal='"+dataf+"' where id="+id;
									Cp.Alterar(sql);
								}
								if(quant!=quant2) {
									System.out.println("quant");
									sql = "Update Producao set quantidade='"+quant+"' where id="+id;
									Cp.Alterar(sql);
								}
								if(!obs.equals(obs2)) {
									System.out.println("obs");
									sql = "Update Producao set observacao='"+obs+"' where id="+id;
									Cp.Alterar(sql);
								}
								
								JOptionPane.showMessageDialog(null,"Alteradodo com sucesso!");
							}
						}else {
							JOptionPane.showMessageDialog(null,"Insira os dados nos campos obrigatorios");
						}
						}
					} catch (Exception se) {
						JOptionPane.showMessageDialog(null,"Erro ao Preencher os campos - "+se);
					}finally {
						c.close();
					
						txtNome.setText(String.valueOf(""));
						txtDatai.setText(String.valueOf(""));
						txtDataf.setText(String.valueOf(""));
						txtQuant.setText(String.valueOf("0"));
						txtObs.setText(String.valueOf(""));
						combxMuda.setSelectedItem("");
						combxMuda.setSelectedItem("");
						combxFuncionario.setSelectedItem("");
						inserirTable("");
					}
					
				}
			}
		});
		bttEditar.setBounds(54, 187, 119, 72);
		panel.add(bttEditar);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(252, 199, 199, 14);
		panel.add(lblStatus);
		
		JLabel lblFuncionario = new JLabel("Funcionario");
		lblFuncionario.setBounds(252, 270, 121, 14);
		panel.add(lblFuncionario);
		
		JLabel lblProducao = new JLabel("Produ\u00E7\u00E3o");
		lblProducao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 36));
		lblProducao.setBounds(252, 0, 403, 53);
		panel.add(lblProducao);
		
		JButton bttVoltar = new JButton("Voltar");
		bttVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				producao.this.dispose();
				inicial ini = new inicial();
				ini.setVisible(true);
			}
		});
		bttVoltar.setBounds(54, 11, 120, 29);
		panel.add(bttVoltar);
		
		combxFuncionario = new JComboBox();
		combxFuncionario.setBounds(252, 294, 240, 35);
		panel.add(combxFuncionario);
		combxFuncionario.addItem("");
		try {
			String sql = "Select * from Funcionario";
			ResultSet rs = c.Select(sql);
			while(rs.next()) {
				combxFuncionario.addItem(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			c.close();
		}
		
		JButton bttRemover = new JButton("Remover");
		bttRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlProducao Cprodc = new controlProducao();
				int linhaSel = tableProducao.getSelectedRow(); 
				int coluna = tableProducao.getSelectedColumn();
				int colunaSel = 0;
				int id = (int) tableProducao.getValueAt(linhaSel,colunaSel);  
				Cprodc.Excluir(id);
			}
		});
		bttRemover.setBounds(54, 288, 119, 72);
		panel.add(bttRemover);
		
		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(330, 361, 344, 29);
		panel.add(txtBuscar);
		
		txtNome = new JTextField();
		txtNome.setBounds(252, 91, 240, 26);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNomeProducao = new JLabel("Nome da Produ\u00E7\u00E3o");
		lblNomeProducao.setBounds(252, 66, 199, 14);
		panel.add(lblNomeProducao);
		
		combxFiltro = new JComboBox<String>();
		combxFiltro.setBounds(252, 361, 78, 29);
		panel.add(combxFiltro);
		
		JLabel lblFiltrarPor = new JLabel("Filtrar por:");
		lblFiltrarPor.setBounds(252, 347, 121, 14);
		panel.add(lblFiltrarPor);
		
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(234, 413, 609, 198);
		panel.add(scrollTable);
		
		tableProducao = new JTable();
		tableProducao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlProdutos Cprodt = new controlProdutos();
				int id = (int) tableProducao.getValueAt(tableProducao.getSelectedRow(),0);  
				DB c = new DB();
				String sql = "Select * from Producao pr inner join Produto p on pr.idProduto = p.id"+
						" inner join Funcionario f on pr.idFuncionario = f.id where pr.id="+id;
				ResultSet rs = c.Select(sql);
				try {
					while(rs.next()) {
						txtNome.setText(String.valueOf(rs.getString("nome")));
						txtQuant.setText(String.valueOf(rs.getString("quantidade")));
						txtDatai.setText(String.valueOf(rs.getString("datainicial")));
						txtDataf.setText(String.valueOf(rs.getString("datafinal")));
						txtObs.setText(String.valueOf(rs.getString("observacao")));
						combxFuncionario.setSelectedItem(rs.getInt("idFuncionario"));
						combxMuda.setSelectedItem(Cprodt.buscarNomeProduto(rs.getInt("idTipoProduto")));
						combxStatus.setSelectedItem(rs.getString("pstatus"));
					}
				} catch (Exception se) {
					JOptionPane.showMessageDialog(null,"Erro ao Preencher os campos - "+se);
				}finally {
					c.close();
				}
			}
		});
		scrollTable.setViewportView(tableProducao);
		
		txtQuant = new JTextField();
		txtQuant.setColumns(10);
		txtQuant.setBounds(533, 91, 93, 26);
		panel.add(txtQuant);
		
		JLabel lblQuant = new JLabel("Quantidade");
		lblQuant.setBounds(533, 66, 199, 14);
		panel.add(lblQuant);
		inserirTable("");
	}
	
	public void  inserirTable(String sql){
		controlProdutos Cprodt = new controlProdutos();
		ArrayList<Object> dados = new ArrayList<Object>();
		String [] colunas = new String[] {"Id","Nome","Muda","Funcionario","Quantidade","Status","DataInicial","DataFinal","Observação"};
		DB c = new DB();
		if(sql.isEmpty()) {
			sql = "Select pr.id,pr.nome as nomeProdc,idProduto,idFuncionario,quantidade,pstatus,datainicial,datafinal,pr.observacao from Producao pr "+
					"inner join Produto p on pr.idProduto = p.id ";
		}
		ResultSet rs = c.Select(sql);
		
		try {
			while(rs.next()) {
				String nomeProduto = Cprodt.buscarNomeProduto(rs.getInt("idProduto"));
				//String nomeFunc = Cfunc.buscarNomeProduto(rs.getInt("idFuncionario"));
				dados.add(new Object[] {rs.getInt("id"),rs.getString("nomeProdc"),nomeProduto,rs.getInt("idFuncionario"),rs.getString("Quantidade"),rs.getString("pStatus"),rs.getString("DataInicial"),rs.getString("DataFinal"),rs.getString("Observacao")});	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao Preencher a Table - "+e);
		}
		TableModel tableMod = new TableModel(dados,colunas);
		tableProducao.setModel(tableMod);
		tableProducao.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableProducao.getColumnModel().getColumn(0).setResizable(false);
		tableProducao.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableProducao.getColumnModel().getColumn(1).setResizable(false);
		tableProducao.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableProducao.getColumnModel().getColumn(2).setResizable(false);
		tableProducao.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableProducao.getColumnModel().getColumn(3).setResizable(false);
		tableProducao.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableProducao.getColumnModel().getColumn(4).setResizable(false);
		tableProducao.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableProducao.getColumnModel().getColumn(5).setResizable(false);
		tableProducao.getTableHeader().setReorderingAllowed(false);
		tableProducao.setAutoResizeMode(tableProducao.AUTO_RESIZE_OFF);
		tableProducao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		c.close();
	}
	
	public void setId(int idProducao) {
		this.idProducao = idProducao;
	}
	
	public int getId() {
		return idProducao;
	}
}
