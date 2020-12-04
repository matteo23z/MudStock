package com.mudstock;

import util.DB;

public class RegistroProducao {
	private int id;
	private int idProducao;
	private String nome;
	private String data;
	private boolean pragas;
	private boolean irrigacao;
	private int mudasMortas;
	private boolean adubacao;
	private String obs;
	
	public RegistroProducao(int idProducao, String nome, String data, boolean pragas, boolean irrigacao, int mudasmortas, boolean adubacao, String obs) {
		this.setIdProducao(idProducao);
		this.nome = nome;
		this.data = data;
		this.pragas = pragas;
		this.irrigacao = irrigacao;
		this.mudasMortas = mudasmortas;
		this.adubacao =adubacao;
		this.obs = obs;
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

	public int isPragas() {
		if(pragas) {
			return 1;
		}else {
			return 0;
		}
	}

	public void setPragas(boolean pragas) {
		this.pragas = pragas;
	}

	public int isIrrigacao() {
		if(irrigacao) {
			return 1;
		}else {
			return 0;
		}
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

	public int isAdubacao() {
		if(adubacao) {
			return 1;
		}else {
			return 0;
		}
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

	public int getIdProducao() {
		return idProducao;
	}

	public void setIdProducao(int idProducao) {
		this.idProducao = idProducao;
	}
}

