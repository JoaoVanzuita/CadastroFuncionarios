package Util;

import System.*;
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    private final Scanner inputNumber = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    private final Scanner inputString = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    private final DataBase dataBase;

    public Menu(DataBase database) {
        this.dataBase = database;
    }

    public void abrirMenu(){

        System.out.println("Qual operação deseja fazer? (Digite o número correspondente)\n");

        System.out.println("1 - Cadastrar Funcionário\n");
        System.out.println("2 - Consultar cadastro de funcionário\n");
        System.out.println("3 - Editar cadastro de Funcionário\n");
        System.out.println("4 - Excluir funcionário\n");
        System.out.println("5 - Encerrar sessão\n");

        int intOpcao = inputNumber.nextInt();

        executarAcao(intOpcao);

    }

    public void executarAcao(int opcao){

        switch(opcao){

            case 1 -> cadastro();

            case 2 -> consulta();

            case 3 -> edicaoCadastro();

            case 4 -> excluir();

            case 5 ->{

                System.out.println("Encerrando...\n");
                System.exit(0);

            }

            default -> opcaoInvalida();

        }

        abrirMenu();

    }

    private void cadastro() {

        System.out.println("Deseja cadastrar um funcionário PJ ou CLT? (PJ/CLT/CANCEL)\n");

        String stringOpcao = inputString.nextLine().toUpperCase();

        switch (stringOpcao){

            case "PJ"->{

                Pj funcionarioPj = dataBase.cadastrarPj();

                boolean contemCpf = verificaCpf(funcionarioPj.getCpf());

                if(!contemCpf) {

                    dataBase.cadastrar(funcionarioPj);

                }else{

                    System.out.println("Esse CPF já foi registrado.\n");

                    desejaAbrirMenu();

                }
            }

            case "CLT" ->{

                Clt funcionarioClt = dataBase.cadastrarClt();

                boolean contemCpf = verificaCpf(funcionarioClt.getCpf());

                if(!contemCpf) {

                    dataBase.cadastrar(funcionarioClt);

                }else{

                    System.out.println("Esse CPF já foi registrado.\n");

                    desejaAbrirMenu();

                }

            }

            case "CANCEL" -> abrirMenu();

            default -> opcaoInvalida();

        }

    }

    private void consulta(){

        Long cpf = dataBase.inserirCpf();


        if(!verificaCpf(cpf)){

            System.out.println("Registro não encontrado.\n");

            desejaAbrirMenu();

        }

            dataBase.consultarFuncionario(cpf);

    }

    private void edicaoCadastro() {

        Long cpf = dataBase.inserirCpf();

        if(!verificaCpf(cpf)){

            System.out.println("Registro não encontrado.\n");

            desejaAbrirMenu();

        }

        Funcionario funcionario = encontrarFuncionario(cpf);

        if(funcionario instanceof Clt){

            dataBase.editarClt((Clt) funcionario);

        }else if(funcionario instanceof Pj){

            dataBase.editarPj((Pj) funcionario);

        }


    }

    private void excluir() {

        Long cpf = dataBase.inserirCpf();

        if(!verificaCpf(cpf)){

            System.out.println("Registro não encontrado.\n");

            desejaAbrirMenu();

        }

        Funcionario funcionario = encontrarFuncionario(cpf);

        dataBase.excluirFuncionario(funcionario);

    }

    public void opcaoInvalida(){

        String stringOpcao;

        do {

            System.out.println("Opção inválida. Deseja abrir o menu novamente? (S/N)\n");

            stringOpcao = inputString.nextLine().toUpperCase();

        }while(!stringOpcao.equals("S") && !stringOpcao.equals("N"));

        switch (stringOpcao) {
            case "S" -> abrirMenu();

            case "N" -> System.exit(0);

        }
    }

    public void desejaAbrirMenu(){

        System.out.println("Deseja abrir o menu novamente: (S/N)\n");

        String stringOpcao = inputString.next().toUpperCase();

        switch (stringOpcao) {

            case "S" -> abrirMenu();

            case "N" -> System.exit(0);

            default -> opcaoInvalida();
        }
    }

    public boolean verificaCpf(Long cpf){

        boolean contemCpf = false;

        for (Funcionario funcionario: dataBase.retornarLista()) {

            if (cpf.equals(funcionario.getCpf())) {

                contemCpf = true;

                break;
            }

        }

        return contemCpf;
    }

    public Funcionario encontrarFuncionario(Long cpf){

        Funcionario funcionario = null;

        for (Funcionario funcionario0: dataBase.retornarLista()) {

            if(cpf.equals(funcionario0.getCpf())){

                funcionario = funcionario0;

            }
        }

        return funcionario;
    }

}