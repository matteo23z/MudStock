package com.mudstock;

public class RegistroProducao {
	private int id;
	private String nome;
	private String data;
	private boolean pragas;
	private boolean irrigacao;
	private int mudasMortas;
	private boolean adubacao;
	private String obs;
	
	public RegistroProducao() {
		//this.id = id;
		//this.nome = nome;
		//this.data = data;
		//this.pragas = pragas;
		//this.irrigacao = irrigacao;
		//this.mudasMortas = mudasmortas;
		//this.adubacao =adubacao;
		//this.obs = obs;
	}
	
	public RegistroProducao Registrar() {
		DB c = new DB();
		
		String sql = "Insert into RegistroProducao (nome,data,pragas,irrigacao,mudasmortas) values('" 
				+ nome + "', '" + data + "', '" + pragas + "', '" + irrigacao + "', " + mudasMortas + "', '" + adubacao + "','" + obs + "')";
		c.Comando(sql);

		return null;
	}
	public RegistroProducao Alterar(int id, String coluna, String var) {
		DB c = new DB();
		
		String sql = "Update RegistroProducao set "+coluna+"='"+var+" where id="+id;
		c.Comando(sql);
		return null;
	}
	public RegistroProducao Buscar() {
		return null;
	}
	public RegistroProducao Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from RegistroProducao where id="+id;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isPragas() {
		return pragas;
	}

	public void setPragas(boolean pragas) {
		this.pragas = pragas;
	}

	public boolean isIrrigacao() {
		return irrigacao;
	}

	public void setIrrigacao(boolean irrigacao) {
		this.irrigacao = irrigacao;
	}

	public int getMudasMortas() {
		return mudasMortas;
	}

	public void setMudasMortas(int mudasMortas) {
		this.mudasMortas = mudasMortas;
	}

	public boolean isAdubacao() {
		return adubacao;
	}

	public void setAdubacao(boolean adubacao) {
		this.adubacao = adubacao;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
}

