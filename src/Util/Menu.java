package Util;

import system.*;

import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private final Scanner inputNumber = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    private final Scanner inputString = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    int intOpcao;
    private String stringOpcao;
    private DataBase dataBase;

    public Menu(DataBase database) {
        this.setDataBase(database);
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

        switch (opcao) {

            case 1 -> {
                System.out.println("Deseja cadastrar um funcionário CLT ou PJ? (CLT/PJ/CANCEL) ");
                stringOpcao = inputString.next().toUpperCase();
                switch (stringOpcao) {

                    case "CLT" -> {

                        cadastrarClt();

                    }

                    case "PJ" -> {

                        cadastrarPj();

                    }
                    case "CANCEL" -> {
                        System.out.println("Cancelado.");
                        abrirMenu();
                    }

                    default -> opcaoInvalida();

                }

                desejaAbrirMenu();
            }
            case 2 -> {

                consultarFuncionario();

                desejaAbrirMenu();

            }

            case 3 ->{

                System.out.println("A implementar...");

                System.out.println("Deseja editar um funcionário PJ ou CLT?");
                stringOpcao = inputString.next().toUpperCase();

                switch (stringOpcao) {

                    case "CLT" -> editarClt();

                    case "PJ" -> editarPj();

                    case "CANCEL" -> {
                        System.out.println("Cancelado.");

                        abrirMenu();
                    }

                    default -> opcaoInvalida();

                }

                desejaAbrirMenu();

            }
            case 4 ->{

                excluirFuncionario();

                desejaAbrirMenu();

            }

            case 5 -> {
                System.out.println("Encerrando...");
                System.exit(0);
            }

            default -> opcaoInvalida();

        }
    }

    public static void pularLinha(){
        System.out.println();
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public String inserirNome(){
        System.out.println("Insira o nome do funcionário:");

        String nome = inputString.next();

        return nome;
    }

    public String inserirSexo(){
        System.out.println("Insira o sexo do funcionário (M/F):");
        String sexo = inputString.next().toUpperCase();

        while(!sexo.equals("M") && !sexo.equals("F")) {
            System.out.println("Pof favor, insira uma das opções solicitadas.");
            sexo = inputString.next().toUpperCase();
        }

        return sexo;
    }

    public Long inserirCpf(){

        System.out.println("Insira o CPF do funcionário:");
        Long cpf = inputNumber.nextLong();

        while(cpf.toString().length() != 11){
            System.out.println("Por favor, insira um CPF válido.");
            cpf = inputNumber.nextLong();
        }

        return cpf;
    }

    public String inserirDataNasc(){
        System.out.println("Insira a data de nascimento do funcionário: (DD/MM/AAA)");
        String dataNasc = inputString.next();

        while(dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/' || dataNasc.length() != 10){
            System.out.println("Por favor, insira a data de nascimento no formato solicitado.");
            dataNasc = inputString.next();
        }

        return dataNasc;
    }

    public double inserirSalario(){
        System.out.println("Insira o salário do funcionário: (Utilizando ',')");
        double salario = inputNumber.nextDouble();

            while (salario < 1192.40) {
                System.out.println("O valor inserido é menor que o valor do salário mínimo.");
                salario = inputNumber.nextDouble();
            }

            return  salario;
    }

    public double inserirValeTransporte(double salario){
        System.out.println("Insira o valor do vale transporte do funcionário:");
        double valeTransporte = inputNumber.nextDouble();

        while(valeTransporte > salario * 0.2 || valeTransporte < salario * 0.1){
            System.out.println("O valor do vale transporte não pode ser inferior a 10% ou superior a 20% do salário do funcionário\n" +
                    "Valor do salário: " + salario + "\n 10%: " + salario * 0.1 + "\n 20%: " + salario * 0.2);
            valeTransporte = inputNumber.nextDouble();
        }

        return valeTransporte;
    }

    public double inserirValeSaude(double salario){
        System.out.println("Insira o valor do vale saúde do funcionário:");
        double valeSaude = inputNumber.nextDouble();

        while(valeSaude > salario * 0.25 || valeSaude < salario * 0.15){
            System.out.println("O valor do vale saúde não pode ser inferior a 15% ou superior a 25% do salário do funcionário\n" +
                    "Valor do salário: " + salario + "\n 15%: " + salario * 0.15 + "\n 25%: " + salario * 0.25);
            valeSaude = inputNumber.nextDouble();
        }

        return valeSaude;
    }

    //transferir para DataBase
    public void cadastrarClt(){

        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        String dataNasc = inserirDataNasc();

        double salario = inserirSalario();

        double valeTransporte = inserirValeTransporte(salario);

        double valeSaude = inserirValeSaude(salario);

        Clt funcionarioClt = new Clt(nome, sexo, cpf, dataNasc, salario, valeTransporte, valeSaude);

        if(dataBase.retornarLista().contains(funcionarioClt)){
            System.out.println("O CPF desse funcionário já foi cadastrado. Deseja cadastrar outro funcionário? (S/N)");
            String stringOpcao = inputString.next().toUpperCase();

            switch (stringOpcao) {
                case "S" -> cadastrarClt();

                case "N" -> desejaAbrirMenu();

                default -> opcaoInvalida();
            }
            opcaoInvalida();

        }else {
            dataBase.cadastrar(funcionarioClt);

            System.out.println("Funcionário " + funcionarioClt.getNome() +  " registrado com sucesso.");
        }
    }

    //transferir para DataBase
    public void cadastrarPj(){
        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        String dataNasc = inserirDataNasc();

        double salario = inserirSalario();

        Pj funcionarioPj = new Pj(nome, sexo, cpf, dataNasc, salario);

        if(dataBase.retornarLista().contains(funcionarioPj)){
            System.out.println("Esse CPF já foi registrado. Deseja tentar outro CPF? (S/N)");
            String stringOpcao = inputString.next().toUpperCase();

            switch (stringOpcao) {
                case "S" -> inserirCpf();

                case "N" -> desejaAbrirMenu();

                default -> opcaoInvalida();
            }

        }else {
            dataBase.cadastrar(funcionarioPj);

            System.out.println("Funcionário registrado com sucesso.");

            desejaAbrirMenu();

        }
    }

    //transferir para DataBase
    public void consultarFuncionario(){
        boolean contemFuncionario = false;

        Long cpf = inserirCpf();

        for (Funcionario funcionario : dataBase.retornarLista()) {


            if (cpf.equals(funcionario.getCpf())) {
                System.out.println(funcionario);

                contemFuncionario = true;

            }
        }

        if(!contemFuncionario){
            System.out.println("CPF não encontrado.");
        }
    }

    //transferir para DataBase
    public void excluirFuncionario(){

        boolean contemFuncionario = false;

        Long cpf = inserirCpf();

        for (Funcionario funcionario: dataBase.retornarLista()) {

            if(cpf.equals(funcionario.getCpf())){

                dataBase.retornarLista().remove(funcionario);
                System.out.println("Registro excluido com sucesso.");

                contemFuncionario = true;

            }


        }

        if(!contemFuncionario){
            System.out.println("CPF não encontrado.");
        }

        desejaAbrirMenu();

    }

    //transferir para DataBase
    public void editarClt(){

        Long cpf = inserirCpf();
        Clt funcionarioClt = null;

        for (Funcionario funcionario: dataBase.retornarLista()) {

            if(cpf.equals(funcionario.getCpf())){
                funcionarioClt = (Clt)funcionario;
            }
        }


        if(funcionarioClt == null){
            System.out.println("Registro não encontrado.");

            desejaAbrirMenu();

        }else {

            System.out.println("Digite a opção correspondente ao dado que deseja atualizar do funcionário " + funcionarioClt.getNome());
            System.out.println("1 - nome");
            System.out.println("2 - sexo");
            System.out.println("3 - cpf");
            System.out.println("4 - data de nascimento");
            System.out.println("5 - salário");
            System.out.println("6 - vale transporte");
            System.out.println("7 - vale saúde");

            intOpcao = inputNumber.nextInt();

        }

        assert funcionarioClt != null;

        switch (intOpcao){

            case 1 ->{

                String nome = inserirNome();

                funcionarioClt.setNome(nome);


            }

            case 2 ->{

                String stringSexo = inserirSexo();
                    //converter sexo de String para char
                    char sexo = stringSexo.charAt(0);

                funcionarioClt.setSexo(sexo);


            }

            case 3 ->{

                cpf = inserirCpf();

                funcionarioClt.setCpf(cpf);


            }

            case 4 ->{

                String dataNasc = inserirDataNasc();

                funcionarioClt.setDataNasc(dataNasc);


            }

            case 5 ->{

                double salario = inserirSalario();

                funcionarioClt.setSalario(salario);


            }

            case 6 ->{

                double valeTransporte = inserirValeTransporte(funcionarioClt.getSalario());

                funcionarioClt.setValeTransporte(valeTransporte);


            }

            case 7 ->{

                double valeSaude = inserirValeSaude(funcionarioClt.getSalario());

                funcionarioClt.setValeSaude(valeSaude);


            }

        }

        System.out.println("Registro "  + funcionarioClt.getNome() + " atualizado com sucesso.");

        desejaAbrirMenu();

    }

    //transferir para DataBase
    public void editarPj(){

        Long cpf = inserirCpf();
        Pj funcionarioPj = null;

        for (Funcionario funcionario: dataBase.retornarLista()) {

            if(cpf.equals(funcionario.getCpf())){
                funcionarioPj = (Pj)funcionario;
            }
        }


        if(funcionarioPj == null){
            System.out.println("Registro não encontrado.");

            desejaAbrirMenu();

        }else {

            System.out.println("Digite a opção correspondente ao dado que deseja atualizar do funcionário " + funcionarioPj.getNome());
            System.out.println("1 - nome");
            System.out.println("2 - sexo");
            System.out.println("3 - cpf");
            System.out.println("4 - data de nascimento");
            System.out.println("5 - salário");


            intOpcao = inputNumber.nextInt();

        }

        assert funcionarioPj != null;

        switch (intOpcao){

            case 1 ->{

                String nome = inserirNome();

                funcionarioPj.setNome(nome);


            }

            case 2 ->{

                String stringSexo = inserirSexo();
                //converter sexo de String para char
                char sexo = stringSexo.charAt(0);

                funcionarioPj.setSexo(sexo);


            }

            case 3 ->{

                cpf = inserirCpf();

                funcionarioPj.setCpf(cpf);


            }

            case 4 ->{

                String dataNasc = inserirDataNasc();

                funcionarioPj.setDataNasc(dataNasc);


            }

            case 5 ->{

                double salario = inserirSalario();

                funcionarioPj.setSalario(salario);


            }
        }

        System.out.println("Registro "  + funcionarioPj.getNome() + " atualizado com sucesso.");

        desejaAbrirMenu();

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
}