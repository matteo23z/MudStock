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
		
	}
	
	public RegistroProducao Registrar( int id, String nome, String data, boolean pragas,
			boolean irrigacao, int mudasmortas, boolean adubacao, String obs) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.pragas = pragas;
		this.irrigacao = irrigacao;
		this.mudasMortas = mudasmortas;
		this.adubacao =adubacao;
		this.obs = obs;
		return null;
	}
	public RegistroProducao Alterar() {
		return null;
	}
	public RegistroProducao Buscar() {
		return null;
	}
	public RegistroProducao Excluir() {
		return null;
	}
}
}
