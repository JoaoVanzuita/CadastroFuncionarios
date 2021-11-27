package Util;

import System.*;
import com.sun.security.jgss.GSSUtil;

import java.util.Locale;
import java.util.Scanner;

public class Menu {

    private final Scanner inputNumber = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    private final Scanner inputString = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    int intOpcao;
    private String stringOpcao;
    private final DataBase dataBase;

    public Menu(DataBase database) {
        this.dataBase = database;
    }

    public void abrirMenu(){

        System.out.println("Qual operação deseja fazer? (Digite o número correspondente)");

        pularLinha();

        System.out.println("1 - Cadastrar Funcionário\n");
        System.out.println("2 - Consultar cadastro de funcionário\n");
        System.out.println("3 - Editar cadastro de Funcionário\n");
        System.out.println("4 - Excluir funcionário\n");
        System.out.println("5 - Encerrar sessão");

        pularLinha();

        intOpcao = inputNumber.nextInt();

        executarAcao(intOpcao);

    }

    public void executarAcao(int opcao){

        switch(opcao){

            case 1 -> cadastro();

            case 2 -> consulta();

            case 3 -> System.out.println("A implementar");//edicaoCadastro();
        }

    }

    private void cadastro() {

        System.out.println("Deseja cadastrar um funcionário PJ ou CLT? (PJ/CLT/CANCEL)");

        stringOpcao = inputString.nextLine().toUpperCase();

        switch (stringOpcao){

            case "PJ"->{

                Pj funcionarioPj = dataBase.cadastrarPj();

                boolean contemCpf = verificaCpf(funcionarioPj.getCpf());

                if(!contemCpf) {

                    dataBase.cadastrar(funcionarioPj);

                }else{

                    System.out.println("Esse CPF já foi registrado.");

                    desejaAbrirMenu();

                }
            }

            case "CLT" ->{

                Clt funcionarioClt = dataBase.cadastrarClt();

                boolean contemCpf = verificaCpf(funcionarioClt.getCpf());

                if(!contemCpf) {

                    dataBase.cadastrar(funcionarioClt);

                }else{

                    System.out.println("Esse CPF já foi registrado.");

                    desejaAbrirMenu();

                }

            }

            case "CANCEL" -> abrirMenu();

            default -> opcaoInvalida();

        }

    }

    private void consulta(){

        System.out.println("Digite o CPF do registro que deseja consultar:");

        Long cpf = inputNumber.nextLong();


        if(!verificaCpf(cpf)){

            System.out.println("Registro não encontrado.");

            desejaAbrirMenu();

        }

            dataBase.consultarFuncionario(cpf);

    }

    private void edicaoCadastro() {

        System.out.println("Digite o cpf do registro que deseja editar:");

        Long cpf = dataBase.inserirCpf();

        if(!verificaCpf(cpf)){

            System.out.println("Registro não encontrado");

            desejaAbrirMenu();

        }

        for (Funcionario funcionario: dataBase.retornarLista()) {



        }


    }

    public static void pularLinha(){
        System.out.println();
    }

    public void opcaoInvalida(){

        System.out.println("Opção inválida. Deseja abrir o menu novamente? (S/N)");
        String stringOpcao = inputString.next().toUpperCase();


         while(!stringOpcao.equals("S") && !stringOpcao.equals("N")){

             System.out.println("Opção inválida. Deseja abrir o menu novamente? (S/N)");
             stringOpcao = inputString.next().toUpperCase();

         }

        switch (stringOpcao) {
            case "S" -> abrirMenu();

            case "N" -> System.exit(0);

        }
    }

    public void desejaAbrirMenu(){
        System.out.println("Deseja abrir o menu novamente: (S/N)");
        stringOpcao = inputString.next().toUpperCase();
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

}