package test;

import java.util.Scanner;

public class Main {
    public static String[][] projetos = new String[20][20];
    public static int qtdprojetos = 0;
    public static String[][] pessoas = new String[20][7];
    public static int qtdpessoas = 0;
    public static Scanner input = new Scanner(System.in);

    public static void alterarProjetosElabora() {
        System.out.println("Digite o nome COMPLETO do projeto que desejar realizar alterações no status");
        String elaboraca = "Em elaboracao";
        String busca = input.nextLine();
        for(int i = 0; i < qtdprojetos; i++) {//nao esta comparando essa pest
            if(busca.equalsIgnoreCase(projetos[i][1])){
                System.out.println("Projeto encontrado !!");
                if(elaboraca.equalsIgnoreCase(projetos[i][2])) {
                    System.out.printf("Você pode apenas mudar o status desse projeto para EM ANDAMENTO\n1 - Para mudar\n2 - Para não mudar");
                    int escolha = input.nextInt();
                    if(escolha == 1) {
                        projetos[i][3] = "Em andamento";
                        System.out.println("Alteração feita com sucesso");

                    }else {
                        alterarProjetosElabora();
                    }
                }

            }
        }
    }
    public static boolean buscarNome(String nome){
    	int j = 0;
    	int k = 0;
    	for(int i = 0; i < qtdpessoas; i++) {
    		if(nome.equalsIgnoreCase(pessoas[i][0]) && pessoas[i][5].equalsIgnoreCase("aluno")) {
    			j++;
    		}
    		else if(nome.equalsIgnoreCase(pessoas[i][0]) && pessoas[i][5].equalsIgnoreCase("professor")) {
    			k++;
    		}
    	}
    	if (j > 0 && k < 0 ) {
    		return true;
    	}
    	else {
    		return false;
    	}
		
    }

    public static void addColaboradores(int colunas, int colaboradores, int alunos, int professores, int linhas) {
        String nome;
        boolean renome;
        int j = 0;
        for(int cont = 0; cont < colaboradores; cont++){
            System.out.printf("Por favor Digite o nome completo %d Colabores\n", colaboradores-cont);
            nome = input.nextLine();
            renome = buscarNome(nome);
            String elaboracao = "Em elaboracao";
            String aluno2 = "aluno";
            if(renome == true && projetos[linhas][2].equalsIgnoreCase(elaboracao)) {
            	System.out.println("ok");
            	alunos+=1;
            }
            if(renome == false && projetos[linhas][2].equalsIgnoreCase(elaboracao)) {
            	professores+=1;
            }
            else {
            	System.out.println("Colaborador nao encontrado no banco");
            }
            if(alunos > 2) {
            	System.out.println("Voce nao pode adicionar mais alunos ao projeto");
            }
            else if(professores == 0) {
            	System.out.println("Voce precisa de pelo menos 1 professor para projeto");
            }
        	else if(professores >= 2) {
        		System.out.println("Voce nao pode adicionar mais professores ao projeto");
        	}
        }
    }
    public static void pesquisarColaboradores() {
        System.out.print("Seja Bem Vindo a Busca de colaboradores, Digite o nome do colaborador que desejar Buscar em nossa Base de dados");
        String busca = input.nextLine();
        for(int linhas = 0; linhas < qtdpessoas; linhas++) {
            if(busca.equalsIgnoreCase(pessoas[linhas][0])) {
                System.out.println("Esse funcionario faz parte do Quadro de funcionários, Abaixo seguira os dados dele\n Na seguinte sequencia NOME, EMAIL, PROJETOS QUE PARTICIPOU, PROJETOS ATUAIS, CARGO");
                for(int colunas = 0; colunas < 6; colunas++) {
                    System.out.println(pessoas[linhas][colunas]);
                }
            }
        }
        menu();
    }

