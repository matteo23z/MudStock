package com.mudstock;

import java.util.Scanner;

import com.view.login;

import util.DB;

public class Principal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Funcionario func = new Funcionario();
		
		DB conx = new DB();
		
		login log = new login();
		
		log.main(args);
		
		String nome, user, email, data, sen;
		int n = 0, v = 0;
		
		while (n==0) {
			System.out.println("-------------------------------");
			System.out.println("            MUDSTOCK           ");
			System.out.println("-------------------------------");
			System.out.println("|       1 - Login             |");
			System.out.println("|       2 - Cadastro          |");
			System.out.println("|       3 - Sair              |");
			System.out.println("Escolha uma op: ");
			v = scan.nextInt();
			switch(v) {
				case 1:
					System.out.println("-------------------------------");
					System.out.println("             LOGIN             ");
					System.out.println("-------------------------------");
					System.out.println("Digite user: ");
					user = scan.next();
					System.out.println("Digite Senha: ");
					sen = scan.next();
				break;
				case 2:
					System.out.println("-------------------------------");
					System.out.println("           Cadastro            ");
					System.out.println("-------------------------------");
					System.out.println("Digite nome: ");
					func.setNome(scan.next()); 
					System.out.println("Digite email: ");
					func.setEmail(scan.next());
					System.out.println("Digite data nascimento: ");
					func.setDataNasc(scan.next());
					System.out.println("Digite user: ");
					func.setNomeUser(scan.next());
					System.out.println("Digite Senha: ");
					func.setSenha(scan.next());
					
					func.inserir();
				break;
				case 3:
					n=-1;
				break;
			}
		}
		while(n==1) {
			System.out.println("Hello World - você está no menu :)");
			System.out.println("MENU");
			System.out.println("1 - alterar cadastro");
			System.out.println("2 - buscar cadastro");
			System.out.println("3 - excluir cadastro");
			System.out.println("4 - sair");
			System.out.println("Escolha um op: ");
			v = scan.nextInt();
			switch(v) {
			case 1:
				func.buscarAll();
				System.out.println("Alterar");
				System.out.println("Digite nome: ");
				scan.next(func.getNome());
				System.out.println("Digite email: ");
				email = scan.next();
				System.out.println("Digite data nascimento: ");
				data = scan.next();
				System.out.println("Digite user: ");
				user = scan.next();
				System.out.println("Digite Senha: ");
				sen = scan.next();
				
				//func.inserir(nome, user, email, sen, data);
			break;
			case 2:
				System.out.println("Buscar");
				System.out.println("Digite user: ");
				user = scan.next();
				//func.Buscar(user);
			break;
			case 3:
				System.out.println("Excluir");
				System.out.println("Digite user: ");
				user = scan.next();
				//func.Buscar(user);
			break;
			case 4:
				n=-1;
			break;
			}
		}
	}
}
