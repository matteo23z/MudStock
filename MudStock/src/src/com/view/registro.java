package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import com.control.controlProdutos;
import com.control.controlRegistroP;
import com.mudstock.RegistroProducao;
import com.mudstock.TableModel;

import util.DB;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpinnerNumberModel;

public class registro extends JFrame {

	private JPanel contentPane;
	private JTable tableRegistros;
	private JTextField txtBuscar;
	private JTextField txtData;
	private JTextArea txtObs;
	private JCheckBox cbxIrrigacao;
	private JCheckBox cbxAdubacao;
	private JCheckBox cbxPragas;
	private JSpinner spMudasM;
	private JTextField txtNome;
	
	private int idProducao;
	
	public void setProdc(int idProducao){
		this.idProducao = idProducao;
		System.out.println(this.idProducao);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro frame = new registro();
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
	public registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registros");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(119, -3, 143, 38);
		panel.add(lblNewLabel);
		
		JButton bttInserir = new JButton("Inserir");
		bttInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean adubacao = cbxAdubacao.isSelected(),
						irrigacao =  cbxIrrigacao.isSelected(),
						pragas = cbxPragas.isSelected();
				
				String data = txtData.getText(),
					   obs = txtObs.getText(),
					   nome = txtNome.getText();
				
				int mudasm = (int) spMudasM.getValue();

				if(!data.isEmpty() && !data.isEmpty()) {
					controlRegistroP Creg = new controlRegistroP();
					System.out.println("id inserir"+idProducao);
					Creg.Inserir(idProducao,nome , data, pragas, irrigacao, mudasm, adubacao, obs);
					JOptionPane.showMessageDialog(null,"Inserido com Sucesso!");
					inserirTable("");
				}else {
					JOptionPane.showMessageDialog(null,"Insira os dados nos campos obrigatorios");
				}
			}
		});
		bttInserir.setBounds(587, 293, 89, 23);
		panel.add(bttInserir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(487, 240, 146, 23);
		panel.add(btnBuscar);
		
		JButton bttVoltar = new JButton("Voltar");
		bttVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro.this.dispose();
				//producao prod = new producao();
				//prod.setVisible(true);
			}
		});
		bttVoltar.setBounds(5, 11, 89, 23);
		panel.add(bttVoltar);
		
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(49, 68, 609, 161);
		panel.add(scrollTable);
		
		tableRegistros = new JTable();
		tableRegistros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlProdutos Cprodt = new controlProdutos();
				int linhaSel = tableRegistros.getSelectedRow(); 
				//int coluna = tableRegistros.getSelectedColumn();		
				
				//String idR = (String) tableRegistros.getValueAt(linhaSel,0); 
				String nome = (String) tableRegistros.getValueAt(linhaSel,1); 
				String data = (String) tableRegistros.getValueAt(linhaSel,2);
				String irrigacao = (String) tableRegistros.getValueAt(linhaSel,3);
				String adubacao = (String) tableRegistros.getValueAt(linhaSel,4);
				int mudasm = (int) tableRegistros.getValueAt(linhaSel,5);
				String pragas = (String) tableRegistros.getValueAt(linhaSel,6);
				String obs = (String) tableRegistros.getValueAt(linhaSel,7);
				
				
				txtNome.setText(String.valueOf(nome));
				txtData.setText(String.valueOf(data));
				if(irrigacao.equals("Sim")) {
					cbxIrrigacao.setSelected(true);
				}
				if(adubacao.equals("Sim")) {
					cbxAdubacao.setSelected(true);
				}
				if(pragas.equals("Sim")) {
					cbxPragas.setSelected(true);
				}
				spMudasM.setValue(mudasm);
				txtObs.setText(String.valueOf(obs));
			}
		});
		scrollTable.setViewportView(tableRegistros);
		
		tableRegistros.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null},
	                {null, null, null, null}
	            },
	            new String [] {
	                "Title 1", "Title 2", "Title 3", "Title 4"
	            }
	        ));
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(67, 241, 422, 20);
		panel.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		cbxIrrigacao = new JCheckBox("Irriga\u00E7\u00E3o");
		cbxIrrigacao.setBounds(49, 353, 100, 31);
		panel.add(cbxIrrigacao);
		
		txtData = new JTextField();
		txtData.setBounds(309, 293, 126, 23);
		panel.add(txtData);
		txtData.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(309, 343, 257, 119);
		panel.add(scrollPane);
		
		txtObs = new JTextArea();
		scrollPane.setViewportView(txtObs);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(309, 272, 146, 14);
		panel.add(lblData);
		
		JLabel lblTeve = new JLabel("Teve:");
		lblTeve.setBounds(49, 332, 146, 14);
		panel.add(lblTeve);
		
		cbxAdubacao = new JCheckBox("Aduba\u00E7\u00E3o");
		cbxAdubacao.setBounds(169, 353, 101, 31);
		panel.add(cbxAdubacao);
		
		cbxPragas = new JCheckBox("Pragas");
		cbxPragas.setBounds(169, 399, 100, 31);
		panel.add(cbxPragas);
		
		JLabel lblObs = new JLabel("Observa\u00E7\u00F5es:");
		lblObs.setBounds(309, 327, 146, 14);
		panel.add(lblObs);
		
		JButton bttEditar = new JButton("Editar");
		bttEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableRegistros.getSelectedRow() != -1) {
				int linhaSel = tableRegistros.getSelectedRow(); 
				//int coluna = tableRegistros.getSelectedColumn();		
				
				int idR = (int) tableRegistros.getValueAt(linhaSel,0); 
				String nome = (String) tableRegistros.getValueAt(linhaSel,1); 
				String data = (String) tableRegistros.getValueAt(linhaSel,2);
				String irrigacao = (String) tableRegistros.getValueAt(linhaSel,3);
				String adubacao = (String) tableRegistros.getValueAt(linhaSel,4);
				int mudasm = (int) tableRegistros.getValueAt(linhaSel,5);
				String pragas = (String) tableRegistros.getValueAt(linhaSel,6);
				String obs = (String) tableRegistros.getValueAt(linhaSel,7);
				
			
				boolean adubacao2 = cbxAdubacao.isSelected(),
						irrigacao2 =  cbxIrrigacao.isSelected(),
						pragas2 = cbxPragas.isSelected();
				
				String adu, irr, pra;
				int adu2,irr2,pra2;
				if(irrigacao2) {
					irr = "Sim";
					irr2 = 1;
				}else {
					irr = "Não";
					irr2 = 0;
				}
				if(adubacao2) {
					adu = "Sim";
					adu2 = 1;
				}else {
					adu = "Não";
					adu2 = 0;
				}
				if(pragas2) {
					pra = "Sim";
					pra2 = 1;
				}else {
					pra = "Não";
					pra2 = 0;
				}
				String data2 = txtData.getText(),
					   obs2 = txtObs.getText(),
					   nome2 = txtNome.getText();
				int mudasm2 = (int) spMudasM.getValue();

				if(!data.isEmpty() && !data.isEmpty()) {
					String sql;
					controlRegistroP Creg = new controlRegistroP();
					if(!nome.equals(nome2)) {
						sql = "Update Registro set rnome='"+nome2+" where id="+idR;
						Creg.Alterar(sql);
					}
					if(!data.equals(data2)) {
						sql = "Update Registro set rdata='"+data2+"' where id="+idR;
						Creg.Alterar(sql);
					}
					if(!obs.equals(obs2)) {
						sql = "Update Registro set observacao='"+obs2+"' where id="+idR;
						Creg.Alterar(sql);
					}
					if(!irr.equals(irrigacao)) {
						sql = "Update Registro set irrigacao="+irr2+" where id="+idR;
						Creg.Alterar(sql);
					}
					if(!adu.equals(adubacao)) {
						sql = "Update Registro set adubacao="+adu2+" where id="+idR;
						Creg.Alterar(sql);
					}
					if(!pra.equals(pragas)) {
						sql = "Update Registro set pragas="+pra2+" where id="+idR;
						Creg.Alterar(sql);
					}
					if(mudasm!=mudasm2) {
						sql = "Update Registro set mudasmortas="+mudasm2+" where id="+idR;
						Creg.Alterar(sql);
					}
					JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
					txtNome.setText(String.valueOf(nome));
					txtData.setText(String.valueOf(data));
					cbxIrrigacao.setSelected(false);
					cbxAdubacao.setSelected(false);
					cbxPragas.setSelected(false);
					spMudasM.setValue(mudasm);
					txtObs.setText(String.valueOf(obs));
					inserirTable("");
				}else {
					JOptionPane.showMessageDialog(null,"Insira nos campos obrigatórios para alterar!");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Linha da Tabela não selecionada");
			}
			}
		});
		bttEditar.setBounds(587, 327, 89, 23);
		panel.add(bttEditar);
		
		JButton bttRemover = new JButton("Remover");
		bttRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableRegistros.getSelectedRow() != -1) {
					int linhaSel = tableRegistros.getSelectedRow();
					int dialogButton = JOptionPane.showConfirmDialog (null, "Tem certeza que deseja exluir esse registro?","WARNING",JOptionPane.YES_NO_OPTION);

					if (dialogButton == JOptionPane.YES_OPTION) {
						int idR = (int) tableRegistros.getValueAt(linhaSel,0); 
						controlRegistroP Creg = new controlRegistroP();
						Creg.Excluir(idR);
						JOptionPane.showMessageDialog(null,"Registro excluirdo com Sucesso!");
					} else {
						remove(dialogButton);
					}
				}else {
					JOptionPane.showMessageDialog(null,"Linha da Tabela não selecionada");
				}
			}
		});
		bttRemover.setBounds(587, 361, 89, 23);
		panel.add(bttRemover);
		
		JButton bttAtivar = new JButton("Ativar");
		bttAtivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirTable("");
			}
		});
		bttAtivar.setBounds(49, 45, 70, 23);
		panel.add(bttAtivar);
		
		txtNome = new JTextField();
		txtNome.setBounds(49, 293, 221, 23);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(49, 272, 146, 14);
		panel.add(lblNome);
		
		spMudasM = new JSpinner();
		spMudasM.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spMudasM.setBounds(49, 410, 100, 20);
		panel.add(spMudasM);
		
		JLabel lblmm = new JLabel("Mudas Mortas:");
		lblmm.setBounds(49, 391, 146, 14);
		panel.add(lblmm);
		inserirTable("");
	}
	
	public void inserirTable(String sql){
		controlProdutos Cprodt = new controlProdutos();
		ArrayList<Object> dados = new ArrayList<Object>();
		String [] colunas = new String[] {"IdRegistro","Nome","Data do Registro","Irrigação","Adubação","Mudas Mortas","Pragas","Observação"};
		
		int id = idProducao;
		System.out.println("table id:"+id);
		DB c = new DB();
		if(sql.isEmpty()) {
			if(id!=0) {
			sql = "Select * from Registro r inner join Producao pr on pr.id = r.idProducao where pr.id="+id;
			}else {
				sql = "Select r.id,rnome,rdata,irrigacao, adubacao,mudasmortas,pragas,r.observacao from Registro r inner join Producao pr on pr.id = r.idProducao where pr.id=0";
			}
		}
		ResultSet rs = c.Select(sql);
		
		try {
			while(rs.next()) {
				String irr,adu,pra;
				if(rs.getInt("irrigacao")==1) {
					irr = "Sim";
				}else {
					irr = "Não";
				}
				if(rs.getInt("adubacao")==1) {
					adu = "Sim";
				}else {
					adu = "Não";
				}
				if(rs.getInt("pragas")==1) {
					pra = "Sim";
				}else {
					pra = "Não";
				}
				dados.add(new Object[] {rs.getInt("id"),rs.getString("rnome"),rs.getString("rdata"),irr,adu,rs.getInt("mudasmortas"),pra,rs.getString("observacao")});	
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao Preencher a Table - "+e);
		}
		TableModel tableMod = new TableModel(dados,colunas);
		tableRegistros.setModel(tableMod);
		//tableRegistros.getColumnModel().getColumn(0).setPreferredWidth(100);
		//tableRegistros.getColumnModel().getColumn(0).setResizable(false);
		//tableRegistros.getTableHeader().setReorderingAllowed(false);
		tableRegistros.setAutoResizeMode(tableRegistros.AUTO_RESIZE_OFF);
		tableRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		c.close();
	}
}