    public static void colaboradores() {
        int linhas = 0, colunas;
        System.out.println("Digite o que você deseja fazer\n 1 - Cadastra Colaboradores\n2 - Pesquisar Colaboradores \n3 - Voltar para o menu anterior");
        int escolha = input.nextInt();
        String lixo = input.nextLine();
        if(escolha == 1) {
            System.out.print("Digite o NOME, E-MAIL, PROJETOS QUE PARTICIPOU, PROJETOS EM ANDAMENTO, PRODUÇÃO ACADEMICA, TITULAÇÃO\n");
            for(colunas = 0; colunas < 7; colunas++) {
                if(colunas == 6){
                    qtdpessoas+=1;
                }

                else{
                    pessoas[qtdpessoas][colunas] = input.nextLine();
                }
            }

            System.out.println("Colaboradores Cadastrado com Sucesso");
            colaboradores();
        }
        else if(escolha == 2){
            pesquisarColaboradores();
        }
        else if(escolha == 3){
            menu();
        }
    }

    public static void bubbleSort(String[][] projetos, int qtdProjetos, int colaboradores) {
        String[] aux;
        for(int i = 0; i < qtdProjetos; i++) {//função teoricamente pronta, falta testar apartir da função projeto
            for (int j = 0; j < qtdProjetos - 1; j++) {
                if(Integer.parseInt(projetos[j][3]) > Integer.parseInt(projetos[j+1][3])) {
                    aux = projetos[j];
                    projetos[j] = projetos[j+1];
                    projetos[j+1] = aux;
                }
            }
        }
    }

    public static void projetosFuncao(){
        String elaboraca = "Em elaboracao";
        int colunas = 0;
        int escolha;
        for(;;) {
            System.out.print("Digite o que Desejar\n1 - Cadastrar Projetos\n2 - Alterar Projetos\n3 - Voltar ao menu anterior\n");
            escolha = input.nextInt();
            String lixo2 = input.nextLine();
            if (escolha == 1) {
                int colaboradores = 0;
                System.out.println("Digite quantos Colaboradores o projeto irá possuir");
                colaboradores = input.nextInt();
                String lixo = input.nextLine();
                System.out.printf("Digite o NOME DO PROJETO, LABORATORIO, O STATUS, A DATA (PADRÃO AMERICANO, SEM - E /) , O NOME DOS %d COLABORADORES", colaboradores);
                for (colunas = 0; colunas < 6; colunas++) {
                    if (colunas == 3) {
                        if (elaboraca.equalsIgnoreCase(projetos[qtdprojetos][2])) {
                            System.out.println("Status aceito, pode proseguir com o preenchimento dos dados");
                        } else {
                            System.out.println("Status incorrreto\nSó poderar continuar com o Status EM ELABORACAO");
                            projetosFuncao();
                        }
                    }
                    else if (colunas == 5) {
                    	int linhas = qtdprojetos;
                        qtdprojetos += 1;
                        addColaboradores(colunas, colaboradores,0,0,linhas);
                        System.out.println("Colaboradores Cadastrado com sucesso");
                    } else if(colunas < 5) {
                        projetos[qtdprojetos][colunas] = input.nextLine();
                    }
                }
            } else if (escolha == 2) {
                System.out.println("Seja Bem vindo a aba para realizar alterações nos Projetos");
                alterarProjetosElabora();

            }else{
                menu();
            }
        }
    }

    public static void relatorios() {
    	System.out.println("Digite o nome do labóratio que desejar obter inforrmçoes");
        String lixo = input.nextLine();
    	String busca = input.nextLine();
    	for(int i = 0; i < qtdprojetos; i++) {
    		if(busca.equalsIgnoreCase(projetos[i][1])) {
    			for(int colunas = 0; colunas < 7; colunas++) {
    				System.out.println(projetos[i][colunas]);
    			}
    		}else {
    			System.out.println("Laboratorio nao Encontrado");
    			break;
    		}
    	}
    }

    public static void menu() {

        System.out.print("Digite a opção que deseja navegar no laboratorio \n 1- Colaboradores\n 2- Projetos\n 3- Para abrir o relátorio dos laboratórios\n 4 - Sair\n");
        int escolha = input.nextInt();
        switch(escolha){
            case 1:
                colaboradores();
            case 2:
                projetosFuncao();
            case 3:
                relatorios();

            case 4:
                System.exit(0);
        }
    }

    public static void main(String[]args) {
        menu();
    }
}