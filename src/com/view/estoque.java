package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import com.control.controlEstoque;
import com.mudstock.Estoque;
import com.mudstock.TableModel;

import util.DB;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JMenuItem;

public class estoque extends JFrame {

	private JPanel contentPane;
	private JTable tableEstoque;
	private JTextField txtBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					estoque frame = new estoque();
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
	public estoque() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 507);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProduto = new JMenu("Produtos");
		menuBar.add(mnProduto);
		
		JMenuItem mnPCriar = new JMenuItem("Controle de Produtos");
		mnPCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//estoque.this.dispose();
				produto prodt = new produto();
				prodt.setVisible(true);
			}
		});
		mnProduto.add(mnPCriar);
		
		JMenuItem mnControlTP = new JMenuItem("Controle Tipo de Produtos");
		mnControlTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoProduto tprodt = new tipoProduto();
				tprodt.setVisible(true);
			}
		});
		mnProduto.add(mnControlTP);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton bttEditar = new JButton("Editar");
		bttEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarEstoque ee = new editarEstoque();
				ee.setVisible(true);
			}
		});
		bttEditar.setBounds(647, 167, 137, 64);
		panel.add(bttEditar);
		
		JButton bttInserir = new JButton("Inserir");
		bttInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirEstoque inEst = new inserirEstoque();
				inEst.setVisible(true);
			}
		});
		bttInserir.setBounds(647, 43, 137, 64);
		panel.add(bttInserir);
		
		JButton bttBuscar = new JButton("Buscar");
		bttBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String busca = txtBuscar.getText();
				if(!busca.isEmpty()) {
					inserirTable("Select * from Estoque e inner join Produto p on e.idproduto = p.id where nome='"+busca+"'");
				}else {
					inserirTable("");
				}
			}
		});
		bttBuscar.setBounds(408, 97, 169, 23);
		panel.add(bttBuscar);
		
		JButton bttControle = new JButton("Controle");
		bttControle.setBounds(647, 317, 137, 64);
		panel.add(bttControle);
		
		JLabel lblNewLabel = new JLabel("Estoque");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblNewLabel.setBounds(78, 30, 157, 51);
		panel.add(lblNewLabel);
		
		JButton bttVoltar = new JButton("voltar");
		bttVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estoque.this.dispose();
				inicial ini = new inicial();
				ini.setVisible(true);
			}
		});
		bttVoltar.setBounds(10, 11, 89, 23);
		panel.add(bttVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 146, 531, 247);
		panel.add(scrollPane);
		
		tableEstoque = new JTable();
		scrollPane.setViewportView(tableEstoque);
		
		JButton bttRemover = new JButton("Remover");
		bttRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableEstoque.getSelectedRow() != -1) {
					int linhaSel = tableEstoque.getSelectedRow(); 
					int colunaSel = 0;
					int est = (int) tableEstoque.getValueAt(linhaSel,colunaSel);  
					controlEstoque CEst = new controlEstoque();
					CEst.Excluir(est);
				}
			}
		});
		bttRemover.setBounds(647, 242, 137, 64);
		panel.add(bttRemover);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(46, 97, 365, 23);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		inserirTable("");
	}
	
	public void  inserirTable(String sql){
		ArrayList<Object> dados = new ArrayList<Object>();
		String [] colunas = new String[] {"Id","Nome","Quantidade","DataAtualização","Observação"};
		DB c = new DB();
		if(sql.isEmpty()) {
			sql = "Select * from Estoque e inner join Produto p on e.idproduto = p.id";
		}
		ResultSet rs = c.Select(sql);
		try {
			while(rs.next()) {
				dados.add(new Object[] {rs.getInt("id"),rs.getString("nome"),rs.getInt("quantidade"),rs.getString("dataAtualizacao"),rs.getString("observacao")});	
			}
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null,"Erro ao Preencher a Table - "+e);
		}
		TableModel tableMod = new TableModel(dados,colunas);
		tableEstoque.setModel(tableMod);
		tableEstoque.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableEstoque.getColumnModel().getColumn(0).setResizable(false);
		tableEstoque.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableEstoque.getColumnModel().getColumn(1).setResizable(false);
		tableEstoque.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableEstoque.getColumnModel().getColumn(2).setResizable(false);
		tableEstoque.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableEstoque.getColumnModel().getColumn(3).setResizable(false);
		tableEstoque.getColumnModel().getColumn(4).setPreferredWidth(170);
		tableEstoque.getColumnModel().getColumn(4).setResizable(false);
		tableEstoque.getTableHeader().setReorderingAllowed(false);
		tableEstoque.setAutoResizeMode(tableEstoque.AUTO_RESIZE_OFF);
		tableEstoque.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		c.close();
	}
}


