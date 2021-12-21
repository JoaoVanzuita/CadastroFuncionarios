package util;

import system.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Menu {

    private Locale locale = new Locale("en","US");
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
    private final Scanner inputNumber = new Scanner(System.in).useLocale(locale);
    private final Scanner inputString = new Scanner(System.in).useLocale(locale);
    private final DataBase dataBase;

    public Menu(DataBase database) {
        this.dataBase = database;
    }

    public void abrirMenu() {

        System.out.println(bundle.getString("qualOperacao"));

        System.out.println(bundle.getString("cadastrar"));
        System.out.println(bundle.getString("consultar"));
        System.out.println(bundle.getString("editar"));
        System.out.println(bundle.getString("excluir"));
        System.out.println(bundle.getString("encerrar") + "\n");

        int intOpcao = inputNumber.nextInt();

        //TODO: adicionar try catch

        executarAcao(intOpcao);

    }

    public void executarAcao(int opcao) {

        switch (opcao) {

            case 1 -> cadastro();

            case 2 -> consulta();

            case 3 -> edicaoCadastro();

            case 4 -> excluir();

            case 5 -> {

                System.out.println(bundle.getString("encerrando"));
                System.exit(0);

            }

            default -> opcaoInvalida();

        }

        abrirMenu();

    }

    private void cadastro() {

        System.out.println(bundle.getString("pjOuClt"));

        String stringOpcao = inputString.nextLine();

        switch (stringOpcao.toUpperCase()) {

            case "PJ" -> {

                Pj funcionarioPj = dataBase.cadastrarPj();

                boolean contemCpf = verificaCpf(funcionarioPj.getCpf());

                if (!contemCpf) {

                    dataBase.cadastrar(funcionarioPj);

                } else {

                    System.out.println(bundle.getString("cpfJaRegistrado"));

                    desejaAbrirMenu();

                }
            }

            case "CLT" -> {

                Clt funcionarioClt = dataBase.cadastrarClt();

                boolean contemCpf = verificaCpf(funcionarioClt.getCpf());

                if (!contemCpf) {

                    dataBase.cadastrar(funcionarioClt);

                } else {

                    System.out.println(bundle.getString("cpfJaRegistrado"));

                    desejaAbrirMenu();

                }

            }

            case "CANCEL" -> abrirMenu();

            default -> opcaoInvalida();

        }

    }

    private void consulta() {

        Long cpf = dataBase.inserirCpf();


        if (!verificaCpf(cpf)) {

            System.out.println(bundle.getString("semRegistro"));

            desejaAbrirMenu();

        }

        dataBase.consultarFuncionario(cpf);

    }

    private void edicaoCadastro() {

        Long cpf = dataBase.inserirCpf();

        if (!verificaCpf(cpf)) {

            System.out.println(bundle.getString("semRegistro"));

            desejaAbrirMenu();

        }

        Funcionario funcionario = encontrarFuncionario(cpf);

        if (funcionario instanceof Clt) {

            dataBase.editarClt((Clt) funcionario);

        } else if (funcionario instanceof Pj) {

            dataBase.editarPj((Pj) funcionario);

        }


    }

    private void excluir() {

        Long cpf = dataBase.inserirCpf();

        if (!verificaCpf(cpf)) {

            System.out.println(bundle.getString("semRegistro"));

            desejaAbrirMenu();

        }

        Funcionario funcionario = encontrarFuncionario(cpf);

        dataBase.excluirFuncionario(funcionario);

    }

    public void opcaoInvalida() {

        String stringOpcao;

        do {

            System.out.println(bundle.getString("opcaoInvalida"));

            stringOpcao = inputString.nextLine();

        } while (!stringOpcao.equalsIgnoreCase("S") && !stringOpcao.equalsIgnoreCase("N"));

        switch (stringOpcao) {
            case "S" -> abrirMenu();

            case "N" -> System.exit(0);

        }
    }

    public void desejaAbrirMenu() {

        System.out.println(bundle.getString("desejaAbrirMenu"));

        String stringOpcao = inputString.next();

        switch (stringOpcao.toUpperCase()) {

            case "S" -> abrirMenu();

            case "N" -> System.exit(0);

            default -> opcaoInvalida();
        }
    }

    public boolean verificaCpf(Long cpf) {

        boolean contemCpf = false;

        for (Funcionario funcionario : dataBase.retornarLista()) {

            if (cpf.equals(funcionario.getCpf())) {

                contemCpf = true;

                break;
            }

        }

        return contemCpf;
    }

    public Funcionario encontrarFuncionario(Long cpf) {

        Funcionario funcionario = null;

        for (Funcionario funcionario0 : dataBase.retornarLista()) {

            if (cpf.equals(funcionario0.getCpf())) {

                funcionario = funcionario0;

            }
        }

        return funcionario;
    }

}