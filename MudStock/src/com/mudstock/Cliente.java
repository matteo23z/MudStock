package com.mudstock;

public class Cliente {
	private int id;
	private String nome;
	private String email;
	private String rg;
	private String cpf;
	private String telefone;
	private String situacao;
	
	public Cliente() {
		
	}
	
	public Cliente Registrar() {
		DB c = new DB();
		
		String sql = "Insert into Cliente (nome,email,rg,cpf,telefone,situacao) values('" 
				+ nome + "', '" + email + "', '" + rg + "', " + cpf + "', '" + telefone + "', '" + situacao +  "')";
		c.Comando(sql);
		return null;
	}
	public Cliente Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update Cliente set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public Cliente Buscar() {
		return null;
	}
	public Cliente Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from  where id="+id;
		c.Comando(sql);
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
}
