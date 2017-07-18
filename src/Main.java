import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
	static Scanner in = new Scanner(System.in);
	static int contU = 0;
	static int contR = 0;
	static int contA = 0;
	static int contAl = 0;
	
	public static void main(String[] args) {
		Usuario[] users = new Usuario[1000];
		Recurso[] recursos = new Recurso[1000];
		Atividade[] atividades = new Atividade[1000];
		Alocacao[] alocacoes = new  Alocacao[1000];
		
		
		int opc;
		do {			
			System.out.println("0 : SAIR");
			System.out.println("1 : CADASTRAR USUÁRIO");
			System.out.println("2 : CADASTRAR RECURSO");
			System.out.println("3 : ALOCAR RECURSO");
			System.out.println("4 : REGISTRAR NOVA ATIVIDADE");
			System.out.println("5 : VER ATIVIDADES");
			System.out.println("6 : VER RECURSOS");
			System.out.println("7 : MUDAR STATUS DE RECURSO");
            System.out.println("8 : CONSULTAR USUARIO");
            System.out.println("9 : CONSULTAR RECURSOS");
            System.out.println("10 : EMITIR RELATÓRIO");
			opc = in.nextInt();
			
			if(opc == 1){
                cadastraUsuario(users);
            }
            if(opc == 2){
                cadastraRecurso(recursos);
            }
            if(opc == 3){
                alocaRecurso(recursos, users, alocacoes);
            }
            if(opc == 4){
                cadastraAtividade(atividades, alocacoes, users);
            }
            if(opc == 5){
                verAtividades(atividades);
            }
            if(opc == 6){
                verRecursos(recursos);
            }
            if(opc == 7){
                mudarStatusRecurso(recursos);
            }
            if(opc == 8){
                buscaUsuarioById(users, alocacoes, atividades);
            }
            if(opc == 9){
                buscaRecursoById(recursos);
            }
            if(opc == 10){
                emitirRelatorio(users, alocacoes, atividades);
            }

		} while(opc != 0);
	}
	
	static void cadastraUsuario(Usuario[] users) {
		Usuario u = new Usuario();
		u.setId(contU);
		System.out.println("Nome do Usuario: ");
		u.setNome(in.next());
        System.out.println("Email do Usuario: ");
        u.setEmail(in.next());
		System.out.println("Tipo do Usuario: (1 : Graduacao; 2: Mestrado ou Doutorado; 3: Professor; 4: Admin)");
		u.setTipo(in.nextInt());
		users[contU] = u;
		contU++;
	}

    static void buscaUsuarioById(Usuario[] users, Alocacao[] alocacoes, Atividade[] atividades){
	    System.out.println("Digite o id para buscar: ");
	    int id = in.nextInt();
	    Usuario u = users[id];
	    if(u != null){
            System.out.println("ID: " + u.getId());
            System.out.println("Nome: " + u.getNome());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Recursos alocados para " + u.getNome() + " : ");
            for(int i = 0; i < contAl; i++){
                Alocacao a = alocacoes[i];
                if (a.getResponsavel().getId() == u.getId()){
                    System.out.println("ID do RECURSO: " + a.getRecurso().getId());
                    System.out.println("RECURSO: " + a.getRecurso().getNome());
                } else{
                    System.out.println("Usuario não alocou recursos.");
                }
            }
            System.out.println("Atividades de " + u.getNome() + " : ");
            for (int i = 0; i < contA; i++){
                Atividade a = atividades[i];
                if(a.getResponsavel().getId() == u.getId()){
                    System.out.println("ID da ATIVIDADE: " + a.getId());
                    System.out.println("ATIVIDADE: " + a.getTitulo());
                } else{
                    System.out.println("Usuario não possui atividades!");
                }
            }
        }
        else {
	        System.out.println("Usuario não existe!");
        }
    }

    static void cadastraRecurso(Recurso[] recursos){
        Recurso r = new Recurso();
        r.setId(contR);
        System.out.println("Recurso: ");
        r.setNome(in.next());
        recursos[contR] = r;
        contR++;
    }

    static void alocaRecurso(Recurso[] recursos, Usuario[] users, Alocacao[] alocacoes){
        Alocacao a = new Alocacao();
        a.setId(contAl);
        System.out.println("ID do Responsável: ");
        int id = in.nextInt();
        a.setResponsavel(users[id]);

        System.out.println("ID do Recurso: ");
        id = in.nextInt();
        a.setRecurso(recursos[id]);
        a.getRecurso().setEmProcessoDeAlocacao(true);

        System.out.println("Confirmar Alocação de Recurso? (1: SIM; 2: NÃO)");
        int resp = in.nextInt();
        if(resp == 1){
            a.getRecurso().setEmProcessoDeAlocacao(false);
            a.getRecurso().setAlocado(true);
        }


        alocacoes[contAl] = a;
        contAl++;
    }

    static void buscaRecursoById(Recurso[] recursos){
        System.out.println("Digite o id para buscar: ");
        int id = in.nextInt();
        Recurso r = recursos[id];
        if(r != null){
            System.out.println("ID: " + r.getId());
            System.out.println("RECURSO: " + r.getNome());
            System.out.println("STATUS:");
            if(r.isAlocado()) System.out.println("ALOCADO");
            if(r.isEmProcessoDeAlocacao()) System.out.println("EM PROCESSO DE ALOCAÇÃO");
            if(r.isEmAndamento()) System.out.println("EM ANDAMENTO");
            if(r.isConcluido()) System.out.println("CONCLUIDO");
        }
        else {
            System.out.println("Recurso não existe!");
        }
    }

    static void cadastraAtividade(Atividade[] atividades, Alocacao[] alocacoes, Usuario[] users){
        Atividade a = new Atividade();

        a.setId(contA);

        System.out.println("Titulo: ");
        a.setTitulo(in.next());

        System.out.println("Descrição: ");
        a.setDescricao(in.next());

        System.out.println("Tipo de Atividade: (1: Aula; 2: Apresentação; 3: Laboratório)");
        a.setTipo(in.nextInt());

        System.out.println("ID do Responsável: ");
        int id = in.nextInt();
        a.setResponsavel(users[id]);

        System.out.println("ID da alocação de recurso: ");
        id = in.nextInt();
        a.setAlocacao(alocacoes[id]);

        a.getAlocacao().getRecurso().setEmAndamento(true);

        Calendar c = Calendar.getInstance();
        int dia, mes, ano, hora, min;

        System.out.println("Data e Hora de Inicio : " + c.getTime().toString());
        a.setDataInicio(c);

        System.out.println("Data e Hora de termino (DIA MES ANO HORA MIN)");
        dia = in.nextInt();
        mes = in.nextInt();
        ano = in.nextInt();
        hora = in.nextInt();
        min = in.nextInt();
        c.set(dia, mes, ano, hora, min);
        a.setDataFim(c);

        System.out.println("Máterial de Apoio: ");
        a.setMaterialDeApoio(in.next());

        System.out.println("Quantidade de Participantes: ");
        int qntparticipantes = in.nextInt();
        String[] participantes = new String[qntparticipantes];
        for(int i = 0; i < qntparticipantes; i++){
            System.out.print("Participante " + i + " -> ");
            participantes[i] = in.next();
        }
        a.setParticipantes(participantes);
        atividades[contA] = a;
        contA++;
    }

    static void verAtividades(Atividade[] atividades){
        System.out.println("-- ATIVIDADES CADASTRADAS --");
        if(contA == 0) System.out.println("NENHUMA ATIVIDADE CADASTRADA!");
        else{
            for(int i = 0; i < contA; i++){
                System.out.println("ID: " + atividades[i].getId());
                System.out.println("ATIVIDADE: " + atividades[i].getTitulo());
                System.out.println("RESPONSAVEL: " + atividades[i].getAlocacao().getResponsavel().getNome());
            }
        }

    }

    static void verRecursos(Recurso[] recursos){
        System.out.println("-- RECURSOS CADASTRADOS --");
        if(contR == 0) System.out.println("NENHUM RECURSO CADASTRADO!");
        else{
            for(int i = 0; i < contR; i++){
                System.out.println("ID: " + recursos[i].getId());
                System.out.printf("RECURSO: " + recursos[i].getNome());
                System.out.println("STATUS:");
                if(recursos[i].isAlocado()) System.out.println("ALOCADO");
                if(recursos[i].isEmProcessoDeAlocacao()) System.out.println("EM PROCESSO DE ALOCAÇÃO");
                if(recursos[i].isEmAndamento()) System.out.println("EM ANDAMENTO");
                if(recursos[i].isConcluido()) System.out.println("CONCLUIDO");
            }
        }

    }

    static void mudarStatusRecurso(Recurso[] recursos){
        System.out.println("Digite o id do recurso que deseja mudar: ");
        int id = in.nextInt();
        Recurso r = recursos[id];
        System.out.println("Recurso: " + r.getNome());
        System.out.println("STATUS:");
        if(r.isAlocado()) System.out.println("ALOCADO");
        if(r.isEmProcessoDeAlocacao()) System.out.println("EM PROCESSO DE ALOCAÇÃO");
        if(r.isEmAndamento()) System.out.println("EM ANDAMENTO");
        if(r.isConcluido()) System.out.println("CONCLUIDO");
        System.out.println("Mudar para qual status? (0: não mudar; 1: ALOCADO; 2: EM PROCESSO; 3: EM ANDAMENTO; 4: CONCLUIDO) ");
        int resp = in.nextInt();
        if(resp == 1){
            r.setAlocado(true); r.setEmProcessoDeAlocacao(false); r.setEmAndamento(false); r.setConcluido(false);
        }
        if(resp == 2){
            r.setAlocado(false); r.setEmProcessoDeAlocacao(true); r.setEmAndamento(false); r.setConcluido(false);
        }
        if(resp == 3){
            r.setAlocado(false); r.setEmProcessoDeAlocacao(false); r.setEmAndamento(true); r.setConcluido(false);
        }
        if(resp == 4){
            r.setAlocado(false); r.setEmProcessoDeAlocacao(false); r.setEmAndamento(false); r.setConcluido(true);
        }
    }

    static void emitirRelatorio(Usuario[] users, Alocacao[] alocacoes, Atividade[] atividades){
        int qntProcessoAlocacao = 0, qntAlocado = 0, qntAndamento = 0, qntConcluido = 0;
        for(int i = 0; i < contR; i++){
            if(alocacoes[i].getRecurso().isEmProcessoDeAlocacao() == true) qntProcessoAlocacao++;
            if(alocacoes[i].getRecurso().isAlocado() == true) qntAlocado++;
            if(alocacoes[i].getRecurso().isEmAndamento() == true) qntAndamento++;
            if(alocacoes[i].getRecurso().isConcluido() == true) qntConcluido++;
        }
        System.out.println("TOTAL DE USUARIOS CADASTRADOS: " + contU);
        System.out.println("Numero de recursos em PROCESSO DE ALOCAÇÕES: " + qntProcessoAlocacao);
        System.out.println("Numero de recursos ALOCADOS: " + qntAlocado);
        System.out.println("Numero de recursos EM ANDAMENTO: " + qntAndamento);
        System.out.println("Numero de recuros CONCLUÍDOS: " + qntConcluido);
        System.out.println("TOTAL DE ALOCAÇÕES: " + contAl);
        System.out.println("TOTAL DE ATIVIDADES: " + contA);
    }
}
