package util;

import system.Clt;
import system.Funcionario;
import system.Pj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBase {

    private Locale locale = Locale.getDefault();
    private ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
    private final Scanner inputNumber = new Scanner(System.in).useLocale(locale);
    private final Scanner inputString = new Scanner(System.in).useLocale(locale);
    private final ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    Menu menu = new Menu(this);


    public void cadastrar(Funcionario funcionario) {
        listaFuncionarios.add(funcionario);

        System.out.println(bundle.getString("funcionario") + funcionario.getNome() + bundle.getString("cadastrado"));
    }

    public ArrayList<Funcionario> retornarLista() {
        return this.listaFuncionarios;
    }

    public String inserirNome() {

        System.out.println(bundle.getString("inserirNome"));
        String nome = inputString.nextLine();

        return nome;
    }

    public String inserirSexo() {

        //TODO: adicionar try catch

        System.out.println(bundle.getString("inserirSexo"));
        String sexo = inputString.next();

        while (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
            System.out.println(bundle.getString("sexoInvalido"));
            sexo = inputString.next();
        }

        return sexo;
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public Long inserirCpf() {

        String cpf;
        long cpfRetorno = 0;

        do {

            System.out.println(bundle.getString("inserirCpf"));
            cpf = inputNumber.nextLine();

            if(cpf.length() != 11){

                System.out.println(bundle.getString("cpfInvalido"));

            }

        }while (cpf.length() != 11);

        try{

            cpfRetorno = Long.parseLong(cpf);

        }catch (NumberFormatException e){

            //e.printStackTrace();

            System.out.println(bundle.getString("ocorreuErro") + " (NumberFormatException)");

        }

            return cpfRetorno;
    }

    public Date inserirDataNasc(){

        String dataNasc = "";

        System.out.println(bundle.getString("inserirData"));

        dataNasc = inputString.nextLine();

        while (dataNasc.length() > 0 && (dataNasc.charAt(2) != '/' || dataNasc.charAt(5) != '/' || dataNasc.length() != 10) ) {

            System.out.println(bundle.getString("dataInvalida"));
            dataNasc = inputString.next();

        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient (false);

        Date dataFormatada = null;

        try {

            System.out.println(dataNasc);
            dataFormatada = formato.parse(dataNasc);

        } catch (ParseException e) {

            e.printStackTrace();

            System.out.println(bundle.getString("ocorreuErro") + " (ParseException)");
        }

        return dataFormatada;
    }

    public double inserirSalario() {

        //TODO: inserir String e converter para double; adicionar try catch

        double salario;

        System.out.println(bundle.getString("inserirSalario"));

        salario = inputNumber.nextDouble();


        while (salario < 1192.40) {
            System.out.println(bundle.getString("salarioInvalido"));
            salario = inputNumber.nextDouble();

        }

        return salario;
    }

    public double inserirValeTransporte(double salario) {

        //TODO: inserir String e converter para double; adicionar try catch

        System.out.println(bundle.getString("inserirValeTransporte"));
        double valeTransporte = inputNumber.nextDouble();

        while (valeTransporte > salario * 0.2 || valeTransporte < salario * 0.1) {
            System.out.println(bundle.getString("valeTransporteInvalido") +
                    bundle.getString("valorSalario") + salario + "\n 10%: " + salario * 0.1 + "\n 20%: " + salario * 0.2);
            valeTransporte = inputNumber.nextDouble();
        }

        return valeTransporte;
    }

    public double inserirValeSaude(double salario) {

        //TODO: inserir String e converter para double; adicionar try catch

        System.out.println(bundle.getString("inserirValeSaude"));
        double valeSaude = inputNumber.nextDouble();

        while (valeSaude > salario * 0.25 || valeSaude < salario * 0.15) {
            System.out.println(bundle.getString("valeSaudeInvalido") +
                    bundle.getString("valorSalario") + salario + "\n 15%: " + salario * 0.15 + "\n 25%: " + salario * 0.25);
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

        Date dataNasc = inserirDataNasc();

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

        Date dataNasc = inserirDataNasc();

        double salario = inserirSalario();

        return new Pj(nome, sexo, cpf, dataNasc, salario);
    }

    public void consultarFuncionario(Long cpf) {

        for (Funcionario funcionario0 : listaFuncionarios) {

            if (cpf.equals(funcionario0.getCpf())) {

                if (funcionario0 instanceof Pj funcionario) {

                    System.out.println(funcionario);

                } else if (funcionario0 instanceof Clt funcionario) {

                    System.out.println(funcionario);

                }

                break;
            }
        }
    }

    //CÓDIGO DO MÉTODO CONCLUÍDO
    public void excluirFuncionario(Funcionario funcionario) {

        listaFuncionarios.remove(funcionario);

        System.out.println(bundle.getString("registro") + funcionario.getNome() + bundle.getString("excluido"));

    }

    public void editarClt(Clt funcionarioClt) {

        System.out.println(bundle.getString("digiteOpcao") + funcionarioClt.getNome());
        System.out.println(bundle.getString("nome"));
        System.out.println(bundle.getString("sexo"));
        System.out.println(bundle.getString("cpf"));
        System.out.println(bundle.getString("dataNasc"));
        System.out.println(bundle.getString("salario"));
        System.out.println(bundle.getString("valeTransporte"));
        System.out.println(bundle.getString("valeSaude"));


        String opcao = inputNumber.next();
        int intOpcao = Integer.parseInt(opcao);

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

                Long cpf = inserirCpf();

                funcionarioClt.setCpf(cpf);

            }

            case 4 -> {

                Date dataNasc = inserirDataNasc();

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

        System.out.println(bundle.getString("registro") + funcionarioClt.getNome() + bundle.getString("atualizado"));

    }

    public void editarPj(Pj funcionarioPj) {

        System.out.println(bundle.getString("digiteOpcao") + funcionarioPj.getNome());
        System.out.println(bundle.getString("nome"));
        System.out.println(bundle.getString("sexo"));
        System.out.println(bundle.getString("cpf"));
        System.out.println(bundle.getString("dataNasc"));
        System.out.println(bundle.getString("salario"));

        String opcao = inputNumber.next();
        int intOpcao = Integer.parseInt(opcao);

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

                Long cpf = inserirCpf();

                funcionarioPj.setCpf(cpf);


            }

            case 4 -> {

                Date dataNasc = inserirDataNasc();

                funcionarioPj.setDataNasc(dataNasc);


            }

            case 5 -> {

                double salario = inserirSalario();

                funcionarioPj.setSalario(salario);


            }
        }

        System.out.println(bundle.getString("registro") + funcionarioPj.getNome() + bundle.getString("atualizado"));

    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public Locale getLocale() {
        return locale;
    }
}