package Util;

import System.Clt;
import System.Funcionario;
import System.Pj;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class DataBase {

    private final Scanner inputNumber = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    private final Scanner inputString = new Scanner(System.in).useLocale(Locale.forLanguageTag("pt-BR"));
    private final Collection<Funcionario> listaFuncionarios = new HashSet<>();
    private int intOpcao;


    public void cadastrar(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);
    }

    public Collection<Funcionario> retornarLista() {
        return this.listaFuncionarios;
    }


    public String inserirNome() {
        System.out.println("Insira o nome do funcionário:");

        String nome = inputString.nextLine();

        return nome;
    }

    public String inserirSexo() {
        System.out.println("Insira o sexo do funcionário (M/F):");
        String sexo = inputString.next().toUpperCase();

        while (!sexo.equals("M") && !sexo.equals("F")) {
            System.out.println("Pof favor, insira uma das opções solicitadas.");
            sexo = inputString.next().toUpperCase();
        }

        return sexo;
    }

    public Long inserirCpf() {

        System.out.println("Insira o CPF do funcionário:");
        Long cpf = inputNumber.nextLong();

        while (cpf.toString().length() != 11) {
            System.out.println("Por favor, insira um CPF válido.");
            cpf = inputNumber.nextLong();
        }

        return cpf;
    }

    public String inserirDataNasc() {

        System.out.println("Insira a data de nascimento do funcionário: (DD/MM/AAA)");
        String dataNasc = inputString.next();

        while (dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/' || dataNasc.length() != 10) {
            System.out.println("Por favor, insira a data de nascimento no formato solicitado.");
            dataNasc = inputString.next();
        }

        return dataNasc;
    }

    public double inserirSalario() {

        System.out.println("Insira o salário do funcionário: (Utilizando ',')");
        double salario = inputNumber.nextDouble();

        while (salario < 1192.40) {
            System.out.println("O valor inserido é menor que o valor do salário mínimo.");
            salario = inputNumber.nextDouble();
        }

        return salario;
    }

    public double inserirValeTransporte(double salario) {

        System.out.println("Insira o valor do vale transporte do funcionário:");
        double valeTransporte = inputNumber.nextDouble();

        while (valeTransporte > salario * 0.2 || valeTransporte < salario * 0.1) {
            System.out.println("O valor do vale transporte não pode ser inferior a 10% ou superior a 20% do salário do funcionário\n" +
                    "Valor do salário: " + salario + "\n 10%: " + salario * 0.1 + "\n 20%: " + salario * 0.2);
            valeTransporte = inputNumber.nextDouble();
        }

        return valeTransporte;
    }

    public double inserirValeSaude(double salario) {

        System.out.println("Insira o valor do vale saúde do funcionário:");
        double valeSaude = inputNumber.nextDouble();

        while (valeSaude > salario * 0.25 || valeSaude < salario * 0.15) {
            System.out.println("O valor do vale saúde não pode ser inferior a 15% ou superior a 25% do salário do funcionário\n" +
                    "Valor do salário: " + salario + "\n 15%: " + salario * 0.15 + "\n 25%: " + salario * 0.25);
            valeSaude = inputNumber.nextDouble();
        }

        return valeSaude;
    }

    public Clt cadastrarClt() {

        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        String dataNasc = inserirDataNasc();

        double salario = inserirSalario();

        double valeTransporte = inserirValeTransporte(salario);

        double valeSaude = inserirValeSaude(salario);

        return new Clt(nome, sexo, cpf, dataNasc, salario, valeTransporte, valeSaude);
    }

    public Pj cadastrarPj() {

        String nome = inserirNome();

        String stringSexo = inserirSexo();
            //converter sexo de String para char
            char sexo = stringSexo.charAt(0);

        Long cpf = inserirCpf();

        String dataNasc = inserirDataNasc();

        double salario = inserirSalario();

        return new Pj(nome, sexo, cpf, dataNasc, salario);
    }

    public void consultarFuncionario(Long cpf) {

        for (Funcionario funcionario : listaFuncionarios) {

            if (cpf.equals(funcionario.getCpf())) {

                System.out.println(funcionario);

            }
        }

    }

    public void excluirFuncionario() {

        boolean contemFuncionario = false;

        Long cpf = inserirCpf();

        for (Funcionario funcionario : this.listaFuncionarios) {

            if (cpf.equals(funcionario.getCpf())) {

                listaFuncionarios.remove(funcionario);
                System.out.println("Registro excluido com sucesso.");

                contemFuncionario = true;

            }

        }

        if (!contemFuncionario) {
            System.out.println("CPF não encontrado.");
        }

    }

    public void editarClt() {

        Long cpf = inserirCpf();
        Clt funcionarioClt = null;

        for (Funcionario funcionario : listaFuncionarios) {

            if (cpf.equals(funcionario.getCpf())) {
                funcionarioClt = (Clt) funcionario;
            }
        }


        if (funcionarioClt == null) {
            System.out.println("Registro não encontrado.");


        } else {

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

        switch (intOpcao) {

            case 1 -> {

                String nome = inserirNome();

                funcionarioClt.setNome(nome);


            }

            case 2 -> {

                String stringSexo = inserirSexo();
                //converter sexo de String para char
                char sexo = stringSexo.charAt(0);

                funcionarioClt.setSexo(sexo);


            }

            case 3 -> {

                cpf = inserirCpf();

                funcionarioClt.setCpf(cpf);


            }

            case 4 -> {

                String dataNasc = inserirDataNasc();

                funcionarioClt.setDataNasc(dataNasc);


            }

            case 5 -> {

                double salario = inserirSalario();

                funcionarioClt.setSalario(salario);


            }

            case 6 -> {

                double valeTransporte = inserirValeTransporte(funcionarioClt.getSalario());

                funcionarioClt.setValeTransporte(valeTransporte);


            }

            case 7 -> {

                double valeSaude = inserirValeSaude(funcionarioClt.getSalario());

                funcionarioClt.setValeSaude(valeSaude);


            }

        }

        System.out.println("Registro " + funcionarioClt.getNome() + " atualizado com sucesso.");

    }

    public void editarPj() {

        Long cpf = inserirCpf();
        Pj funcionarioPj = null;

        for (Funcionario funcionario : listaFuncionarios) {

            if (cpf.equals(funcionario.getCpf())) {
                funcionarioPj = (Pj) funcionario;
            }
        }


        if (funcionarioPj == null) {
            System.out.println("Registro não encontrado.");

            return;

        } else {

            System.out.println("Digite a opção correspondente ao dado que deseja atualizar do funcionário " + funcionarioPj.getNome());
            System.out.println("1 - nome");
            System.out.println("2 - sexo");
            System.out.println("3 - cpf");
            System.out.println("4 - data de nascimento");
            System.out.println("5 - salário");


            intOpcao = inputNumber.nextInt();

        }

        assert funcionarioPj != null;

        switch (intOpcao) {

            case 1 -> {

                String nome = inserirNome();

                funcionarioPj.setNome(nome);


            }

            case 2 -> {

                String stringSexo = inserirSexo();
                    //converter sexo de String para char
                    char sexo = stringSexo.charAt(0);

                funcionarioPj.setSexo(sexo);


            }

            case 3 -> {

                cpf = inserirCpf();

                funcionarioPj.setCpf(cpf);


            }

            case 4 -> {

                String dataNasc = inserirDataNasc();

                funcionarioPj.setDataNasc(dataNasc);


            }

            case 5 -> {

                double salario = inserirSalario();

                funcionarioPj.setSalario(salario);


            }
        }

        System.out.println("Registro " + funcionarioPj.getNome() + " atualizado com sucesso.");

    }
}