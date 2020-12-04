package com.control;

import com.mudstock.RegistroProducao;

import util.DB;

public class controlRegistroP {
	public RegistroProducao Inserir(int idProducao,String nome,String data,boolean pragas,boolean irrigacao,int mudasm,boolean adubacao,String obs) {
		DB c = new DB();
		RegistroProducao r = new RegistroProducao(idProducao,nome , data, pragas, irrigacao, mudasm, adubacao, obs);
		String sql = "Insert into Registro (idProducao,rnome,rdata,pragas,irrigacao,mudasmortas,adubacao,observacao) values(" + r.getIdProducao() + ",'"
				+ r.getNome() + "', '" + r.getData() + "'," + r.isPragas() + "," + r.isIrrigacao() + ", " + r.getMudasMortas() + ", " 
				+ r.isAdubacao() + ",'" + r.getObs() + "')";
		c.Comando(sql);
		return null;
	}
	public RegistroProducao Alterar(String sql) {
		DB c = new DB();

		c.Comando(sql);
		return null;
	}
	public RegistroProducao Buscar() {
		return null;
	}
	public RegistroProducao Excluir(int id) {
		DB c = new DB();
		
		String sql = "Delete from Registro where id="+id;
		c.Comando(sql);
		return null;
	}
}
