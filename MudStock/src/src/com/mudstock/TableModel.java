package com.mudstock;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Object> linhas = null;
	private String[] colunas = null;
	
	
	public TableModel (ArrayList<Object> lin, String[] col) {
		setLinhas(lin);
		setColunas(col);
	}
	
	public ArrayList<Object> getLinhas() {
		return linhas;
	}
	public void setLinhas(ArrayList<Object> linhas) {
		this.linhas = linhas;
		
	}
	public String[] getColunas() {
		return colunas;
	}
	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	public String getColumnName(int column) {
		return colunas[column];
	}

	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return linhas.size();
	}

	public Object getValueAt(int nLin,int nCol) {
		Object[] linha = (Object[])getLinhas().get(nLin);
		return linha[nCol];
	}
	
	public void atualizarTabela() {
        fireTableDataChanged();
    }
	
	public void addRow (Estoque e) {
		this.linhas.add(e);
		this.fireTableDataChanged();
	}
	public void removeRow (int linha) {
		this.linhas.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}
}