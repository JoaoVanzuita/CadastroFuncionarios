package util;

import system.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Menu {

    private Locale locale = Locale.getDefault();
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
    private final Scanner input = new Scanner(System.in).useLocale(locale);
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
        System.out.println(bundle.getString("alterarIdioma"));
        System.out.println(bundle.getString("encerrar") + "\n");

        String opcao;
        int intOpcao = 0;

        do{

            opcao = input.nextLine();

            try {

                intOpcao = Integer.parseInt(opcao);

            }catch (NumberFormatException e) {

                //e.printStackTrace();

                System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

            }catch(Exception e){

                //e.printStackTrace();

                System.out.println(bundle.getString("erroDesconhecido"));

            }

        }while (intOpcao == 0);

        executarAcao(intOpcao);
    }

    public void executarAcao(int intOpcao) {

        switch (intOpcao) {

            case 1 -> cadastro();

            case 2 -> consulta();

            case 3 -> edicaoCadastro();

            case 4 -> excluir();

            case 5 -> alterarIdioma();

            case 6 -> encerrar();

            default -> opcaoInvalida();

        }

        abrirMenu();

    }

    public void alterarIdioma() {

        if(dataBase.getLocale().getDisplayLanguage().equalsIgnoreCase("Português")){

            Locale locale1 = new Locale("en","US");
            ResourceBundle bundle1 = ResourceBundle.getBundle("messages", locale1);
            dataBase.setBundle(bundle1);
            dataBase.setLocale(locale1);

        }else if(dataBase.getLocale().getDisplayLanguage().equalsIgnoreCase("Inglês")){

            Locale locale1 = new Locale("pt","BR");
            ResourceBundle bundle1 = ResourceBundle.getBundle("messages", locale1);
            dataBase.setBundle(bundle1);
            dataBase.setLocale(locale1);

        }

        if(locale.getDisplayLanguage().equalsIgnoreCase("Português")){

            this.locale = new Locale("en", "US");
            this.bundle = ResourceBundle.getBundle("messages", locale);

        }else if(locale.getDisplayLanguage().equalsIgnoreCase("Inglês")){

            this.locale = new Locale("pt", "BR");
            this.bundle = ResourceBundle.getBundle("messages", locale);

        }

    }

    private void cadastro() {

        System.out.println(bundle.getString("pjOuClt"));

        String stringOpcao = input.nextLine();

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

                if (!verificaCpf(funcionarioClt.getCpf())) {

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
        System.out.println();

    }

    public void opcaoInvalida() {

        String stringOpcao;

        do {

            System.out.println(bundle.getString("opcaoInvalida"));

            stringOpcao = input.nextLine().toUpperCase();

        } while (!stringOpcao.equals("S") && !stringOpcao.equals("N"));

        switch (stringOpcao) {
            case "S" -> abrirMenu();

            case "N" -> encerrar();

        }
    }

    public void desejaAbrirMenu(){

        System.out.println(bundle.getString("desejaAbrirMenu"));

        String stringOpcao = input.nextLine();

        switch (stringOpcao.toUpperCase()) {

            case "S" -> abrirMenu();

            case "N" -> encerrar();

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

    public void encerrar(){

        System.out.println(bundle.getString("encerrando"));
        System.exit(0);

    }

}